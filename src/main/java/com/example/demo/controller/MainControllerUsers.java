package com.example.demo.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.models.Users;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class MainControllerUsers {
	

	private UserService userService;
	@Autowired
	public MainControllerUsers(UserService userservice){
		this.userService= userservice;
	}
	
	@RequestMapping("/users")
	public String usersAdminPanel(Model model){
		model.addAttribute("users", userService.getAllUsers());
		return "/AdminPanel/users.html";
	}
	
	@RequestMapping("/users/add")
	public String userAdd(){
		return "/AdminPanel/usersEdit.html";
	}
	
	@RequestMapping("/users/save")
	public String createUser(@ModelAttribute Users user) throws IllegalAccessException, InvocationTargetException, IOException{
		System.out.println("this is the result --->"+user.getId());
//		System.out.println("this is the result --->"+user.get());
		userService.addUser(user);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value="/users/edit/{id}",method=RequestMethod.GET )
	public String usersEdit(Model model,@PathVariable("id") int id){
		model.addAttribute("userDetails", userService.findByID(id));
		System.out.println("----------that is->"+userService.findByID(id).getId());
		return "/AdminPanel/usersEdit.html";
	}
	
	@RequestMapping("/users/update")
	public String updateUser(@ModelAttribute Users user) throws IllegalAccessException, InvocationTargetException{
		userService.updateUsers(user);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value="users/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id){
		 userService.delete(id);
		 return "redirect:/admin/users";
	}
	
	@RequestMapping(value={"/loginUser"}, method=RequestMethod.GET)
	public@ResponseBody Users addUsers(Users users) throws IllegalAccessException, InvocationTargetException, IOException{
		return userService.addUser(users);
	}
}
