package kr.kyoungjin.jobs.system.code.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.jobs.member.service.IMemberService;
import kr.kyoungjin.jobs.system.code.service.ICodeService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/code")
public class CodeController {

	@Autowired
	private ICodeService codeService;

	private Log logger = LogFactory.getLog(CodeController.class);
	
	@RequestMapping(value = "/form")
	public ModelAndView list (@RequestParam Map<String,Object> param ) {
		ModelAndView mav = new ModelAndView();

		List<Map<String,Object>> list = codeService.getCodeList(param);
		mav.addObject("list", list);
		mav.setViewName("hello");
		return mav;
	}
	
	
}
