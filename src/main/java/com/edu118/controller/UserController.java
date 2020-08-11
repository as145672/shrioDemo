package com.edu118.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@GetMapping("/login")
	public ModelAndView	login(String username,String password) {
		System.out.println("登陆操作");
		System.out.printf("username = %s,password = %s\r\n",username,password);
		
		//收集数据，封装成令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		//令牌的传递
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return null;
	}
	
	@GetMapping("/addUser")
	@RequiresAuthentication
	public ModelAndView	addUser() {
		System.out.println("添加用户数据！");
		return null;
	}
	
	@GetMapping("/updateUser")
	@RequiresRoles(value = {"aaa","dept"},logical = Logical.OR)
	public ModelAndView updateUser() {
		System.out.println("修改用户数据！");
		return null;
	}
	
	@GetMapping("/deleteUser")
	@RequiresRoles("dept")
	@RequiresPermissions("empl:query")
	public ModelAndView delete() {
		System.out.println("删除用户数据！");
		return null;
	}
	
	@GetMapping("/delete2")
	@RequiresUser
	public ModelAndView delete2() {
		System.out.println("删除用户数据！");
		return null;
	}
}
