package kr.kyoungjin.jobs.batch.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kyoungjin.dataobject.dao.ExcelDao;

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

	private ExcelDao excelDao;
	
	private Log logger = LogFactory.getLog(BatchVWorldDataSetting.class);
	private final String authKey = "3C0D8395-23FD-3870-B012-50F4659E1CC1";
	
	public void doServiceJob() throws Exception {
		logger.debug("batchStart");
		
		String query = "경상남도 고성군 대가면 암전리 691-10";
		
		URL url = new URL("https://api.vworld.kr/req/search"); // 호출할 url
	    Map<String,Object> params = new LinkedHashMap<>(); // 파라미터 세팅
	    params.put("service", "search");
	    params.put("request", "search");
	    params.put("version", "2.0");
	    params.put("crs", "EPSG:900913");
	    params.put("query", query);
	    params.put("type",  "address");
	    params.put("category", "parcel");
	    params.put("format", "json");
	    params.put("errorformat", "json");
	    params.put("apiKey", authKey);
	    
	    StringBuilder postData = new StringBuilder();
	    for(Map.Entry<String,Object> param : params.entrySet()) {
	        if(postData.length() != 0) postData.append('&');
	        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	        postData.append('=');
	        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	    }
	    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	 
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	    conn.setDefaultUseCaches(false);
	    conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    conn.getOutputStream().write(postDataBytes); // POST 호출
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    StringBuilder sb = new StringBuilder();
	    String inputLine;
	    while((inputLine = in.readLine()) != null) { // response 출력
	        sb.append(inputLine).append("\n");
	    }
	    in.close();
		
		logger.debug("batchEnd");
	}

	
	public void doAddressToCrsPointJob() throws Exception {
		logger.debug("batchStart");
		
		String query = "경상남도 고성군 대가면 암전리 691-10";
		
		URL url = new URL("http://api.vworld.kr/req/address"); // 호출할 url
	    Map<String,Object> params = new LinkedHashMap<>(); // 파라미터 세팅
	    params.put("service", "address");
	    params.put("request", "getcoord");
	    params.put("version", "2.0");
	    params.put("crs", "epsg:4326");
	    params.put("query", query);
	    params.put("refine", "true");
	    params.put("simple", "false");
	    params.put("format", "json");
	    params.put("type",  "parcel");
	    params.put("key", authKey);
	    params.put("key", authKey);
	    
	    StringBuilder postData = new StringBuilder();
	    for(Map.Entry<String,Object> param : params.entrySet()) {
	        if(postData.length() != 0) postData.append('&');
	        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	        postData.append('=');
	        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	    }
	    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	 
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	    conn.setDefaultUseCaches(false);
	    conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    conn.getOutputStream().write(postDataBytes); // POST 호출
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    StringBuilder sb = new StringBuilder();
	    String inputLine;
	    while((inputLine = in.readLine()) != null) { // response 출력
	        sb.append(inputLine).append("\n");
	    }
	    in.close();
		
		logger.debug("batchEnd");
	}
	
	public void setExcelDao(ExcelDao excelDao) {
		this.excelDao = excelDao;
	}

}
