package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.AttachVo;
       
@Mapper
public interface AttachDao {
	public List<AttachVo> list ( AttachVo attachVo);
	public AttachVo view ( AttachVo attachVo);
	public void insert(AttachVo attachVo);
	public int  update(AttachVo attachVo);
	public int  delete(AttachVo attachVo);
	public int  count(AttachVo attachVo);
}
