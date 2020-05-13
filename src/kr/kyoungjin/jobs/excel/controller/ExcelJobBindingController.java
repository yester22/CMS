package kr.kyoungjin.jobs.excel.controller;

import java.util.List;
import java.util.Map;

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
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.excel.service.JobBindingService;
import net.sf.json.JSONObject;

/**
 * @since 2020. 5. 14.
 * @author yeste
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 5. 14. yeste : 최초작성
 * </PRE>
 */
@CrossOrigin("*")
@RestController
public class ExcelJobBindingController extends AbstractController {
	
	private Logger logger  = LoggerFactory.getLogger(ExcelJobBindingController.class);
	
	@Autowired
	private JobBindingService jobBindingService; 
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 5. 14.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/notAssignExcelList", method = RequestMethod.POST)
	public JSONObject excelList (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		try {
			List<ExcelUploadVo> rtnList = jobBindingService.getExcelList(params);
			if ( rtnList != null ) {
				result.put(JSONResult.LIST,  rtnList );	
			}
			
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
	
}
