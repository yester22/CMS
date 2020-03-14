package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BoardVo;

@Mapper
public interface BoardDao {
	public List<BoardVo> list ( BoardVo boardVo);
	public BoardVo view ( BoardVo boardVo);
	public void insert(BoardVo boardVo);
	public int  update(BoardVo boardVo);
	public int  delete(BoardVo boardVo);
	public int  count(BoardVo boardVo);
}
