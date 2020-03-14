package kr.kyoungjin.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 문자열 관련 유틸 클래스
 * 
 * @author : CTIS㈜
 * -------------------------- Modification Log ------------------------------------
 * Ver No        Date          Author           Modification
 *  0.1.0.1   2008/09/01       CTIS㈜           Initial Version
 */
public class StringUtil {

	/**
	 * <pre>
	 * 문자열이 null인지 체크한다.
	 * </pre>
	 * @param	str			문자열
	 * @return 	null이면 "", 아니면 그대로 리턴
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String checkNull(String str) {
		return (str==null||str.equals(""))? "":str;
	}
	
	/**
	 * <pre>
	 * 문자열이 null인지 체크한다.
	 * </pre>
	 * @param	str			문자열
	 * @return 	null이면 "", 아니면 그대로 리턴
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String checkNull(Object str) {
		
		if( str == null ){
			return "";
		} else {
			return String.valueOf(str);
		}
	}

	/**
	 * <pre>
	 * 문자열이 null이면 대치할 문자열을 리턴한다.
	 * </pre>
	 * @param	str			문자열
	 * @param	replaceStr	대치할 문자열
	 * @return 	null이면 replaceStr, 아니면 그대로 리턴
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String checkNull(Object str, String replaceStr) {
		if( str == null ){
			return replaceStr;
		} else {
			return String.valueOf(str);
		}
	}

	/**
	 * 문자열을 대문자로 변환한다.
	 * @param	str			문자열
	 * @return 	null이면 "", 아니면 대분자로 변환하여 리턴
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String toUpperCase(String str) {
		return (str==null)? "":str.toUpperCase();
	}

	/**
	 * 문자열을 소문자로 변환한다.
	 * @param	str			문자열
	 * @return 	null이면 "", 아니면 소문자로 변환하여 리턴
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String toLowerCase(String str) {
		return (str==null)? "":str.toLowerCase();
	}

	/**
	 * <pre>
	 * 문자열을 한국어(EUC-KR)로 인코딩한다.
	 * 참고) form또는 querystring에서 넘어온 한글은 KSC5601 또는 EUC-KR로 인코딩 해야 한글로 인식함
	 * 용례) StringUtil.toKR(request.getParameter("content")) // US-ASCII
	 * </pre>
	 * @param	str			한글로 인코딩할 문자열
	 * @return 	한글(EUC-KR)로 인코딩된 문자열
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String toKR(String str) {
		if (str == null || str.trim().equals("")) {
			return "";
		}
		try {
			return new String(str.getBytes("ISO-8859-1"), "EUC-KR"); //EUC-KR
		}catch (UnsupportedEncodingException ex) {
			return null;
		}
	}

	/**
	 * <pre>
	 * 문자열을 한국어(MS949)로 인코딩한다.
	 * 참고) form또는 querystring에서 넘어온 한글은 MS949 로 인코딩 해야 한글로 인식함
	 * 용례) StringUtil.toKrMs(request.getParameter("content"))
	 * </pre>
	 * @param	str			한글로 인코딩할 문자열
	 * @return 	한글(EUC-KR)로 인코딩된 문자열
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String toKrMs(String str) {
		if (str == null || str.trim().equals("")) {
			return "";
		}
		try {
			return new String(str.getBytes("ISO-8859-1"), "MS949");
		} catch (UnsupportedEncodingException ex) {
			return null;
		}
	}

	/**
	 * <pre>
	 * 문자열을 ISO-8859-1로 인코딩한다.
	 * </pre>
	 * @param	str			  인코딩할 문자열
	 * @return 	ISO-8859-1    인코딩된 문자열
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String toAscii(String str) {
		if (str == null || str.trim().equals("")) {
			return "";
			//		return str;
		}

		try {
			return new String(str.getBytes("EUC-KR"), "ISO-8859-1");
		} catch (UnsupportedEncodingException ex) {
			return null;
		}
	}

	/**
	 * <pre>
	 * 문자열중에서 A문자열을 B문자열로 모두 대치한다.(대소문자 구별안함)
	 * 용례) StringUtil.replace(ex.getMessage(), "\n", "<br>")
	 * </pre>
	 * @param	original	오리지날 문자열
	 * @param	find		찾고자 하는 문자열
	 * @param	replace		바꾸고자 하는 문자열
	 * @return	대치된 문자열
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String replace(String original, String find, String replace) {
		if(original==null || find==null || replace==null || original.length()<1 || find.length()<1) {
			return original;
		}
		int index=-1, fromIndex=0, tempIndex;
		StringBuffer sb=new StringBuffer();
		while((tempIndex=original.indexOf(find, fromIndex))>=0)
		{
			index=tempIndex;
			sb.append(original.substring(fromIndex, index)).append(replace);
			fromIndex=index+find.length();
		}
		if(sb.length()<1) {
			return original;
		}

		sb.append(original.substring(index+find.length()));
		return sb.toString();
	}

	/**
	 * <pre>
	 * 출력 문자열 길이를 기준으로 원본 스트링 왼쪽부터 특정 문자로 padding 한다.
	 * </pre>
	 * @param src 원본 스트링
	 * @param ch padding 할 문자
	 * @param num 출력 문자열 길이
	 * @return
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String leftPadding(String src, String ch, int num, boolean mode) {
		if(mode){
			return leftPadding(src, ch, num - getStringLength(src));
		}else{
			return leftPadding(src, ch, num);
		}
	}
	public static String leftPadding(String src, String ch, int num) {
		String result = "";

		if(src == null || src.length() >= num) {
			return src;
		}

		int cnt = num - src.length();

		for(int i=0; i < cnt; i++) {
			result += ch;
		}

		return result+src;
	}


	/**
	 * <pre>
	 * 출력 문자열 길이를 기준으로 원본 스트링 오른쪽부터 특정 문자로 padding 한다.
	 * </pre>
	 * @param src 원본 스트링
	 * @param ch padding 할 문자
	 * @param num 출력 문자열 길이
	 * @return
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String RightPadding(String src, String ch, int num) {
		String result = "";

		if(src == null || src.length() >= num) {
			return src;
		}

		int cnt = num - src.length();

		for(int i=0; i < cnt; i++) {
			result += ch;
		}

		return src+result;
	}

	/**
	 * 이력된 문자열을 trim처리한다.
	 *
	 * @param str
	 * @param defValue
	 * @return String
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String trim(String str) {
		return trim(str, "");
	}
	public static String trim(String str, String defValue) {
		if (str == null || "".equals(str)) {
			return defValue;
		} else if (str.trim().length() == 0) {
			return defValue;
		} else {
			return str.trim();
		}
	}

	/**
	 * 입력된 Object를 String을 변환 후 trim 처리한다.
	 * @param src
	 * @param defValue
	 * @return
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String trim(Object src, String defValue) {
		if (src != null) {
			return src.toString().trim();
		} else {
			return defValue;
		}
	}

	/**
	 * 입력된 문자열에서 숫자만을 골라 리턴한다.
	 * 
	 * @param string
	 * @return
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String getNumber(String string){
		if(string == null) {
			return "";
		}

		char[] source = string.toCharArray();
		char[] result = new char[source.length];
		int j = 0;
		for (int i = 0 , y = result.length; i < y ; i++ ){
			if (Character.isDigit(source[i])) {
				result[j++] = source[i];
			}
		}
		return new String(result, 0 , j);
	}

	/**
	 * 글자수를 return 한다. 한글의 경우는 2글자로 return
	 * @param str
	 * @return
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static int getStringLength(String str)
	{
		char ch[] = str.toCharArray();
		int max = ch.length;
		int count = 0;

		for (int i = 0; i < max; i++)
		{
			if (ch[i] > 0x80) {
				count++;
			}
			count++;
		}
		return count;
	}

	/**
	 * 원하는 길이만큰 숫자 앞에 '0'을 채워준다.
	 * @param int 길이, long 숫자값
	 * @return 앞에 '0'이 채워진 스트링
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String fillZero( int length, long lvalue )
	{
		String value = "" +lvalue;
		return fillZero( length, value );
	}
	public static String fillZero( int length, String value )
	{
		if (value == null ) {
			return "";
		}

		char[] cValue = value.toCharArray();
		for (int i = 0; i < cValue.length; i++)
		{
			if( !Character.isDigit( cValue[i] ) ){
				return "";
			}
		}

		String result = value;
		int intLength = getStringLength(result);

		if(intLength == length) {
			return result;
		} else if (intLength > length) {
			return hanSubstr( length, value );
		}

		for (int i = 0; i < length; i++)
		{
			result = "0" + result;
			i = getStringLength(result)-1;
		}
		return result;
	}
	public static String hanSubstr(int length, String value) {
		if (value == null || value.length() == 0) {
			return "";
		}

		int szBytes = value.getBytes().length;

		if (szBytes <= length) {
			return value;
		}

		String result = new String(value.getBytes(), 0, length);
		if( result.equals("") ) {
			result = new String(value.getBytes(), 0, length-1);
		}

		return result;
	}

	/**
	 * 숫자에 콤마(,)를 붙여서 리턴한다.
	 * @param  String
	 * @return #,###,###,###
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String getFormat(String str, String format) {
		if( format == null || format.equals("") ) {
			format = "###,###,###.###";
		}

		String temp = null;

		if (str == null) {
			temp = "0";
		} else {
			double change = Double.valueOf(str).doubleValue();
			DecimalFormat decimal = new DecimalFormat(format);
			temp = decimal.format(change);
		}

		return temp;
	}

	/**
	 * 숫자에 콤마(,)를 붙여서 리턴한다.
	 * @param  String
	 * @return #,###,###,###
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static String getFormat(int istr, String format) {
		String str = Integer.toString(istr);
		if( format == null || format.equals("") ) {
			format = "###,###,###.###";
		}

		String temp = null;

		if (str == null) {
			temp = "0";
		} else {
			double change = Double.valueOf(str).doubleValue();
			DecimalFormat decimal = new DecimalFormat(format);
			temp = decimal.format(change);
		}

		return temp;
	}


	/**
	 * <pre>
	 * 문자열이 영어/숫자인지 체크한다.
	 * </pre>
	 * @param	str			문자열
	 * @return 	영어나 숫자면 true, 아니면 false 리턴
	 */
	public static boolean isAlphanumeric(String str){
		return StringUtils.isAlphanumeric(str);
	}

	/**
	 * 난수를 발생한다.
	 * @return String
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2009/12/01
	 */
	public static String getRandomStr() {
		Random random = new Random();
		long l = random.nextLong();

		return Long.toString(l);
	}

	/**
	 * 
	 * @param var
	 * @param src
	 * @param dist
	 * @return String
	 * @exception
	 */
	public static String getReplace(String inputValue, String src, String dist){
		return inputValue.replaceAll(src, dist);
	}

	public static String[] toArray(Object src){
		return toArray((String)src);
	}
	public static String[] toArray(Object src, String div){
		return toArray((String)src, div);
	}
	/**                                                                                                    
 	 * toArray                                                                                      
 	 * @param String
 	 * @return String[]                                                                         
 	 */ 
	public static String[] toArray(String src){
		String div = "|";
		if(src.indexOf(div) > 0){
			return toArray(src, div);
		}else{
			return toArray(src, ",");
		}
	}
	/**                                                                                                    
 	 * toArray                                                                                      
 	 * @param String
 	 * @param String
 	 * @return String[]                                                                         
 	 */ 
	public static String[] toArray(String src, String div){

		String START_TAG = "[" ; 
		String END_TAG = "]" ; 
		if(div.indexOf(START_TAG) > 0){
			div = START_TAG+div+END_TAG;
		}
		return (String[])src.split(div);
	}

	public static String toUnicode(String s) {
		StringBuffer uni_s = new StringBuffer();
		String temp_s = null;
		final String U = "%u";
		final String OO = "00";
		for( int i=0 ; i < s.length() ; i++){
			temp_s = Integer.toHexString( s.charAt(i) );
			uni_s.append( U).append((temp_s.length()==4 ? temp_s : OO + temp_s ) );
		}
		return uni_s.toString();
	}
	
	public static String getCalcStr(String str, int sLoc, int eLoc) {
		byte[] bystStr;
		String rltStr = "";
		try
		{
			bystStr = str.getBytes();
			rltStr = new String(bystStr, sLoc, eLoc - sLoc);
		}
		catch(Exception e)
		{
			return "";
		}
		return rltStr;
	}    

}
