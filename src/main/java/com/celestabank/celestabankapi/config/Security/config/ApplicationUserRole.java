package com.celestabank.celestabankapi.config.Security.config;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.celestabank.celestabankapi.config.Security.config.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    CUSTOMER(Sets.newHashSet(CUSTOMER_READ,ACCOUNT_READ)),
    ADMIN(Sets.newHashSet(CUSTOMER_READ,CUSTOMER_WRITE,ACCOUNT_READ,ACCOUNT_WRITE)),
    AGENT(Sets.newHashSet(MAKE_TRANSFER,CUSTOMER_READ,CUSTOMER_WRITE,ACCOUNT_WRITE ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public  Set<ApplicationUserPermission> getPermissions(){
        return  permissions;
    }

    public Set<SimpleGrantedAuthority> getGranedAutorities(){
        Set<SimpleGrantedAuthority> permissions  =getPermissions().stream().map(permission-> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return  permissions;
    }
}
