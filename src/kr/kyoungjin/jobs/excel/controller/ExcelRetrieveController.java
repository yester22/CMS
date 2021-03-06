package kr.kyoungjin.jobs.excel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.kyoungjin.common.abstractObject.AbstractController;
import kr.kyoungjin.common.abstractObject.JSONResult;
import kr.kyoungjin.common.util.ExcelUtil;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.excel.service.ExcelRetrieveService;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;

/**
 * @since 2020. 3. 24.
 * @author yeste
 * <PRE>
 * -------------------------
 * 媛쒖젙�씠�젰
 * 2020. 3. 24. yeste : 理쒖큹�옉�꽦
 * </PRE>
 */
@CrossOrigin("*")
@RestController
public class ExcelRetrieveController extends AbstractController {
	private Logger logger  = LoggerFactory.getLogger(ExcelRetrieveController.class);
	
	@Autowired
	private ExcelRetrieveService excelRetrieveService;
	
	@Autowired
	private IMessageService messageService;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/excelRetrieve", method = RequestMethod.POST)
	public JSONObject excelRetrieve (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = excelRetrieveService.getExcelUploadList(params);
			if ( rtnList.get("LIST") != null ) {
				result.put(JSONResult.LIST,   rtnList.get("LIST"));	
			}
			if ( rtnList.get("COUNT") != null ) {
				result.put(JSONResult.COUNT,   rtnList.get("COUNT"));	
			}
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/excelDataRetrieve", method = RequestMethod.POST)
	public JSONObject excelDataRetrieve (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = excelRetrieveService.getExcelDetailList(params);
			if ( rtnList.get("LIST") != null ) {
				result.put(JSONResult.LIST,   rtnList.get("LIST"));	
			}
			if ( rtnList.get("COUNT") != null ) {
				result.put(JSONResult.COUNT,   rtnList.get("COUNT"));	
			}
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/excelDataRetrieveByPaging", method = RequestMethod.POST)
	public JSONObject excelDataRetrieveByPaging (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = excelRetrieveService.getExcelDetailListByPaging(params);
			if ( rtnList.get("LIST") != null ) {
				result.put(JSONResult.LIST,   rtnList.get("LIST"));	
			}
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/excelDataRetrieveCount", method = RequestMethod.POST)
	public JSONObject excelDataRetrieveCount (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
;			Long cnt = excelRetrieveService.getExcelDetailCount(params);
			result.put(JSONResult.COUNT, cnt);
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
	
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : excelList
	 * @return : void
	 */
	@RequestMapping(value = "/admin/excelDown")
	public void excelList(HttpServletRequest request ,HttpServletResponse response, @RequestParam Map<String, Object> params ) throws InvalidFormatException {
		Map<String , Object> beans = new HashMap<String , Object>();
		Map<String, Object> rtnList = null;
		ExcelUploadVo uploadVo = null;
		try {
			uploadVo =  excelRetrieveService.getExcelUploadInfo(params);
			beans.put("INFO",   uploadVo);
			
			rtnList = excelRetrieveService.getExcelDetailList(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( rtnList != null && rtnList.get("LIST") != null ) {
			beans.put(JSONResult.LIST,   rtnList.get("LIST"));
		}
		String fileName = uploadVo.getOriginalFileName().replaceAll(".xlsx", "");
		fileName = uploadVo.getOriginalFileName().replaceAll(".xls", "");
		
		ExcelUtil.download(request, response, beans, fileName,  "EL_0001.xlsx");
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/admin/excelDataDelete", method = RequestMethod.POST)
	public JSONObject excelDelete(HttpServletRequest request ,HttpServletResponse response, @RequestParam Map<String, Object> params ) throws Exception {
		JSONObject result = new JSONObject();
		
		String deleteData = params.get("deleteData").toString();
		if ( deleteData != null && ! "".equals(deleteData)) {
			if ( deleteData.lastIndexOf(",") > 0 ) {
				params.put("deleteArray", deleteData.split(","));
			} else {
				String[] arr = { deleteData };
				params.put("deleteArray", arr);
			}
		}
		
		try {
			excelRetrieveService.deleteExcelData (params );
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch(Exception e ) {
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
	
}
