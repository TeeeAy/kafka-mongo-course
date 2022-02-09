package com.internship.producer.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum UserRole {

    ADMIN(Sets.newHashSet(UserPermission.PRODUCT_WRITE));

    UserRole(Set<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    private final Set<UserPermission> userPermissions;

    public Set<UserPermission> getUserPermissions() {
        return userPermissions;
    }


}
