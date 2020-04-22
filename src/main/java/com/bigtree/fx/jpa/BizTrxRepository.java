package com.bigtree.fx.jpa;

import com.bigtree.fx.entity.BizTrxEntity;

import org.springframework.data.repository.CrudRepository;

public interface BizTrxRepository extends CrudRepository<BizTrxEntity, Long>{

}