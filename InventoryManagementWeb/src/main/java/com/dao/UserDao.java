package com.dao;

import com.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getUserByName(String userName);

    void saveUser(User user);
}
