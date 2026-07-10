package com.anish.opensphere.security;

import com.anish.opensphere.entity.Role;
import com.anish.opensphere.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long principalId;
    private final String username;
    private final String password;
    private final boolean active;
    private final List<Long> roleIds;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.principalId = user.principal().id();
        this.username = user.email();
        this.password = user.password();
        this.active = user.active();
        this.roleIds = user.roles()
                .stream()
                .map(Role::id)
                .toList();
        this.authorities = buildAuthorities(user);
    }

    /** Used by JwtAuthenticationFilter to build principal from token claims — no DB hit. */
    public CustomUserDetails(Long principalId, String username, List<Long> roleIds,
                             Collection<? extends GrantedAuthority> authorities) {
        this.principalId = principalId;
        this.username = username;
        this.password = null;
        this.active = Boolean.TRUE;
        this.roleIds = roleIds;
        this.authorities = authorities;
    }

    private Set<GrantedAuthority> buildAuthorities(User user) {
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        user.roles().forEach(role -> {
            authoritySet.add(new SimpleGrantedAuthority(role.name()));
            role.permissions().forEach(permission ->
                    authoritySet.add(new SimpleGrantedAuthority(permission.name()))
            );
        });
        return authoritySet;
    }
}