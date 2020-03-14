package kr.kyoungjin.common.util;

//base class
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//user class

import kr.kyoungjin.common.util.CastUtil;

/**
 * 
 * ���ڿ� ���� ������ �������� ����� �ִ� ��ƿ Ŭ����
 * 
 * @author : CTIS��
 * -------------------------- Modification Log ------------------------------------
 * Ver No        Date          Author           Modification
 *  0.1.0.1   2008/09/01       CTIS��           Initial Version
 */
public class FormatUtil {
	/**
	 * <pre>
	 * ���ڿ��� �־��� �������� ��ġ��Ų��.
	 * ���) matchFormat("20010101", "####/##/##") -> "2001/01/01"
	 *       matchFormat("12345678", "##/## : ##") -> "12/34 : 56"
	 * </pre>
	 * @param	str			���ڿ�
	 * @param	format		��� ��������('#'���ڿ� �� ���ڰ� ��ġ, �׿� ���ڴ� �״�� ǥ��)
	 * @return 	�������� ��ȯ�� ���ڿ�
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 */ 
	public static String matchFormat(String str, String format) {
		if(str == null || str.length() == 0 ) return "";
		int len = format.length(); 
		char[] result = new char[len];
		for(int i=0,j=0; i<len; i++,j++) {
			if(format.charAt(i)=='#') {
				try {
					result[i]= str.charAt(j);
				}catch(StringIndexOutOfBoundsException e) {
					result[i]= '\u0000';
				}
			} else {
				result[i]= format.charAt(i);
				j--;
			}
		}
		return new String(result);
	}
	
	/**
	 * <pre>
	 * ���������� ���ڿ����� ���ڿ��� �̾Ƴ���.
	 * ���) releaseFormat("2001/01/01", "####/##/##") -> "20010101"
	 *       releaseFormat("123/456", "###/#########") -> "123456"
	 * </pre>
	 * @param	str			���������� ���ڿ�
	 * @param	format		���ڿ��� �̾Ƴ� ��������('#'���� ��ġ�� �ִ� �� ���ڸ� �̾ƿ�)
	 * @return 	���ڿ�
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 */
	public static String releaseFormat(String str, String format) {
		if(str == null || str.length() == 0 ) return str;
		int len = format.length();
		char[] result = new char[len];
		for(int i=0,j=0; i<len; i++,j++) {
			if(format.charAt(i)=='#') {
				try {
					result[j] = str.charAt(i);
				}catch(StringIndexOutOfBoundsException e) {
					result[j]= '\u0000';
				}
			} else {
				j--;
			}
		}
		return (new String(result)).trim();
	}

	/**
	 * <pre>
	 * Date���� �־��� ��¥ �������� ��ȯ�Ѵ�.
	 * ���) matchFormat(new Date(), "yyyy/MM/dd a hh:mm:ss") -> "2001/09/18 ���� 03:31:32"
	 * </pre>
	 * @param	dt			Date ��ü
	 * @param	format		��ȯ ��¥���� (��������� Java API SimpleDateFormat ��ü ����)
	 * @return 	��ȯ�� ���ڿ�, ������ �ҷ��̸� "invalid format"
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 */
	public static String matchFormat(Date dt, String format) {
		if (dt == null) return "";
		try {
			return new SimpleDateFormat(format).format(dt);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "invalid format";
 	}
 	
	/**
	 * <pre>
	 * String���� ��ȭ�������� ���ڿ��� ��ȯ�Ѵ�.
	 * </pre>
	 * @param	arg			String �Ǵ� double
	 * @return 	��ȭ���·� ��ȭ�� ���ڿ�
	 * @throws	NumberFormatException	���ڿ��� �ҷ��϶�(����,period,E �̿��� ����)
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 */
 	public static String toCurFormat(String arg) { return toCurFormat(CastUtil.toDbl(arg)); }
	/**
	 * <pre>
	 * double���� ��ȭ�������� ���ڿ��� ��ȯ�Ѵ�.
	 * ����) �Ҽ����� �ݿø� ��
	 * </pre>
	 * @param	arg			String �Ǵ� double
	 * @return 	��ȭ���·� ��ȭ�� ���ڿ�
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 */
	public static String toCurFormat(double arg) { return new DecimalFormat("###,##0").format(arg); }
	
	/**
	 * <pre>
	 * String���� �Ҽ��� ���� "0.00" ������ ���ڿ��� ��ȯ�Ѵ�.
	 * ���) toPointFormat("35000", 1) -> "35000.1"
	 *      toPointFormat("35000", 234) -> "35000.234"
	 * </pre>
	 * @param	arg			String
	 * @param	pointDigit	�Ҽ��� ���� �ڸ���
	 * @return 	�Ҽ��� ���� ���·� ��ȭ�� ���ڿ�
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 **/
	public static String toPointFormat(String arg, int pointDigit) { return toPointFormat(CastUtil.toDbl(arg), pointDigit); }
	
	/**
	 * <pre>
	 * double���� �Ҽ��� ���� "0.00" ������  ���ڿ��� ��ȯ�Ѵ�.
	 * ���) toPointFormat("35000.0", 123) -> "35000.123"
	 *      toPointFormat("35000.0", 432) -> "35000.432"* </pre>
	 * @param	arg			double
	 * @param	pointDigit	�Ҽ��� ���� �ڸ���
	 * @return 	�Ҽ��� ���� ���·� ��ȭ�� ���ڿ�
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 **/
	public static String toPointFormat(double arg, int pointDigit) {
		if (pointDigit<=0) {
			return new DecimalFormat("##0").format(arg);
		} else {
			String formatStr = "##0.";
			//for (int i=0; i<pointDigit; i++) formatStr += "0";
			formatStr +=pointDigit;
			return new DecimalFormat(formatStr).format(arg);
		}
	}
	
	/**
	 * <pre>
	 * ���ڿ��� Date������ ��ȯ�Ѵ�.
	 * </pre>
	 * @param	str			"YYYYMMDD", "YYYY/MM/DD", "YYYY-MM-DD" ������ ���ڿ�
	 * @return 	��ȯ�� Date��, �ҷ� ���ڿ��̸� null ����
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 */
	public static Date toDate(String str) {
		if(str == null || str.length() == 0 ) return null;
		String format = (str.length()==8)? "yyyyMMdd":(str.indexOf("-")>=0)? "yyyy-MM-dd":"yyyy/MM/dd";
		try {
			return new SimpleDateFormat(format).parse(str);
		} catch (ParseException ex) { return null; }
	}

	/**
	 * ���� ��¥�� ������ pattern���� ������ ���� return �Ѵ�.
	 *
	 * @param pattern 
	 *  @return String
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 */
	public static String getDate(String pattern) { 
		/*
		  <pattern>
		  G: Era designator
		  y: Year
		  M: Month in year
		  w: Week in year
		  W: Week in month
		  D: Day in year
		  d: Day in month
		  F: Day of week in month
		  E: Day in week
		  a: Am/pm marker
		  H: Hour in day (0-23)
		  k: Hour in day (1-24)
		  K: Hour in am/pm (0-11)
		  h: Hour in am/pm (1-12)
		  m: Minute in hour
		  s: Second in minute
		  S: Millisecond
		  z: Time zone
		  Z: Time zone
		*/

		SimpleDateFormat format = new SimpleDateFormat(pattern);
	    Date curDate = new Date();
	    return format.format(curDate);
	}

	/**
	 * <pre>
	 * �ش� ��Ʈ���� �н����� ���·� �������ش�.
	 * ���) releaseFormat("12345", "*") -> "*****"
	 * </pre>
	 * @param	str			������ ���ڿ�
	 * @param	format		���ڿ��� ������ ���� 
	 * @return 	����ȹ��ڿ�
	 * ----- Logging Comment -------
	 * CTIS�� : 2008/09/01 
	 */
	public static String toPassword(String str, String format) {
		if(str == null || str.length() == 0 ) return "";
		if(format.length() != 1) return str;
		String result = "";
		for(int i=0; i<str.length(); i++) {
			result += format;
		}
		return result;
	}
}
