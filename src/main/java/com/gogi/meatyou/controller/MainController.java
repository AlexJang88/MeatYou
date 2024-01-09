package com.gogi.meatyou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductDetailDTO;
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
      List<ProductDTO> newProduct = service.newProductBest();
      model.addAttribute("cusList", cusList);
      model.addAttribute("meatList", meatList);
      model.addAttribute("forkList", forkList);
      model.addAttribute("newProduct", newProduct);
      return "main/main";
   }

   
   @RequestMapping("search")
   public String searchList(Model model, @RequestParam(value="pageNum", defaultValue = "1") 
      int pageNum , String desc, String searchOption, String search) {
      service.searchList(pageNum, model , desc ,searchOption ,search);
      return "main/searchList";
   }
   
   @RequestMapping("searchPrice") 
   public String searchPrice(Model model, @RequestParam(value="pageNum", defaultValue = "1") 
      int pageNum , String price, String searchOption, String search) {
      service.searchPrice(pageNum, model , price ,searchOption ,search);
      return "main/searchPrice";
   }
   
   @RequestMapping("searchSale")
   public String searchSale(Model model, @RequestParam(value="pageNum", defaultValue = "1") 
      int pageNum , String price, String searchOption, String search) {
      service.searchSale(pageNum, model , searchOption ,search);
      return "main/searchSale";
   }
   
   @RequestMapping("mainMeat")
   public String mainMeat(Model model, String price , int category, String sale, String reg, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
      service.mainMeat(pageNum, model, price , category, sale, reg);
      model.addAttribute("category", category);
      return "main/mainMeat";
   }
   
   @RequestMapping("powerLink")
   public String powerLink(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
      service.poLinkList(pageNum, model);
      return "main/powerLink";
   }
   
   @RequestMapping("meatBest")
   public String meatBest(Model model, String price , int category, String sale, String reg, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
      service.mainMeat(pageNum, model, price , category, sale, reg);
      model.addAttribute("category", category);
      return "main/meatBest";
   }
   
   @RequestMapping("forkBest")
   public String forkBest(Model model, String price , int category, String sale, String reg, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
      service.mainMeat(pageNum, model, price , category, sale, reg);
      model.addAttribute("category", category);
      return "main/forkBest";
   }
   
   @RequestMapping("newBest")
   public String newBest(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
      service.newProduct(pageNum, model);
      return "main/newBest";
   }
   
   @RequestMapping("newProduct")
   public String newProduct(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
      service.newProduct(pageNum, model);
      return "main/newProduct";
   }

   @RequestMapping("product")
   public String product(ProductDetailDTO dto, Model model) {
	   dto = service.productDetail(dto, model);
	   model.addAttribute("dto", dto);
      return "main/productDetail";
   }
   
   @RequestMapping("review")
   public String review() {
      return "main/review";
   }
   
   @RequestMapping("modal")
   public String modal() {
      return "main/modal";
   }



   
   
}