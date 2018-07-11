package com.uxb2b.ecp.persist;

import java.util.List;
import org.springframework.data.domain.Page;
import com.uxb2b.ecp.model.Ctbflbq2;

/**
 * 
 * CTBFLBQ2 TABLE
 * @author steve
 *
 */
public interface ICtbflbq2RepositoryPersist {
 
/**
 * 查詢
 * 條件:QUATION_NO、項次序號
 * @param pageNumber
 * @param pageSize
 * @return
 */
 public Page<Ctbflbq2> findCtbflbq2ByQuationNo(int pageNumber,int pageSize,String quationNo);

 /**
  * 查詢
  * 條件:QUATION_NO、項次序號
  * 
  * @param quationNo 報價單號碼
  * @param itemNo 項次序號
  * @return
  */
 public Ctbflbq2 findByIdQuationNoAndIdItemNo(String quationNo,int itemNo);
 
 /**
  * SAVE CTBFLBQ2(list)
  * 
  * @param ctbflbq2
  */
 public void saveCtbflbq2(List<Ctbflbq2> ctbflbq2);
 
 /**
  * SAVE CTBFLBQ2
  * 
  * @param ctbflbq2
  */
 public void saveCtbflbq2(Ctbflbq2 ctbflbq2);
 
 
 /**
  * 查詢
  * 條件:QUATION_NO
  * ORDER BY:ITEM_NO
  * 
  * @param quationNo 報價單號碼
  * @return
  */
 public List<Ctbflbq2> findByIdQuationNoOrderByIdItemNo(String quationNo);
 
 /**
  * 刪除 CTBFLBQ2
  * 條件:QUATION_NO
  * 
  * @param quationNo 報價單號碼
  */
 public void removeCtbflbq2ByQuationNo(String quationNo);
}

