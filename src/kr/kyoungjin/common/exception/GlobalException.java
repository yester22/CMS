package kr.kyoungjin.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @since 2020. 3. 21.
 * @author yeste
 * <PRE>
 * -------------------------
 * 媛쒖젙�씠�젰
 * 2020. 3. 21. yeste : 理쒖큹�옉�꽦
 * </PRE>
 */
public class GlobalException implements HandlerExceptionResolver {

	
	private String exceptionAttribute = "exception";
	private String exceptionMappings = "";
	private String defaultErrorView = "error";
	
	
	private Logger logger  = LoggerFactory.getLogger(GlobalException.class);
	
	/**
	 *�삁�쇅 泥섎━�븯�뒗 �겢�옒�뒪
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
		// �듅�젙�븳 Excpetion 蹂� �꽭�똿�씠 �엳�뒗 寃쎌슦�뿉 findMatchingViewName(this.exceptionMappings, ex)�뿉�꽌 �빐�떦 viewName �씠 �꽆�뼱�삤怨� �븘�땶 寃쎌슦 null �씠 �꽆�뼱�삩�떎. 
		if (this.exceptionMappings != null) {
			viewName = findMatchingViewName(this.exceptionMappings, ex);
		}
 
		//viewName �뿉 null �삤怨� defaultErrorView 媛� 議댁옱�븯硫� �뵒�뤃�듃 �솕硫댁쑝濡� viewNmae�쓣 �꽕�젙�븯�뿬 ViewResolver 濡� 蹂대궡�뼱吏꾨떎. 
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
