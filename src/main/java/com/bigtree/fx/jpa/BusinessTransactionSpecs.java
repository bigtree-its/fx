package com.bigtree.fx.jpa;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bigtree.fx.entity.BusinessTransactionEntity;

import org.springframework.data.jpa.domain.Specification;

public class BusinessTransactionSpecs {

    public static Specification<BusinessTransactionEntity> isLongTermCustomer() {
        return new Specification<BusinessTransactionEntity>() {
            private static final long serialVersionUID = 1L;
            public Predicate toPredicate(Root<BusinessTransactionEntity> root, CriteriaQuery<?> query,
                CriteriaBuilder builder) {
    
                LocalDate date = LocalDate.now().minusYears(2);
                return builder.equal(root.get("date"), date);
            }
        };
      }
    
}