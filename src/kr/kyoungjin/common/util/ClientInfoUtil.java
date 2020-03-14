package kr.kyoungjin.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.HttpServletRequest;

public class ClientInfoUtil {


	/** 클라이언트의 IPAddress 반환
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getClientIpAddr(HttpServletRequest request) throws Exception {
		 if (request.getHeader("x-forwarded-for") == null) { 
	          return request.getRemoteAddr(); 
	     } 
	     return request.getHeader("x-forwarded-for"); 
	}


	/** 사용자의 MAC 주소 반환
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getClientMacAddr(HttpServletRequest request) throws Exception {
		
		String str = ""; 
		String macAddress = ""; 
		try { 
			String ip = ClientInfoUtil.getClientIpAddr(request);
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip); 
			InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
			LineNumberReader input = new LineNumberReader(ir); 
			for (int i = 1; i <100; i++) { 
				str = input.readLine(); 
				if (str != null) { 
					if (str.indexOf("MAC Address") > 1) { 
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
						break; 
					} 
				} 
			} 
		} catch (IOException e) { 
			e.printStackTrace(System.out); 
		} 
		return macAddress; 
	}




}
