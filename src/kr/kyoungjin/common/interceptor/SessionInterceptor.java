package kr.kyoungjin.common.interceptor;

import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.util.AccessiblePageUtil;

/**
 * 페이지 이동마다 세션을 체크하거나, 페이지를 체크하여
 * 세션없이 이동가능한 URL을 제외한 페이지는 루트로 보낸다
 * @author 정경진
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    protected Log log = LogFactory.getLog(SessionInterceptor.class);
    
    @Value("#{config['ACCESS_URL_XML_PATH']}")
    private String ACCESS_URL_XML_PATH;
    
    /**
     * 전처리
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	//session에 사용자 정보가 존재하지 않을경우 루트로 redirection
    	boolean isRedirect = false;
    	HttpSession session = request.getSession();
    	if ( session.getAttribute(ConstantNames.SESSION_USER_INFO) == null  ) isRedirect = true;
    	
   /* 	if ( request.getRequestURI().equals("/go") ) {
    		String param = request.getParameter("go");
    		if ( param.equals("/login/form")) isRedirect = false;
    		else isRedirect = true;
    	} */
    	
    	//xml 파일을 읽어서 허용 페이지 목록을 반환한다
    	Resource resource = new ClassPathResource(ACCESS_URL_XML_PATH);
    	List<String> list = AccessiblePageUtil.getAccessiblePageList(resource.getFile());
    	ListIterator<String> iter = list.listIterator();
    	while ( iter.hasNext()) {
    		if ( iter.next().equals(request.getRequestURI())) isRedirect = false;
    	}
    	
    	log.debug("====================================================" );
    	log.debug( "#URL = " + request.getRequestURI() );
    	log.debug("====================================================" );
    		
    	if ( isRedirect ) {
    		response.sendRedirect("/");
    	} else if ( session.getAttribute(ConstantNames.SESSION_USER_INFO) != null &&  request.getRequestURI().equals("/login")) {
    		response.sendRedirect("/main");
    	}
    	
                
        return super.preHandle(request, response, handler);
    }

     
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { }
}
