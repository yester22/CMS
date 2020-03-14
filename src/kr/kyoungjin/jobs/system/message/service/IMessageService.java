package kr.kyoungjin.jobs.system.message.service;

import java.util.List;

import kr.kyoungjin.dataobject.vo.MessageVo;

public interface IMessageService {
	public List<MessageVo> list ( MessageVo messageVo) throws Exception;
	public MessageVo view ( MessageVo messageVo) throws Exception;
}
