package com.internship.producer.security;

public enum UserPermission {

    PRODUCT_WRITE("product:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
