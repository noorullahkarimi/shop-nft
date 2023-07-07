package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Authorities;
import com.example.demo.service.AuthoritiesService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class MainControllerAuthority {
	
	private AuthoritiesService authoritiesService;
	private UserService userService;
	
	public MainControllerAuthority(AuthoritiesService authoritiesServic , UserService userServices){
		this.authoritiesService = authoritiesServic;
		this.userService = userServices;
	}

	@RequestMapping("/authorities" )
	public String listAuthorities(Model model){
		model.addAttribute("authorities", authoritiesService.getAllAuthorities());
		return "/AdminPanel/authorities.html";
	}
	
	@RequestMapping("/authorities/add" )
	public String addAuthorities(Model model){
		model.addAttribute("user", userService.getAllUsers());
		return "/AdminPanel/authoritiesEdit.html";
	}
	
	@RequestMapping(value="/authorities/edit/{id}",method=RequestMethod.GET)
	public String editAuthorities(Model model,@PathVariable("id") int id){
		model.addAttribute("authorities", authoritiesService.findByIDAuthorities(id));
		return "/AdminPanel/authoritiesEdit.html";
	}

	@RequestMapping(value="authorities/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id){
		 authoritiesService.delete(id);
		 return "redirect:/admin/authorities";
		 
	}
	
	@RequestMapping("/authorities/register")
	public String addRoles(Authorities authorities){
		System.out.println("this is data -->>"+authorities.getUsername());
		System.out.println("this is data -->>"+authorities.getAuthorities());
		String place[] = authorities.getAuthorities().split(",");
		for(int i = 0 ;i<place.length;i++){
		System.out.println("----------------------"+i+"--------------------");
		 authoritiesService.add(authorities.getUsername(), place[i]);
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		authoritiesService.save(authorities);
		return "redirect:/admin/authorities";
	}
}
