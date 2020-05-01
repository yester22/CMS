package kr.kyoungjin.jobs.member.service;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.dataobject.vo.LoginTryVo;
import kr.kyoungjin.dataobject.vo.MemberVo;

public interface IMemberService {
	public MemberVo view (MemberVo memberVo) throws Exception ;
	public int updateLoginFail(LoginTryVo loginTryVo) throws Exception ;
	public int updateLoginSuccess(LoginTryVo loginTryVo) throws Exception ;
	public Map<String,Object> getMemberList(Map<String, Object> param)  throws Exception ;
}
