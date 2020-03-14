package kr.kyoungjin.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.kr.cmc.cc.service.dao.CmcCcCommDao;


/**
 * 
 * 날짜와 시간에 대한 처리 기능을 제공하는 유틸 클래스.
 * 
 * @author : PMNET
 * -------------------------- Modification Log ------------------------------------
 * Ver No        Date          Author           Modification
 *  0.1.0.1   2008/09/01       PMNET           Initial Version
 */

/*
 * 포맷의 형태는 다음을 참조하여 포맷형태를 만들면 된다.
 * <pre>
 *  Symbol   Meaning                 Presentation        Example
 *  ------   -------                 ------------        -------
 *  G        era designator          (Text)              AD
 *  y        year                    (Number)            1996
 *  M        month in year           (Text & Number)     July & 07
 *  d        day in month            (Number)            10
 *  h        hour in am/pm (1~12)    (Number)            12
 *  H        hour in day (0~23)      (Number)            0
 *  m        minute in hour          (Number)            30
 *  s        second in minute        (Number)            55
 *  S        millisecond             (Number)            978
 *  E        day in week             (Text)              Tuesday
 *  D        day in year             (Number)            189
 *  F        day of week in month    (Number)            2 (2nd Wed in July)
 *  w        week in year            (Number)            27
 *  W        week in month           (Number)            2
 *  a        am/pm marker            (Text)              PM
 *  k        hour in day (1~24)      (Number)            24
 *  K        hour in am/pm (0~11)    (Number)            0
 *  z        time zone               (Text)              Pacific Standard Time
 *  '        escape for text         (Delimiter)
 *  ''       single quote            (Literal)           '
 */
@SuppressWarnings("unused")
public class DateUtil {
	protected static Log _log = LogFactory.getLog(DateUtil.class);

	/**
	 * DB 현재 날짜정보를 가져온다.
	 * 
	 * @return 	DB서버의 현재년도월일
	 */
	public static String getCurrentDay()
	{
		String result = "";

		try{
			/*
			CmcCcCommDao cmcCmCommDao = new CmcCcCommDao();
			Map paramMap = new HashMap();

			result = StringUtil.trim(cmcCmCommDao.getYYYYMMDD(paramMap), "");
			 */
		}catch (Exception e) {
			_log.error (StringUtil.toAscii("Exception:"), e);
		}

		return result;

	}

	/**
	 * 현재시간 가져오기
	 * 
	 * @param conn DB 커넥션 핸들
	 * @return String:yyyyMMddHH24MISS
	 * @throws
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getCurrentTime(){
		return getCurrentTime(null);
	}

	public static String getCurrentTime(String format){
		if( format == null ) {
			format = "yyyyMMddHHmmssms";
		}

		return getTime(new Date(), format);
	}

	/**
	 * Calendar.get(year + 1900, month, date)
	 * App서버의 현재년도월일을  리턴한다.
	 * 
	 * @return 	App서버의 현재년도월일
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static int getSysYearDay()
	{
		return CastUtil.toInt(FormatUtil.matchFormat(new Date(), "yyyyMMdd"));
	}

	/**
	 * App서버의 현재년도월일을  리턴한다.
	 * 
	 * @return 	App서버의 현재년도월일
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysYearSecond()
	{
		return FormatUtil.matchFormat(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * App서버의 현재년도를 리턴한다.
	 * 
	 * @return 	App서버의 현재년도
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysYear()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.YEAR));
	}

	/**
	 * App서버의 현재월을 리턴한다.
	 * 
	 * @return 	App서버의 현재월(1 ~ 12)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysMonth()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.MONTH) + 1);
	}

	/**
	 * App서버의 현재일을 리턴한다.
	 * 
	 * @return 	App서버의 현재일(1 ~ 31)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysDay()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * App서버의 현재일을 리턴한다.
	 * 
	 * @return 	App서버의 현재일(1 ~ 31)
	 */
	public static String getSysMonthDay()
	{
		return FormatUtil.matchFormat(new Date(), "MMdd");
	}

	/**
	 * App서버의 현재시를 리턴한다.
	 * 
	 * @return 	App서버의 현재시(0 ~ 23)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysHour()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * App서버의 현재분을 리턴한다.
	 * 
	 * @return 	App서버의 현재분(0 ~ 59)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysMinute()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.MINUTE));
	}

	/**
	 * App서버의 현재초을 리턴한다.
	 * 
	 * @return 	App서버의 현재초(0 ~ 59)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysSecond()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.SECOND));
	}

	/**
	 * 현재 시간을 포맷된 형태로 돌려 준다.<BR>
	 * 예를 들어 "2001/10/05 21:30:23"를 만들고 싶으면 포맷 문자열을 "yyyy'/'MM'/'dd' 'HH':'mm':'ss"
	 * 와 같이 하면 된다.
	 * 
	 * @param format 시간의 표현할 포맷 형식 문자열
	 * @return 포맷형식으로 포맷된 현재시간이 반환된다.
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getTime(String format)
	{
		if(format == null || format.equals("")) {
			return "";
		}

		return getTime(new Date(), format);
	}

	/**
	 * 지정된 날짜를 포맷된 문자열로 반환한다.<BR>
	 * 
	 * @param date java.util.Date의 객체로 지정한 날짜
	 * @param format 반환하고자 하는 문자열의 포맷
	 * @return 포맷형식으로 포맷된 시간이 반환된다.
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getTime(java.util.Date date, String format)
	{
		if(date == null) {
			return "";
		}

		if(format == null || format.equals("")) {
			format = "yyyy'년'MM'월'dd'일 'HH'시'mm'분'dd'초'";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		return formatter.format(date);
	}

	/**
	 * 년 또는 년월 또는 년월일 문자열을 입력받아 Date객체를 리턴한다.<br>
	 * 참고) 년의경우 월일은 1월1일로, 년월의경우 일은 1일로 설정된다.
	 * 
	 * @param	date	"YYYY" 또는 "YYYYMM" 또는 "YYYYMMDD"
	 * @return 	Date 객체(시간은 0시0분0초), Date 객체로 변환할 수 없는 경우 null
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMin(String date) {
		if (date==null || date.equals("") || !NumberUtil.isNumeric(date)) {
			return null;
		}
		int len = date.length();
		if (len<4 || len>8) {
			return null;
		}
		return getDate(date.substring(0, 4), len>=6? date.substring(4, 6):"1", len>=8? date.substring(6, 8):"1", "0", "0", "0");
	}


	/**
	 * 년월일을 입력받아 0시0분0초의 Date객체를 리턴한다.
	 * 
	 * @param	year	년
	 * @param	month	월("1" ~ "12")
	 * @param	day		일("1" ~ "31")
	 * @return 	Date 객체(시간은 0시0분0초), 년월일 중 값이 없는 경우 null
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMin(String year, String month, String day) {
		return getDate(year, month, day, "0", "0", "0");
	}

	/**
	 * 년월일을 입력받아 0시0분0초의 Date객체를 리턴한다.
	 * 
	 * @param	year	년
	 * @param	month	월(1 ~ 12)
	 * @param	day		일(1 ~ 31)
	 * @return 	Date 객체(시간은 0시0분0초)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMin(int year, int month, int day) {
		return getDate(year, month, day, 0, 0, 0);
	}

	/**
	 * 년 또는 년월 또는 년월일 문자열을 입력받아 Date객체를 리턴한다.<br>
	 * 참고) 년의경우 월일은 1월1일로, 년월의경우 일은 1일로 설정된다.
	 * 
	 * @param	date	"YYYY" 또는 "YYYYMM" 또는 "YYYYMMDD"
	 * @return 	Date 객체(시간은 23시59분59초), Date 객체로 변환할 수 없는 경우 null
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMax(String date) {
		if (date==null || date.equals("") || !NumberUtil.isNumeric(date)) {
			return null;
		}
		int len = date.length();
		if (len<4 || len>8) {
			return null;
		}
		return getDate(date.substring(0, 4), len>=6? date.substring(4, 6):"1", len>=8? date.substring(6, 8):"1", "23", "59", "59");
	}

	/**
	 * 년월일을 입력받아 23시59분59초의 Date객체를 리턴한다.
	 * 
	 * @param	year	년
	 * @param	month	월("1" ~ "12")
	 * @param	day		일("1" ~ "31")
	 * @return 	Date 객체(시간은 23시59분59초), 년월일 중 값이 없는 경우 null
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMax(String year, String month, String day) {
		return getDate(year, month, day, "23", "59", "59");
	}

	/**
	 * 년월일을 입력받아 23시59분59초의 Date객체를 리턴한다.
	 * 
	 * @param	year	년
	 * @param	month	월(1 ~ 12)
	 * @param	day		일(1 ~ 31)
	 * @return 	Date 객체(시간은 23시59분59초)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMax(int year, int month, int day) {
		return getDate(year, month, day, 23, 59, 59);
	}

	/**
	 * 년월일시분초를 입력받아 Date객체를 리턴한다.
	 * 
	 * @param	year	년
	 * @param	month	월("1" ~ "12")
	 * @param	day		일("1" ~ "31")
	 * @param	hour	시("0" ~ "23")
	 * @param	minute	분("0" ~ "59")
	 * @param	second	초("0" ~ "59")
	 * @return 	Date 객체, 년월일시분초 중 값이 없는 경우 null
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDate(String year, String month, String day, String hour, String minute, String second) {
		if (year.equals("") || month.equals("") || day.equals("") || hour.equals("") || minute.equals("") || second.equals("")) {
			return null;
		}
		return getDate(CastUtil.toInt(year), CastUtil.toInt(month), CastUtil.toInt(day), CastUtil.toInt(hour), CastUtil.toInt(minute), CastUtil.toInt(second));
	}

	/**
	 * 년월일시분초를 입력받아 Date객체를 리턴한다.
	 * 
	 * @param	year	년
	 * @param	month	월(1 ~ 12)
	 * @param	day		일(1 ~ 31)
	 * @param	hour	시(0 ~ 23)
	 * @param	minute	분(0 ~ 59)
	 * @param	second	초(0 ~ 59)
	 * @return 	Date 객체
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day, hour, minute, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}


	/**
	 * <pre>년월일시분초 스트링을 입력받아 Date객체를 리턴한다.</pre>
	 * 
	 * @param	datetime yyyyMMddHHmmss
	 * @return 	Date 객체, 값이 없는 경우 null
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDate(String datetime) throws Exception {
		String year, month, day, hour, minute, second;

		if (datetime==null ||
				"".equals(datetime) ||
				datetime.length() != 14) {
			return null;
		}

		year = datetime.substring(0, 4);
		month = datetime.substring(4, 6);
		day = datetime.substring(6, 8);
		hour = datetime.substring(8, 10);
		minute = datetime.substring(10, 12);
		second = datetime.substring(12, 14);

		return getDate(CastUtil.toInt(year), CastUtil.toInt(month), CastUtil.toInt(day), CastUtil.toInt(hour), CastUtil.toInt(minute), CastUtil.toInt(second));
	}

	/**
	 * <pre>
	 * 두 일시의 차를 시간차를 구한다.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return 두 일시의 차를 시간차
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static int getTimeInterval(Date d1, Date d2) throws Exception {
		int interval = 0;

		interval = (int)((d1.getTime() - d2.getTime()) / (60 * 60 * 1000));

		return interval;

	}

	/**
	 * Method Name : getTimeCalc<br>
	 * 이 메소드는 시간을 더한다.<br>
	 *
	 * @param strTime
	 * @param value
	 * @param nMode
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getTimeCalc(String strTime, int value, int nMode)
	{
		String strRet = "";
		int hh = 0;
		int mm = 0;

		if (strTime == null || strTime.length() != 4) {
			return "";
		}

		hh = Integer.parseInt(strTime.substring(0,2));
		mm = Integer.parseInt(strTime.substring(2));
		switch(nMode)
		{
		case 1:
			hh += value;
			break;
		case 2:
			mm += value;
			break;
		default:
		}

		if (mm >= 60)
		{
			hh += 1;
			mm -= 60;
		}

		if (hh >= 24)
		{
			hh -= 24;
		}

		strRet = hh + "" + mm;
		return strRet;
	}


	/**
	 * <pre>
	 * 두 일시의 차를 분차를 구한다.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return 두 일시의 분의 시간차
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static int getMinuteInterval(Date d1, Date d2) throws Exception {
		int interval = 0;

		interval = (int)((d1.getTime() - d2.getTime()) / (60 * 1000));

		return interval;

	}

	/**
	 * <pre>
	 * 두 날짜의 차를 구한다.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return 두 날짜의 일수차
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static int getDateInterval(Date d1, Date d2) throws Exception {
		int interval = 0;

		interval = (int)((d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000));

		return interval;

	}

	/**
	 * <pre>
	 * 두 날짜의 월수 차를 구한다.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return 두 날짜의 월수차
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static int getMonthInterval(Date d1, Date d2) throws Exception {
		int interval = 0;

		// 비교1 년도, 달
		int yyyy_d1 = CastUtil.toInt(getTime(d1, "yyyy"));
		int mm_d1 = CastUtil.toInt(getTime(d1, "MM"));

		// 비교2 년도, 달
		int yyyy_d2 = CastUtil.toInt(getTime(d2, "yyyy"));
		int mm_d2 = CastUtil.toInt(getTime(d2, "MM"));

		// 년도에 따른 달수 계산..
		int _month = ( yyyy_d2 - yyyy_d1 ) * 12;

		// 결과값
		interval = ( mm_d2 - mm_d1 ) + _month;

		return interval;
	}


	/**
	 * <pre>
	 * Date 객체에 일정기간을 증감하여 리턴한다.
	 * </pre>
	 * @param	date	원래 Date 객체
	 * @param	ymd		증감필드(1:년, 2:월, 3:일, 4:시, 5:분)
	 * @param	amt		증감기간(양수:미래로~, 음수:과거로~)
	 * @return 	증감된 Date 객체
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateAdd(Date date, int ymd, int amt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(ymd==1? Calendar.YEAR:ymd==2? Calendar.MONTH:ymd==3? Calendar.DAY_OF_MONTH:ymd==4? Calendar.HOUR:Calendar.MINUTE, amt);
		return cal.getTime();
	}

	/**
	 * <pre>
	 * Date 객체에 일정기간을 증감하여 리턴한다.
	 * </pre>
	 * @param	ymd		증감필드(1:년, 2:월, 3:일, 4:시, 5:분)
	 * @param	amt		증감기간(양수:미래로~, 음수:과거로~)
	 * @return 	증감된 Date 객체
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateAdd(int ymd, int amt) {
		return getDateAdd(new Date(), ymd, amt);
	}

	/**
	 * 특정일자에 년,월,일을 가감한다.
	 * 가감구분값
	 *    Y : 년을 가감
	 *    M : 월을 가감
	 *    D : 일을 가감
	 * 
	 * @param strDate 일자
	 * @param intDate 가감수
	 * @param strymd 가감구분
	 * @return 가감된 날짜
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getDateCalc( String strDate, int intDate, String strymd )
	{
		int yyyy, mm, dd;
		strDate = StringUtil.getNumber(strDate);	//숫자아닌 다른 값 들어올 경우 처리
		String strRet = "";

		if (strDate.length() == 8)
		{
			yyyy   = Integer.parseInt(strDate.substring(0,4));
			mm     = Integer.parseInt(strDate.substring(4,6));
			dd     = Integer.parseInt(strDate.substring(6,8));

			Calendar cal = Calendar.getInstance();
			cal.set(yyyy,mm-1,dd);
			if (strymd.equals("Y")){
				cal.add(Calendar.YEAR, + intDate);
			}else if (strymd.equals("M")){
				cal.add(Calendar.MONTH, + intDate);
			}else if (strymd.equals("D")){
				cal.add(Calendar.DATE, + intDate);
			}
			Date sdate = cal.getTime();
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			strRet = date.format(sdate);
		}

		return strRet;
	}

	/**
	 * 주어진 날짜에 '-'와 ':'를 추가 또는 제거
	 * @param datetime
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String DateTime(String datetime)
	{
		if(datetime.length() == 14){
			return StringToDateTime(datetime);
		}
		else
		{
			return DateTimeToString(datetime);
		}
	}
	public static String StringToDateTime(String datetime)
	{
		if((datetime.length()<=0) || (datetime.length()!=14)) {
			return datetime;
		}
		return datetime.substring(0, 4) + "-" + datetime.substring(4, 6) + "-" + datetime.substring(6, 8) +
		" " + datetime.substring(8, 10) + ":" + datetime.substring(10, 12) + ":" + datetime.substring(12);
	}
	public static String DateTimeToString(String datetime)
	{
		if((datetime.length()<=0) || (datetime.length()!=19)) {
			return datetime;
		}
		return datetime.substring(0, 4) + datetime.substring(5, 7) + datetime.substring(8, 10) +
		datetime.substring(11, 13) + datetime.substring(14, 16) + datetime.substring(17);
	}

	/**
	 * 주어진 날짜에 '-'를 추가 또는 제거
	 * @param date
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String Date(String date){
		if( date == null ) {
			return "";
		}
		if(date.length() == 8){
			return StringToDate(date);
		}else{
			return DateToString(date);
		}
	}

	/**
	 * 스트링문자열을 날짜형식으로 변환하여 리턴한다.
	 * 
	 * @param date
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String StringToDate(String date){
		if( date == null ) {
			return "";
		}
		if((date.length()<=0) || (date.length()!=8)) {
			return date;
		}
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
	}

	/**
	 * 날자형식의 문자열에서 '-'를 제거하여 리턴한다.
	 * 
	 * @param date
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String DateToString(String date){
		if( date == null ) {
			return "";
		}
		if((date.length()<=0) || (date.length()!=10)) {
			return date;
		}
		return date.substring(0, 4) + date.substring(5, 7) + date.substring(8);
	}

	/**
	 * <pre>
	 * 현재날짜에 대한 요일명을 얻는다.
	 * </pre>
	 * @param date 예:20040528
	 * @return 요일명
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getDayOfWeek(String date) {
		String result = "";

		if(date == null || date.length() < 8) {
			return result;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.getDateMin(date.substring(0, 8)));

		switch(c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY :
			result = "일";
			break;
		case Calendar.MONDAY :
			result = "월";
			break;
		case Calendar.TUESDAY :
			result = "화";
			break;
		case Calendar.WEDNESDAY :
			result = "수";
			break;
		case Calendar.THURSDAY :
			result = "목";
			break;
		case Calendar.FRIDAY :
			result = "금";
			break;
		case Calendar.SATURDAY :
			result = "토";
			break;

		}

		return result;
	}

	/**
	 * <pre>
	 * 현재날짜에 대한 요일의 숫자를 얻는다.
	 * </pre>
	 * @param date 예:20040528
	 * @return 요일번호
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static int getIntDayOfWeek(String date) {
		int result = 0;

		if(date == null || date.length() < 8) {
			return result;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.getDateMin(date.substring(0, 8)));

		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Date형 객체와 DateFormat을 스트링으로 넘겨주면 날짜를 반환하는 메서드
	 * @param dDate
	 * @param strDateFormat
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSystemDate(Date dDate, String strDateFormat){
		if (dDate == null) {
			return "";
		}
		SimpleDateFormat date = new SimpleDateFormat(strDateFormat);
		String strDate = date.format(dDate);
		return strDate;
	}

	/**
	 * 주어진 년, 월의 마지막 일자를 구한다
	 * 예) 2003, 8 이면 31을 리턴
	 * @param year
	 * @param month
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getLastDay(int year, int month){
		Calendar cal = Calendar.getInstance();
		cal.set(year,month,1);
		cal.add(Calendar.DATE, - 1);
		Date ydate = cal.getTime();
		SimpleDateFormat date = new SimpleDateFormat("dd");
		String strDate = date.format(ydate);

		return strDate;
	}

	/**
	 * 시간 00~24시
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String[] getHourList(){
		String[] list = new String[24];
		for(int i=0; i<24; i++){
			if(i<10){
				list[i] = "0" + i;
			}else{
				list[i] = "" + i;
			}
		}
		return list;
	}

	/**
	 * 분 00~50분 (10분단위)
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String[] getMinuteList(){
		String[] list = new String[6];
		for(int i=0; i<6; i++){
			if(i<1){
				list[i] = "0" + i;
			}else{
				list[i] = "" + (i*10);
			}
		}
		return list;
	}

	/**
	 * 주어진 초를 "시간:분:초"로 변환하여 리턴
	 * @param Second
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String toHourMinSec(int Second){
		String strHour = "" + (Second / 3600);
		String strMin = "" + ((Second % 3600) / 60);
		String strSec = "" + ((Second % 3600) % 60);

		if( strMin.length() == 1 ) {
			strMin = "0" + strMin;
		}
		if( strSec.length() == 1 ) {
			strSec = "0" + strSec;
		}

		return strHour + ":" + strMin + ":" + strSec;
	}

	/**
	 * 주어진 초를 "시간:분:초"로 변환하여 리턴
	 * @param Second
	 * @return
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String toHourMinSec(String Second){
		if (Second == null) {
			return "";
		}
		String hValue=null;
		String mValue=null;
		String sValue=null;
		String hmsValue=null;
		int hCal = 0;
		int mCal = 0;
		int mmCal = 0;
		int sCal = 0;
		int hmsCal=0;
		int hms = Integer.parseInt(Second);
		if(hms >= 3600){
			hCal = hms/3600;
			mCal = hms%3600;
			if(mCal >= 60){
				mmCal = mCal;
				mCal = mCal/60;
				sCal = mmCal%60;
			}else{
				sCal = mCal;
				mCal = 0;
			}
		}else if(hms >= 60){
			mCal =hms/60;
			sCal = hms%60;
		}else{
			sCal=hms;
		}
		hValue = String.valueOf(hCal).length() == 1 ? "0" + hCal : String.valueOf(hCal);
		mValue = String.valueOf(mCal).length() == 1 ? "0" + mCal : String.valueOf(mCal);
		sValue = String.valueOf(sCal).length() == 1 ? "0" + sCal : String.valueOf(sCal) ;
		hmsValue = hValue + " : " + mValue + " : " + sValue ;
		return hmsValue;
	}




	/**
	 * 시스템 처리시간 모니터링
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	private static SimpleDateFormat logDateFormat	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DecimalFormat df = new DecimalFormat(",###");
	private static String monitorName 		= "";	// 모니터링명
	private static long currentTimeMillis 	= 0;	// 시스템 처리시간 모니터링
	private static double diffTime 			= 0;	// 시스템 처리시간

	/**
	 * 현재 작업명 입력
	 * @param name
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static void setCurrentTimeMillis( String name )
	{
		currentTimeMillis = System.currentTimeMillis();
		monitorName = name;
		//System.out.println( "[ currentTimeMillis ] ["+ logDateFormat.format( new Date(currentTimeMillis) )+"] :: ["+ monitorName +"]" );
	}
}
