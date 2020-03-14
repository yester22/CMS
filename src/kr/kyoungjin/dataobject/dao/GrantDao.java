package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.GrantVo;

public interface GrantDao       {         
	public List<GrantVo> list ( GrantVo grantVo);
	public GrantVo view ( GrantVo grantVo);
	public void insert(GrantVo grantVo);
	public int  update(GrantVo grantVo);
	public int  delete(GrantVo grantVo);
	public int  count(GrantVo  grantVo);
}
