package kr.kyoungjin.jobs.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import kr.kyoungjin.common.abstractObject.AbstractService;
import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.jobs.login.service.ILoginService;
import kr.kyoungjin.jobs.member.service.IMemberService;

public class LoginServiceImpl extends AbstractService implements ILoginService {

	@Autowired
	private IMemberService memberService;
	
	public MemberVo view(MemberVo member) throws Exception {
		return memberService.view(member);
	}

}
