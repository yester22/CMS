package kr.kyoungjin.jobs.login.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import kr.kyoungjin.jobs.excel.service.impl.ExcelUploadServiceImpl;
import kr.kyoungjin.jobs.member.service.IMemberService;
import kr.kyoungjin.jobs.system.encryption.service.EncriptionService;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;

/**
 * 濡쒓렇�씤 �봽濡쒖꽭�뒪瑜� 泥섎━�븯湲� �쐞�븳 �겢�옒�뒪
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
	
	private Logger logger  = LoggerFactory.getLogger(LoginController.class);
	
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
	 * �궗�슜�옄 �젙蹂� 議고쉶
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
			//Login�쓣 怨꾩냽�쟻�쑝濡� �떆�룄�븷�떆 �뿉�윭瑜� �깮�꽦�븯湲� �쐞�븳 泥섎━
			LoginTryVo loginTryVo = new LoginTryVo();
			loginTryVo.setIpAddr( clientIpAddr   );
			loginTryVo.setNicAddr( clientMacAddr );
			
			MemberVo mv = memberService.view(memberVo);
			if ( mv != null ) {
				//濡쒓렇�씤 �떎�뙣 移댁슫�듃媛� 10踰� �씠�긽�씤 寃쎌슦
				if ( mv.getLoginFailCnt() > 9 ) {
					
				} else {
					if (  mv.getPwd().equals(encodedPw)) {
						result.put(JSONResult.RESULT, JSONResult.OK);
						
						//�궗�슜�옄 濡쒓렇�씤 �꽦怨� 泥섎━
						loginTryVo.setMemberId(mv.getMemberId());
						loginTryVo.setLoginPw(encodedPw);
						loginTryVo.setSuccessYn("Y");
						memberService.updateLoginSuccess(loginTryVo);
						
						mv.setPwd("");
						HttpSession session = request.getSession();
						session.setAttribute(ConstantNames.SESSION_USER_INFO,  mv);
						
					} else { //�뙣�뒪�썙�뱶媛� 留욎��븡�쓣 寃쎌슦
						result.put(JSONResult.RESULT, JSONResult.NOT_MATCH);	
						result.put("failCnt", mv.getLoginFailCnt() + 1);
						
						//�궗�슜�옄 濡쒓렇�씤 �떎�뙣泥섎━
						loginTryVo.setMemberId(mv.getMemberId());
						loginTryVo.setSuccessYn("N");
						loginTryVo.setIdExist("Y");
						loginTryVo.setLoginPw(memberVo.getPwd());
						
						memberService.updateLoginFail(loginTryVo);
						
						messageVo.setMsgKey("LGN0000003");
						result.put(JSONResult.MESSAGE, messageService.view(messageVo).getMessage());
					}
				}
			} else  { //怨꾩젙�씠 議댁옱�븯吏� �븡�쓣 寃쎌슦
				result.put(JSONResult.RESULT, JSONResult.FAIL);

				//�궗�슜�옄 濡쒓렇�씤 �떎�뙣泥섎━
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
