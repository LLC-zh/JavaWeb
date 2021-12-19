package com.web.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.model.User;

public interface UserDao extends Serializable {
	
	//查询所有用户信息方法
	public List<Map<String,Object>> selectUserList();
	//按UUid查询用户信息
	public User selectUserByUUid(String uuid);
	//查询用户名是否存在
	public boolean selectUserByName(String userName);
	//查询用户登录信息是否存在
	public User selectUserLogin(@Param("userName") String userName, @Param("userPassword") String userPassword); 
	//插入新用户
	public int insertUser(User user);
	//修改用户信息
	public int updateUser(User user);
	//删除用户信息
	public int deleteUserByUUid(String uuid);
}
