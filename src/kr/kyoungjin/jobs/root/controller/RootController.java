package kr.kyoungjin.jobs.root.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.kyoungjin.common.abstractObject.AbstractController;


@Controller
@RequestMapping(value="/")
public class RootController extends AbstractController {
	
	private Logger logger  = LoggerFactory.getLogger(this.getClass());
	
	/** 페이지 이동만을 위한 메소드
	 * @param go
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "go")
	public String go ( @RequestParam("go") String go, HttpServletRequest request , ModelAndView mv) {
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
		return go;
	}


	@RequestMapping(value = "/main")
	public String defaultPage ( Model model, HttpServletRequest request ) {
		return "dashboard";
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
