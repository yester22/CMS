package kr.kyoungjin.jobs.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.kyoungjin.common.abstractObject.AbstractController;
import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.abstractObject.JSONResult;
import kr.kyoungjin.dataobject.vo.BoardMngVo;
import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.jobs.board.service.BoardMngService;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;

@CrossOrigin("*")
@RestController
public class BoardMngController extends AbstractController {
	private Logger logger  = LoggerFactory.getLogger(BoardMngController.class);
	
	@Autowired
	private BoardMngService BoardMngService;
	
	@Autowired
	private IMessageService messageService;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/boardMngList", method = RequestMethod.POST)
	public JSONObject boardMngList (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = BoardMngService.getBoardMngList(params);
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
	@RequestMapping(value = "/admin/boardMngDelete", method = RequestMethod.POST)
	public JSONObject boardMngDelete(HttpServletRequest request ,HttpServletResponse response, @RequestParam Map<String, Object> params ) throws Exception {
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
			BoardMngService.deleteBoardMng (params );
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch(Exception e ) {
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/boardMngReg", method = RequestMethod.POST)
	public JSONObject boardMngReg (MultipartHttpServletRequest request , @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			MemberVo sessionMemberInfo = (MemberVo)request.getSession().getAttribute(ConstantNames.SESSION_USER_INFO);
			List<String> uploadFilesId = new ArrayList<String>();
			String boardId = "";

			boardId = BoardMngService.insertBoardMng(params, sessionMemberInfo.getMemberId());
			if (boardId != null && !"".equals(boardId) ) {
				uploadFilesId.add(boardId);
			}

			result.put("boardIds", uploadFilesId);
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/boardMngRead", method = RequestMethod.POST)
	public JSONObject boardMngRead (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		BoardMngVo rtnList = null;
		
		try {
			rtnList = BoardMngService.getBoardMngRead(params);
			if ( rtnList != null ) {
				System.out.println("rtnList=============="+rtnList);
				result.put("boardCd",   rtnList.getBoardCd());
				result.put("boardNm",   rtnList.getBoardNm());
				result.put("listRowCnt",   rtnList.getListRowCnt());
				result.put("listBlockCnt",   rtnList.getListBlockCnt());
				result.put("titleSplitLen",   rtnList.getTitleSplitLen());
				result.put("contentsSplitLen",   rtnList.getContentsSplitLen());
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
	@RequestMapping(value = "/admin/boardCodeRead", method = RequestMethod.POST)
	public JSONObject boardCordRead (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = BoardMngService.getBoardCode(params);
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