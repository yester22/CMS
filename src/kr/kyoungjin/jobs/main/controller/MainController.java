package kr.kyoungjin.jobs.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kyoungjin.common.abstractObject.AbstractController;

@Controller
@RequestMapping(value="/main")
public class MainController extends AbstractController {
	private Logger logger  = LoggerFactory.getLogger(this.getClass());
	
	
	
}
