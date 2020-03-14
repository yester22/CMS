package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BoardMngVo;

@Mapper
public interface BoardMngDao {
	public List<BoardMngVo> list ( BoardMngVo boardMngVo);
	public BoardMngVo view ( BoardMngVo boardMngVo);
	public void insert(BoardMngVo boardMngVo);
	public int  update(BoardMngVo boardMngVo);
	public int  delete(BoardMngVo boardMngVo);
	public int  count(BoardMngVo boardMngVo);
}
