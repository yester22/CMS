package kr.kyoungjin.jobs.member.controller;

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
import kr.kyoungjin.jobs.board.service.BoardService;
import kr.kyoungjin.jobs.member.service.IMemberService;
import kr.kyoungjin.jobs.system.encryption.service.EncriptionService;
import net.sf.json.JSONObject;

/**
 * @since 2020. 4. 30.
 * @author yeste
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 4. 30. yeste : 최초작성
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
	@RequestMapping(value = "/admin/memberList")
	public JSONObject memberList (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = memberService.getMemberList(params);
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


}
