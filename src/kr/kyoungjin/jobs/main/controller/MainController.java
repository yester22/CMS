package kr.kyoungjin.jobs.main.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kyoungjin.common.abstractObject.AbstractController;

@Controller
@RequestMapping(value="/main")
public class MainController extends AbstractController {
	private Log logger = LogFactory.getLog(MainController.class);
	
	
	
}
