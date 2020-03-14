package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.TermsAgreeVo;

public interface TermsAgreeDao  {       
	public List<TermsAgreeVo> list ( TermsAgreeVo termsAgreeVo);
	public TermsAgreeVo view ( TermsAgreeVo termsAgreeVo);
	public void insert(TermsAgreeVo termsAgreeVo);
	public int  update(TermsAgreeVo termsAgreeVo);
	public int  delete(TermsAgreeVo termsAgreeVo);
	public int  count(TermsAgreeVo  termsAgreeVo);
}
