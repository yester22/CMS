package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.CompanyVo;

public interface CompanyDao {
	public List<CompanyVo> list ( CompanyVo companyVo);
	public CompanyVo view ( CompanyVo companyVo);
	public void insert(CompanyVo companyVo);
	public int  update(CompanyVo companyVo);
	public int  delete(CompanyVo companyVo);
	public int  count(CompanyVo  companyVo);
}
