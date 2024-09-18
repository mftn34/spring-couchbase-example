package com.mtfn.spring.couchbase.example.service;

import com.mtfn.spring.couchbase.example.domain.repository.couchbase.StoreCrudRepositoryForCouchbase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class CouchbaseStoreService implements Serializable {

    private final StoreCrudRepositoryForCouchbase storeCrudRepositoryForCouchbase;

    private final StoreService storeService;

    public void save(String storeCode) {
        var store = storeService.getStoreDetail(storeCode);
        var couchbaseDoc = com.mtfn.spring.couchbase.example.domain.entity.couchbase
                .Store
                .builder()
                .id(store.getId())
                .code(store.getCode())
                .phoneNumber(store.getPhoneNumber())
                .build();

        storeCrudRepositoryForCouchbase.save(couchbaseDoc);
    }
}
