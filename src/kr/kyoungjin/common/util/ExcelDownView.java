package kr.kyoungjin.common.util;

import java.awt.Font;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;


@Component
public class ExcelDownView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
		HSSFWorkbook workbook, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
//		List list = (List)model.get("list");
//		String fileName = (String)model.get("fileName");
//		String sheet = (String)model.get("sheet");
//		System.out.println("buildExcelDocument fileName : "+fileName);
//		System.out.println("buildExcelDocument sheet : "+sheet);

		this.createExcelExport(model, request, response);
	}

	public void createExcelExport(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 기본값
		String excelFileName = (String)model.get("fileName");
		String sheetName = (String)model.get("sheet");
		XSSFWorkbook workBook = new XSSFWorkbook();
		excelFileName += ".xlsx";
		
		XSSFFont xssFont  = ExcelUtil.getXssFont(workBook);
		
		// 스타일{header,center,left,right,number};
		XSSFCellStyle[] csArray = ExcelUtil.getXssStyle(workBook, xssFont);
		XSSFCellStyle header = csArray[0];
		XSSFCellStyle center = csArray[1];
		XSSFCellStyle left = csArray[2];
		XSSFCellStyle right = csArray[3];
		XSSFCellStyle number = csArray[4];		
		
		// 쉬트 생성
		XSSFSheet sheet = workBook.createSheet(sheetName);
		// 헤더 로우 생성
		int rowIndex = 0;
		XSSFRow headerRow = sheet.createRow(rowIndex);
		rowIndex++;
		// header정보 생성
		List<String> columns = new ArrayList<String>();
		int cellIdx = 0;
		//for(RequestParameter params: columns) {
			XSSFCell headerCell = headerRow.createCell(cellIdx);
			headerCell.setCellStyle(header);
			headerCell.setCellValue("TEST_TITLE");
			cellIdx++;
		//}
			
		// 데이터 생성			
		XSSFRow dataRow = sheet.createRow(rowIndex);
		XSSFCell datacell = dataRow.createCell(0);
		datacell.setCellValue("데이타1");
		datacell.setCellStyle(center);

		datacell = dataRow.createCell(1);
		datacell.setCellValue("데이타1");
		datacell.setCellStyle(center);

		datacell = dataRow.createCell(2);
		datacell.setCellValue("데이타1");
		datacell.setCellStyle(center);
		
		response = ExcelUtil.setResponseType(request, response, excelFileName);
		
		OutputStream os = null;
		try{
			os = response.getOutputStream();
			workBook.write(os);
		}catch(Exception e){
			throw new Exception();
		}finally{
			if(os != null){
				os.flush();
				os.close();
				os = null;
			}
		}
	}
	
	
	
	/** 지정한 엑셀 템플릿으로 엑셀 파일 다운로드 
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void createExcelExportTemplate(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 기본값
		String excelFileName =  (String)model.get("fileName");
		String[] excelHeader =  (String[])model.get("excelHeader");
		String[] columns =  	(String[])model.get("columns");
		List rowExcelData    =  (List)model.get("excelData");
		String sheetName =      (String)model.get("sheetName");
		XSSFWorkbook workBook = new XSSFWorkbook();
		excelFileName += ".xlsx";
		
		// 쉬트 생성
		XSSFSheet sheet = workBook.createSheet(sheetName);
		// 헤더 로우 생성
		int rowIndex = 0;
		XSSFRow headerRow = sheet.createRow(rowIndex);
		rowIndex++;

		// header정보 생성
		int cellIdx = 0;
		XSSFFont fontBLACK = workBook.createFont();
        fontBLACK.setColor(HSSFColor.BLACK.index);

        CellStyle header = workBook.createCellStyle();
        header.setAlignment(CellStyle.ALIGN_CENTER);                        
        header.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        header.setFont(fontBLACK);                                          
        header.setBorderTop(CellStyle.BORDER_THIN);                         
        header.setBorderLeft(CellStyle.BORDER_THIN);
        header.setBorderRight(CellStyle.BORDER_THIN);
        header.setBorderBottom(CellStyle.BORDER_THIN);
        
		if ( excelHeader != null && excelHeader.length > 0 )
		{
			for ( String headerString : excelHeader ) {
				XSSFCell headerCell = headerRow.createCell(cellIdx);
				headerCell.setCellStyle(header);
				headerCell.setCellValue(headerString);
				cellIdx++;
			}
		}
		
		// cell 스타일 생성
		CellStyle dataStyle = workBook.createCellStyle();
		dataStyle.setAlignment(CellStyle.ALIGN_CENTER);                        
		dataStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		dataStyle.setFont(fontBLACK);                                          
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);                         
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);
        dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
        // 데이터 생성			
		if ( rowExcelData != null && rowExcelData.size() > 0 ) {
			ListIterator iter = rowExcelData.listIterator();
			Map tmp = null;
			XSSFCell dataCell = null;
			int colIndex = 0;
			
			
			while ( iter.hasNext() ) {
				tmp = (HashMap)iter.next();
				XSSFRow dataRow = sheet.createRow(rowIndex);
				
				for ( String cols : columns) {
					String CellValue = (String)tmp.get(cols);
					dataCell = dataRow.createCell( colIndex );
					dataCell.setCellStyle(dataStyle);
					dataCell.setCellValue(  CellValue  );
					colIndex++;
				}
				colIndex = 0;
				rowIndex++;
			}
		}

		
		response = ExcelUtil.setResponseType(request, response, excelFileName);
		
		OutputStream os = null;
		try{
			os = response.getOutputStream();
			workBook.write(os);
		}catch(Exception e){
			throw new Exception();
		}finally{
			if(os != null){
				os.flush();
				os.close();
				os = null;
			}
		}
	}
}
