package kr.kyoungjin.jobs.member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.abstractObject.JSONResult;
import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.jobs.board.service.BoardService;
import kr.kyoungjin.jobs.member.service.IMemberService;
import kr.kyoungjin.jobs.system.encryption.service.EncriptionService;
import net.sf.json.JSONObject;

/**
 * @since 2020. 4. 30.
 * @author yeste
 * <PRE>
 * -------------------------
 * 媛쒖젙�씠�젰
 * 2020. 4. 30. yeste : 理쒖큹�옉�꽦
 * </PRE>
 */
@CrossOrigin("*")
@RestController
public class MemberController extends AbstractController{
	private Logger logger  = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private IMemberService memberService;
	
	@Autowired
	private EncriptionService encriptionService;

	@Autowired
	private BoardService BoardService;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody	
	@RequestMapping(value = "/admin/memberList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf8")
	public String memberList (HttpServletRequest request ,HttpServletResponse response, @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			
			 List<MemberVo> memberList = memberService.getMemberList(params); 
			 if ( memberList != null ) { 
				 result.put(JSONResult.LIST, memberList); 
			} else {
				result.put(JSONResult.LIST, new ArrayList()); 
			}
			
			long count = memberService.getMemberListCount(params);
			result.put(JSONResult.COUNT,  count);
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result.toString();
	}


	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody	
	@RequestMapping(value = "/admin/memberView", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf8")
	public String memberView (HttpServletRequest request ,HttpServletResponse response, @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			
			 MemberVo member = memberService.getMember(params); 
			 if ( member != null ) { 
				 result.put(JSONResult.OBJECT, member); 
			} else {
				result.put(JSONResult.OBJECT, new Object()); 
			}
			
			long count = memberService.getMemberListCount(params);
			result.put(JSONResult.COUNT,  count);
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result.toString();
	}
	

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody	
	@RequestMapping(value = "/admin/checkIdExists", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf8")
	public String checkIdExists (HttpServletRequest request ,HttpServletResponse response, @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			
			int count = memberService.getCount(params);
			result.put(JSONResult.COUNT,  count);
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result.toString();
	}
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody	
	@RequestMapping(value = "/admin/memberSave", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf8")
	public String memberSave (HttpServletRequest request ,HttpServletResponse response, HttpSession session, @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			MemberVo sesMember = (MemberVo)session.getAttribute(ConstantNames.SESSION_USER_INFO);
			params.put("regMember", sesMember.getMemberId());
			
			if ( params.get("memberPw") != null ) {
				String memberPw = (String)params.get("memberPw");
				memberPw = encriptionService.encode(memberPw);
				params.put("encriptionMemberPw", memberPw);
			}
			
			
			int nSuccess = this.memberService.saveMember(params);
			if ( nSuccess > 0 ) result.put(JSONResult.RESULT, JSONResult.OK);
			else result.put(JSONResult.RESULT, JSONResult.FAIL);
			
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result.toString();
	}

	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody	
	@RequestMapping(value = "/admin/memberDelete", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf8")
	public String memberDelete (HttpServletRequest request ,HttpServletResponse response, HttpSession session, @RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			MemberVo sesMember = (MemberVo)session.getAttribute(ConstantNames.SESSION_USER_INFO);
			params.put("uptMember", sesMember.getMemberId());
			
			int nSuccess = this.memberService.deleteMember(params);
			if ( nSuccess > 0 ) result.put(JSONResult.RESULT, JSONResult.OK);
			else result.put(JSONResult.RESULT, JSONResult.FAIL);
			
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result.toString();
	}

}
