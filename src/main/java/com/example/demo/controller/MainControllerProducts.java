package com.example.demo.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Products;
import com.example.demo.models.Users;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CollectionService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class MainControllerProducts {
	
	private ProductService productservice;
	private CollectionService collectionService;
	private CategoryService categoryService;
	
	@Autowired
	public MainControllerProducts(ProductService productsservice,CollectionService collectionService,CategoryService categoryService) {
		this.productservice = productsservice;
		this.collectionService = collectionService;
		this.categoryService = categoryService;
	}
	//finish router pages
	
	@RequestMapping("/products")
	public String listProducts(Model model){
		model.addAttribute("products", productservice.getAllProducts());
		return "/AdminPanel/products.html";
	}
	
	@RequestMapping("/products/add")
	public String addNewProduct(Model model){
		model.addAttribute("optionsCollection", collectionService.getAllcollection());
		model.addAttribute("optionsCategory", categoryService.getAllCategorys());
		return "AdminPanel/productEdit.html";
	}
	
	@RequestMapping(value={"/products/save"}, method=RequestMethod.POST)
	public String addProducts(@ModelAttribute Products product) throws IOException, IllegalAccessException, InvocationTargetException{
		System.out.println("----------------------------------------");
		productservice.uploadFile(product);
		System.out.println("----------------------------------------");
		return "redirect:/admin/products";
	}
	
	@RequestMapping(value="/products/edit/{id}", method=RequestMethod.GET)
	public String editProduct(Model model,@PathVariable("id") int id){
		model.addAttribute("product",productservice.findByIdProduct(id));
		model.addAttribute("optionsCollection", collectionService.getAllcollection());
		model.addAttribute("optionsCategory", categoryService.getAllCategorys());
		return "/AdminPanel/productEdit.html";
	}
	
	@RequestMapping(value="products/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id){
		 productservice.delete(id);
		 return "redirect:/admin/products";
	}
	
	@RequestMapping(value={"/editProductParams"}, method=RequestMethod.POST)
	public String editProductParams(@ModelAttribute Products product) throws IOException, IllegalAccessException, InvocationTargetException{
		System.out.println("----------------------------------------");
		productservice.uploadFile(product);
		System.out.println("----------------------------------------");
		return "redirect:/admin/products";
	}
	
	@RequestMapping("/productsTbl")
	public String productsTbl(Model model){
		model.addAttribute("products",productservice.getAllProducts());
		return "/form/producttbl.html";
	}
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public@ResponseBody List<Products> getFourProduct(){
		return productservice.gettingFour();
	}
}
