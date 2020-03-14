/**
 * 
 */
package kr.kyoungjin.jobs.system.code.service;

import java.util.List;
import java.util.Map;

/**
 * @author 컴퓨터
 *
 */
public interface ICodeService {
	public List<Map<String,Object>> getCodeList ( Map<String,Object> param );
}
