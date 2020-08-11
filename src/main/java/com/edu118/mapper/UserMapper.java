package com.edu118.mapper;

import java.util.Set;

import com.edu118.bean.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{
	public User findByName(String userName);
	public Set<String> findAllRoleByUser(Integer uid);
	public Set<String> findAllpermissionByUser(Integer uid);
	
	Set<String> findUserRoleByUsername(String username);
	Set<String> findUserPermissionByUsername(String username);

}
