package kr.kyoungjin.jobs.root.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.kyoungjin.common.abstractObject.AbstractController;


@Controller
@RequestMapping(value="/")
public class RootController extends AbstractController {
	private Log logger = LogFactory.getLog(RootController.class);
	
	/** 페이지 이동만을 위한 메소드
	 * @param go
	 * @return
	 */
	@RequestMapping(value = "go")
	public ModelAndView go ( @RequestParam("go") String go, HttpServletRequest request ) {
		ModelAndView mv = new ModelAndView();
		
		logger.debug(this.getClass() + "'s go method go = " + go );
		
		try {
			//go 이외의 파라미터들이 포함되어 있을경우  attribute에 등록하여 전송한다
			Enumeration<String> params = request.getParameterNames();
			if ( params != null && params.hasMoreElements() ) {
				String element = "";
				while ( params.hasMoreElements() ) {
					element = 	params.nextElement();				
					mv.addObject(element, request.getParameter(element) ); 
				}
			}
			mv.setViewName(go);
		} catch ( Exception e ) { 
			e.printStackTrace();
			mv.setViewName("/error/error");
			mv.addObject("ERROR_MSG", e.getMessage());
		}
		return mv;
	}


	@RequestMapping(value = "/main")
	public String defaultPage ( Model model, HttpServletRequest request ) {
		return "default";
	}

	@RequestMapping(value = "/hello")
	public String hello ( Model model, HttpServletRequest request ) {
		return "hello";
	}
	
	@RequestMapping(value = "/login")
	public String login ( Model model, HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		if ( request.getSession().getAttribute("USER_INFO") != null)  {
			response.sendRedirect("/main");
		}
		return "login";
	}
	
	/** 로그아웃 
	 */
	@RequestMapping(value = "/logout")
	public void logout ( HttpServletRequest request , HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			session.invalidate();
			response.sendRedirect("/");
		} catch ( Exception e ) { }
	}
	
}
