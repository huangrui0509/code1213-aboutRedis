package com.example.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.UserServiceTest;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("login")
	public String toUserLogin() {
		return "userLogin";
	}
	@RequestMapping("getUserInfo")
	public String getUserInfo(String userName,String email,String id,Model model) {
		User user = new User(userName,email,Integer.parseInt(id));
		userService.setUserToRedis(user);
		
		Set<User> userFromRedis = userService.getUserFromRedis();
		model.addAttribute("users", userFromRedis);
		return "loginSuccess";
		
	}
	
	
	@RequestMapping("login2")
	public String toUserLogin2() {
		return "userByAjax";
	}
	@RequestMapping("getUserInfoByAjax")
	@ResponseBody
	public Map getUserInfoByAjax(String userName,String email,String id) {
		//System.out.println(userName);
		User user = new User(userName,email,Integer.parseInt(id));
		userService.setUserToRedis(user);
		
		Set<User> userFromRedis = userService.getUserFromRedis();
		Map map = new HashMap();
		Iterator<User> it = userFromRedis.iterator();  
		while (it.hasNext()) { 
			int i=1;
			User u = it.next();
			map.put("u"+i, u);
		}  
		//System.out.println(userFromRedis);
		//System.out.println(map);
		return map;
		
	}
	
	
}
