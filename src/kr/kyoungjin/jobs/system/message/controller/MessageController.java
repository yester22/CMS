package kr.kyoungjin.jobs.system.message.controller;

import java.util.Locale;

import javax.inject.Qualifier;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import kr.kyoungjin.common.abstractObject.AbstractController;
import kr.kyoungjin.dataobject.vo.MessageVo;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends AbstractController {
	private Log logger = LogFactory.getLog(MessageController.class);

	@Autowired
	private IMessageService messageService;
	
	/**
	 * 사용자 정보 조회
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/get")
	public JSONObject get(MessageVo messageVo, Locale locale, HttpServletRequest request ) {
		JSONObject result = new JSONObject(); 
		
		try {
			MessageVo mv = messageService.view(messageVo);
			if ( mv != null ) {
				result.put("MSG", mv.getMessage());
				result.put("result", "OK");	
			} else  {
				result.put("result", "FALSE");
			}
		}catch ( Exception e ) {
			result.put("result", "ERROR");
			result.put("msg", e.getMessage());
			
			logger.debug(this.getClass() + "' excetion : " +  e.getMessage());
			
		}
		return result;

	}
	
	
}
