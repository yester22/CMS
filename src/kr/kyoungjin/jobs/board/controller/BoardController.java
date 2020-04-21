package kr.kyoungjin.jobs.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.kyoungjin.common.abstractObject.AbstractController;
import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.abstractObject.JSONResult;
import kr.kyoungjin.dataobject.vo.BoardVo;
import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.jobs.board.service.BoardService;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;

@CrossOrigin("*")
@RestController
public class BoardController extends AbstractController {
	private Logger logger  = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService BoardService;
	
	@Autowired
	private IMessageService messageService;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/BoardList", method = RequestMethod.POST)
	public JSONObject BoardList (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = BoardService.getBoardList(params);
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
	@RequestMapping(value = "/admin/BoardDelete", method = RequestMethod.POST)
	public JSONObject BoardDelete(HttpServletRequest request ,HttpServletResponse response, @RequestParam Map<String, Object> params ) throws Exception {
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
			BoardService.deleteBoard (params );
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch(Exception e ) {
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/BoardReg", method = RequestMethod.POST)
	public JSONObject BoardReg (MultipartHttpServletRequest request , @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			MemberVo sessionMemberInfo = (MemberVo)request.getSession().getAttribute(ConstantNames.SESSION_USER_INFO);
			List<String> uploadFilesId = new ArrayList<String>();
			String boardId = "";

			boardId = BoardService.insertBoard(params, sessionMemberInfo.getMemberId());
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
	@RequestMapping(value = "/admin/BoardRead", method = RequestMethod.POST)
	public JSONObject BoardRead (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		BoardVo rtnList = null;
		
		try {
			rtnList = BoardService.getBoardRead(params);
			if ( rtnList != null ) {
				System.out.println("rtnList=============="+rtnList);
				result.put("boardCd",   rtnList.getBoardCd());
				result.put("boardSeq",   rtnList.getBoardSeq());
				result.put("title",   rtnList.getTitle());
				result.put("body",   rtnList.getBody());
				result.put("tag",   rtnList.getTag());
				result.put("htmlYn",   rtnList.getHtmlYn());
			}

			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
}