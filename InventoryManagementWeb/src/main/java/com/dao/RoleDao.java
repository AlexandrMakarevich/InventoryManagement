package com.dao;

import com.entity.Role;

import java.util.List;

public interface RoleDao {

    void saveRole(Role role);

    List<Role> getRoleByName(String roleName);
}
