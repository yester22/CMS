package kr.kyoungjin.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.kyoungjin.common.util.MessageHandler;


/**
 * 
 * @author
 * @since 2010. 8. 16.
 * @version 1.0
 * @see
 *
 *
 * <pre>
 * 
 *   �닔�젙�씪                    �닔�젙�옄                  �궡�슜
 *  -----------  --------   --------------------------
 *   2010. 8. 16.            理쒖“�옉�꽦
 * 
 * </pre>
 */
@SuppressWarnings("unchecked")
public class JsonUtil {
	private static Logger _log  = LoggerFactory.getLogger(JsonUtil.class);
	private final static String IMG_NEW_SRC = "@{new}";
	private final static String IMG_NEW_TARGET_OPEN = "<img src='";
	private final static String IMG_NEW_TARGET_CLOSE = "/images/btn_star.gif' width='13' height='13' />";

	/**
	 * MapToString
	 * @param map
	 * @param res
	 * @return void
	 * @exception
	 */
	public static void getConvertMap(Map<String, Object> map, HttpServletResponse res){
		JSONArray jObj = JSONArray.fromObject(map);
		sendToJson(jObj.toString(), res);
	}
	public static JSONArray getConvertMap(Map<String, Object> map){
		JSONArray jObj = JSONArray.fromObject(map);
		return jObj;
	}
	public static void returnJson(List list, final String[]cols, HttpServletResponse res, final String contextPath){
		sendToJson(parseJson(list, cols, 999999, contextPath), res);
	}
	public static void returnTreeJson(List list, final String[]cols, HttpServletResponse res, final String contextPath){
		sendToJson(parseTreeJson(list, cols,contextPath), res);
	}
	
	public static void returnJson(List list, final String[]cols, final int totalPage, HttpServletResponse res, final String contextPath){
		sendToJson(parseJson(list, cols, totalPage, contextPath), res);
	}


	/**
	 * ListToString
	 * @param list
	 * @param res
	 * @return void
	 * @exception
	 */
	public static void getConvertList(List<Object> list, HttpServletResponse res){
		JSONArray jObj = JSONArray.fromObject(list);
		sendToJson(jObj.toString(), res);
	}
	public static JSONArray getConvertList(List<Object> list){
		JSONArray jObj = JSONArray.fromObject(list);
		return jObj;
	}

	public static void sendToJson(String jObj, HttpServletResponse res) {
		try {
			res.setHeader("Cache-Control", "no-cache");
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			// 2012.12.14 源�紐낆꽠
			// 洹몃━�뱶 議고쉶 寃곌낵媛� �뾾�쑝硫� alert�쓣 �쓣�슦�뒗�뜲, 
			// �븳 �솕硫댁뿉 �뿬�윭媛� 洹몃━�뱶媛� �엳�뒗 寃쎌슦 alert�씠 洹몃━�뱶 媛��닔留뚰겮 �쑉�뒗 寃쎌슦媛� �엳�쓬.
			// 洹몃옒�꽌 �씪�떒 alert�쓣 留됱쓬
			if(!jObj.equals("{ totalPage : 0, rows: [ ]}")){
			//if(!jObj.equals("{rows: [ ]}")){
				res.getWriter().print(jObj);
			} else {
				res.getWriter().print("nodata");
			}

			if(_log.isInfoEnabled()) {
				_log.info("################################");
				_log.info("json Object :"+jObj);
				_log.info("################################");
			}
		} catch (IOException e) {
			MessageHandler.getMessage("json.send.message");
			e.printStackTrace();
		}
	}

	/**
	 * 寃곌낵諛섑솚 �삎�떇�쓣 諛섑솚�븳�떎.
	 * 
	 * @param infoName
	 * @param infoDDto
	 * @param resultName
	 * @param resultList
	 * @return String
	 */
	/*
	public static String getJsonResult(String infoName, CmcCcJsonInfoDto infoDDto, String resultName, List resultList) {

		Map map = new HashMap();

		if("".equals(StringUtil.trim(infoName, ""))||infoDDto==null){
			_log.error("JSONUtil.getResultJson() INPUT Error : info is null!!");
			return "";
		}

		// 寃곌낵 INFO援ъ꽦
		JSONArray infoArray = JSONArray.fromObject(infoDDto);
		map.put(infoName, infoArray);

		// 寃곌낵 List援ъ꽦
		if(!"".equals(StringUtil.trim(resultName, ""))||resultList!=null){
			map.put(resultName, resultList);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);

		return jsonObject.toString();
	}
	 */

	public static String parseJson(List list, final String[]cols, final int totalPage, final String contextPath) {
		final String OPEN = "{ id : \"";
		final String DATA = "\", data : [ ";
		final String COT = "\"";
		final String COMMA = ",";

		StringBuffer result = new StringBuffer("{ totalPage : "+totalPage+", rows: [ ");
		
		int listSize = list.size();
		int colsLen = cols.length ;
		Map map = null;
		if (list != null && listSize>0 && cols != null && colsLen > 0) {
			
			for (int i = 0; i < listSize; i++) {
				
				if (i != 0) {
					result.append(COMMA);
				}
				
				map = (Map) list.get(i);
				result.append(OPEN).append(i+1).append(DATA);
				
				int colCnt = 0;
				for (String string : cols) {
					if(colCnt > 0){
						result.append(COMMA);
					}
					result.append(COT).append(get(map.get(string), contextPath)).append(COT);
					colCnt++;
				}
				
				result.append("]}");
			}
		}

		result.append("]}");

		return result.toString();
	}
	
	public static String parseTreeJson(List list, final String[]cols,final String contextPath) {
		//final String OPEN = "[";
		//final String DATA = "\", data : [ \"";
		final String COT = "\"";
		final String COMMA = ",";

		StringBuffer result = new StringBuffer(1024);
		if (list != null && list.size()>0 && cols != null && cols.length > 0) {
			result.append("[");
			int listSize = list.size() ; 
			Map map = null;
			for (int i = 0; i < listSize; i++) {
				if (i != 0) {
					result.append(COMMA);
				}
				map = (Map) list.get(i);
				result.append("[");
				int j=0;
				for (String string : cols) {
					if (j>0) {
						result.append(COMMA);
					}
					result.append(COT).append(get(map.get(string), contextPath)).append(COT);
					j++;
				}
				result.append("]");
			}
			result.append("]");
		}
		//result.append("]}");
		
		return result.toString();
	}
	
	/**
	 * �뿏�꽣瑜� <br/>濡� 蹂��솚�븳�떎.
	 * @param object
	 * @return
	 */
	private static Object get(Object object, final String contextPath) {
		if(object == null) {
			return "";
		}
		if(object instanceof java.lang.String) {
			String result = StringUtil.replace((String)object, "\n", "<br/>");
			result = StringUtil.replace(result, IMG_NEW_SRC, IMG_NEW_TARGET_OPEN+contextPath + IMG_NEW_TARGET_CLOSE);
			result = StringUtil.replace(result, "\r", "<br/>");
			if(result.indexOf('"')!=-1) {
				//				System.out.print(result);
				result = StringUtil.replace(result, "\"", "竊�");
				//				System.out.println(" ----> " + result);
			}
			
			if(result.indexOf("\\")!=-1){
				result = StringUtil.replace(result, "\\", "\\\\");
			}
			
			return result;
		}
		return object;
	}
}
