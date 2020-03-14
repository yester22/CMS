package kr.kyoungjin.jobs.system.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import kr.kyoungjin.common.abstractObject.AbstractService;
import kr.kyoungjin.dataobject.dao.MessageDao;
import kr.kyoungjin.dataobject.vo.MessageVo;
import kr.kyoungjin.jobs.system.message.service.IMessageService;

@Service("IMessageService")
public class MessageServiceImpl extends AbstractService implements IMessageService {

	@Autowired
	private MessageDao messageDao;

	
	@Override
	public List<MessageVo> list(MessageVo messageVo) throws Exception {
		return messageDao.list(messageVo);
	}

	@Override
	public MessageVo view(MessageVo messageVo) throws Exception {
		messageVo.setNationCode( super.getLanguage() );		
		return messageDao.view(messageVo);
	}

}
