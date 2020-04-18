package kr.kyoungjin.jobs.board.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.kyoungjin.common.abstractObject.AbstractController;
import kr.kyoungjin.common.abstractObject.JSONResult;
import kr.kyoungjin.jobs.board.service.BoardMngService;
import kr.kyoungjin.jobs.system.message.service.IMessageService;
import net.sf.json.JSONObject;

@CrossOrigin("*")
@RestController
public class BoardMngController extends AbstractController {
	private Logger logger  = LoggerFactory.getLogger(BoardMngController.class);
	
	@Autowired
	private BoardMngService BoardMngService;
	
	@Autowired
	private IMessageService messageService;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 18.
	 * @Method Name : excelRetrieve
	 * @return : JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/boardMngList", method = RequestMethod.POST)
	public JSONObject boardMngList (@RequestParam Map<String,Object> params) {
		JSONObject result = new JSONObject();
		
		try {
			Map<String,Object> rtnList = BoardMngService.getBoardMngList(params);
			if ( rtnList.get("LIST") != null ) {
				result.put(JSONResult.LIST,   rtnList.get("LIST"));	
			}
			if ( rtnList.get("COUNT") != null ) {
				result.put(JSONResult.COUNT,   rtnList.get("COUNT"));	
			}
			result.put(JSONResult.RESULT, JSONResult.OK);
		} catch( Exception e ) {
			e.printStackTrace();
			result.put(JSONResult.RESULT, JSONResult.ERROR);
		}
		return result;
	}
}
