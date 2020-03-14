package kr.kyoungjin.common.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import kr.kyoungjin.dataobject.dao.MessageDao;

/**
 * 서버 시작시 국제화 코드 텍스트를 가져와서 XML로 저장하는 서블릿
 * @author 정경진
 *
 */
@SuppressWarnings("serial")
public class I18nServlet extends HttpServlet {
	
	@Autowired
	private MessageDao messageDao;
	
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	@Override  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		 
	 }  
}
