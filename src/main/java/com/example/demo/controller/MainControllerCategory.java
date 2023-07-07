package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.Categorys;
import com.example.demo.models.Collection;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("admin")
public class MainControllerCategory {

	private CategoryService categorysService;
	private UserService userService;
	
	@Autowired
	public MainControllerCategory(CategoryService categoryService, UserService usersService){
		this.categorysService = categoryService;
		this.userService = usersService;
	}
	
	@RequestMapping("/categories")
	public String categoryTable(Model model){
		model.addAttribute("categories", categorysService.getAllCategorys());
		return "/AdminPanel/category.html";
	}
	
	@RequestMapping("/categories/add")
	public String addingCategory(Model model){
		model.addAttribute("optionCateories", userService.getAllUsers());
		return "/AdminPanel/categoryEdit.html";
	}
	
	@RequestMapping(value="/categories/save", method=RequestMethod.GET)
	public String rediretToCategoryTable(@ModelAttribute Categorys categories){
		categorysService.addCategory(categories);
		return "redirect:/admin/categories";
	}
	
	@RequestMapping(value="/categories/edit/{id}", method=RequestMethod.GET)
	public String editingTheCategory(Model model, @PathVariable("id") int id){
		model.addAttribute("categories", categorysService.editById(id));
		model.addAttribute("optionCateories", userService.getAllUsers());
		return "/AdminPanel/categoryEdit.html";
	}
	
	@RequestMapping(value="categories/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id){
		 categorysService.delete(id);
		 return "redirect:/admin/categories";
		 
	}
	
	@RequestMapping(value="/categories/update", method=RequestMethod.GET)
	public String saveChange(@ModelAttribute Categorys category) throws IllegalAccessException, InvocationTargetException{
		categorysService.updateCategory(category);
		return "redirect:/admin/categories";
	}
	
//	editCategoryParams
}
