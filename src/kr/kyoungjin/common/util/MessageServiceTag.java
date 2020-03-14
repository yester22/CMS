/**
 * 
 */
package kr.kyoungjin.common.util;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import kr.kyoungjin.dataobject.vo.MessageVo;
import kr.kyoungjin.jobs.system.message.service.IMessageService;

/**
 * @author 정경진
 *
 */
public class MessageServiceTag extends RequestContextAwareTag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static IMessageService messageService;
	private static MessageVo msg;	
	private String msgCode;
	
	public void setMsgCode ( String msgCode ) {
		this.msgCode = msgCode;
	}

	@Override
	protected int doStartTagInternal() throws Exception {

		  JspWriter out = pageContext.getOut();  
		    try{
		    	if ( messageService == null ) {
		    		 messageService = (IMessageService) getRequestContext().getWebApplicationContext().getBean(IMessageService.class);
		    	}
				
		    	msg = ( msg == null ) ? new MessageVo() : msg;
		    	msg.setMsgKey(msgCode);
		    	msg = messageService.view( msg );
		    	String writeStr = (( msg != null ) ? msg.getMessage() : "" ) ;
		    	
		    	out.print( writeStr  );
		    }catch(Exception e){
		    	e.printStackTrace();
		    	try {
		    		
					out.print( "" );
				} catch (IOException e1) {}

		    	e.printStackTrace();
		    	
		    }  
		
    	return SKIP_BODY;
	}  
	
}
