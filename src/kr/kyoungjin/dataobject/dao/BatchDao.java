package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BatchVo;

@Mapper
public interface BatchDao {
	public List<BatchVo> list ( BatchVo batchVo);
	public BatchVo view ( BatchVo batchVo);
	public void insert(BatchVo batchVo);
	public int  update(BatchVo batchVo);
	public int  delete(BatchVo batchVo);
	public int  count(BatchVo batchVo);
}
