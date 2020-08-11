package com.edu118.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu118.bean.User;
import com.edu118.mapper.UserMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
//	public User get(String userName) throws Exception {
//		return userMapper.findByName(userName);
//	}
//
//	public Map<String, Object> listAuthByUser(Integer uid) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("allRole", userMapper.findAllRoleByUser(uid));
//		map.put("allPermission", userMapper.findAllpermissionByUser(uid));
//		return map;
//	}
	
	public Set<String> findUserRoleByUsername(String username){
		return userMapper.findUserRoleByUsername(username);
	}
	
	public Set<String> findUserPermissionByUsername(String username){
		return userMapper.findUserPermissionByUsername(username);
	}
	
	public User findUserByUsernameAndPassword(User user) {
		Example example = new Example(User.class);
		example.createCriteria()
		.andEqualTo("username", user.getUsername())
		.andEqualTo("password",user.getPassword());
		return userMapper.selectOneByExample(example);
	}
}
