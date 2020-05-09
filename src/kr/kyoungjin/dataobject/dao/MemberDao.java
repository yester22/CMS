package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.MemberVo;


@Mapper
public interface MemberDao {
	public MemberVo view(MemberVo memberVo) throws Exception;
	public List<MemberVo> list(Map<String, Object> param)  throws Exception;
	public Long selectMembercount(Map<String, Object> param) throws Exception;
	public void insert(MemberVo memberVo)  throws Exception;
	public int  update(MemberVo memberVo) throws Exception;
	public int  delete(MemberVo memberVo)  throws Exception;
	public int  count(MemberVo memberVo)  throws Exception;
	public int  updateFailCount(MemberVo memberVo)  throws Exception;
	public MemberVo selectMember(Map<String, Object> params)   throws Exception;
	public int deleteMemberList(Map<String, Object> params)   throws Exception;;
}
