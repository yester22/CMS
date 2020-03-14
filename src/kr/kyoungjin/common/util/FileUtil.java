package kr.kyoungjin.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;



/**
 * @Class Name : FileUtil.java
 * @Description : 기본서비스 틀을 잡기 위함
 * @Modification Information
 * 수정일			수정자		수정내용
 * ---------	---------	------------------------------
 * 2010. 9. 9.	swchoi		최초생성
 *
 * @since 2010. 9. 9.
 * @version 1.0
 * @see
 */
public class FileUtil {
	
	//private static Properties properties = PropertyUtil.getProperties();
	
	/** 이미지파일 목록 */		public static final String IMG_FILE_LIST[] = {"JPEG", "GIF", "BMP", "JPG", "TIF", "TIFF", "PNG", "JPE"};
	/** 엑셀파일 목록 */		public static final String EXCEL_FILE_LIST[] = {"XLS", "XLSX","CSV"};
	/** 업로드파일 목록 */		public static final String ALL_FILE_LIST[] = {"XLS", "XLSX","HWP", "DOC", "DOCX", "PPT", "PPTX", "TXT"
		,"JPEG", "GIF", "BMP", "JPG", "TIF", "TIFF", "PNG", "JPE", "CSV", "WAV", "MP3"};
	/**	저장된파일들 */		public static final String STORED_FILES = "STORED_FILES";

	/** 파일 업로드  파일ID */	public static final String FILE_ID = "ATTFILE_ID"; //"ATTFILE_NO";
	/** 파일 객체명 */			public static final String FILE_NAME = "FORM_FILE_NAME";
	/** 파일 업로드 원 파일명 */	public static final String ORIGIN_FILE_NM = "FILE_NAME";
	/** 업로드된 파일명 */		public static final String UPLOAD_FILE_NM = "SYS_FILE_NAME";
	/** 파일 확장자 */			public static final String FILE_EXT = "FILE_EXT";
	/** 파일크기 */			public static final String FILE_SIZE = "FILE_SIZE";
	/** 파일경로 */			public static final String FILE_PATH = "FILE_PATH";
	/** 파일설명 */			public static final String FILE_REMARK = "REMARK_";

	/** 파일ROOT경로 */		//public static final String FILE_UPLOAD_DIR = "/uniwas_nas/dscc/attach_file/";
	/** 파일ROOT경로 */		public static final String POST_UPLOAD_DIR = "/uniwas_nas/dscc/post/";
	/** 파일ROOT경로 */			

	//public static final String FILE_UPLOAD_DIR = "/svc/ohnew/tomcat-7.0.50/webapps/ohnew/uploadFile/";
	
	public static final String FILE_UPLOAD_DIR_TEST = "d:/test/";
	public static final String FILE_UPLOAD_DIR_REAL = "/svc/ohnew/tomcat-7.0.50/webapps/ohnew/uploadFile/";
	
	public static final String FILE_UPLOAD_ENV_DIR_TEST = "d:/test/";
	public static final String FILE_UPLOAD_ENV_DIR_REAL = "/svc/ohnew/tomcat-7.0.50/webapps/ohnew/uploadFile/";
	
	/** 버퍼크기 */			
	public static final int BUFF_SIZE = 2048;

	public static boolean isPicture(String ext) {
		boolean result = false;
		if(ext != null) {
			ext = ext.toUpperCase();
			for (String pic: IMG_FILE_LIST) {
				if(pic.equals(ext)) {
					result = true;
				}
			}
		}
		return result;
	}

	public static boolean isExcel(String ext) {
		boolean result = false;
		if(ext != null) {
			ext = ext.toUpperCase();
			for (String pic: EXCEL_FILE_LIST) {
				if(pic.equals(ext)) {
					result = true;
				}
			}
		}
		return result;
	}

	public static boolean isAllFile(String ext) {
		boolean result = false;
		if(ext != null) {
			ext = ext.toUpperCase();
			for (String pic: ALL_FILE_LIST) {
				if(pic.equals(ext)) {
					result = true;
				}
			}
		}
		return result;
	}

	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	public static String getExt(MultipartFile file) {
		return getExt(file.getOriginalFilename());
	}

	/**
	 * 첨부로 등록된 파일을 서버에 업로드한다.
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, String> uploadFile(MultipartFile file,String uploadPath) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		//Write File 이후 Move File????
		String newName = null;
		String storedPath = DateUtil.getCurrentTime().substring(0, 8);;
		String stordFilePath = uploadPath+storedPath;
		//	String stordFilePath = EgovProperties.getProperty("Globals.fileStorePath");
		String orginFileName = file.getOriginalFilename();

		String fileExt = getExt(orginFileName);
		long size = file.getSize();

		//너무 빠른 처리 시 파일명이 중복되어 1개 파일만 저장되는 경우가 있어서 잠시 멈춰주는 부분 추가
		//Thread.sleep(1000);
		//newName 은 Naming Convention에 의해서 생성
		newName = DateUtil.getCurrentTime() + "." + fileExt;
		writeFile(file, newName, stordFilePath);
		//storedFilePath는 지정
		map.put(FILE_NAME, file.getName());
		map.put(ORIGIN_FILE_NM, orginFileName);
		map.put(UPLOAD_FILE_NM, newName);
		map.put(FILE_EXT, fileExt);
		map.put(FILE_PATH, stordFilePath + "/" + newName);
		map.put(FILE_SIZE, String.valueOf(size));
		
		System.out.println("----------------------");
		
		System.out.println("FORM_FILE_NAME : " + file.getName());
		System.out.println("FILE_NAME : " + orginFileName);
		System.out.println("SYS_FILE_NAME : " + newName);
		System.out.println("FILE_SIZE : " + String.valueOf(size));
		System.out.println("FILE_PATH : " + stordFilePath);
		
		System.out.println("----------------------");

		return map;
	}

	/**
	 * 파일을 실제 물리적인 경로에 생성한다.
	 *
	 * @param file
	 * @param newName
	 * @param stordFilePath
	 * @throws Exception
	 */
	public static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = file.getInputStream();
			File cFile = new File(stordFilePath);

			if (!cFile.isDirectory()) {
				cFile.mkdirs();
			}
			System.out.println("writeFile ---> "+stordFilePath + File.separator + newName);
			
			bos = new FileOutputStream(stordFilePath + File.separator + newName);

			int bytesRead = 0;
			byte[] buffer = new byte[BUFF_SIZE];

			while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception ignore) {
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

	/**
	 * 서버의 파일을 다운로드한다.
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
//	public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		String downFileName = "";
//		String orgFileName = (String)request.getAttribute("orginFile");//new String(((String)request.getAttribute("orginFile")).getBytes("8859_1"), "UTF-8");
//		String filePath = (String)request.getAttribute("filePath");//new String(((String)request.getAttribute("orginFile")).getBytes("8859_1"), "UTF-8");
//		
//		System.out.println("downFile orginFile : "+orgFileName);
//		System.out.println("downFile filePath : "+filePath);		
//		
//		boolean MSIE = request.getHeader("user-agent").indexOf("MSIE") != -1;
//
//		if (orgFileName == null) {
//			orgFileName = "";
//		} else {
//			//IE,FF 각각 다르게 파일이름을 적용해서 구분해주어야 한다.
//			if(MSIE){
//				//브라우저가 IE일 경우 저장될 파일 이름
//				//공백이 '+'로 인코딩된것을 다시 공백으로 바꿔준다.
//				//orgFileName = (URLEncoder.encode(orgFileName, "EUC-KR")+"_"+URLEncoder.encode(orgFileName, "8859_1")+"_"+URLEncoder.encode(orgFileName, "UTF-8")).replaceAll("\\+", " ");
//				orgFileName = URLEncoder.encode(orgFileName, "UTF-8").replaceAll("\\+", " ");
//			}else{
//				//브라우저가 IE가 아닐 경우 저장될 파일 이름
//				orgFileName = new String(orgFileName.getBytes("UTF-8"), "8859_1");
//			}
//			//		orgFileName = "테스트";//(String)request.getAttribute("orginFile");
//		}
//
//		File file = new File(filePath);
//
//		if (!file.exists()) {
//			throw new FileNotFoundException(downFileName);
//		}
//
//		if (!file.isFile()) {
//			throw new FileNotFoundException(downFileName);
//		}
//
//		byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
//
//		response.setContentType("application/x-msdownload");
//		response.setHeader("Content-Disposition", "attachment;filename=\"" + orgFileName +"\";");
//		response.setHeader("Content-Transfer-Encoding", "binary");
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Expires", "0");
//
//		BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
//		BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
//		int read = 0;
//
//		while ((read = fin.read(b)) != -1) {
//			outs.write(b, 0, read);
//		}
//
//		outs.close();
//		fin.close();
//	}
	
	public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String downFileName = "";
		String orgFileName = (String)request.getAttribute("orginFile");//new String(((String)request.getAttribute("orginFile")).getBytes("8859_1"), "UTF-8");
		String filePath = (String)request.getAttribute("filePath");//new String(((String)request.getAttribute("orginFile")).getBytes("8859_1"), "UTF-8");
		
		//System.out.println("downFile orginFile : "+orgFileName);
		//System.out.println("downFile filePath : "+filePath);		

		File file = new File(filePath);

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.

		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//한글이 정상적으로 다운로드 되는 것으로 지정한다.
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("MSIE") > -1) { // MS IE (보통은 6.x 이상 가정)
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(orgFileName, "UTF-8") + ";");
		} else { // 모질라, 오페라
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(orgFileName.getBytes("euc-kr"), "latin1")
					+ "\";");
		}

		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
		int read = 0;

		while ((read = fin.read(b)) != -1) {
			outs.write(b, 0, read);
		}

		outs.close();
		fin.close();
	}	
	
	public static void writeEnvFile(List<HashMap<String, Object>> serverList)throws FileNotFoundException, IOException, Exception {
		// ini 파일 경로 key
		String basePath = "";
		if(OhnewConfig.isReal) {				
			basePath = FILE_UPLOAD_ENV_DIR_REAL;
		}else{
			basePath = FILE_UPLOAD_ENV_DIR_TEST;
		}		
		//System.out.println("basePath ---> : "+ basePath);
		
		String fileName = "env.ini";
		Properties proper = null;
		FileOutputStream output = null;
		BufferedWriter bw = null;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		for(int i=0;i<serverList.size();i++){
			HashMap<String, Object> tempMap = serverList.get(i);
			paramMap.put("ServerPort"+i, tempMap.get("PORT"));
			paramMap.put("ServerIP"+i, tempMap.get("ipAddr"));
		}
		
		try {
			// 디렉토리 확인
			File dir = new File(basePath);
			if(!dir.exists())
				dir.mkdir();

			// 기존파일 확인/삭제
			File exitFile = new File(basePath + fileName);
			if(exitFile.exists())
				exitFile.delete();
			
			bw = new BufferedWriter(new FileWriter(basePath + fileName));
			
			bw.write("[Default]"); bw.newLine();
			
			for(int i=0;i<serverList.size();i++){
				HashMap<String, Object> tempMap = serverList.get(i);
				bw.write("ServerPort"+i + "=" + tempMap.get("PORT")); bw.newLine();
			}
			for(int i=0;i<serverList.size();i++){
				HashMap<String, Object> tempMap = serverList.get(i);
				bw.write("ServerIP"+i + "=" + tempMap.get("ipAddr")); bw.newLine();
			}
			
			//default data
			bw.newLine();
			bw.write("SysInfoTransterPeriodSec" + "=" + "60"); bw.newLine();
			bw.write("SizeOfCapFile" + "=" + "1"); bw.newLine();
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			throw new FileNotFoundException("ini 파일을 찾을수 없습니다.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new IOException("writeEnvFile Exception!", ioe);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
			}
		}
	}	
	

}
