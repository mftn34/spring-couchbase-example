package com.mtfn.spring.couchbase.example.domain.entity.couchbase;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.repository.Collection;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Setter
@Getter
@Document(expiry = 30, expiryUnit = TimeUnit.MINUTES)
@Collection("store")
@Builder
public class Store implements Serializable {

    @Serial
    private static final long serialVersionUID = 982331198303288293L;

    @Id
    private Long id;

    private String code;

    private String name;

    private String phoneNumber;
}