package com.uxb2b.ecp.persist;

/**
 * 傳送電文
 * 
 * @author steve
 *
 */
public interface ISendMessagePersist {
	
	public String sendMessage(String url,String jsonMessage,String txId)throws Exception;

	public String daemonMessage(String url,String jsonMessage);
}

