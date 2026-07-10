package com.anish.opensphere.security.util;

import com.anish.opensphere.config.properties.JwtProperties;
import com.anish.opensphere.security.CustomUserDetails;
import com.anish.opensphere.util.Constants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof CustomUserDetails principal)) {
            throw new IllegalStateException("Authentication principal is not a CustomUserDetails instance");
        }

        List<Long> roleIds = principal.getRoleIds();
        List<String> authorities = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Instant now = Instant.now();
        return Jwts.builder()
                .subject(principal.getUsername())
                .issuer(jwtProperties.issuer())
                .claim(Constants.Claims.TOKEN_TYPE, Constants.Claims.CLAIM_TYPE_USER)
                .claim(Constants.Claims.PRINCIPAL_ID, principal.getPrincipalId())
                .claim(Constants.Claims.ROLE_IDS, roleIds)
                .claim(Constants.Claims.AUTHORITIES, authorities)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(jwtProperties.expirationMs())))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateClientToken(String clientId, Long principalId, List<String> scopes) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(clientId)
                .issuer(jwtProperties.issuer())
                .claim(Constants.Claims.TOKEN_TYPE, Constants.Claims.CLAIM_TYPE_CLIENT)
                .claim(Constants.Claims.PRINCIPAL_ID, principalId)
                .claim(Constants.Claims.AUTHORITIES, scopes)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(jwtProperties.expirationMs())))
                .signWith(getSigningKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    public String getTokenType(String token) {
        return parseClaims(token).get(Constants.Claims.TOKEN_TYPE, String.class);
    }

    public Long getPrincipalIdFromToken(String token) {
        return parseClaims(token).get(Constants.Claims.PRINCIPAL_ID, Long.class);
    }

    public List<Long> getRoleIdsFromToken(String token) {
        List<?> raw = parseClaims(token).get(Constants.Claims.ROLE_IDS, List.class);
        return raw.stream()
                .map(v->((Number) v).longValue())
                .toList();
    }

    public List<String> getAuthoritiesFromToken(String token) {
        List<?> raw = parseClaims(token).get(Constants.Claims.AUTHORITIES, List.class);
        return raw.stream()
                .map(String.class::cast)
                .toList();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return Boolean.TRUE;
        } catch (ExpiredJwtException e) {
            log.error("JWT token expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT token: {}", e.getMessage());
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return Boolean.FALSE;
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
