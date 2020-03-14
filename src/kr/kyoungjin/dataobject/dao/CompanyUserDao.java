package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.CompanyUserVo;

public interface CompanyUserDao {
	public List<CompanyUserVo> list ( CompanyUserVo companyUserVo);
	public CompanyUserVo view ( CompanyUserVo companyUserVo);
	public void insert(CompanyUserVo companyUserVo);
	public int  update(CompanyUserVo companyUserVo);
	public int  delete(CompanyUserVo companyUserVo);
	public int  count(CompanyUserVo  companyUserVo);
}
