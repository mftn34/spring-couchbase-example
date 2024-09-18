package com.mtfn.spring.couchbase.example.domain.repository.jpa;

import com.mtfn.spring.couchbase.example.domain.entity.jpa.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByCode(String code);
}