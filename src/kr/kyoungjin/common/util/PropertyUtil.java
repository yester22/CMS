package kr.kyoungjin.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * 
 * Property  Util
 * 
 * @author : �젙寃쎌쭊
 * -------------------------- Modification Log ------------------------------------
 *  2018.06.11      �젙寃쎌쭊     �떊洹�
 */
@Component("prop")
public class PropertyUtil {
	private Logger log  = LoggerFactory.getLogger(PropertyUtil.class);
	private Properties properties;
	public void setProperties( String filepath ) throws IOException {
		FileSystemResourceLoader propFile =  new  FileSystemResourceLoader();
		Resource resource = propFile.getResource(filepath);
		InputStream is = resource.getInputStream();
		properties = new Properties();
		properties.load(is);
	}
	
	/** �궎媛믪쓣 媛��졇�삩�떎
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

