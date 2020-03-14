package kr.kyoungjin.common.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public ExcelUtil() {}
	
	/**
	 * 2007 이후 버젼 디폴트 폰트 
	 * @return
	 */
	public static XSSFFont getXssFont(XSSFWorkbook workBook) {
		
		XSSFFont xssFont = null;
		
		// 생성
		xssFont = workBook.createFont();
		xssFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		xssFont.setColor(IndexedColors.BLACK.index);
		xssFont.setFontName("Arial");
		xssFont.setFontHeight((short)9);
		xssFont.setFontHeightInPoints((short)9);
		
		return xssFont;
	}
		
	/**
	 * 2007 이후 버젼 디폴트 스타일
	 */
	public static XSSFCellStyle[] getXssStyle(XSSFWorkbook workBook, XSSFFont xssFont) {
		XSSFCellStyle header = null;
		XSSFCellStyle center = null;
		XSSFCellStyle number = null;
		XSSFCellStyle left = null;
		XSSFCellStyle right = null;	
		
//		// 디폴트 폰트 생성
//		this.getXssFont();

		// 생성
		header = workBook.createCellStyle();
		header.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		header.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		header.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		header.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		header.setBorderTop(HSSFCellStyle.BORDER_THIN);
		header.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		header.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		header.setBorderRight(HSSFCellStyle.BORDER_THIN);
		header.setFont(xssFont);
		
		center = workBook.createCellStyle();		
		center.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		center.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		center.setFillPattern(XSSFCellStyle.NO_FILL);
		center.setFillForegroundColor(IndexedColors.WHITE.index);
		center.setBorderTop(HSSFCellStyle.BORDER_THIN);
		center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		center.setBorderRight(HSSFCellStyle.BORDER_THIN);
		center.setFont(xssFont);
		
		left = workBook.createCellStyle();
		left.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		left.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		left.setFillPattern(XSSFCellStyle.NO_FILL);
		left.setFillForegroundColor(IndexedColors.WHITE.index);
		left.setBorderTop(HSSFCellStyle.BORDER_THIN);
		left.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		left.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		left.setBorderRight(HSSFCellStyle.BORDER_THIN);
		left.setFont(xssFont);
		
		right = workBook.createCellStyle();
		right.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		right.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		right.setFillPattern(XSSFCellStyle.NO_FILL);
		right.setFillForegroundColor(IndexedColors.WHITE.index);
		right.setBorderTop(HSSFCellStyle.BORDER_THIN);
		right.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		right.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		right.setBorderRight(HSSFCellStyle.BORDER_THIN);
		right.setFont(xssFont);
		
		number = workBook.createCellStyle();
		number.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		number.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		number.setFillPattern(XSSFCellStyle.NO_FILL);
		number.setFillForegroundColor(IndexedColors.WHITE.index);
		number.setBorderTop(HSSFCellStyle.BORDER_THIN);
		number.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		number.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		number.setBorderRight(HSSFCellStyle.BORDER_THIN);
		number.setDataFormat(workBook.createDataFormat().getFormat("###,###,###,###"));
		number.setFont(xssFont);
		
		XSSFCellStyle[] csArray = {header,center,left,right,number};
		
		return csArray;
	}
	
	/**
	 * 헤더 정의
	 */
	public static HttpServletResponse setResponseType(HttpServletRequest request,
			HttpServletResponse response, String excelFileName) throws UnsupportedEncodingException {
		
		// 리턴 타입
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Transfer-Encoding", "binary");
			
		//한글이 정상적으로 다운로드 되는 것으로 지정한다.
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("MSIE") > -1) { // MS IE (보통은 6.x 이상 가정)
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(excelFileName, "UTF-8") + ";");
		} else { // 모질라, 오페라
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(excelFileName.getBytes("euc-kr"), "latin1")
					+ "\";");
		}
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		return response;		
	}

}