package com.example.demo.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.Collection;
import com.example.demo.service.CollectionService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class MainControllerCollections {

	private CollectionService collectionService;
	private UserService userService;
	
	@Autowired
	public MainControllerCollections(CollectionService collectionService, UserService userService){
		this.collectionService = collectionService;
		this.userService = userService;
	}
	
	@RequestMapping("/collections")
	public String collectionAdminList(Model model){
		model.addAttribute("collections", collectionService.getAllcollection());
		return "/AdminPanel/collection.html";
	}
	
	@RequestMapping("/collections/add")
	public String collectionAdd(Model model){
		model.addAttribute("optionUsers", userService.getAllUsers());
		return "/AdminPanel/collectionEdit.html";
	}
	
	@RequestMapping("/collections/save")
	public String createCollection(Collection collection) throws IOException, IllegalAccessException, InvocationTargetException{
		collectionService.addCollection(collection);
		return "redirect:/admin/collections";
	}
	
	@RequestMapping("/collections/edit/{id}")
	public String collectionEdit(@PathVariable("id") int id, Model model){
		model.addAttribute("collection", collectionService.getCollection(id));
		model.addAttribute("optionUsers", userService.getAllUsers());
		return "/AdminPanel/collectionEdit.html";
	}
	
	@RequestMapping(value="collections/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id){
		 collectionService.delete(id);
		 return "redirect:/admin/collections";
	}
	
}
