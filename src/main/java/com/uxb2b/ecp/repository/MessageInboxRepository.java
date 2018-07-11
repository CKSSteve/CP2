package com.uxb2b.ecp.repository;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.uxb2b.ecp.model.MessageInbox;




@Repository
public  interface MessageInboxRepository extends JpaRepository<MessageInbox, Integer>,PagingAndSortingRepository<MessageInbox, Integer>, JpaSpecificationExecutor<MessageInbox> {

	public Page<MessageInbox> findByReceiverRoleAndBranchId(int receiverRole,String branchId,Pageable pageRequest);
	
	public MessageInbox findByMessageId(long messageId);
	
	public int countByReceiverRoleAndBranchId(int receiverRole,String branchId);
}
