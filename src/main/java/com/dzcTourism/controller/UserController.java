package com.dzcTourism.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dzcTourism.domain.Msg;
import com.dzcTourism.domain.User;
import com.dzcTourism.service.UserService;


/**
 * 用户信息
 * @author wangzhitong
 *
 */
@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 保存方法，使用JSP303校验
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public Msg saveUser(@RequestBody @Valid User user,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError field : fieldErrors) {
				map.put(field.getField(), field.getDefaultMessage());
			}
			return Msg.fail().add("data", map);
		}else {
			userService.saveUser(user);
			return Msg.success();
		}
	}
	
	/**
	 * 检测用户名是否用
	 * @return
	 */
	@RequestMapping(value = "/checkuser",method = RequestMethod.GET)
	public Msg checkUser(String userName) {
		//先判断用户名是否是合法的表达式;
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!userName.matches(regex)) {
			return Msg.fail().add("msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
		}
		boolean bValue = userService.checkUsername(userName);
		if(bValue) {
			return Msg.success();
		}else {
			return Msg.fail().add("msg", "用户名不可用");
		}
	}
	
	@RequestMapping(value = "/delUser",method = RequestMethod.GET)
	public Msg delUser(String id) {
		userService.deleteUser(id);
		return Msg.success();
	}
	
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST)
	public Msg updateUser(@RequestBody User user) {
		if(user != null && user.getId()!=null) {
			String id = user.getId();
			User oldUser = userService.getUserById(id);
			if(oldUser != null) {
				userService.updateUser(user);
				return Msg.success();
			}else {
				return Msg.fail();
			}
		}else {
			return Msg.fail();
		}
	}
	
	@RequestMapping(value = "/getAll",method = RequestMethod.GET)
	public Msg getUserAll() {
		List<User> userAll = userService.getUserAll();
		return Msg.success().add("data", userAll);
	}
	
	//登录
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Msg login(@RequestBody User user) {
		System.out.println("userName:"+user.getUserName()+"   password:"+user.getPassword());
		boolean flag = userService.login(user.getUserName(), user.getPassword());
		if(flag) {
			return Msg.success().add("msg", "登录成功");
		}else {
			return Msg.fail().add("msg", "用户名或密码错误");
		}
	}
	
}
