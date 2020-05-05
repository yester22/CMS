package kr.kyoungjin.jobs.member.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 *사용자 목록 가져오기
	 */
	@Override
	public List<MemberVo> getMemberList(Map<String, Object> param) throws Exception {
		return memberDao.list(param);
	}

	@Override
	public long getMemberListCount(Map<String, Object> param) throws Exception {
		return memberDao.selectMembercount(param);
	}

	@Override
	public MemberVo getMember(Map<String, Object> params) throws Exception {
		return memberDao.selectMember(params);
	}

	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		MemberVo paramVo = new MemberVo();
		paramVo.setMemberId(params.get("memberId").toString());
		return memberDao.count(paramVo);
	}

	@Override
	public int saveMember(Map<String, Object> params) throws Exception {

		MemberVo writeInfo = new MemberVo();
		writeInfo.setMemberId(params.get("memberId").toString());
		writeInfo.setMemberNm(params.get("memberNm").toString());
		writeInfo.setMemberType(params.get("memberType").toString());
		writeInfo.setRegId( params.get("regId").toString() );
		writeInfo.setChkPwCode("");
		writeInfo.setChkIdCode("");
		writeInfo.setLastLoginDt(null);
		writeInfo.setLastLogoutDt(null);
		
		String regType = params.get("mode").toString();
		if (regType == null || regType.equals("")) return 0;
		
		int nRtnVal = 0;
		if (regType.equals("REG")) {
			writeInfo.setPwd(params.get("encriptionMemberPw").toString());
			this.memberDao.insert(writeInfo);
			nRtnVal = 1;
		}else {
			writeInfo.setUptId( params.get("regId").toString() );
			nRtnVal = this.memberDao.update(writeInfo);	
		}

		return nRtnVal;
	}
	
}
