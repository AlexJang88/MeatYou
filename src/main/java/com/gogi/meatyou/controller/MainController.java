package com.gogi.meatyou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.MainService;

@Controller
@RequestMapping("/main/*")
public class MainController {
	
	@Autowired
	private MainService service;

	@RequestMapping("main")
	public String main(Model model) {
		List<ProductDTO> cusList = service.mainCUS();
		List<ProductDTO> meatList = service.meatBest();
		List<ProductDTO> forkList = service.forkBest();
		List<ProductDTO> newProduct = service.newProduct();
		model.addAttribute("cusList", cusList);
		model.addAttribute("meatList", meatList);
		model.addAttribute("forkList", forkList);
		model.addAttribute("newProduct", newProduct);
		return "main/main";
	}

	@RequestMapping("search")
	public String searchList(HttpSession session, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
	int pageNum , String desc, String searchOption, String search) {
	service.searchList(pageNum, model , desc ,searchOption ,search);
	return "main/searchList";
	}
	
	@RequestMapping("searchPrice")
	public String searchPrice(HttpSession session, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
	int pageNum , String price, String searchOption, String search) {
	service.searchPrice(pageNum, model , price ,searchOption ,search);
	return "main/searchPrice";
	}
	
	@RequestMapping("searchSale")
	public String searchSale(HttpSession session, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
	int pageNum , String price, String searchOption, String search) {
	service.searchSale(pageNum, model , searchOption ,search);
	return "main/searchSale";
	}
}
