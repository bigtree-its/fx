package com.bigtree.fx.jpa;

import java.time.LocalDate;
import java.util.List;

import com.bigtree.fx.entity.BusinessTransactionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessTransactionRepository extends JpaRepository<BusinessTransactionEntity, Long>, JpaSpecificationExecutor<BusinessTransactionEntity>{
   
    // @Query("FROM BizTrxEntity WHERE date between ?1 and ?2")
    List<BusinessTransactionEntity> findAllByDateBetween(LocalDate from, LocalDate to);

    @Query(value="select sum(amount),reference,transaction_type from biztrx where extract(YEAR from date)='?1' group by reference,transaction_type", nativeQuery=true)
	List<BusinessTransactionEntity> getSummary(int year);
}   