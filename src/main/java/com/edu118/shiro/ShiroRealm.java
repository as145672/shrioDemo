package com.edu118.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu118.bean.User;
import com.edu118.service.UserService;

public class ShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	@Override		//登陆验证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("============登陆验证============");
		//获取到传递过来的用户名和密码
		String username = (String) token.getPrincipal();
		//密码传递的时候是一个char数组，获取的时候要进行转换
		String password = new String((char[])token.getCredentials());
		System.out.printf("验证的username = %s,password = %s\r\n",username,password);
		
		//数据的查询验证
		User user = userService.findUserByUsernameAndPassword(
				new User().setUsername(username).setPassword(password));
		System.out.println("查询到的user = " + user) ;
		if (null == user) {
			//查询失败
			throw new AuthenticationException("您提供的用户名或密码错误！");
		}
		//查询到数据，就把登陆用户的数据保存到Shiro中。
		return new SimpleAuthenticationInfo(username, password, "shiroRealm");
	}

	@Override	//授权验证，包括了角色验证的权限验证
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("************角色权限验证************");
		//获取到传递的用户名
		String username = (String)principals.getPrimaryPrincipal();
		Set<String> roles = userService.findUserRoleByUsername(username);
		Set<String> permissions = userService.findUserPermissionByUsername(username);
		System.out.println("roles = " + roles);
		System.out.println("permissions" + permissions);
		
		//把角色和权限给Shiro
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}
}
