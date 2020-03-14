package kr.kyoungjin.common.util;

//base class
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * 
 * 숫자 관련 유틸리티
 * 
 * @author : CTIS㈜
 * -------------------------- Modification Log ------------------------------------
 * Ver No        Date          Author           Modification
 *  0.1.0.1   2008/09/01       CTIS㈜           Initial Version
 */
public class NumberUtil {
	/**
	 * <pre>
	 * 문자열이 숫자로만 되어있는지 체크한다.
	 * 용례) isNumeric("99999") -> true, isNumeric("99,999") -> false
	 * </pre>
	 * @param	str			문자열
	 * @return 	문자열이 모두 숫자로만 이루어진경우 true
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static boolean isNumeric(String str) {
		try{
			Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	/**
	 * <pre>
	 * 문자열이 통화형태(comma/period 포함)로 되어있는지 체크한다.
	 * 용례) isNumeric("99999") -> true, isNumeric("99,999.99") -> true
	 * </pre>
	 * @param	str			문자열
	 * @return 	문자열이 모두 숫자와 comma, period로만 이루어진경우 true
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static boolean isCurrency(String str) {
		try{
			new DecimalFormat().parse(str.trim());
		} catch (ParseException ex) {
			return false;
		}
		return true;
	}

	/**
	 * <pre>
	 * 통화량 형태의 문자열을 정수로 변환한다.
	 * 주의) 정수범위를 넘어가거나 소수이하 데이타손실
	 * 용례) curToInt("99,999.99") -> 99999
	 * </pre>
	 * @param	arg			통화량형태 문자열
	 * @return 	변환된 정수, 문자열이 불량일경우 -1
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static int curToInt(String arg) {
		try {
			return new DecimalFormat().parse(arg.trim()).intValue();
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return -1;
	}


	/**
	 * <pre>
	 * 통화량 형태의 문자열을 long으로 변환 (데이타손실 가능)
	 * 주의) long범위를 넘어가거나 소수이하 데이타손실
	 * 용례) curToInt("99,999.99") -> 99999
	 * </pre>
	 * @param	arg			통화량형태 문자열
	 * @return 	변환된 long, 문자열이 불량일경우 -1
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static long curToLong(String arg) {
		try {
			return new DecimalFormat().parse(arg.trim()).longValue();
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	/**
	 * 입력받은 값이 null이 아닐경우 int 형태로 변경하고 null인 경우 defValue를 return한다.
	 *
	 * @param src
	 * @param defValue
	 *
	 *  @return int
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static int getIntValue(Object src, int defValue) {
		String v_value;
		if (src != null) {
			try {
				v_value = (String)src;
				return Integer.parseInt(v_value);
			} catch (Exception e) {
				return defValue;
			}
		} else {
			return defValue;
		}
	}

	/**
	 * 입력받은 값이 null이 아닐경우 trim() 메소드를 실행한 후 int 형태로 변경하고 null인 경우 0을 return한다.
	 *
	 * @param src
	 * @param defValue
	 *
	 *  @return int
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static int getIntValue(Object src) {
		if (src != null) {
			try {
				return getIntValue(StringUtil.trim(src,"0"),0);
			} catch (Exception e) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * 입력받은 값이 null이 아닐경우 long 형태로 변경하고 null인 경우 defValue를 return한다.
	 *
	 * @param src
	 * @param defValue
	 *  @return long
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static long getLongValue(Object src, long defValue) {
		String v_value;
		if (src != null) {
			try {
				v_value = (String)src;
				return Long.parseLong(v_value);
			} catch (Exception e) {
				return defValue;
			}
		} else {
			return defValue;
		}
	}

	/**
	 * 입력받은 값이 null이 아닐경우 trim() 메소드를 실행한 후 long 형태로 변경하고 null인 경우 0를 return한다.
	 * @param src
	 * @return
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static long getLongValue(Object src) {
		if (src != null) {
			try {
				return getLongValue(StringUtil.trim(src,"0"),0);
			} catch (Exception e) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * 입력받은 값이 null이 아닐경우 float 형태로 변경하고 null인 경우 defValue를 return한다.
	 *
	 * @param src
	 * @param defValue
	 *  @return float
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static float getFloatValue(Object src, float defValue) {
		String v_value;
		if (src != null) {
			try {
				v_value = (String)src;
				return Float.parseFloat(v_value);
			} catch (Exception e) {
				return defValue;
			}
		} else {
			return defValue;
		}
	}

	/**
	 * 입력받은 값이 null이 아니고 "true"와 같은경우 true를 return하고 그외의 경우 defValue를 return한다.
	 *
	 * @param key
	 * @param defValue
	 *  @return boolean
	 * ----- Logging Comment -------
	 * CTIS㈜ : 2008/09/01
	 */
	public static boolean getBoolValue(String key, boolean defValue) {
		if (key == null) {
			return defValue;
		} else {
			if (key.equalsIgnoreCase("true")) { return true; }
			else { return false; }
		}
	}
}
