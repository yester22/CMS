package kr.kyoungjin.jobs.system.encryption.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.kyoungjin.common.abstractObject.AbstractService;
import kr.kyoungjin.common.util.AES256Util;
import kr.kyoungjin.jobs.system.encryption.service.EncriptionService;

@Service
public class EncriptionServiceImpl extends AbstractService implements EncriptionService {

	@Value("#{config['ENCRPTY.KEY']}")
	private String ENCRPTY_KEY;
	
	/* 값을 복호화하여 반환한다
	 * @param key 복호화할 값
	 */
	public String decode ( String key ) throws Exception {
		AES256Util decUtil = new AES256Util ( ENCRPTY_KEY );
		String decodeVal = decUtil.aesDecode(key); 
		decUtil = null;
		return decodeVal;
	};
	
	
	/* 값을 암호화하여 반환한다
	 * @param key 암호화할 값
	 */
	public String encode ( String key ) throws Exception {
		AES256Util encUtil = new AES256Util ( ENCRPTY_KEY );
		String encodeVal = encUtil.aesEncode(key); 
		encUtil = null;
		return encodeVal;
	};
	
}
