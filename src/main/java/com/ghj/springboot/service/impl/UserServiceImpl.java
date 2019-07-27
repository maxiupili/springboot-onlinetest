package com.ghj.springboot.service.impl;

import com.ghj.springboot.mapper.UserMapper;
import com.ghj.springboot.model.User;
import com.ghj.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String username, String password, String usertype) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            if (user.getPassword().equals(password)&&user.getUsertype().equals(usertype)) {
                return user;
            } else {
                return null;
            }
        }

    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void addUser(String name, String username, String password, String usertype) {
        userMapper.addUser(name,username,password,usertype);
    }

    @Override
    public void editUser(Integer id, String name, String username, String password, String usertype) {
        userMapper.editUser(id,name,username,password,usertype);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }
}
