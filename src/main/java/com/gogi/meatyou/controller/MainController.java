package com.gogi.meatyou.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductDetailDTO;
import com.gogi.meatyou.bean.ReviewDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.service.MainService;

@Controller
@RequestMapping("/main/*")
public class MainController {
	   
	   @Autowired
	   private MainService service;

	   @RequestMapping("main")
	   public String main(ProductDetailDTO dto, Principal seid, Model model) {
		  List<ProductDTO> cusList = service.mainCUS();
	      List<ProductDTO> meatList = service.meatBest();
	      List<ProductDTO> forkList = service.forkBest();
	      List<ProductDTO> newProduct = service.newProductBest();
	      model.addAttribute("cusList", cusList);
	      model.addAttribute("meatList", meatList);
	      model.addAttribute("forkList", forkList);
	      model.addAttribute("newProduct", newProduct);
	      int CartCNT=0;
	      if(seid != null) {
	         String shop_m_id = (String)seid.getName();
	         CartCNT = service.ShoppingCartCNT2(shop_m_id);
	        model.addAttribute("CartCNT", CartCNT);
	      } else {
	         CartCNT=0;
	         model.addAttribute("CartCNT", CartCNT);
	      }
	      
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      
	      for(ProductDTO pdto : cusList) {
	    	  double star = service.reviewStar(pdto.getP_num());
	          model.addAttribute("star", star);
	      }
	      for(ProductDTO mdto : meatList) {
	    	  double mstar = service.reviewStar(mdto.getP_num());
	          model.addAttribute("mstar", mstar);
	      }
	      for(ProductDTO fdto : forkList) {
	    	  double fstar = service.reviewStar(fdto.getP_num());
	          model.addAttribute("fstar", fstar);
	      }
	      for(ProductDTO ndto : newProduct) {
	    	  double nstar = service.reviewStar(ndto.getP_num());
	          model.addAttribute("nstar", nstar);
	      }

	      return "main/main";
	   }

	   
	   @RequestMapping("search")
	   public String searchList(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
	      int pageNum , String desc, String searchOption, String search) {
	      service.searchList(pageNum, model , desc ,searchOption ,search);
	      int CartCNT=0;
	      if(seid != null) {
	         String shop_m_id = (String)seid.getName();
	         CartCNT = service.ShoppingCartCNT2(shop_m_id);
	        model.addAttribute("CartCNT", CartCNT);
	      } else {
	         CartCNT=0;
	         model.addAttribute("CartCNT", CartCNT);
	      }
	      
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      return "main/searchList";
	   }
	   
	   @RequestMapping("searchPrice") 
	   public String searchPrice(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
	      int pageNum , String price, String searchOption, String search) {
	      service.searchPrice(pageNum, model , price ,searchOption ,search);
	      int CartCNT=0;
	      if(seid != null) {
	         String shop_m_id = (String)seid.getName();
	         CartCNT = service.ShoppingCartCNT2(shop_m_id);
	        model.addAttribute("CartCNT", CartCNT);
	      } else {
	         CartCNT=0;
	         model.addAttribute("CartCNT", CartCNT);
	      }
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      return "main/searchPrice";
	   }
	   
	   @RequestMapping("searchSale")
	   public String searchSale(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
	      int pageNum , String price, String searchOption, String search, @RequestParam(value="CartCNT", defaultValue = "0")int CartCNT) {
	      service.searchSale(pageNum, model , searchOption ,search);
	      model.addAttribute("CartCNT", CartCNT);
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      return "main/searchSale";
	   }
	   
	   @RequestMapping("mainMeat")
	   public String mainMeat(Principal seid, Model model, String price , int category, String sale, String reg, 
	                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	      service.mainMeat(pageNum, model, price , category, sale, reg);
	      model.addAttribute("category", category);
	      int CartCNT=0;
	      if(seid != null) {
	         String shop_m_id = (String)seid.getName();
	         CartCNT = service.ShoppingCartCNT2(shop_m_id);
	        model.addAttribute("CartCNT", CartCNT);
	      } else {
	         CartCNT=0;
	         model.addAttribute("CartCNT", CartCNT);
	      }
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      return "main/mainMeat";
	   }
	   
	   @RequestMapping("powerLink")
	   public String powerLink(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	      service.poLinkList(pageNum, model);
	      int CartCNT=0;
	      if(seid != null) {
	         String shop_m_id = (String)seid.getName();
	         CartCNT = service.ShoppingCartCNT2(shop_m_id);
	        model.addAttribute("CartCNT", CartCNT);
	      } else {
	         CartCNT=0;
	         model.addAttribute("CartCNT", CartCNT);
	      }
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      return "main/powerLink";
	   }
	   
	   @RequestMapping("meatBest")
	   public String meatBest(Principal seid, Model model, String price , int category, String sale, String reg, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	      service.mainMeat(pageNum, model, price , category, sale, reg);
	      model.addAttribute("category", category);
	      int CartCNT=0;
	      if(seid != null) {
	         String shop_m_id = (String)seid.getName();
	         CartCNT = service.ShoppingCartCNT2(shop_m_id);
	        model.addAttribute("CartCNT", CartCNT);
	      } else {
	         CartCNT=0;
	         model.addAttribute("CartCNT", CartCNT);
	      }
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      return "main/meatBest";
	   }
	   
	   @RequestMapping("forkBest")
	   public String forkBest(Principal seid, Model model, String price , int category, String sale, String reg, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	      service.mainMeat(pageNum, model, price , category, sale, reg);
	      model.addAttribute("category", category);
	      int CartCNT=0;
	      if(seid != null) {
	         String shop_m_id = (String)seid.getName();
	         CartCNT = service.ShoppingCartCNT2(shop_m_id);
	        model.addAttribute("CartCNT", CartCNT);
	      } else {
	         CartCNT=0;
	         model.addAttribute("CartCNT", CartCNT);
	      }
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      return "main/forkBest";
	   }
	   
	   @RequestMapping("newBest")
	   public String newBest(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	      service.newProduct(pageNum, model);
	      int CartCNT=0;
	      if(seid != null) {
	         String shop_m_id = (String)seid.getName();
	         CartCNT = service.ShoppingCartCNT2(shop_m_id);
	        model.addAttribute("CartCNT", CartCNT);
	      } else {
	         CartCNT=0;
	         model.addAttribute("CartCNT", CartCNT);
	      }
	      int pickCNT=0;
	      if(seid != null) {
	         String ppic_m_id = (String)seid.getName();
	         pickCNT = service.pickCNT(ppic_m_id);
	        model.addAttribute("pickCNT", pickCNT);
	      } else {
	         pickCNT=0;
	         model.addAttribute("pickCNT", pickCNT);
	      }
	      return "main/newBest";
	   }
	   
	   @RequestMapping("newProduct")
	   public String newProduct(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	      service.newProduct(pageNum, model);
	      return "main/newProduct";
	   }

	   @RequestMapping("product")
	   public String product(ProductDetailDTO dto, Model model, Principal seid, ReviewDTO rsdto, @RequestParam(value="pageNum", defaultValue = "1") int pageNum, int p_num) {
	      dto = service.productDetail(dto, model);
	      model.addAttribute("dto", dto);
	      
	      if(seid != null) {
	         String mid = (String)seid.getName(); 
	         model.addAttribute("mid", mid);
	         service.reviewCNT(mid, dto.getP_num() , model);  
	         p_num = dto.getP_num();
	      }
	      
	      List<ProductDetailDTO> opList = service.otherProductDetail(dto, model);
	      model.addAttribute("opList",opList);
	      
	      double star = service.reviewStar(dto.getP_num());
	      model.addAttribute("star", star);

	      int star1 = service.star1(dto.getP_num());
	      int star2 = service.star2(dto.getP_num());
	      int star3 = service.star3(dto.getP_num());
	      int star4 = service.star4(dto.getP_num());
	      int star5 = service.star5(dto.getP_num());

	      model.addAttribute("star1",star1);
	      model.addAttribute("star2",star2);
	      model.addAttribute("star3",star3);
	      model.addAttribute("star4",star4);
	      model.addAttribute("star5",star5);
	      
	      double star1Per = service.star1Per(dto.getP_num());
	      double star2Per = service.star2Per(dto.getP_num());
	      double star3Per = service.star3Per(dto.getP_num());
	      double star4Per = service.star4Per(dto.getP_num());
	      double star5Per = service.star5Per(dto.getP_num());
	      
	      model.addAttribute("star1Per",star1Per);
	      model.addAttribute("star2Per",star2Per);
	      model.addAttribute("star3Per",star3Per);
	      model.addAttribute("star4Per",star4Per);
	      model.addAttribute("star5Per",star5Per);
	      
	      int reviewAllCNT = service.reviewAllCNT(dto.getP_num());
	      model.addAttribute("reviewAllCNT",reviewAllCNT);
	      
	      service.reviewAllPaging(model, p_num, pageNum);
	      
	      int CartCNT=0;
	      if(seid != null) {
	            String shop_m_id = (String)seid.getName();
	            CartCNT = service.ShoppingCartCNT2(shop_m_id);
	           model.addAttribute("CartCNT", CartCNT);
	         } else {
	            CartCNT=0;
	            model.addAttribute("CartCNT", CartCNT);
	         }
		      int pick_p_numCNT=0;
		      int pickCNT=0;
		      String ppic_m_id = (String)seid.getName();
		      pickCNT = service.pickCNT(ppic_m_id);
		      model.addAttribute("pickCNT", pickCNT);
	         if(seid != null) {
	            ppic_m_id = (String)seid.getName();
	            int ppic_p_num = p_num;
	            pick_p_numCNT = service.pick_p_numCNT(ppic_m_id, ppic_p_num);
	            model.addAttribute("pick_p_numCNT", pick_p_numCNT);
	         } else {
	        	pick_p_numCNT = 0;
	            model.addAttribute("pick_p_numCNT", pick_p_numCNT);
	         }
	      return "main/productDetail";

	   }

	   @RequestMapping("review")
	   public String review(ReviewDTO dto, int r_p_num, String r_m_id) {
	      service.reviewInsert(dto);
	      int p_num = r_p_num;
	      String m_id = r_m_id;
	      return "redirect:/main/product?p_num="+p_num+"&p_m_id="+m_id;
	   }
	   
	   @RequestMapping("ShoppingCartInsert")
	   public String ShoppingCartInsert(Principal seid, Model model, ShoppingCartDTO dto) {
	      int CartCNT = 0;
	      int p_num = 0;
	      String m_id = "";
	         CartCNT = service.ShoppingCartInsert(model, dto);
	         p_num = dto.getShop_p_num();
	         m_id = dto.getShop_m_id();  
	      return "redirect:/main/product?p_num="+p_num+"&p_m_id="+m_id+"&CartCNT="+CartCNT+"&dto="+dto;
	   }

	   @RequestMapping("reviewAllPaging")
	   public String reviewAllPaging(Model model, int p_num, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	      service.reviewAllPaging(model, p_num, pageNum);
	      return "main/reviewAllPaging";
	   }
	   
	   @RequestMapping("pickInsert")
	   public String pickInsert(Principal seid, Model model, PPicDTO dto , String ppic_m_id, int ppic_p_num) {
	      int p_num = 0;
	      String m_id = "";
	      service.pickInsert(model, dto, ppic_m_id, ppic_p_num);
	      p_num = dto.getPpic_p_num();
	      m_id = dto.getPpic_m_id();  
	      System.out.println("p_num : "+p_num);
	      System.out.println("m_id : "+m_id);
	      return "redirect:/main/product?p_num="+p_num+"&p_m_id="+m_id;
	   }


	}