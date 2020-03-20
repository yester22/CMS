package kr.kyoungjin.jobs.login.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kyoungjin.common.abstractObject.AbstractController;
import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.abstractObject.JSONResult;
import kr.kyoungjin.common.util.ClientInfoUtil;
import kr.kyoungjin.dataobject.vo.LoginTryVo;
import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.dataobject.vo.MessageVo;
import kr.kyoungjin.jobs.member.service.IMemberService;
import kr.kyoungjin.jobs.system.encryption.service.EncriptionService;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;

/**
 * 로그인 프로세스를 처리하기 위한 클래스
 * @author yeste
 *
 */
@Controller
@RequestMapping(value="/login")
public class LoginController extends AbstractController{
	
	@Autowired
	private IMemberService memberService;
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private EncriptionService encriptionService;
	
	private Log logger = LogFactory.getLog(LoginController.class);
	
	/**
	 * LOGIN CHECK
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/view")
	public JSONObject view (MemberVo memberVo ) {
		JSONObject result = new JSONObject(); 

		try {
			MemberVo mv = memberService.view(memberVo);
			if ( mv != null ) {
				result.put("member", mv);
				result.put("result", "OK");	
			} else  {
				result.put("result", "FALSE");
			}
		} catch ( Exception e ) {
			result.put("result", "ERROR");
			result.put("msg", e.getMessage());
			
			logger.debug(this.getClass() + "' excetion : " +  e.getMessage());
			
		}
		return result;
	}
	
	
	
	/**
	 * 사용자 정보 조회
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loginCheck")
	public JSONObject loginCheck(MemberVo memberVo, HttpServletRequest request ) throws Exception {
		JSONObject result = new JSONObject(); 
		MessageVo messageVo = new MessageVo(); 
		
		String clientIpAddr = "";
		String clientMacAddr = "";
		
		try {
		
			clientIpAddr = ClientInfoUtil.getClientIpAddr(request);
			clientMacAddr = ClientInfoUtil.getClientMacAddr(request);
			
			logger.debug("original = "  + memberVo.getPwd());
			logger.debug("encoded pw = "  + encriptionService.encode(memberVo.getPwd()));
			
			String encodedPw = encriptionService.encode(memberVo.getPwd());
			//Login을 계속적으로 시도할시 에러를 생성하기 위한 처리
			LoginTryVo loginTryVo = new LoginTryVo();
			loginTryVo.setIpAddr( clientIpAddr   );
			loginTryVo.setNicAddr( clientMacAddr );
			
			MemberVo mv = memberService.view(memberVo);
			if ( mv != null ) {
				//로그인 실패 카운트가 10번 이상인 경우
				if ( mv.getLoginFailCnt() > 9 ) {
					
				} else {
					if (  mv.getPwd().equals(encodedPw)) {
						result.put(JSONResult.RESULT, JSONResult.OK);
						
						//사용자 로그인 성공 처리
						loginTryVo.setMemberId(mv.getMemberId());
						loginTryVo.setLoginPw(encodedPw);
						loginTryVo.setSuccessYn("Y");
						memberService.updateLoginSuccess(loginTryVo);
						
						mv.setPwd("");
						HttpSession session = request.getSession();
						session.setAttribute(ConstantNames.SESSION_USER_INFO,  mv);
						
					} else { //패스워드가 맞지않을 경우
						result.put(JSONResult.RESULT, JSONResult.NOT_MATCH);	
						result.put("failCnt", mv.getLoginFailCnt() + 1);
						
						//사용자 로그인 실패처리
						loginTryVo.setMemberId(mv.getMemberId());
						loginTryVo.setSuccessYn("N");
						loginTryVo.setIdExist("Y");
						loginTryVo.setLoginPw(memberVo.getPwd());
						
						memberService.updateLoginFail(loginTryVo);
						
						messageVo.setMsgKey("LGN0000003");
						result.put(JSONResult.MESSAGE, messageService.view(messageVo).getMessage());
					}
				}
			} else  { //계정이 존재하지 않을 경우
				result.put(JSONResult.RESULT, JSONResult.FAIL);

				//사용자 로그인 실패처리
				loginTryVo.setMemberId(memberVo.getMemberId());
				loginTryVo.setSuccessYn("N");
				loginTryVo.setIdExist("N");
				loginTryVo.setLoginPw(memberVo.getPwd());
				
				memberService.updateLoginFail(loginTryVo);
				
				messageVo.setMsgKey("LGN0000002");
				result.put(JSONResult.MESSAGE, messageService.view(messageVo).getMessage());
			}
		} catch ( Exception e ) {
			result.put(JSONResult.RESULT, JSONResult.ERROR);
			result.put(JSONResult.MESSAGE, e.getMessage());
			e.printStackTrace();
			logger.debug(this.getClass() + "' excetion : " +  e.getMessage());
			
		}
		
		return result;
	}
	
	
	
	
}
