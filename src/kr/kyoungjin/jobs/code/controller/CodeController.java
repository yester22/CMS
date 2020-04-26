package kr.kyoungjin.jobs.code.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.kyoungjin.common.abstractObject.AbstractController;
import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.abstractObject.JSONResult;
import kr.kyoungjin.dataobject.vo.BoardVo;
import kr.kyoungjin.dataobject.vo.CodeVo;
import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.jobs.code.service.CodeService;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;

public class CodeController extends AbstractController {
	private Logger logger  = LoggerFactory.getLogger(CodeController.class);

	@Autowired
	private CodeService CodeService;

	@Autowired
	private IMessageService messageService;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/boardList", method = RequestMethod.POST)
	public JSONObject boardList (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = CodeService.getCodeList(params);
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

	@ResponseBody
	@RequestMapping(value = "/admin/codeDelete", method = RequestMethod.POST)
	public JSONObject codeDelete(HttpServletRequest request ,HttpServletResponse response, @RequestParam Map<String, Object> params ) throws Exception {
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
			CodeService.deleteCode (params );
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch(Exception e ) {
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/codeReg", method = RequestMethod.POST)
	public JSONObject BoardReg (MultipartHttpServletRequest request , @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			MemberVo sessionMemberInfo = (MemberVo)request.getSession().getAttribute(ConstantNames.SESSION_USER_INFO);
			List<String> uploadFilesId = new ArrayList<String>();
			String codeId = "";

			codeId = CodeService.insertCode(params, sessionMemberInfo.getMemberId());
			if (codeId != null && !"".equals(codeId) ) {
				uploadFilesId.add(codeId);
			}

			result.put("codeIds", uploadFilesId);
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/codeRead", method = RequestMethod.POST)
	public JSONObject codeRead (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		CodeVo rtnList = null;
		
		try {
			rtnList = CodeService.getCodeRead(params);
			if ( rtnList != null ) {
				System.out.println("rtnList=============="+rtnList);
				result.put("code",   rtnList.getCode());
				result.put("codeNm",   rtnList.getCodeNm());
				result.put("upperCdKey",   rtnList.getUpperCdKey());
				result.put("orderSeq",   rtnList.getOrderSeq());
				result.put("useYn",   rtnList.getUseYn());
			}

			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/codeKeyRead", method = RequestMethod.POST)
	public JSONObject codeKeyRead (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = CodeService.getCodeKeyRead(params);
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
}
