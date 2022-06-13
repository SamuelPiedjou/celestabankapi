package com.celestabank.celestabankapi.config.Security.config;




public enum ApplicationUserPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    ACCOUNT_READ("account:read"),
    ACCOUNT_WRITE("account:write"),
    MAKE_TRANSFER("account:tranfer");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission(){return permission;}
}
