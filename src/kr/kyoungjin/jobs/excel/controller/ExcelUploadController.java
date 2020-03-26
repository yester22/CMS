package kr.kyoungjin.jobs.excel.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.kyoungjin.common.abstractObject.AbstractController;
import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.abstractObject.JSONResult;
import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.jobs.excel.service.ExcelUploadService;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;


/**
 * @since 2020. 3. 20.
 * @author yeste
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 3. 20. yeste : 최초작성
 * </PRE>
 */
@CrossOrigin("*")
@RestController
public class ExcelUploadController  extends AbstractController {
	
	private Log logger = LogFactory.getLog(ExcelUploadController.class);
	
	@Autowired
	private ExcelUploadService excelUploadService;
	
	
	@Autowired
	private IMessageService messageService;


	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : excelupload
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/excelUpload", method = RequestMethod.POST)
	public JSONObject excelUpload (MultipartHttpServletRequest request , @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			MemberVo sessionMemberInfo = (MemberVo)request.getSession().getAttribute(ConstantNames.SESSION_USER_INFO);
			List<String> uploadFilesId = new ArrayList<String>();
			String excelId = "";
			
			Iterator<String> itr =  request.getFileNames();
			if(itr.hasNext()) {
				List<MultipartFile> mpf = request.getFiles(itr.next());
				for (  MultipartFile uploadfile : mpf ) {
					excelId = excelUploadService.excelUpload(params, uploadfile, sessionMemberInfo.getMemberId());
					if (excelId != null && !"".equals(excelId) ) {
						uploadFilesId.add(excelId);
					}
				}
			}
			
			result.put("excelIds", uploadFilesId);
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
	
}
