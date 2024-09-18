package com.mtfn.spring.couchbase.example.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Caches {

    public static final String ETERNAL = "ETERNAL";
    public static final String ONE_MINUTE = "ONE_MINUTE";
    public static final String FIVE_MINUTES = "FIVE_MINUTES";
    public static final String FIFTEEN_MINUTES = "FIFTEEN_MINUTES";
    public static final String HALF_HOUR = "HALF_HOUR";
    public static final String ONE_HOUR = "ONE_HOUR";
    public static final String ONE_DAY = "ONE_DAY";
    public static final String ONE_WEEK = "ONE_WEEK";


    public static final int ETERNAL_TTL = 0;
    public static final int ONE_MINUTE_TTL = 60;
    public static final int FIVE_MINUTES_TTL = ONE_MINUTE_TTL * 5;
    public static final int FIFTEEN_MINUTES_TTL = ONE_MINUTE_TTL * 15;
    public static final int HALF_HOUR_TTL = ONE_MINUTE_TTL * 30;
    public static final int ONE_HOUR_TTL = ONE_MINUTE_TTL * 60;
    public static final int ONE_DAY_TTL = ONE_HOUR_TTL * 24;
    public static final int ONE_WEEK_TTL = ONE_DAY_TTL * 7;

    @Getter
    @AllArgsConstructor
    public enum CacheDefinition {
        ETERNAL(Caches.ETERNAL, ETERNAL_TTL),
        ONE_MINUTE(Caches.ONE_MINUTE, ONE_MINUTE_TTL),
        FIVE_MINUTES(Caches.FIVE_MINUTES, FIVE_MINUTES_TTL),
        FIFTEEN_MINUTES(Caches.FIFTEEN_MINUTES, FIFTEEN_MINUTES_TTL),
        HALF_HOUR(Caches.HALF_HOUR, HALF_HOUR_TTL),
        ONE_HOUR(Caches.ONE_HOUR, ONE_HOUR_TTL),
        ONE_DAY(Caches.ONE_DAY, ONE_DAY_TTL),
        ONE_WEEK(Caches.ONE_WEEK, ONE_WEEK_TTL);

        private final String name;

        private final int ttl;

    }
}
