/**
 * 
 */
package kr.kyoungjin.common.abstractObject;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author 컴퓨터
 *
 */
public class AbstractService {
	
	@Autowired
	protected SqlSession sqlSession;

	@Autowired
	protected SessionLocaleResolver sessionLocaleResolver;
	
	//logger
	protected Log logger = LogFactory.getLog(AbstractService.class);


	private static HttpServletRequest request;
	private static HttpServletResponse response;
	
	/** request return;
	 * @return httpRequest
	 */
	private static HttpServletRequest getRequest() {
		if ( request == null ) { 
		 request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		}
		return request;
	}
	
	
	/** response return;
	 * @return httpResponse
	 */
	private static HttpServletResponse getResponse() {
		if ( response == null ) { 
			response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		}
		return response;
	}
	
	
	/**로케일 스트링을 반환한다
	 * @return language return
	 */
	public String getLanguage() {
		String language = "";
		try {
			language = sessionLocaleResolver.resolveLocale( getRequest() ).getLanguage();
		} catch ( Exception e ) {
			language = "en";
		}
	
		return language;
	}

	/**
	 * 언어 설정
	 * @param language 
	 */
	public void setLanguage( String language ) {
		Locale locale = new Locale(language);
		sessionLocaleResolver.setLocale(getRequest(), getResponse(), locale);
	}
	


	
	
}
