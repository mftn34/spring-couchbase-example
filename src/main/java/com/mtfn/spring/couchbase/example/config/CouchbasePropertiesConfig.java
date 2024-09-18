package com.mtfn.spring.couchbase.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class CouchbasePropertiesConfig {

    @Value("${couchbase.properties.connection-string}")
    private String couchbaseConnectionString;

    @Value("${couchbase.properties.username}")
    private String couchbaseUsername;

    @Value("${couchbase.properties.password}")
    private String couchbasePassword;

    @Value("${couchbase.properties.bucket-name}")
    private String couchbaseBucketName;

    @Value("${couchbase.properties.scope-name}")
    private String couchbaseScopeName;

    @Value("${couchbase.properties.cacheable-collection}")
    private String cacheableCollection;
}
