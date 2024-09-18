package com.mtfn.spring.couchbase.example.service;

import com.mtfn.spring.couchbase.example.domain.entity.jpa.Store;
import com.mtfn.spring.couchbase.example.exception.StoreNotFoundException;
import com.mtfn.spring.couchbase.example.domain.repository.jpa.StoreRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class StoreService implements Serializable {

    private final StoreRepository storeRepository;

    @Transactional
    //Since it was a simple example, there was no need to return dto
    public Store getStoreDetail(String storeCode) {
        return storeRepository.findByCode(storeCode).orElseThrow(() -> new StoreNotFoundException("Store not found"));
    }
}
