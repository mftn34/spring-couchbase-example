package com.mtfn.spring.couchbase.example.domain.repository.couchbase;

import com.mtfn.spring.couchbase.example.domain.entity.couchbase.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// extend crud repository for N1QL
@Repository
public interface StoreCrudRepositoryForCouchbase extends CrudRepository<Store, String> {

}
