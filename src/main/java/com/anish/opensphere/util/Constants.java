package com.anish.opensphere.util;

public class Constants {

    private Constants() { }

    public static final class Claims {

        private Claims() { }

        public static final String TOKEN_TYPE = "tokenType";
        public static final String PRINCIPAL_ID = "principalId";
        public static final String ROLE_IDS = "roleIds";
        public static final String AUTHORITIES = "authorities";
        public static final String CLAIM_TYPE_USER = "user";
        public static final String CLAIM_TYPE_CLIENT = "client";
    }

    public static final class Filters {

        private Filters() { }

        public static final String AUTHORIZATION = "Authorization";
        public static final String REQUEST_ID_HEADER = "X-Request-Id";
        public static final String MDC_KEY = "requestId";
    }

    public static final class SecurityConstants {

        private SecurityConstants() { }

        public static final String[] PUBLIC_ENDPOINTS = {
                "/api/v1/auth/**",
                "/api/v1/public",
                "/api/v1/swagger-ui/**",
        };
    }
}