package com.uxb2b.ecp.persist.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.MessageInbox;

import com.uxb2b.ecp.persist.IMessageInboxRepositoryPersist;
import com.uxb2b.ecp.repository.MessageInboxRepository;


@Repository
@Transactional
public class MessageInboxRepositoryPersistImpl implements IMessageInboxRepositoryPersist{

	
	@Autowired
	MessageInboxRepository messageInboxRepository;
	
	@Override
	public Page<MessageInbox> findMessageInboxByReceiverRole(int receiverRole,String branchId ,int pageNumber, int pageSize) {
		
		Pageable pageRequest = buildPageRequest(pageNumber,pageSize);
		
		return messageInboxRepository.findByReceiverRoleAndBranchId(receiverRole,branchId,pageRequest);
	}
	
	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize,new Sort(Direction.DESC,"messageTime"));
	}


	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteMessageInbox(MessageInbox MessageInbox) {
		
		messageInboxRepository.delete(MessageInbox);
	}

	@Override
	public MessageInbox findMessageInboxByMessageId(long messageId) {
		
		return messageInboxRepository.findByMessageId(messageId);
	}

	@Override
	public int getMessageInboxCountByReceiverRole(int receiverRole,String branchId) {
		
		return messageInboxRepository.countByReceiverRoleAndBranchId(receiverRole,branchId);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveMessageInbox(MessageInbox messageInbox) {
		
		messageInboxRepository.save(messageInbox);
		
	}

	
}
