package com.mtfn.spring.couchbase.example.config;

import com.couchbase.client.java.codec.JsonTranscoder;
import com.couchbase.client.java.codec.Transcoder;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.metrics.micrometer.MicrometerMeter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mtfn.spring.couchbase.example.enums.Caches;
import io.micrometer.core.instrument.Metrics;
import org.springframework.boot.autoconfigure.cache.CouchbaseCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.cache.CouchbaseCacheConfiguration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Duration;


@Configuration
@EnableTransactionManagement
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    public CouchbaseConfig(CouchbasePropertiesConfig couchbasePropertiesConfig) {
        this.couchbasePropertiesConfig = couchbasePropertiesConfig;
    }

    private final CouchbasePropertiesConfig couchbasePropertiesConfig;

    @Override
    public String getConnectionString() {
        return couchbasePropertiesConfig.getCouchbaseConnectionString();
    }

    @Override
    public String getUserName() {
        return couchbasePropertiesConfig.getCouchbaseUsername();
    }

    @Override
    public String getPassword() {
        return couchbasePropertiesConfig.getCouchbasePassword();
    }

    @Override
    public String getBucketName() {
        return couchbasePropertiesConfig.getCouchbaseBucketName();
    }

    @Override
    public String getScopeName() {
        return couchbasePropertiesConfig.getCouchbaseScopeName();
    }

    @Override
    protected void configureEnvironment(ClusterEnvironment.Builder builder) {
        var timeouts = new CouchbaseProperties.Timeouts();

        // timeout configurations..
        builder.timeoutConfig(config -> config.kvTimeout(timeouts.getKeyValue())
                .analyticsTimeout(timeouts.getAnalytics())
                .kvDurableTimeout(timeouts.getKeyValueDurable())
                .queryTimeout(timeouts.getQuery())
                .viewTimeout(timeouts.getView())
                .searchTimeout(timeouts.getSearch())
                .managementTimeout(timeouts.getManagement())
                .connectTimeout(timeouts.getConnect())
                .disconnectTimeout(timeouts.getDisconnect()));

        // micrometer configurations..
        builder.meter(MicrometerMeter.wrap(Metrics.globalRegistry));
    }

    @Bean
    public CouchbaseCacheManagerBuilderCustomizer couchbaseCacheManagerBuilderCustomizer(ObjectMapper objectMapper) {
        var couchbaseObjectMapper = couchbaseObjectMapper(objectMapper);
        var objectMapperSerializer = new CouchbaseJsonSerializer(couchbaseObjectMapper);
        var transcoder = JsonTranscoder.create(objectMapperSerializer);

        return builder -> builder
                .withCacheConfiguration(Caches.ETERNAL,
                        getCacheConfiguration(Duration.ofSeconds(Caches.ETERNAL_TTL),
                                couchbasePropertiesConfig.getCacheableCollection(),
                                transcoder))
                .withCacheConfiguration(Caches.ONE_MINUTE,
                        getCacheConfiguration(Duration.ofSeconds(Caches.ONE_MINUTE_TTL),
                                couchbasePropertiesConfig.getCacheableCollection(),
                                transcoder))
                .withCacheConfiguration(Caches.FIVE_MINUTES,
                        getCacheConfiguration(Duration.ofSeconds(Caches.FIVE_MINUTES_TTL),
                                couchbasePropertiesConfig.getCacheableCollection(),
                                transcoder))
                .withCacheConfiguration(Caches.FIFTEEN_MINUTES,
                        getCacheConfiguration(Duration.ofSeconds(Caches.FIFTEEN_MINUTES_TTL),
                                couchbasePropertiesConfig.getCacheableCollection(),
                                transcoder))
                .withCacheConfiguration(Caches.HALF_HOUR,
                        getCacheConfiguration(Duration.ofSeconds(Caches.HALF_HOUR_TTL),
                                couchbasePropertiesConfig.getCacheableCollection(),
                                transcoder))
                .withCacheConfiguration(Caches.ONE_HOUR,
                        getCacheConfiguration(Duration.ofSeconds(Caches.ONE_HOUR_TTL),
                                couchbasePropertiesConfig.getCacheableCollection(),
                                transcoder))
                .withCacheConfiguration(Caches.ONE_DAY,
                        getCacheConfiguration(Duration.ofSeconds(Caches.ONE_DAY_TTL),
                                couchbasePropertiesConfig.getCacheableCollection(),
                                transcoder)).build();
    }

    private ObjectMapper couchbaseObjectMapper(ObjectMapper objectMapper) {
        var couchbaseObjectMapper = objectMapper.copy();

        var deserializerModule = new SimpleModule();
        deserializerModule.addDeserializer(ResponseEntity.class, new ResponseEntityDeserializer());
        couchbaseObjectMapper.registerModule(deserializerModule);

        couchbaseObjectMapper.activateDefaultTyping(
                couchbaseObjectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);

        return couchbaseObjectMapper;
    }

    private CouchbaseCacheConfiguration getCacheConfiguration(Duration ttl, String collection,
                                                              Transcoder transcoder) {

        return CouchbaseCacheConfiguration.defaultCacheConfig()
                .valueTranscoder(transcoder)
                .collection(collection)
                .entryExpiry(ttl);
    }
}