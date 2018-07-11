package com.uxb2b.ecp.persist;

import org.springframework.data.domain.Page;
import com.uxb2b.ecp.model.MessageInbox;

/**
 * MESSAGE_INBOX TABLE
 * @author steve
 *
 */
public interface IMessageInboxRepositoryPersist {
	
	/**
	 * 查詢MESSAGE_INBOX TABLE
	 * 條件:RECEIVER_ROLE、BRANCH_ID
	 * 
	 * @param receiverRole 訊息接收者
	 * @param branchId 分行代號
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<MessageInbox> findMessageInboxByReceiverRole(int receiverRole,String branchId, int pageNumber, int pageSize) ;
	
	/**
	 * DELETE MESSAGE_INBOX
	 * 
	 * @param MessageInbox
	 */
	public void deleteMessageInbox(MessageInbox MessageInbox);
	
	/**
	 * 查詢MESSAGE_INBOX TABLE
	 * 條件:MESSAGE_ID
	 * 
	 * @param messageId 訊息匣流水編號
	 * @return
	 */
	public MessageInbox findMessageInboxByMessageId(long messageId);

	/**
	 * 取得MESSAGE_INBOX TABLE 資料筆數 
	 * 條件:BRANCH_ID、RECEIVER_ROLE
	 * 
	 * @param receiverRole 訊息接收者
	 * @param branchId 分行代號
	 * @return
	 */
	public int getMessageInboxCountByReceiverRole(int receiverRole,String branchId);
	
	/**
	 * SAVE MESSAGE_INBOX
	 * 
	 * @param messageInbox
	 */
	public void saveMessageInbox(MessageInbox messageInbox);

}
