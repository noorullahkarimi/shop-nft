package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.Authorities;
import com.example.demo.models.Collection;
import com.example.demo.models.Users;
import com.example.demo.service.CollectionService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class Router {
	
	private ProductService productservice;
	private CollectionService collectionService;
	private UserService userService;
	
	@Autowired
	public Router(ProductService productsservice, CollectionService collectionService, UserService userService) {
		this.productservice = productsservice;
		this.collectionService = collectionService;
		this.userService = userService;
	}

	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("nfts",productservice.gettingFour());
//		List<Object[]> collect = collectionService.getThreeCollection();
////		Integer newArr[] = new Integer[3];
////		for(int i =0; i<collect.size();i++){
////			System.out.println(collect.get(i).getCreator());
////			newArr[i] = collect.get(i).getCreator();
////		}
//		for (int i=0; i<collect.size(); i++){
//			   Object[] row = (Object[]) collect.get(i);
//			   System.out.println("Element "+i+Arrays.toString(row));
//			}
//		
//		System.out.println("____________________________________________"+collect.get(1).length);
		model.addAttribute("collection", collectionService.getThreeCollection());
//		model.addAttribute("authurCollections",userService.getThreeCreatorCollections(newArr) );
		model.addAttribute("users", userService.getThree());
		return "/NFTMarket/index.html";
	}
	
	@RequestMapping("/login")
	public String login(Model model){
		return "/AdminPanel/loginAdminPanel.html";
	}
	
	@RequestMapping("/redirect")
	public String redirectToHomePage(Model model){
		model.addAttribute("products",productservice.getAllProducts());
		return "redirect:/";
	}
	@RequestMapping("/admin")
	public String adminPanel(Model model){
		return "/AdminPanel/admin.html";
	}
	
	@RequestMapping("/notfound")
	public String Notfound(Model model){
		return "/NFTMarket/404.html";
	}
	
	@RequestMapping("/productsList")
	public String productsList(Model model){
		model.addAttribute("products", productservice.getAllProducts());
		return "/NFTMarket/marketplace.html";
	}
	
	@RequestMapping("/collectionList")
	public String collectionList(Model model){
		model.addAttribute("collection", collectionService.getAllcollection());
		return "/NFTMarket/collection.html";
	}
	
	@RequestMapping("/artists")
	public String artists(Model model){
		model.addAttribute("users", userService.getAllUsers());
		return "/NFTMarket/artists.html";
	}
	
	@RequestMapping("/loginCllients")
	public String loginusers(){
		return "/NFTMarket/login.html";
	}
	
	@RequestMapping(value="/createCllients", method=RequestMethod.POST)
	public String createUsers(Users users){
//		userService.addUser(users);
		return "redirect:/";
	}
	
	@RequestMapping("/registerCllients")
	public String registerClient(){
		return "/NFTMarket/register.html";
	}
	
	@RequestMapping(value="/contactus", method=RequestMethod.GET)
	public String editProduct2(Model model,@PathVariable("id") int id){
		model.addAttribute("products",productservice.findByIdProduct(id));
		return "/form/productreg.html";
	}
	
	@RequestMapping(value="/aboutus", method=RequestMethod.GET)
	public String editProduct3(Model model,@PathVariable("id") int id){
		model.addAttribute("products",productservice.findByIdProduct(id));
		return "/form/productreg.html";
	}
	
	@RequestMapping(value="/admin/x", method=RequestMethod.GET)
	public String getting(Authorities auto){
		System.out.println(auto);
		return "";
	}
}
