package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.GrantMenuVo;

public interface GrantMenuDao   {   
	public List<GrantMenuVo> list ( GrantMenuVo grantMenuVo);
	public GrantMenuVo view ( GrantMenuVo grantMenuVo);
	public void insert(GrantMenuVo grantMenuVo);
	public int  update(GrantMenuVo grantMenuVo);
	public int  delete(GrantMenuVo grantMenuVo);
	public int  count(GrantMenuVo  grantMenuVo);
}




