package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.TermsVo;

public interface TermsDao       {     
	public List<TermsVo> list ( TermsVo termsVo);
	public TermsVo view ( TermsVo termsVo);
	public void insert(TermsVo termsVo);
	public int  update(TermsVo termsVo);
	public int  delete(TermsVo termsVo);
	public int  count(TermsVo  termsVo);

}
