package kr.kyoungjin.common.util;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * 
 * @author 
 * @since 2011. 1. 1.
 * @version 1.0
 * @see 
 *
 * <pre>
 *   
 *    날짜		   작성자      변경내용
 *  -----------  --------   --------------------------
 *   2012. 09. 03.   김명섭    에러핸들러 신규  
 * 
 * </pre>
 */
public class MessageHandler {
	private static MessageSourceAccessor messageAccessor;
	
	/**
	 * 
	 * @param messageSourceAccessor
	 * @return void
	 * @exception  
	 */
	public void setMessageAccessor(MessageSourceAccessor messageSourceAccessor)
	{
		MessageHandler.messageAccessor = messageSourceAccessor;
	}
	
	/**
	 * 
	 * @param key
	 * @return String
	 * @exception  
	 */
	public static String getMessage(String key)
	{
		return messageAccessor.getMessage(key, "ko");
	}
	
	
	/**
	 * 
	 * @param key
	 * @param obj
	 * @return String
	 * @exception  
	 */
	public static String getMessage(String key, Object[] obj)
	{
		return messageAccessor.getMessage(key, obj, "ko");
	}
}
