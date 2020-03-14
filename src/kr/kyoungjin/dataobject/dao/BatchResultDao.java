package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BatchResultVo;
import kr.kyoungjin.dataobject.vo.BatchVo;

@Mapper
public interface BatchResultDao {
	public List<BatchResultVo> list ( BatchResultVo batchResultVo);
	public BatchResultVo view ( BatchResultVo batchResultVo);
	public void insert(BatchResultVo batchResultVo);
	public int  update(BatchResultVo batchResultVo);
	public int  delete(BatchResultVo batchResultVo);
	public int  count(BatchResultVo batchResultVo);
}
