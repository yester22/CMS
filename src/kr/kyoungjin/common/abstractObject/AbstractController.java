package kr.kyoungjin.common.abstractObject;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

public abstract class AbstractController {
	
	protected Log logger = LogFactory.getLog(AbstractController.class );
	
	
	/**
	 * this is for ajaxSumit (jquery.form.js) 
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(MultipartFile.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				logger.debug("initBinder MultipartFile.class: {}; set null;");
				setValue(null);
			}
		});
	}

	
}
