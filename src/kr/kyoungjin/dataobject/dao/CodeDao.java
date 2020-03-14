package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.CodeVo;

@Mapper
public interface CodeDao {
	public List<CodeVo> list ( CodeVo codeVo);
	public CodeVo view ( CodeVo codeVo);
	public void insert(CodeVo codeVo);
	public int  update(CodeVo codeVo);
	public int  delete(CodeVo codeVo);
	public int  count(CodeVo codeVo);
}
