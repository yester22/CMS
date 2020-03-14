package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.SiteConfigVo;

public interface SiteConfigDao  {   
	public List<SiteConfigVo> list ( SiteConfigVo siteConfigVo);
	public SiteConfigVo view ( SiteConfigVo siteConfigVo);
	public void insert(SiteConfigVo siteConfigVo);
	public int  update(SiteConfigVo siteConfigVo);
	public int  delete(SiteConfigVo siteConfigVo);
	public int  count(SiteConfigVo  siteConfigVo);
}