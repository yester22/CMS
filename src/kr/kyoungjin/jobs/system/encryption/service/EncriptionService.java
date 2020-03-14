package kr.kyoungjin.jobs.system.encryption.service;

public interface EncriptionService {
	public String decode ( String key ) throws Exception;
	public String encode ( String key ) throws Exception;

}
