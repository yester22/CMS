package kr.kyoungjin.jobs.login.service;

import kr.kyoungjin.dataobject.vo.MemberVo;

public interface ILoginService {
	public MemberVo view(MemberVo member) throws Exception;
}
