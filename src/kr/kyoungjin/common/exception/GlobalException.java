package kr.kyoungjin.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @since 2020. 3. 21.
 * @author yeste
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 3. 21. yeste : 최초작성
 * </PRE>
 */
public class GlobalException implements HandlerExceptionResolver {

	
	private String exceptionAttribute = "exception";
	private String exceptionMappings = "";
	private String defaultErrorView = "error";
	
	
	private Log logger = LogFactory.getLog(GlobalException.class);
	
	/**
	 *예외 처리하는 클래스
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(ex, request);
		if (viewName != null) {
			return getModelAndView(viewName, ex, request);
		}
		else {
			return null;
		}
		
	}
	
	
	protected String determineViewName(Exception ex, HttpServletRequest request) {
		String viewName = null;
		// 특정한 Excpetion 별 세팅이 있는 경우에 findMatchingViewName(this.exceptionMappings, ex)에서 해당 viewName 이 넘어오고 아닌 경우 null 이 넘어온다. 
		if (this.exceptionMappings != null) {
			viewName = findMatchingViewName(this.exceptionMappings, ex);
		}
 
		//viewName 에 null 오고 defaultErrorView 가 존재하면 디폴트 화면으로 viewNmae을 설정하여 ViewResolver 로 보내어진다. 
		if (viewName == null && this.defaultErrorView != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Resolving to default view '" + this.defaultErrorView + "' for exception of type [" + ex.getClass().getName() + "]");
			}
			viewName = this.defaultErrorView;
		}
		return viewName;
	}

	
	private String findMatchingViewName(String exceptionMappings2, Exception ex) {
		return "error";
	}


	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 21.
	 * @Method Name : getModelAndView
	 * @return : ModelAndView
	 */
	protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(viewName);
		if ( this.exceptionAttribute != null) {
			if( logger.isDebugEnabled()) {
				logger.debug("Exposing Exception as Model Attribute '"  + this.exceptionAttribute);
			}
			mv.addObject(this.exceptionAttribute , ex);
			mv.addObject("url" , request.getRequestURL());
		}
		return mv;
	}
}
