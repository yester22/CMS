package kr.kyoungjin.jobs.excel.service;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.dataobject.vo.AddrCourtDongPolygonVo;

/**
 * @since 2020. 4. 13.
 * @author yester21
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 4. 13. yester21 : 최초작성
 * </PRE>
 */
public interface AddrCourtdongPolygonService {
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 13.
	 * @Method Name : getPolygonDataInputTarget
	 * @return : List<AddrCourtDongPolygonVo>
	 */
	public List<AddrCourtDongPolygonVo> getPolygonDataInputTarget(Map<String,Object> param) throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 13.
	 * @Method Name : insert
	 * @return : void
	 */
	public void insert(List<AddrCourtDongPolygonVo> insertList ) throws Exception;
}
