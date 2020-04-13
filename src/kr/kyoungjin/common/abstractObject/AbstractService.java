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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author 而댄벂�꽣
 *
 */
public class AbstractService {
	
	@Autowired
	protected SqlSession sqlSession;

	@Autowired
	protected SessionLocaleResolver sessionLocaleResolver;
	
	//logger
	private Logger logger  = LoggerFactory.getLogger(AbstractService.class);


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
	
	
	/**濡쒖��씪 �뒪�듃留곸쓣 諛섑솚�븳�떎
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
	 * �뼵�뼱 �꽕�젙
	 * @param language 
	 */
	public void setLanguage( String language ) {
		Locale locale = new Locale(language);
		sessionLocaleResolver.setLocale(getRequest(), getResponse(), locale);
	}
	


	
	
}
