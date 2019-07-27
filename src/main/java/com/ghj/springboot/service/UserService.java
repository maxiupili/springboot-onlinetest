package com.ghj.springboot.service;

import com.ghj.springboot.model.User;

import java.util.List;


public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @param usertype
     * @return 成功返回用户 否则返回null
     */
    User login(String username,String password,String usertype);

    /**
     * 依据用户id查询用户
     * @param id
     * @return user
     */
    User getUserById(Integer id);

    /**
     * 依据用户id查询用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 查询所有用户
     * @return 用户集
     */
    List<User> getAllUsers();

    /**
     * 插入用户
     * @param name
     * @param username
     * @param password
     * @param usertype
     */
    void addUser(String name,String username,String password,String usertype);

    /**
     * 编辑用户
     * @param id
     * @param name
     * @param username
     * @param password
     * @param usertype
     */
    void editUser(Integer id,String name,String username,String password,String usertype);

    /**
     * 删除用户
     * @param id
     */
    void deleteUserById(Integer id);

}
