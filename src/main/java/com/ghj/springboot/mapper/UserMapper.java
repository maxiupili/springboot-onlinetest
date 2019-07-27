package com.ghj.springboot.mapper;

import com.ghj.springboot.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User getUserById(Integer id);

    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    @Select("select * from user")
    List<User> getAllUsers();

    @Insert("insert into user(name,username,password,usertype) values(#{name},#{username},#{password},#{usertype})")
    void addUser(String name,String username,String password,String usertype);

    @Update("update user set name=#{name},username=#{username},password=#{password},usertype=#{usertype} where id=#{id}")
    void editUser(Integer id,String name,String username,String password,String usertype);

    @Delete("delete from user where id=#{id}")
    void deleteUserById(Integer id);

}
