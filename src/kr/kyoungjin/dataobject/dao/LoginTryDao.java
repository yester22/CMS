package kr.kyoungjin.dataobject.dao;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.LoginTryVo;

@Mapper
public interface LoginTryDao {
	public void insert(LoginTryVo loginTryVo) throws Exception;
	public int  update(LoginTryVo loginTryVo) throws Exception;
	public int  delete(LoginTryVo loginTryVo) throws Exception;
	public int  count(LoginTryVo  loginTryVo) throws Exception;
}
