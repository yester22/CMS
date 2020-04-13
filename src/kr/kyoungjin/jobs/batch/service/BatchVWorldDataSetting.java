package kr.kyoungjin.jobs.batch.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.dataobject.dao.BatchHistoryInfoDao;
import kr.kyoungjin.dataobject.dao.ExcelDao;
import kr.kyoungjin.dataobject.vo.BatchHistoryInfoVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadDetailVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;


/**
 * @since 2020. 3. 30.
 * @author yeste21
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 3. 30. yester21 : 최초작성
 * </PRE>
 */
public class BatchVWorldDataSetting {
	
	
	private String NSDI_KEY;
	private String VWORLD_KEY;
	
	private ExcelDao excelDao;
	private BatchHistoryInfoDao batchDao;
	
	public static final String USER_AGENT = "Mozilla/5.0";
	
	private Logger logger  = LoggerFactory.getLogger(BatchVWorldDataSetting.class);
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 2.
	 * @Method Name : doDataCrsPostionSynch
	 * @return : void
	 */
	public void doDataCrsPostionSynch() {
		logger.debug("doServiceJob batchStart");
		String batchKey = "";

		//batch 시작플래그 생성
		BatchHistoryInfoVo batchVo = new BatchHistoryInfoVo();
		try {
			batchKey = batchDao.selectBatchKeyGenerate();
			batchVo.setBatchKey(batchKey);
			batchVo.setBatchType(ConstantNames.BATCH_KEY_VWORLD_SEARCH);
		} catch ( Exception e) { }
		
		try {
			
			ExcelUploadVo excelUploadVo = new ExcelUploadVo();			
			excelUploadVo.setValidCompleteYn(ConstantNames.USE_YN_N);
			excelUploadVo.setUseYn(ConstantNames.USE_YN_Y);
			excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_REG);
			String excelKey = excelDao.selectExcelKeyForStatusConfirm(excelUploadVo);
			
			if ( excelKey != null ) {
				
				Map<String,Object> param = new HashMap<String,Object>();
				param.put(ConstantNames.EXCEL_KEY, excelKey);
				List<ExcelUploadDetailVo> list = excelDao.selectExcelUploaDataAddress(param);
				if ( list.size() > 1 ) {
					//excelUpload 데이터의 상태 변경
					excelUploadVo.setExcelKey(excelKey);
					excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_DS_POS_START);
					excelDao.updateExcelUpoadStatus(excelUploadVo);

					//batch 내용  insert
					batchVo.setBatchTarget(excelKey);
					batchDao.insertBatchHistory(batchVo);
					
					
					// 서비스 호출을 위한 파라미터 세팅
					String url = "https://api.vworld.kr/req/search?";
					Map<String,Object> params = new LinkedHashMap<>(); 
				    params.put("service", "search");
				    params.put("request", "search");
				    params.put("version", "2.0");
				    params.put("crs", "EPSG:900913");
				    params.put("type",  "address");
				    params.put("category", "parcel");
				    params.put("format", "json");
				    params.put("errorformat", "json");
				    params.put("key", VWORLD_KEY);
				    
				    JSONObject obj, res, result, element, point;
				    JSONArray items;
				    JSONParser parser;
				    String crs, xPos, yPos;
				    StringBuffer strReaseon = new StringBuffer();
					for ( ExcelUploadDetailVo item : list ) {
						params.put("query", item.getFullAddr());
						
					    String response = this.httpGetJsonString( url, params );
					    if ( response != null ) {
					    	parser = new JSONParser();
					    	obj = (JSONObject) parser.parse(response);
					    	
					    	res = (JSONObject) obj.get("response");
					    	result  = (JSONObject) res.get("result");
					    	try {
					    		items = (JSONArray) result.get("items");
					    		element = (JSONObject) items.get(0);
						    	point = (JSONObject) element.get("point");
						    	
						    	crs = result.get("crs").toString();
						    	xPos = point.get("x").toString();
						    	yPos = point.get("y").toString();
						    	
						    	item.setxPos(xPos);
						    	item.setyPos(yPos);
						    	item.setCrs(crs);
					    	} catch(NullPointerException ne) {
					    		strReaseon.append(item.getExcelKey() + "," + item.getDataSeq() + " data get error, ");
					    		item.setxPos("");
						    	item.setyPos("");
						    	item.setCrs("");
						    	
						    	logger.debug(response);
					    	}
					    	excelDao.updateExcelDataForService(item);

					    	//메모리 해제 시키기
					    	crs = null;
					    	xPos = null;
					    	yPos = null;
					    	point = null;
					    	element = null;
					    	items = null;
					    	result = null;
					    	res = null;
					    	obj = null;
					    	parser = null;
					    }
					    
					    //1초 대기 
					    try { Thread.sleep(10000); } catch ( Exception e) { }
					
					}
					//batch 완료 시키기
					batchVo.setSuccessYn(ConstantNames.YN_Y);
					batchVo.setDescript(strReaseon.toString());
					batchDao.updateBatchHistory(batchVo);
					    
					//excelUpload 데이터의 상태 변경
					excelUploadVo.setExcelKey(excelKey);
					excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_DS_POS_END);
					excelDao.updateExcelUpoadStatus(excelUploadVo);
				}
			} 
			excelUploadVo = null;
		} catch(Exception e ) {
			e.printStackTrace();
			
		}
		
		logger.debug("doServiceJob batchEnd");
	}

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 31.
	 * @Method Name : httpGetJsonString
	 * @return : String
	 */
	private String httpGetJsonString( String urlPath,  Map<String,Object> params) {
		String rtnString = "";
		try {
			StringBuilder parameter = new StringBuilder();
		    for(Map.Entry<String,Object> param : params.entrySet()) {
		        if(parameter.length() != 0) parameter.append('&');
		        parameter.append(URLEncoder.encode(param.getKey(), "UTF-8"));
		        parameter.append('=');
		        parameter.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		    }
		   String url = urlPath + parameter.toString();
		    
	        URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("User-Agent", USER_AGENT);
	        con.setConnectTimeout(10000);  
	        con.setReadTimeout(5000); 

	        Charset charset = Charset.forName("UTF-8");
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        rtnString = response.toString();
		} catch( Exception e ) {
			e.printStackTrace();
			rtnString = "";
		}
		return rtnString;
	}
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 2.
	 * @Method Name : doLndpclarJob
	 * @return : void
	 */
	public void doLndpclarJob() throws Exception {
		logger.debug("doLndpclarJob batchStart");
		
		String batchKey = "";
		//batch 시작플래그 생성
		BatchHistoryInfoVo batchVo = new BatchHistoryInfoVo();
		try {
			batchKey = batchDao.selectBatchKeyGenerate();
			batchVo.setBatchKey(batchKey);
			batchVo.setBatchType(ConstantNames.BATCH_KEY_NSDI_LADFRLLIST);
		} catch ( Exception e) { }
		
		try {
			
			ExcelUploadVo excelUploadVo = new ExcelUploadVo();			
			excelUploadVo.setValidCompleteYn(ConstantNames.USE_YN_N);
			excelUploadVo.setUseYn(ConstantNames.USE_YN_Y);
			excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_DS_POS_END);
			String excelKey = excelDao.selectExcelKeyForStatusConfirm(excelUploadVo);
			
			if ( excelKey != null ) {
				
				Map<String,Object> param = new HashMap<String,Object>();
				param.put(ConstantNames.EXCEL_KEY, excelKey);
				List<ExcelUploadDetailVo> list = excelDao.selectExcelUploaData(param);
				
				if ( list.size() > 1 ) {
					//excelUpload 데이터의 상태 변경
					excelUploadVo.setExcelKey(excelKey);
					excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_INFO_START);
					excelDao.updateExcelUpoadStatus(excelUploadVo);

					//batch 내용  insert
					batchVo.setBatchTarget(excelKey);
					batchDao.insertBatchHistory(batchVo);
					
					
					// 서비스 호출을 위한 파라미터 세팅
					String url = "http://openapi.nsdi.go.kr/nsdi/eios/LadfrlService/ladfrlList.json?";
					Map<String,Object> params = new LinkedHashMap<>(); 
					params.put("authkey", NSDI_KEY);
					params.put("numOfRows", "10");
					params.put("pageNo", "1");
				    
				    JSONObject obj, result, element;
				    JSONArray items;
				    JSONParser parser;
				    String lndpclAr;
				    StringBuffer strReaseon = new StringBuffer();
					for ( ExcelUploadDetailVo item : list ) {
						params.put("pnu", item.getPmuCd());
						
					    String response = this.httpGetJsonString( url, params );
					    if ( response != null ) {
					    	parser = new JSONParser();
					    	obj = (JSONObject) parser.parse(response);
					    	
					    	result  = (JSONObject) obj.get("ladfrlVOList");
					    	try {
					    		items = (JSONArray) result.get("ladfrlVOList");
					    		element = (JSONObject) items.get(0);
						    	lndpclAr = element.get("lndpclAr").toString();
						    	
						    	item.setLandWidth(lndpclAr);
						    	
					    	} catch(Exception ne) {
					    		strReaseon.append(item.getExcelKey() + "," + item.getDataSeq() + " data get error, ");
					    		item.setLandWidth("");
						    	
						    	logger.debug(response);
					    	}
					    	excelDao.updateExcelDataForService(item);

					    	//메모리 해제 시키기
					    	element = null;
					    	items = null;
					    	result = null;
					    	obj = null;
					    	parser = null;
					    }
					    
					    //1초 대기 
					    try { Thread.sleep(10000); } catch ( Exception e) { }
					
					}
					//batch 완료 시키기
					batchVo.setSuccessYn(ConstantNames.YN_Y);
					batchVo.setDescript(strReaseon.toString());
					batchDao.updateBatchHistory(batchVo);
					    
					//excelUpload 데이터의 상태 변경
					excelUploadVo.setExcelKey(excelKey);
					excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_INFO_END);
					excelDao.updateExcelUpoadStatus(excelUploadVo);
				}
			} 
			excelUploadVo = null;
		} catch(Exception e ) {
			e.printStackTrace();
		}
			logger.debug("doLndpclarJob batchEnd");
	}

	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 8.
	 * @Method Name : doDataPolygonSynch
	 * @return : void
	 */
	public void doDataPolygonSynch() {
		logger.debug("doDataPolygonSynch batchStart");
		String batchKey = "";

		//batch 시작플래그 생성
		BatchHistoryInfoVo batchVo = new BatchHistoryInfoVo();
		try {
			batchKey = batchDao.selectBatchKeyGenerate();
			batchVo.setBatchKey(batchKey);
			batchVo.setBatchType(ConstantNames.BATCH_KEY_VWORLD_POLYGON);
		} catch ( Exception e) { }
		
		try {
			
			ExcelUploadVo excelUploadVo = new ExcelUploadVo();			
			excelUploadVo.setValidCompleteYn(ConstantNames.USE_YN_N);
			excelUploadVo.setUseYn(ConstantNames.USE_YN_Y);
			excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_INFO_END);
			String excelKey = excelDao.selectExcelKeyForStatusConfirm(excelUploadVo);
			
			if ( excelKey != null ) {
				
				Map<String,Object> param = new HashMap<String,Object>();
				param.put(ConstantNames.EXCEL_KEY, excelKey);
				List<ExcelUploadDetailVo> list = excelDao.selectExcelUploaData(param);
				if ( list.size() > 1 ) {
					//excelUpload 데이터의 상태 변경
					excelUploadVo.setExcelKey(excelKey);
					excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_POLYGON_START);
					excelDao.updateExcelUpoadStatus(excelUploadVo);

					//batch 내용  insert
					batchVo.setBatchTarget(excelKey);
					batchDao.insertBatchHistory(batchVo);
					
					// 서비스 호출을 위한 파라미터 세팅
					String url = "http://api.vworld.kr/req/data?";
					Map<String,Object> params = new LinkedHashMap<>(); 
				    params.put("key",   VWORLD_KEY);
				    params.put("service",    "data");
				    params.put("version",    "2.0");
				    params.put("request",    "getfeature");
				    params.put("format",     "json");
				    params.put("size",       "100");
				    params.put("page",       "1");
				    params.put("geometry",   "true");
				    params.put("attribute",  "true");
				    params.put("crs",        "EPSG:900913");
				    params.put("data",       "LP_PA_CBND_BUBUN");
				    params.put("domain",     "http://localhost");
				    
				    JSONObject obj, res, result;
				    JSONParser parser;
				    StringBuffer strReaseon = new StringBuffer();
					for ( ExcelUploadDetailVo item : list ) {
						if ( item.getxPos() == null || item.getyPos() == null ) continue;
						else {
							params.put("geomfilter", "POINT(" + item.getxPos() + " " + item.getyPos() + ")");
						    String response = this.httpGetJsonString( url, params );
						    if ( response != null ) {
						    	parser = new JSONParser();
						    	obj = (JSONObject) parser.parse(response);
						    	
						    	res = (JSONObject) obj.get("response");
						    	result  = (JSONObject) res.get("result");
						    	try {
						    		item.setPoligonData(result.toString());
						    	} catch(NullPointerException ne) {
						    		strReaseon.append(item.getExcelKey() + "," + item.getDataSeq() + " data get error, ");
						    		item.setPoligonData("");
							    	
							    	logger.debug(response);
						    	}
						    	excelDao.updateExcelDataForService(item);
	
						    	//메모리 해제 시키기
						    	result = null;
						    	res = null;
						    	obj = null;
						    	parser = null;
						    }
					    }
					    
					    //1초 대기 
					    try { Thread.sleep(10000); } catch ( Exception e) { }
					
					}
					//batch 완료 시키기
					batchVo.setSuccessYn(ConstantNames.YN_Y);
					batchVo.setDescript(strReaseon.toString());
					batchDao.updateBatchHistory(batchVo);
					    
					//excelUpload 데이터의 상태 변경
					excelUploadVo.setExcelKey(excelKey);
					excelUploadVo.setStatusCode(ConstantNames.EXCEL_STATUS_DS_POLYGON_END);
					excelDao.updateExcelUpoadStatus(excelUploadVo);
				}
			} 
			excelUploadVo = null;
		} catch(Exception e ) {
			e.printStackTrace();
			
		}
		
		logger.debug("doDataPolygonSynch batchEnd");
	}
	
	
	public void setExcelDao(ExcelDao excelDao) {
		this.excelDao = excelDao;
	}

	public void setBatchDao(BatchHistoryInfoDao batchDao) {
		this.batchDao = batchDao;
	}

	public String getNSDI_KEY() {
		return NSDI_KEY;
	}

	public void setNSDI_KEY(String nSDI_KEY) {
		NSDI_KEY = nSDI_KEY;
	}

	public String getVWORLD_KEY() {
		return VWORLD_KEY;
	}

	public void setVWORLD_KEY(String vWORLD_KEY) {
		VWORLD_KEY = vWORLD_KEY;
	}
	
}
