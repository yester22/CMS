package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;
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
@Mapper
public interface AddrCourtdongPolygonDao {
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 13.
	 * @Method Name : selectPolygonDataInputTarget
	 * @return : List<AddrCourtDongPolygonVo>
	 */
	public List<AddrCourtDongPolygonVo> selectPolygonDataInputTarget(Map<String,Object> param) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 13.
	 * @Method Name : insertItem
	 * @return : void
	 */
	public void insertPolygonData(AddrCourtDongPolygonVo item)  throws Exception;
}
