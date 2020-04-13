package kr.kyoungjin.jobs.excel.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kyoungjin.dataobject.dao.AddrCourtdongPolygonDao;
import kr.kyoungjin.dataobject.vo.AddrCourtDongPolygonVo;
import kr.kyoungjin.jobs.batch.service.BatchVWorldDataSetting;
import kr.kyoungjin.jobs.excel.service.AddrCourtdongPolygonService;

/**
 * @since 2020. 4. 13.
 * @author yester21
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 4. 13. yester21 : 최초작성
 * </PRE>
 */
@Service
public class AddrCourtdongPolygonServiceImpl implements AddrCourtdongPolygonService {

	private Logger logger  = LoggerFactory.getLogger(BatchVWorldDataSetting.class);
	
	@Autowired
	private AddrCourtdongPolygonDao addrCourtdongPolygonDao; 
	

	@Override
	public List<AddrCourtDongPolygonVo> getPolygonDataInputTarget(Map<String, Object> param) throws Exception {
		return this.addrCourtdongPolygonDao.selectPolygonDataInputTarget(param);
	}

	@Override
	@Transactional
	public void insert(List<AddrCourtDongPolygonVo> insertList ) throws Exception {
		if ( insertList == null || insertList.size() == 0 ) return;
		
		for ( AddrCourtDongPolygonVo item : insertList ) {
			if ( logger.isDebugEnabled()) {
				logger.debug(item.toString());
			}
			this.addrCourtdongPolygonDao.insertPolygonData(item);
		}
	}
}
