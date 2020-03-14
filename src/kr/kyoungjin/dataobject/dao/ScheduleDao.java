package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.dataobject.vo.ScheduleVo;


public interface ScheduleDao    { 
	public List<ScheduleVo> list ( ScheduleVo scheduleVo);
	public ScheduleVo view ( ScheduleVo scheduleVo);
	public void insert(ScheduleVo scheduleVo);
	public int  update(ScheduleVo scheduleVo);
	public int  delete(ScheduleVo scheduleVo);
	public int  count(ScheduleVo  scheduleVo);
}


