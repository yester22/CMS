package kr.kyoungjin.jobs.member.service;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.dataobject.vo.LoginTryVo;
import kr.kyoungjin.dataobject.vo.MemberVo;

public interface IMemberService {
	public MemberVo view (MemberVo memberVo) throws Exception ;
	public int updateLoginFail(LoginTryVo loginTryVo) throws Exception ;
	public int updateLoginSuccess(LoginTryVo loginTryVo) throws Exception ;
	public List<MemberVo> getMemberList(Map<String, Object> param)  throws Exception ;
	public long getMemberListCount(Map<String, Object> param) throws Exception;
	public MemberVo getMember(Map<String, Object> params)  throws Exception;
	public int getCount(Map<String, Object> params) throws Exception;
	public int saveMember(Map<String,Object> params) throws Exception;
	public int deleteMember(Map<String, Object> params) throws Exception;
}
