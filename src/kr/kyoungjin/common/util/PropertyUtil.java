package kr.kyoungjin.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * 
 * Property  Util
 * 
 * @author : 정경진
 * -------------------------- Modification Log ------------------------------------
 *  2018.06.11      정경진     신규
 */
@Component("prop")
public class PropertyUtil {
	Log log = LogFactory.getLog(this.getClass());
	
	private Properties properties;
	public void setProperties( String filepath ) throws IOException {
		FileSystemResourceLoader propFile =  new  FileSystemResourceLoader();
		Resource resource = propFile.getResource(filepath);
		InputStream is = resource.getInputStream();
		properties = new Properties();
		properties.load(is);
	}
	
	/** 키값을 가져온다
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String rtnValue = "";
		if ( properties != null ) {
			rtnValue = properties.getProperty(key);
		}
		return rtnValue;
	}
	
}

