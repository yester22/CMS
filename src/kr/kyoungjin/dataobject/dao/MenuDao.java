package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.MenuVo;

public interface MenuDao        {     
	public List<MenuVo> list ( MenuVo memuVo);
	public MenuVo view ( MenuVo memuVo);
	public void insert(MenuVo memuVo);
	public int  update(MenuVo memuVo);
	public int  delete(MenuVo memuVo);
	public int  count(MenuVo  memuVo);
}

