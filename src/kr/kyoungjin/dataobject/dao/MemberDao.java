package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.MemberVo;


@Mapper
public interface MemberDao {
	public MemberVo view(MemberVo memberVo) ;
	public List<MemberVo> list(MemberVo memberVo);
	public void insert(MemberVo memberVo);
	public int  update(MemberVo memberVo);
	public int  delete(MemberVo memberVo);
	public int  count(MemberVo memberVo);
	public int  updateFailCount(MemberVo memberVo);
}
