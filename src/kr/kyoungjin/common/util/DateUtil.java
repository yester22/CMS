package kr.kyoungjin.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.kr.cmc.cc.service.dao.CmcCcCommDao;


/**
 * 
 * �궇吏쒖� �떆媛꾩뿉 ���븳 泥섎━ 湲곕뒫�쓣 �젣怨듯븯�뒗 �쑀�떥 �겢�옒�뒪.
 * 
 * @author : PMNET
 * -------------------------- Modification Log ------------------------------------
 * Ver No        Date          Author           Modification
 *  0.1.0.1   2008/09/01       PMNET           Initial Version
 */

/*
 * �룷留룹쓽 �삎�깭�뒗 �떎�쓬�쓣 李몄“�븯�뿬 �룷留룻삎�깭瑜� 留뚮뱾硫� �맂�떎.
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
	private static Logger _log  = LoggerFactory.getLogger(DateUtil.class);
	/**
	 * DB �쁽�옱 �궇吏쒖젙蹂대�� 媛��졇�삩�떎.
	 * 
	 * @return 	DB�꽌踰꾩쓽 �쁽�옱�뀈�룄�썡�씪
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
	 * �쁽�옱�떆媛� 媛��졇�삤湲�
	 * 
	 * @param conn DB 而ㅻ꽖�뀡 �빖�뱾
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
	 * App�꽌踰꾩쓽 �쁽�옱�뀈�룄�썡�씪�쓣  由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱�뀈�룄�썡�씪
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static int getSysYearDay()
	{
		return CastUtil.toInt(FormatUtil.matchFormat(new Date(), "yyyyMMdd"));
	}

	/**
	 * App�꽌踰꾩쓽 �쁽�옱�뀈�룄�썡�씪�쓣  由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱�뀈�룄�썡�씪
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysYearSecond()
	{
		return FormatUtil.matchFormat(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * App�꽌踰꾩쓽 �쁽�옱�뀈�룄瑜� 由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱�뀈�룄
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysYear()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.YEAR));
	}

	/**
	 * App�꽌踰꾩쓽 �쁽�옱�썡�쓣 由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱�썡(1 ~ 12)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysMonth()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.MONTH) + 1);
	}

	/**
	 * App�꽌踰꾩쓽 �쁽�옱�씪�쓣 由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱�씪(1 ~ 31)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysDay()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * App�꽌踰꾩쓽 �쁽�옱�씪�쓣 由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱�씪(1 ~ 31)
	 */
	public static String getSysMonthDay()
	{
		return FormatUtil.matchFormat(new Date(), "MMdd");
	}

	/**
	 * App�꽌踰꾩쓽 �쁽�옱�떆瑜� 由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱�떆(0 ~ 23)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysHour()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * App�꽌踰꾩쓽 �쁽�옱遺꾩쓣 由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱遺�(0 ~ 59)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysMinute()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.MINUTE));
	}

	/**
	 * App�꽌踰꾩쓽 �쁽�옱珥덉쓣 由ы꽩�븳�떎.
	 * 
	 * @return 	App�꽌踰꾩쓽 �쁽�옱珥�(0 ~ 59)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getSysSecond()
	{
		return CastUtil.toStr(Calendar.getInstance().get(Calendar.SECOND));
	}

	/**
	 * �쁽�옱 �떆媛꾩쓣 �룷留룸맂 �삎�깭濡� �룎�젮 以��떎.<BR>
	 * �삁瑜� �뱾�뼱 "2001/10/05 21:30:23"瑜� 留뚮뱾怨� �떢�쑝硫� �룷留� 臾몄옄�뿴�쓣 "yyyy'/'MM'/'dd' 'HH':'mm':'ss"
	 * �� 媛숈씠 �븯硫� �맂�떎.
	 * 
	 * @param format �떆媛꾩쓽 �몴�쁽�븷 �룷留� �삎�떇 臾몄옄�뿴
	 * @return �룷留룻삎�떇�쑝濡� �룷留룸맂 �쁽�옱�떆媛꾩씠 諛섑솚�맂�떎.
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
	 * 吏��젙�맂 �궇吏쒕�� �룷留룸맂 臾몄옄�뿴濡� 諛섑솚�븳�떎.<BR>
	 * 
	 * @param date java.util.Date�쓽 媛앹껜濡� 吏��젙�븳 �궇吏�
	 * @param format 諛섑솚�븯怨좎옄 �븯�뒗 臾몄옄�뿴�쓽 �룷留�
	 * @return �룷留룻삎�떇�쑝濡� �룷留룸맂 �떆媛꾩씠 諛섑솚�맂�떎.
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getTime(java.util.Date date, String format)
	{
		if(date == null) {
			return "";
		}

		if(format == null || format.equals("")) {
			format = "yyyy'�뀈'MM'�썡'dd'�씪 'HH'�떆'mm'遺�'dd'珥�'";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		return formatter.format(date);
	}

	/**
	 * �뀈 �삉�뒗 �뀈�썡 �삉�뒗 �뀈�썡�씪 臾몄옄�뿴�쓣 �엯�젰諛쏆븘 Date媛앹껜瑜� 由ы꽩�븳�떎.<br>
	 * 李멸퀬) �뀈�쓽寃쎌슦 �썡�씪�� 1�썡1�씪濡�, �뀈�썡�쓽寃쎌슦 �씪�� 1�씪濡� �꽕�젙�맂�떎.
	 * 
	 * @param	date	"YYYY" �삉�뒗 "YYYYMM" �삉�뒗 "YYYYMMDD"
	 * @return 	Date 媛앹껜(�떆媛꾩� 0�떆0遺�0珥�), Date 媛앹껜濡� 蹂��솚�븷 �닔 �뾾�뒗 寃쎌슦 null
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
	 * �뀈�썡�씪�쓣 �엯�젰諛쏆븘 0�떆0遺�0珥덉쓽 Date媛앹껜瑜� 由ы꽩�븳�떎.
	 * 
	 * @param	year	�뀈
	 * @param	month	�썡("1" ~ "12")
	 * @param	day		�씪("1" ~ "31")
	 * @return 	Date 媛앹껜(�떆媛꾩� 0�떆0遺�0珥�), �뀈�썡�씪 以� 媛믪씠 �뾾�뒗 寃쎌슦 null
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMin(String year, String month, String day) {
		return getDate(year, month, day, "0", "0", "0");
	}

	/**
	 * �뀈�썡�씪�쓣 �엯�젰諛쏆븘 0�떆0遺�0珥덉쓽 Date媛앹껜瑜� 由ы꽩�븳�떎.
	 * 
	 * @param	year	�뀈
	 * @param	month	�썡(1 ~ 12)
	 * @param	day		�씪(1 ~ 31)
	 * @return 	Date 媛앹껜(�떆媛꾩� 0�떆0遺�0珥�)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMin(int year, int month, int day) {
		return getDate(year, month, day, 0, 0, 0);
	}

	/**
	 * �뀈 �삉�뒗 �뀈�썡 �삉�뒗 �뀈�썡�씪 臾몄옄�뿴�쓣 �엯�젰諛쏆븘 Date媛앹껜瑜� 由ы꽩�븳�떎.<br>
	 * 李멸퀬) �뀈�쓽寃쎌슦 �썡�씪�� 1�썡1�씪濡�, �뀈�썡�쓽寃쎌슦 �씪�� 1�씪濡� �꽕�젙�맂�떎.
	 * 
	 * @param	date	"YYYY" �삉�뒗 "YYYYMM" �삉�뒗 "YYYYMMDD"
	 * @return 	Date 媛앹껜(�떆媛꾩� 23�떆59遺�59珥�), Date 媛앹껜濡� 蹂��솚�븷 �닔 �뾾�뒗 寃쎌슦 null
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
	 * �뀈�썡�씪�쓣 �엯�젰諛쏆븘 23�떆59遺�59珥덉쓽 Date媛앹껜瑜� 由ы꽩�븳�떎.
	 * 
	 * @param	year	�뀈
	 * @param	month	�썡("1" ~ "12")
	 * @param	day		�씪("1" ~ "31")
	 * @return 	Date 媛앹껜(�떆媛꾩� 23�떆59遺�59珥�), �뀈�썡�씪 以� 媛믪씠 �뾾�뒗 寃쎌슦 null
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMax(String year, String month, String day) {
		return getDate(year, month, day, "23", "59", "59");
	}

	/**
	 * �뀈�썡�씪�쓣 �엯�젰諛쏆븘 23�떆59遺�59珥덉쓽 Date媛앹껜瑜� 由ы꽩�븳�떎.
	 * 
	 * @param	year	�뀈
	 * @param	month	�썡(1 ~ 12)
	 * @param	day		�씪(1 ~ 31)
	 * @return 	Date 媛앹껜(�떆媛꾩� 23�떆59遺�59珥�)
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateMax(int year, int month, int day) {
		return getDate(year, month, day, 23, 59, 59);
	}

	/**
	 * �뀈�썡�씪�떆遺꾩큹瑜� �엯�젰諛쏆븘 Date媛앹껜瑜� 由ы꽩�븳�떎.
	 * 
	 * @param	year	�뀈
	 * @param	month	�썡("1" ~ "12")
	 * @param	day		�씪("1" ~ "31")
	 * @param	hour	�떆("0" ~ "23")
	 * @param	minute	遺�("0" ~ "59")
	 * @param	second	珥�("0" ~ "59")
	 * @return 	Date 媛앹껜, �뀈�썡�씪�떆遺꾩큹 以� 媛믪씠 �뾾�뒗 寃쎌슦 null
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
	 * �뀈�썡�씪�떆遺꾩큹瑜� �엯�젰諛쏆븘 Date媛앹껜瑜� 由ы꽩�븳�떎.
	 * 
	 * @param	year	�뀈
	 * @param	month	�썡(1 ~ 12)
	 * @param	day		�씪(1 ~ 31)
	 * @param	hour	�떆(0 ~ 23)
	 * @param	minute	遺�(0 ~ 59)
	 * @param	second	珥�(0 ~ 59)
	 * @return 	Date 媛앹껜
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
	 * <pre>�뀈�썡�씪�떆遺꾩큹 �뒪�듃留곸쓣 �엯�젰諛쏆븘 Date媛앹껜瑜� 由ы꽩�븳�떎.</pre>
	 * 
	 * @param	datetime yyyyMMddHHmmss
	 * @return 	Date 媛앹껜, 媛믪씠 �뾾�뒗 寃쎌슦 null
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
	 * �몢 �씪�떆�쓽 李⑤�� �떆媛꾩감瑜� 援ы븳�떎.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return �몢 �씪�떆�쓽 李⑤�� �떆媛꾩감
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
	 * �씠 硫붿냼�뱶�뒗 �떆媛꾩쓣 �뜑�븳�떎.<br>
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
	 * �몢 �씪�떆�쓽 李⑤�� 遺꾩감瑜� 援ы븳�떎.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return �몢 �씪�떆�쓽 遺꾩쓽 �떆媛꾩감
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
	 * �몢 �궇吏쒖쓽 李⑤�� 援ы븳�떎.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return �몢 �궇吏쒖쓽 �씪�닔李�
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
	 * �몢 �궇吏쒖쓽 �썡�닔 李⑤�� 援ы븳�떎.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return �몢 �궇吏쒖쓽 �썡�닔李�
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static int getMonthInterval(Date d1, Date d2) throws Exception {
		int interval = 0;

		// 鍮꾧탳1 �뀈�룄, �떖
		int yyyy_d1 = CastUtil.toInt(getTime(d1, "yyyy"));
		int mm_d1 = CastUtil.toInt(getTime(d1, "MM"));

		// 鍮꾧탳2 �뀈�룄, �떖
		int yyyy_d2 = CastUtil.toInt(getTime(d2, "yyyy"));
		int mm_d2 = CastUtil.toInt(getTime(d2, "MM"));

		// �뀈�룄�뿉 �뵲瑜� �떖�닔 怨꾩궛..
		int _month = ( yyyy_d2 - yyyy_d1 ) * 12;

		// 寃곌낵媛�
		interval = ( mm_d2 - mm_d1 ) + _month;

		return interval;
	}


	/**
	 * <pre>
	 * Date 媛앹껜�뿉 �씪�젙湲곌컙�쓣 利앷컧�븯�뿬 由ы꽩�븳�떎.
	 * </pre>
	 * @param	date	�썝�옒 Date 媛앹껜
	 * @param	ymd		利앷컧�븘�뱶(1:�뀈, 2:�썡, 3:�씪, 4:�떆, 5:遺�)
	 * @param	amt		利앷컧湲곌컙(�뼇�닔:誘몃옒濡�~, �쓬�닔:怨쇨굅濡�~)
	 * @return 	利앷컧�맂 Date 媛앹껜
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
	 * Date 媛앹껜�뿉 �씪�젙湲곌컙�쓣 利앷컧�븯�뿬 由ы꽩�븳�떎.
	 * </pre>
	 * @param	ymd		利앷컧�븘�뱶(1:�뀈, 2:�썡, 3:�씪, 4:�떆, 5:遺�)
	 * @param	amt		利앷컧湲곌컙(�뼇�닔:誘몃옒濡�~, �쓬�닔:怨쇨굅濡�~)
	 * @return 	利앷컧�맂 Date 媛앹껜
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static Date getDateAdd(int ymd, int amt) {
		return getDateAdd(new Date(), ymd, amt);
	}

	/**
	 * �듅�젙�씪�옄�뿉 �뀈,�썡,�씪�쓣 媛�媛먰븳�떎.
	 * 媛�媛먭뎄遺꾧컪
	 *    Y : �뀈�쓣 媛�媛�
	 *    M : �썡�쓣 媛�媛�
	 *    D : �씪�쓣 媛�媛�
	 * 
	 * @param strDate �씪�옄
	 * @param intDate 媛�媛먯닔
	 * @param strymd 媛�媛먭뎄遺�
	 * @return 媛�媛먮맂 �궇吏�
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	public static String getDateCalc( String strDate, int intDate, String strymd )
	{
		int yyyy, mm, dd;
		strDate = StringUtil.getNumber(strDate);	//�닽�옄�븘�땶 �떎瑜� 媛� �뱾�뼱�삱 寃쎌슦 泥섎━
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
	 * 二쇱뼱吏� �궇吏쒖뿉 '-'�� ':'瑜� 異붽� �삉�뒗 �젣嫄�
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
	 * 二쇱뼱吏� �궇吏쒖뿉 '-'瑜� 異붽� �삉�뒗 �젣嫄�
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
	 * �뒪�듃留곷Ц�옄�뿴�쓣 �궇吏쒗삎�떇�쑝濡� 蹂��솚�븯�뿬 由ы꽩�븳�떎.
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
	 * �궇�옄�삎�떇�쓽 臾몄옄�뿴�뿉�꽌 '-'瑜� �젣嫄고븯�뿬 由ы꽩�븳�떎.
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
	 * �쁽�옱�궇吏쒖뿉 ���븳 �슂�씪紐낆쓣 �뼸�뒗�떎.
	 * </pre>
	 * @param date �삁:20040528
	 * @return �슂�씪紐�
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
			result = "�씪";
			break;
		case Calendar.MONDAY :
			result = "�썡";
			break;
		case Calendar.TUESDAY :
			result = "�솕";
			break;
		case Calendar.WEDNESDAY :
			result = "�닔";
			break;
		case Calendar.THURSDAY :
			result = "紐�";
			break;
		case Calendar.FRIDAY :
			result = "湲�";
			break;
		case Calendar.SATURDAY :
			result = "�넗";
			break;

		}

		return result;
	}

	/**
	 * <pre>
	 * �쁽�옱�궇吏쒖뿉 ���븳 �슂�씪�쓽 �닽�옄瑜� �뼸�뒗�떎.
	 * </pre>
	 * @param date �삁:20040528
	 * @return �슂�씪踰덊샇
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
	 * Date�삎 媛앹껜�� DateFormat�쓣 �뒪�듃留곸쑝濡� �꽆寃⑥＜硫� �궇吏쒕�� 諛섑솚�븯�뒗 硫붿꽌�뱶
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
	 * 二쇱뼱吏� �뀈, �썡�쓽 留덉�留� �씪�옄瑜� 援ы븳�떎
	 * �삁) 2003, 8 �씠硫� 31�쓣 由ы꽩
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
	 * �떆媛� 00~24�떆
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
	 * 遺� 00~50遺� (10遺꾨떒�쐞)
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
	 * 二쇱뼱吏� 珥덈�� "�떆媛�:遺�:珥�"濡� 蹂��솚�븯�뿬 由ы꽩
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
	 * 二쇱뼱吏� 珥덈�� "�떆媛�:遺�:珥�"濡� 蹂��솚�븯�뿬 由ы꽩
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
	 * �떆�뒪�뀥 泥섎━�떆媛� 紐⑤땲�꽣留�
	 * ----- Logging Comment -------
	 * PMNET : 2008/09/01
	 */
	private static SimpleDateFormat logDateFormat	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DecimalFormat df = new DecimalFormat(",###");
	private static String monitorName 		= "";	// 紐⑤땲�꽣留곷챸
	private static long currentTimeMillis 	= 0;	// �떆�뒪�뀥 泥섎━�떆媛� 紐⑤땲�꽣留�
	private static double diffTime 			= 0;	// �떆�뒪�뀥 泥섎━�떆媛�

	/**
	 * �쁽�옱 �옉�뾽紐� �엯�젰
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
