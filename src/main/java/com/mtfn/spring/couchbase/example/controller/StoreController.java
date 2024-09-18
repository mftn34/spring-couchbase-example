package com.mtfn.spring.couchbase.example.controller;

import com.mtfn.spring.couchbase.example.service.CouchbaseStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/stores", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class StoreController {

    private final CouchbaseStoreService couchbaseStoreService;

    @PostMapping("/detail")
    public void saveStoreDetail(@RequestParam String storeCode) {
        couchbaseStoreService.save(storeCode);
    }
}
