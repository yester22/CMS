package kr.kyoungjin.jobs.member.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.kyoungjin.common.abstractObject.AbstractService;
import kr.kyoungjin.dataobject.dao.LoginTryDao;
import kr.kyoungjin.dataobject.dao.MemberDao;
import kr.kyoungjin.dataobject.vo.LoginTryVo;
import kr.kyoungjin.dataobject.vo.MemberVo;
import kr.kyoungjin.jobs.member.service.IMemberService;


@Service
@Transactional
public class MemberServiceImpl extends AbstractService implements IMemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	
	@Autowired
	private LoginTryDao loginTryDao;
	
	/**
	 * 사용자 정보 조회
	 */
	@Override
	public MemberVo view(MemberVo memberVo) throws Exception {
		MemberVo rtn = memberDao.view ( memberVo); 
		return rtn;
	}

	/**
	 * 사용자 로그인 실패 처리
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
	public int updateLoginFail(LoginTryVo loginTryVo) throws Exception {
		MemberVo memberVo = new MemberVo();
		
		try {
			//id가 존재하나 비번이 틀렸을 경우 사용자 실패 카운트를 update 한다
			if ( loginTryVo.getIdExist().equals("Y")) {
				memberVo.setMemberId(loginTryVo.getMemberId());
				memberDao.updateFailCount(memberVo);
			}
			
			loginTryDao.insert(loginTryVo);
			
		} catch ( Exception e ) {
			throw new Exception(e);
		}
		return 0;
	}

	/**
	 * 사용자 로그인 성공 처리
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
	public int updateLoginSuccess(LoginTryVo loginTryVo) throws Exception {
		MemberVo memberVo = new MemberVo();
		
		try {
			memberVo.setMemberId(loginTryVo.getMemberId());
			memberVo.setLoginFailCnt(0);
			memberDao.update(memberVo);
			
			loginTryDao.insert(loginTryVo);
			
		} catch ( Exception e ) {
			throw new Exception(e);
		}
		return 0;
	}

}
