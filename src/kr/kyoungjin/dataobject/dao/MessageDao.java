package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.MessageVo;

@Mapper
public interface MessageDao {
	public List<MessageVo> list ( MessageVo messageVo);
	public MessageVo view ( MessageVo messageVo);
	public void insert(MessageVo messageVo);
	public int  update(MessageVo messageVo);
	public int  delete(MessageVo messageVo);
	public int  count(MessageVo messageVo);
}
