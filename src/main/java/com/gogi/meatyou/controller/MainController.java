package com.gogi.meatyou.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.meatyou.HomeController;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.OtherProductDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PreferDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductDetailDTO;
import com.gogi.meatyou.bean.ReviewDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.service.MainService;

@Controller
@RequestMapping("/main/*")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
   @Autowired
   private MainService service;

   @RequestMapping("main")
   public String main(ProductDetailDTO dto, Principal seid, Model model, String pre_m_id) {
	   
	   /* 파워링크 */
	  List<ProductDTO> cusList = service.mainCUS();
	  String m_id="";
	  int p_num = dto.getP_num();
	  if(seid != null) {
		  m_id = (String)seid.getName(); 
          model.addAttribute("m_id", m_id);
      }
	  for(ProductDTO cdto : cusList) {
		  String ppic_m_id = m_id;
	      int ppic_p_num = cdto.getP_num();
	      cdto.setPpic_m_id(ppic_m_id);
	      cdto.setPpic_p_num(ppic_p_num);
	      
    	  double cstar = service.reviewStar(cdto.getP_num());
          cdto.setStar(cstar);
          
          String category1="";
          String category2="";
          String category3="";
          String cate = cdto.getP_category()+"";
          if((cate.charAt(0)-48) == 1) {
        	  category1 = "국내산";
          } else{
        	  category1 = "수입산";
          }
          if((cate.charAt(1)-48) == 1) {
        	  category2 = "돼지고기";
          } else{
        	  category2 = "소고기";
          }
          if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
        	  category3 = "특수부위";
          } else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
        	  category3 = "삼겹살";
          }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
        	  category3 = "목살";
          }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
        	  category3 = "안심";
          }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
        	  category3 = "등심";
          }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
        	  category3 = "앞다리살";
          }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
        	  category3 = "갈매기살";
          }
	      	
          if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
        	  category3 = "특수부위";
          } else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
        	  category3 = "등심";
          }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
        	  category3 = "안심";
          }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
        	  category3 = "갈비";
          }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
        	  category3 = "채끝";
          }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
        	  category3 = "목심";
          }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
        	  category3 = "부채살";
          }
          cdto.setCategory1(category1);
          cdto.setCategory2(category2);
          cdto.setCategory3(category3);

          int r_p_num = service.reviewAllCNT(cdto.getP_num());
          cdto.setReviewAllCNT(r_p_num);
	  }
	  model.addAttribute("cusList", cusList);
	  
	   /* 소고기 베스트 */
	  List<ProductDTO> meatList = service.meatBest();
	  for(ProductDTO mdto : meatList) {
		  String ppic_m_id = m_id;
	      int ppic_p_num = mdto.getP_num();
	      mdto.setPpic_m_id(ppic_m_id);
	      mdto.setPpic_p_num(ppic_p_num);
	      
    	  double mstar = service.reviewStar(mdto.getP_num());
    	  mdto.setStar(mstar);
    	  model.addAttribute("p_num", mdto.getP_num());
    	  
    	  String category1="";
    	  String category2="";
    	  String category3="";
    	
    	  String cate = mdto.getP_category()+"";

    	  if((cate.charAt(0)-48) == 1) {
    		  category1 = "국내산";
    	  } else{
    		  category1 = "수입산";
    	  }
	      	
    	  if((cate.charAt(1)-48) == 1) {
    		  category2 = "돼지고기";
    	  } else{
    		  category2 = "소고기";
    	  }
	      	
    	  if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
    		  category3 = "특수부위";
    	  } else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
    		  category3 = "삼겹살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
    		  category3 = "목살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
    		  category3 = "안심";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
    		  category3 = "등심";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
    		  category3 = "앞다리살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
    		  category3 = "갈매기살";
    	  }
	      	
    	  if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
    		  category3 = "특수부위";
    	  } else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
    		  category3 = "등심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
    		  category3 = "안심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
    		  category3 = "갈비";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
    		  category3 = "채끝";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
    		  category3 = "목심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
    		  category3 = "부채살";
    	  }
    	  mdto.setCategory1(category1);
    	  mdto.setCategory2(category2);
    	  mdto.setCategory3(category3);

    	  int r_p_num = service.reviewAllCNT(mdto.getP_num());
    	  mdto.setReviewAllCNT(r_p_num);
	      }
      model.addAttribute("meatList", meatList);
      
      /* 돼지고기 베스트 */
      List<ProductDTO> forkList = service.forkBest();
      for(ProductDTO fdto : forkList) {
    	  String ppic_m_id = m_id;
	      int ppic_p_num = fdto.getP_num();
	      fdto.setPpic_m_id(ppic_m_id);
	      fdto.setPpic_p_num(ppic_p_num);
	      
    	  double fstar = service.reviewStar(fdto.getP_num());
    	  fdto.setStar(fstar);
    	  model.addAttribute("p_num", fdto.getP_num());
    	  
    	  String category1="";
    	  String category2="";
    	  String category3="";
    	
    	  String cate = fdto.getP_category()+"";
	
    	  if((cate.charAt(0)-48) == 1) {
    		  category1 = "국내산";
    	  } else{
    		  category1 = "수입산";
    	  }
	      	
    	  if((cate.charAt(1)-48) == 1) {
    		  category2 = "돼지고기";
    	  } else{
    		  category2 = "소고기";
    	  }
    	  
    	  if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
    		  category3 = "특수부위";
    	  } else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
    		  category3 = "삼겹살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
    		  category3 = "목살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
    		  category3 = "안심";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
    		  category3 = "등심";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
    		  category3 = "앞다리살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
    		  category3 = "갈매기살";
    	  }
	      	
    	  if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
    		  category3 = "특수부위";
    	  } else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
    		  category3 = "등심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
    		  category3 = "안심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
    		  category3 = "갈비";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
    		  category3 = "채끝";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
    		  category3 = "목심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
    		  category3 = "부채살";
    	  }
    	  fdto.setCategory1(category1);
    	  fdto.setCategory2(category2);
    	  fdto.setCategory3(category3);

    	  int r_p_num = service.reviewAllCNT(fdto.getP_num());
    	  	fdto.setReviewAllCNT(r_p_num);
	      }
      model.addAttribute("forkList", forkList);
      
      
      
      /* 신상품 베스트 */
      List<ProductDTO> newProduct = service.newProductBest();
      for(ProductDTO ndto : newProduct) {
    	  String ppic_m_id = m_id;
	      int ppic_p_num = ndto.getP_num();
	      ndto.setPpic_m_id(ppic_m_id);
	      ndto.setPpic_p_num(ppic_p_num);
	      
    	  double fstar = service.reviewStar(ndto.getP_num());
    	  ndto.setStar(fstar);
    	  model.addAttribute("p_num", ndto.getP_num());
    	  
    	  String category1="";
    	  String category2="";
    	  String category3="";
    	
    	  String cate = ndto.getP_category()+"";
	
    	  if((cate.charAt(0)-48) == 1) {
    		  category1 = "국내산";
    	  } else{
    		  category1 = "수입산";
    	  }
	      	
    	  if((cate.charAt(1)-48) == 1) {
    		  category2 = "돼지고기";
    	  } else{
    		  category2 = "소고기";
    	  }
    	  
    	  if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
    		  category3 = "특수부위";
    	  } else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
    		  category3 = "삼겹살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
    		  category3 = "목살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
    		  category3 = "안심";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
    		  category3 = "등심";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
    		  category3 = "앞다리살";
    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
    		  category3 = "갈매기살";
    	  }
	      	
    	  if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
    		  category3 = "특수부위";
    	  } else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
    		  category3 = "등심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
    		  category3 = "안심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
    		  category3 = "갈비";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
    		  category3 = "채끝";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
    		  category3 = "목심";
    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
    		  category3 = "부채살";
    	  }
    	  ndto.setCategory1(category1);
    	  ndto.setCategory2(category2);
    	  ndto.setCategory3(category3);

    	  int r_p_num = service.reviewAllCNT(ndto.getP_num());
    	  ndto.setReviewAllCNT(r_p_num);
	      }
      model.addAttribute("newProduct", newProduct);


      
      if(seid != null) {
          m_id = (String)seid.getName(); 
          model.addAttribute("m_id", m_id);
       }
      
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
      int pick_P_CNT=0;
      if(seid != null) {
    	  String ppic_m_id = (String)seid.getName();
    	  pickCNT = service.pickCNT(ppic_m_id);
    	  pick_P_CNT = service.pick_P_CNT(ppic_m_id);
    	  model.addAttribute("pickCNT", pickCNT);
    	  model.addAttribute("pick_P_CNT", pick_P_CNT);
      } else {
    	  pickCNT=0;
    	  pick_P_CNT=0;
    	  model.addAttribute("pickCNT", pickCNT);
    	  model.addAttribute("pick_P_CNT", pick_P_CNT);
      }

      
		/* 마춤형 BEST */
      if(m_id != null && m_id != "") {
	      pre_m_id = m_id;
	      MemberDTO mdto = service.name(m_id);
	      model.addAttribute("mdto", mdto);
	      PreferDTO pdto = service.customOrderCategory(pre_m_id , model);
	      int pResult=0;
	      int cate_response1 = pdto.getPre0_response();
	      int cate_response2 = pdto.getPre1_response();
	      if(cate_response1 == 100) {
	    	  cate_response1 = 110;
			}else {
				cate_response1 = 120;
			}
	      pResult = cate_response1+cate_response2;
	      pdto.setPre_category(pResult);
	      
	      
	      List<ProductDTO> customOrderBest = service.customOrderBest(pdto.getPre_category());
	      for(ProductDTO ndto : newProduct) {
	    	  String ppic_m_id = m_id;
		      int ppic_p_num = ndto.getP_num();
		      ndto.setPpic_m_id(ppic_m_id);
		      ndto.setPpic_p_num(ppic_p_num);
		      
	    	  double fstar = service.reviewStar(ndto.getP_num());
	    	  ndto.setStar(fstar);
	    	  model.addAttribute("p_num", ndto.getP_num());
	    	  
	    	  String category1="";
	    	  String category2="";
	    	  String category3="";
	    	
	    	  String cate = ndto.getP_category()+"";
		
	    	  if((cate.charAt(0)-48) == 1) {
	    		  category1 = "국내산";
	    	  } else{
	    		  category1 = "수입산";
	    	  }
		      	
	    	  if((cate.charAt(1)-48) == 1) {
	    		  category2 = "돼지고기";
	    	  } else{
	    		  category2 = "소고기";
	    	  }
	    	  
	    	  if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
	    		  category3 = "특수부위";
	    	  } else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
	    		  category3 = "삼겹살";
	    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
	    		  category3 = "목살";
	    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
	    		  category3 = "안심";
	    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
	    		  category3 = "등심";
	    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
	    		  category3 = "앞다리살";
	    	  }else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
	    		  category3 = "갈매기살";
	    	  }
		      	
	    	  if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
	    		  category3 = "특수부위";
	    	  } else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
	    		  category3 = "등심";
	    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
	    		  category3 = "안심";
	    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
	    		  category3 = "갈비";
	    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
	    		  category3 = "채끝";
	    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
	    		  category3 = "목심";
	    	  }else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
	    		  category3 = "부채살";
	    	  }
	    	  ndto.setCategory1(category1);
	    	  ndto.setCategory2(category2);
	    	  ndto.setCategory3(category3);
	
	    	  int r_p_num = service.reviewAllCNT(ndto.getP_num());
	    	  ndto.setReviewAllCNT(r_p_num);
		      }
	      model.addAttribute("customOrderBest", customOrderBest);
      }
      
      return "main/main";
   }

   
   @RequestMapping("search")
   public String searchList(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
      int pageNum , String desc, String searchOption, String search) {
	   List<ProductDTO> searchList = service.searchList(seid, pageNum, model , desc ,searchOption ,search);
	   String m_id="";
      int CartCNT=0;
      
      if(seid != null) {
    	 m_id = (String)seid.getName(); 
         model.addAttribute("m_id", m_id);
         String shop_m_id = (String)seid.getName();
         CartCNT = service.ShoppingCartCNT2(shop_m_id);
        model.addAttribute("CartCNT", CartCNT);
      } else {
         CartCNT=0;
         model.addAttribute("CartCNT", CartCNT);
      }

      model.addAttribute("pickList", searchList);
      
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
	   	List<ProductDTO> searchPrice = service.searchPrice(seid, pageNum, model , price ,searchOption ,search);
	   	int CartCNT=0;
	   	String m_id="";
	   	if(seid != null) {
	   		String shop_m_id = (String)seid.getName();
	   		CartCNT = service.ShoppingCartCNT2(shop_m_id);
	   		model.addAttribute("CartCNT", CartCNT);
	   		model.addAttribute("m_id", shop_m_id);
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
	   List<ProductDTO> searchSale = service.searchSale(seid, pageNum, model , searchOption ,search);
	   String m_id="";
	   model.addAttribute("CartCNT", CartCNT);
	   int pickCNT=0;
	   if(seid != null) {
		   String ppic_m_id = (String)seid.getName();
		   pickCNT = service.pickCNT(ppic_m_id);
		   model.addAttribute("pickCNT", pickCNT);
		   model.addAttribute("m_id", ppic_m_id);
	   } else {
		   pickCNT=0;
		   model.addAttribute("pickCNT", pickCNT);
	   }
	   return "main/searchSale";
   }
   
   @RequestMapping("searchStar") 
   public String searchStar(ProductDTO dto, Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
      	int pageNum ,String searchOption, String search) {
	   	List<ProductDTO> searchListStar = service.searchListStar(seid, dto, pageNum, model ,searchOption ,search);
	   	String m_id="";
	   	int CartCNT=0;
	   	if(seid != null) {
     	String shop_m_id = (String)seid.getName();
    	CartCNT = service.ShoppingCartCNT2(shop_m_id);
        model.addAttribute("CartCNT", CartCNT);
        model.addAttribute("m_id", shop_m_id);
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
	   	return "main/searchStar";
   }
   
   @RequestMapping("searchReview") 
   public String searchReview(ProductDTO dto, Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") 
      	int pageNum ,String searchOption, String search) {
	   	List<ProductDTO> searchListReview = service.searchListReview(seid, dto, pageNum, model ,searchOption ,search);
	   	String m_id="";
	   	int CartCNT=0;
	   	if(seid != null) {
	   		String shop_m_id = (String)seid.getName();
	   		CartCNT = service.ShoppingCartCNT2(shop_m_id);
	   		model.addAttribute("CartCNT", CartCNT);
	   		model.addAttribute("m_id", shop_m_id);
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
	   	return "main/searchReview";
   }
   
   @RequestMapping("mainMeat")
   public String mainMeat(Principal seid, Model model, String price , int category, String sale, String reg, 
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String news, String star) {
	   service.mainMeat(seid, pageNum, model, price , category, sale, reg, news, star);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
   
   @RequestMapping("mainMeatKOR")
   public String mainMeatKOR(Principal seid, Model model, String price , int category, String sale, String reg, 
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String news, String star, String review) {
	   service.mainMeatKOR(seid, pageNum, model, price , category, sale, reg, news, star, review);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/mainMeatKOR";
   }
   
   @RequestMapping("mainMeatEX")
   public String mainMeatEX(Principal seid, Model model, String price , int category, String sale, String reg, 
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String news, String star, String review) {
	   service.mainMeatEX(seid, pageNum, model, price , category, sale, reg, news, star, review);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/mainMeatEX";
   }
   
   @RequestMapping("mainForkKOR")
   public String mainForkKOR(Principal seid, Model model, String price , int category, String sale, String reg, 
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String news, String star, String review) {
	   service.mainForkKOR(seid, pageNum, model, price , category, sale, reg, news, star, review);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/mainForkKOR";
   }
   
   @RequestMapping("mainForkEX")
   public String mainForkEx(Principal seid, Model model, String price , int category, String sale, String reg, 
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String news, String star, String review) {
	   service.mainForkEX(seid, pageNum, model, price , category, sale, reg, news, star, review);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/mainForkEX";
   }
   
   @RequestMapping("mainMeatSort")
   public String mainMeatSort(Principal seid, Model model, int category,
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String star) {
	   service.mainMeatSort(seid, pageNum, model, category, star);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) {
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/mainMeatSort";
   }
   
   @RequestMapping("mainMeatReview")
   public String mainMeatReview(Principal seid, Model model, int category,
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String star) {
	   service.mainMeatReview(seid, pageNum, model, category, star);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/mainMeatReview";
   }
   
   @RequestMapping("setMenu")
   public String setMenu(Principal seid, Model model, int p_s_category,
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.setMenu(seid, model, p_s_category, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/setMenu";
   }
   
   @RequestMapping("setMenuPrice")
   public String setMenuPrice(Principal seid, Model model,
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.setMenuPrice(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/setMenuPrice";
   }
   
   @RequestMapping("setMenuSales")
   public String setMenuSales(Principal seid, Model model,
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.setMenuSales(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/setMenuSales";
   }
   
   @RequestMapping("setMenuStars")
   public String setMenuStars(Principal seid, Model model,
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.setMenuStars(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/setMenuStars";
   }
   
   @RequestMapping("setMenuReviews")
   public String setMenuReviews(Principal seid, Model model,
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.setMenuReviews(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/setMenuReviews";
   }
   
   @RequestMapping("setMenuNews")
   public String setMenuNews(Principal seid, Model model,
                        @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.setMenuNews(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/setMenuNews";
   }
   
   @RequestMapping("giftSetPrice")
   public String giftSetPrice(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.giftSetPrice(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/giftSetPrice";
   }
   
   @RequestMapping("giftSetSales")
   public String giftSetSales(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.giftSetSales(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/giftSetSales";
   }
   
   @RequestMapping("giftSetStars")
   public String giftSetStars(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.giftSetStars(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/giftSetStars";
   }
   
   @RequestMapping("giftSetReviews")
   public String giftSetReviews(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.giftSetReviews(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/giftSetReviews";
   }
   
   @RequestMapping("giftSetNews")
   public String giftSetNews(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.giftSetNews(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/giftSetNews";
   }
   
   @RequestMapping("giftSet")
   public String giftSet(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.giftSet(seid, model, pageNum);
	   int CartCNT=0;
	   if(seid != null) { 
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   return "main/giftSet";
   }
   
   @RequestMapping("powerLink")
   public String powerLink(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String price, String sale, String star, String review, String news) {
	   service.poLinkList(seid, pageNum, model, price, sale, star, review, news);
	   int CartCNT=0;
	   if(seid != null) {
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
   
   @RequestMapping("quantity")
   public String quantity(int co_p_num) {
	   service.quantity(co_p_num);
	   return "main/powerLink";
   }
   

   @RequestMapping("meatBest")
   public String meatBest(Principal seid, Model model, String price , int category, String sale, String reg, 
		   				   @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String news, String star) {
	   service.mainMeat(seid, pageNum, model, price , category, sale, reg, news, star);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) {
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
   public String forkBest(Principal seid, Model model, String price , int category, String sale, String reg, 
		   				   @RequestParam(value="pageNum", defaultValue = "1") int pageNum, String news, String star) {
	   service.mainMeat(seid, pageNum, model, price , category, sale, reg, news, star);
	   model.addAttribute("category", category);
	   int CartCNT=0;
	   if(seid != null) {
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
	   service.newProduct(seid, pageNum, model);
	   int CartCNT=0;
	   if(seid != null) {
		   String shop_m_id = (String)seid.getName();
		   model.addAttribute("m_id", shop_m_id);
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
   public String newProduct(Principal seid, Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
	   service.newProduct(seid, pageNum, model);
	   if(seid != null) {
		   String m_id = (String)seid.getName();
		   model.addAttribute("m_id", m_id);
	   }
      
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
	   return "main/newProduct";
   }

   @RequestMapping("product")
   public String product(OtherProductDetailDTO odto ,ProductDetailDTO dto, Model model, Principal seid, ReviewDTO rsdto, @RequestParam(value="pageNum", defaultValue = "1") int pageNum, int p_num) {
	   	dto = service.productDetail(dto, model);
	   	model.addAttribute("dto", dto);
	   	System.out.println("Thumb : "+dto.getThumb());
	    service.quantity(dto.getP_num());
		String PDcategory1="";
 		String PDcategory2="";
 		String PDcategory3="";
   	
	  	String PDcate = dto.getP_category()+"";
	
	 	if((PDcate.charAt(0)-48) == 1) {
	 		PDcategory1 = "국내산";
	  	} else{
	      	PDcategory1 = "수입산";
	 	}
	      	
		if((PDcate.charAt(1)-48) == 1) {
	      	PDcategory2 = "돼지고기";
		} else{
	      	PDcategory2 = "소고기";
	 	}
	      	
	 	if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(1)-48) == 0) {
	      	PDcategory3 = "특수부위";
	  	} else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 1){
	      	PDcategory3 = "삼겹살";
	 	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 2){
	      	PDcategory3 = "목살";
	  	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 3){
	      	PDcategory3 = "안심";
	  	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 4){
	     	PDcategory3 = "등심";
	  	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 5){
	      	PDcategory3 = "앞다리살";
	 	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 6){
	    	PDcategory3 = "갈매기살";
	  	}
	      	
	 	if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(1)-48) == 0) {
	     	PDcategory3 = "특수부위";
	  	} else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 1){
	      	PDcategory3 = "등심";
	  	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 2){
	      	PDcategory3 = "안심";
	  	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 3){
	  		PDcategory3 = "갈비";
	 	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 4){
	    	PDcategory3 = "채끝";
	 	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 5){
	      	PDcategory3 = "목심";
	 	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 6){
	     	PDcategory3 = "부채살";
	 	}
	  	model.addAttribute("PDcategory1", PDcategory1);
	  	model.addAttribute("PDcategory2", PDcategory2);
	  	model.addAttribute("PDcategory3", PDcategory3);

	   	
	   	if(seid != null) {
	   		String mid = (String)seid.getName(); 
	   		model.addAttribute("mid", mid);
	   		service.reviewCNT(mid, dto.getP_num() , model);  
	   		p_num = dto.getP_num();
	   	}

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
	   	int pick_p_numCNT=0;
	   	int pickCNT=0;
	   	String ppic_m_id="";
	   	if(seid != null) {
	   		String shop_m_id = (String)seid.getName();
	   		CartCNT = service.ShoppingCartCNT2(shop_m_id);
	   		model.addAttribute("CartCNT", CartCNT);
	   	} else {
            CartCNT=0;
            model.addAttribute("CartCNT", CartCNT);
	   	} 
      
	   	if(seid != null) {
	    	ppic_m_id = (String)seid.getName();
		 	pickCNT = service.pickCNT(ppic_m_id);
			model.addAttribute("pickCNT", pickCNT); 
	      } else {
	    	  pickCNT=0;
	    	  model.addAttribute("pickCNT", pickCNT);
	      }

         if(seid != null) {
            ppic_m_id = (String)seid.getName();
            int ppic_p_num = p_num;
            pick_p_numCNT = service.pick_p_numCNT(ppic_m_id, ppic_p_num);
            model.addAttribute("pick_p_numCNT", pick_p_numCNT);
         } else {
        	pick_p_numCNT = 0;
            model.addAttribute("pick_p_numCNT", pick_p_numCNT);
         }
         
         

         String p_m_id = dto.getP_m_id();
         int count = service.otherProductCount(p_m_id);{
         List<OtherProductDetailDTO> opList = service.otherProductDetail(odto, model);
         model.addAttribute("opList",opList);
         
         for(OtherProductDetailDTO ordto : opList) {
        	double ostar = service.reviewStar(ordto.getP_num());
         	ordto.setStar(ostar);
               
          	String category1="";
         	String category2="";
         	String category3="";
           	
     	  	String cate = ordto.getP_category()+"";

     	 	if((cate.charAt(0)-48) == 1) {
     	 		category1 = "국내산";
     	  	} else{
     	      	category1 = "수입산";
     	 	}
     	      	
     		if((cate.charAt(1)-48) == 1) {
     	      	category2 = "돼지고기";
     		} else{
     	      	category2 = "소고기";
     	 	}
     	      	
     	 	if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
     	      	category3 = "특수부위";
     	  	} else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
     	      	category3 = "삼겹살";
     	 	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
     	      	category3 = "목살";
     	  	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
     	      	category3 = "안심";
     	  	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
     	     	category3 = "등심";
     	  	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
     	      	category3 = "앞다리살";
     	 	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
     	    	category3 = "갈매기살";
     	  	}
     	      	
     	 	if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
     	     	category3 = "특수부위";
     	  	} else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
     	      	category3 = "등심";
     	  	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
     	      	category3 = "안심";
     	  	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
     	      	category3 = "갈비";
     	 	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
     	    	category3 = "채끝";
     	 	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
     	      	category3 = "목심";
     	 	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
     	     	category3 = "부채살";
     	 	}
     	   	ordto.setCategory1(category1);
     		ordto.setCategory2(category2);
     	  	ordto.setCategory3(category3);
     	 	}
         }
         return "main/productDetail";
   }
   
   @RequestMapping("reviewVeiw")
   public String reviwView(OtherProductDetailDTO odto ,ProductDetailDTO dto, Model model, Principal seid, ReviewDTO rsdto, @RequestParam(value="pageNum", defaultValue = "1") int pageNum, int p_num) {
	   	dto = service.productDetail(dto, model);
	   	model.addAttribute("dto", dto);
      
		String PDcategory1="";
 		String PDcategory2="";
 		String PDcategory3="";
   	
	  	String PDcate = dto.getP_category()+"";
	
	 	if((PDcate.charAt(0)-48) == 1) {
	 		PDcategory1 = "국내산";
	  	} else{
	      	PDcategory1 = "수입산";
	 	}
	      	
		if((PDcate.charAt(1)-48) == 1) {
	      	PDcategory2 = "돼지고기";
		} else{
	      	PDcategory2 = "소고기";
	 	}
	      	
	 	if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(1)-48) == 0) {
	      	PDcategory3 = "특수부위";
	  	} else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 1){
	      	PDcategory3 = "삼겹살";
	 	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 2){
	      	PDcategory3 = "목살";
	  	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 3){
	      	PDcategory3 = "안심";
	  	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 4){
	     	PDcategory3 = "등심";
	  	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 5){
	      	PDcategory3 = "앞다리살";
	 	}else if((PDcate.charAt(1)-48) == 1 && (PDcate.charAt(2)-48) == 6){
	    	PDcategory3 = "갈매기살";
	  	}
	      	
	 	if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(1)-48) == 0) {
	     	PDcategory3 = "특수부위";
	  	} else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 1){
	      	PDcategory3 = "등심";
	  	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 2){
	      	PDcategory3 = "안심";
	  	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 3){
	  		PDcategory3 = "갈비";
	 	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 4){
	    	PDcategory3 = "채끝";
	 	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 5){
	      	PDcategory3 = "목심";
	 	}else if((PDcate.charAt(1)-48) == 2 && (PDcate.charAt(2)-48) == 6){
	     	PDcategory3 = "부채살";
	 	}
	  	model.addAttribute("PDcategory1", PDcategory1);
	  	model.addAttribute("PDcategory2", PDcategory2);
	  	model.addAttribute("PDcategory3", PDcategory3);

	   	
	   	if(seid != null) {
	   		String mid = (String)seid.getName(); 
	   		model.addAttribute("mid", mid);
	   		service.reviewCNT(mid, dto.getP_num() , model);  
	   		p_num = dto.getP_num();
	   	}

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
	   	int pick_p_numCNT=0;
	   	int pickCNT=0;
	   	String ppic_m_id="";
	   	if(seid != null) {
	   		String shop_m_id = (String)seid.getName();
	   		CartCNT = service.ShoppingCartCNT2(shop_m_id);
	   		model.addAttribute("CartCNT", CartCNT);
	   	} else {
            CartCNT=0;
            model.addAttribute("CartCNT", CartCNT);
	   	} 
      
	   	if(seid != null) {
	    	ppic_m_id = (String)seid.getName();
		 	pickCNT = service.pickCNT(ppic_m_id);
			model.addAttribute("pickCNT", pickCNT); 
	      } else {
	    	  pickCNT=0;
	    	  model.addAttribute("pickCNT", pickCNT);
	      }

         if(seid != null) {
            ppic_m_id = (String)seid.getName();
            int ppic_p_num = p_num;
            pick_p_numCNT = service.pick_p_numCNT(ppic_m_id, ppic_p_num);
            model.addAttribute("pick_p_numCNT", pick_p_numCNT);
         } else {
        	pick_p_numCNT = 0;
            model.addAttribute("pick_p_numCNT", pick_p_numCNT);
         }
         
         

         String p_m_id = dto.getP_m_id();
         int count = service.otherProductCount(p_m_id);{
         List<OtherProductDetailDTO> opList = service.otherProductDetail(odto, model);
         model.addAttribute("opList",opList);
         
         for(OtherProductDetailDTO ordto : opList) {
        	double ostar = service.reviewStar(ordto.getP_num());
         	ordto.setStar(ostar);
               
          	String category1="";
         	String category2="";
         	String category3="";
           	
     	  	String cate = ordto.getP_category()+"";

     	 	if((cate.charAt(0)-48) == 1) {
     	 		category1 = "국내산";
     	  	} else{
     	      	category1 = "수입산";
     	 	}
     	      	
     		if((cate.charAt(1)-48) == 1) {
     	      	category2 = "돼지고기";
     		} else{
     	      	category2 = "소고기";
     	 	}
     	      	
     	 	if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
     	      	category3 = "특수부위";
     	  	} else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
     	      	category3 = "삼겹살";
     	 	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
     	      	category3 = "목살";
     	  	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
     	      	category3 = "안심";
     	  	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
     	     	category3 = "등심";
     	  	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
     	      	category3 = "앞다리살";
     	 	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
     	    	category3 = "갈매기살";
     	  	}
     	      	
     	 	if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
     	     	category3 = "특수부위";
     	  	} else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
     	      	category3 = "등심";
     	  	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
     	      	category3 = "안심";
     	  	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
     	      	category3 = "갈비";
     	 	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
     	    	category3 = "채끝";
     	 	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
     	      	category3 = "목심";
     	 	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
     	     	category3 = "부채살";
     	 	}
     	   	ordto.setCategory1(category1);
     		ordto.setCategory2(category2);
     	  	ordto.setCategory3(category3);
     	 	}
         }
         return "main/reviewVeiw";
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
   
   @RequestMapping("ShoppingCartInsert2")
   public String ShoppingCartInsert2(Principal seid, Model model, String m_id, int p_num, int shop_quantity) {
	   	int CartCNT = 0;
	   	CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
	   	return "forward:/main/main";
   }
   
   @RequestMapping("ShoppingCartInsertMainMeat")
   public String ShoppingCartInsertMainMeat(Principal seid, Model model, String m_id, int p_num, int shop_quantity, String price , int category) {
	   	int CartCNT = 0;
	   	CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
	   	return "forward:/main/mainMeat?category="+category+"&price=desc";
   }
   
   @RequestMapping("ShoppingCartInsertSearchList")
   public String ShoppingCartInsertSearchList(Principal seid, Model model, String m_id, int p_num, int shop_quantity, String searchOption , String search) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
	   return "forward:/main/search";
   }
   
   @RequestMapping("ShoppingCartInsertSearchSale")
   public String ShoppingCartInsertSearchSale(Principal seid, Model model, String m_id, int p_num, int shop_quantity, String searchOption , String search) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
	   return "forward:/main/search";
   }
   
   @RequestMapping("ShoppingCartInsertSearchPrice")
   public String ShoppingCartInsertSearchPrice(Principal seid, Model model, String m_id, int p_num, int shop_quantity, String searchOption , String search) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
      return "forward:/main/searchPrice";
   }
   
   @RequestMapping("ShoppingCartInsertSearchReview")
   public String ShoppingCartInsertSearchReview(Principal seid, Model model, String m_id, int p_num, int shop_quantity, String searchOption , String search) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
      return "forward:/main/searchPrice";
   }
   
   @RequestMapping("ShoppingCartInsertSearchStar")
   public String ShoppingCartInsertSearchStar(Principal seid, Model model, String m_id, int p_num, int shop_quantity, String searchOption , String search) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
      return "forward:/main/searchPrice";
   }
   
   @RequestMapping("ShoppingCartInsertPowerLink")
   public String ShoppingCartInsertPowerLink(Principal seid, Model model, String m_id, int p_num, int shop_quantity) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
	   return "forward:/main/powerLink";
   }
   
   @RequestMapping("ShoppingCartInsertnewProduct")
   public String ShoppingCartInsertnewProduct(Principal seid, Model model, String m_id, int p_num, int shop_quantity) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
	   return "forward:/main/newProduct";
   }
   
   @RequestMapping("ShoppingCartInsertsetMenu")
   public String ShoppingCartInsertsetMenu(Principal seid, Model model, String m_id, int p_num, int shop_quantity) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
	   return "redirect:/main/setMenu?p_s_category=1";
   }

   @RequestMapping("ShoppingCartInsertgiftSet")
   public String ShoppingCartInsertgiftSet(Principal seid, Model model, String m_id, int p_num, int shop_quantity) {
	   int CartCNT = 0;
	   CartCNT = service.ShoppingCartInsert2(model, m_id, p_num, shop_quantity);
	   return "redirect:/main/giftSet";
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
      int piIntert = service.pick_p_numCNT(ppic_m_id, ppic_p_num);
      model.addAttribute("piIntert", piIntert);
      service.pickInsert(model, dto, ppic_m_id, ppic_p_num);
      p_num = dto.getPpic_p_num();
      m_id = dto.getPpic_m_id();  
      return "redirect:/main/product?p_num="+p_num+"&p_m_id="+m_id;
   }
   
   @RequestMapping("pickInsertMain")
   public String pickInsertMain(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
	      int piIntert = service.pick_p_numCNT(ppic_m_id, ppic_p_num);
	      model.addAttribute("piIntert", piIntert);
	      service.pickInsertMain(model, ppic_m_id, ppic_p_num);
	      return "redirect:/main/main";
   }
   
   @RequestMapping("pickInsertMain2")
   public String pickInsertMain2(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
	      int piIntert = service.pick_p_numCNT(ppic_m_id, ppic_p_num);
	      model.addAttribute("piIntert", piIntert);
	      service.pickInsertMain(model, ppic_m_id, ppic_p_num);
	      return "redirect:/main/main";
   }
   
   @RequestMapping("pickInsertSearch")
   public String pickInsertSearch(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
	   service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
	   return "forward:/main/search";
   }
   
   @RequestMapping("pickInsertSearchPrice")
   public String pickInsertSearchPrice(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/searchPrice";
   }
   
   @RequestMapping("pickInsertSearchReview")
   public String pickInsertSearchReview(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/searchReview";
   }
   
   @RequestMapping("pickInsertSearchSale")
   public String pickInsertSearchSale(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/searchSale";
   }
   
   @RequestMapping("pickInsertSearchStar")
   public String pickInsertSearchStar(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/searchStar";
   }
   
   @RequestMapping("pickInsertMainMeat")
   public String pickInsertMainMeat(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/mainMeat";
   }
   
   @RequestMapping("pickInsertMainSort")
   public String pickInsertMainSort(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/mainMeatSort";
   }
   
   @RequestMapping("pickInsertMainReview")
   public String pickInsertMainReview(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/mainMeatReview";
   }
   
   @RequestMapping("pickInsertMainPlink")
   public String pickInsertMainPlink(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/powerLink";
   }
   
   @RequestMapping("pickInsertMainNewProduct")
   public String pickInsertMainNewProduct(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "forward:/main/newProduct";
   }
   
   @RequestMapping("pickInsertSetMenu")
   public String pickInsertSetMenu(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "redirect:/main/setMenu?p_s_category=1";
   }
   
   @RequestMapping("pickInsertgiftSet")
   public String pickInsertgiftSet(Principal seid, Model model, String ppic_m_id, int ppic_p_num) {
      service.pickInsertMain(model, ppic_m_id, ppic_p_num); 
      return "redirect:/main/giftSet";
   }
   
   

   @RequestMapping("boot")
   public String boot() { 
      return "main/bootstrap";
   }
   
   @RequestMapping("main2")
   public String main2(ProductDetailDTO dto, Principal seid, Model model) {
	  List<ProductDTO> cusList = service.mainCUS();
	  String m_id="";
	  int p_num = dto.getP_num();
	  if(seid != null) {
          m_id = (String)seid.getName(); 
          model.addAttribute("m_id", m_id);
       }
	  for(ProductDTO cdto : cusList) {
	      String ppic_m_id = m_id;
	      int ppic_p_num = cdto.getP_num();
	      cdto.setPpic_m_id(ppic_m_id);
	      cdto.setPpic_p_num(ppic_p_num);
	      
    	  double cstar = service.reviewStar(cdto.getP_num());
          cdto.setStar(cstar);
          
        	String category1="";
      		String category2="";
      		String category3="";
      	
	      	String cate = cdto.getP_category()+"";
	      	if((cate.charAt(0)-48) == 1) {
	      		category1 = "국내산";
	      	} else{
	      		category1 = "수입산";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 1) {
	      		category2 = "돼지고기";
	      	} else{
	      		category2 = "소고기";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
	      		category3 = "특수부위";
	      	} else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
	      		category3 = "삼겹살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
	      		category3 = "목살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
	      		category3 = "안심";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
	      		category3 = "등심";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
	      		category3 = "앞다리살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
	      		category3 = "갈매기살";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
	      		category3 = "특수부위";
	      	} else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
	      		category3 = "등심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
	      		category3 = "안심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
	      		category3 = "갈비";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
	      		category3 = "채끝";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
	      		category3 = "목심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
	      		category3 = "부채살";
	      	}
	      	cdto.setCategory1(category1);
	      	cdto.setCategory2(category2);
	      	cdto.setCategory3(category3);

	      	int r_p_num = service.reviewAllCNT(cdto.getP_num());
	      	cdto.setReviewAllCNT(r_p_num);
	      }
	  	model.addAttribute("cusList", cusList);
	  
      List<ProductDTO> meatList = service.meatBest();
      for(ProductDTO mdto : meatList) {
    	  String ppic_m_id = m_id;
	      int ppic_p_num = mdto.getP_num();
	      mdto.setPpic_m_id(ppic_m_id);
	      mdto.setPpic_p_num(ppic_p_num);
	      
    	  double mstar = service.reviewStar(mdto.getP_num());
    	  mdto.setStar(mstar);
    	  model.addAttribute("p_num", mdto.getP_num());
    	  
    	  String category1="";
    	  String category2="";
    	  String category3="";
    	
    	  String cate = mdto.getP_category()+"";

    	  if((cate.charAt(0)-48) == 1) {
    		  category1 = "국내산";
    	  } else{
    		  category1 = "수입산";
    	  }
	      	
    	  if((cate.charAt(1)-48) == 1) {
    		  category2 = "돼지고기";
    	  } else{
    		  category2 = "소고기";
    	  }
	      	
	      	if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
	      		category3 = "특수부위";
	      	} else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
	      		category3 = "삼겹살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
	      		category3 = "목살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
	      		category3 = "안심";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
	      		category3 = "등심";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
	      		category3 = "앞다리살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
	      		category3 = "갈매기살";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
	      		category3 = "특수부위";
	      	} else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
	      		category3 = "등심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
	      		category3 = "안심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
	      		category3 = "갈비";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
	      		category3 = "채끝";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
	      		category3 = "목심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
	      		category3 = "부채살";
	      	}
	      	mdto.setCategory1(category1);
	      	mdto.setCategory2(category2);
	      	mdto.setCategory3(category3);

	      	int r_p_num = service.reviewAllCNT(mdto.getP_num());
	      	mdto.setReviewAllCNT(r_p_num);
	      	}
      		model.addAttribute("meatList", meatList);
      
      List<ProductDTO> forkList = service.forkBest();
      for(ProductDTO fdto : forkList) {
	      String ppic_m_id = m_id;
	      int ppic_p_num = fdto.getP_num();
	      fdto.setPpic_m_id(ppic_m_id);
	      fdto.setPpic_p_num(ppic_p_num);
	      
    	  double fstar = service.reviewStar(fdto.getP_num());
    	  fdto.setStar(fstar);
    	  model.addAttribute("p_num", fdto.getP_num());
    	  
    	  	String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = fdto.getP_category()+"";
	
	      	if((cate.charAt(0)-48) == 1) {
	      		category1 = "국내산";
	      	} else{
	      		category1 = "수입산";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 1) {
	      		category2 = "돼지고기";
	      	} else{
	      		category2 = "소고기";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
	      		category3 = "특수부위";
	      	} else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
	      		category3 = "삼겹살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
	      		category3 = "목살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
	      		category3 = "안심";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
	      		category3 = "등심";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
	      		category3 = "앞다리살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
	      		category3 = "갈매기살";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
	      		category3 = "특수부위";
	      	} else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
	      		category3 = "등심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
	      		category3 = "안심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
	      		category3 = "갈비";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
	      		category3 = "채끝";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
	      		category3 = "목심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
	      		category3 = "부채살";
	      	}
	      	fdto.setCategory1(category1);
	      	fdto.setCategory2(category2);
	      	fdto.setCategory3(category3);

	      	int r_p_num = service.reviewAllCNT(fdto.getP_num());
	      	fdto.setReviewAllCNT(r_p_num);
	      }
      model.addAttribute("forkList", forkList);
      
      List<ProductDTO> newProduct = service.newProductBest();
      for(ProductDTO ndto : newProduct) {
    	  String ppic_m_id = m_id;
	      int ppic_p_num = ndto.getP_num();
	      ndto.setPpic_m_id(ppic_m_id);
	      ndto.setPpic_p_num(ppic_p_num);
    	  
    	  double nstar = service.reviewStar(ndto.getP_num());
    	  ndto.setStar(nstar);
    	  model.addAttribute("p_num", ndto.getP_num());
    	  
    	  	String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = ndto.getP_category()+"";
	
	      	if((cate.charAt(0)-48) == 1) {
	      		category1 = "국내산";
	      	} else{
	      		category1 = "수입산";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 1) {
	      		category2 = "돼지고기";
	      	} else{
	      		category2 = "소고기";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 1 && (cate.charAt(1)-48) == 0) {
	      		category3 = "특수부위";
	      	} else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 1){
	      		category3 = "삼겹살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 2){
	      		category3 = "목살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 3){
	      		category3 = "안심";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 4){
	      		category3 = "등심";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 5){
	      		category3 = "앞다리살";
	      	}else if((cate.charAt(1)-48) == 1 && (cate.charAt(2)-48) == 6){
	      		category3 = "갈매기살";
	      	}
	      	
	      	if((cate.charAt(1)-48) == 2 && (cate.charAt(1)-48) == 0) {
	      		category3 = "특수부위";
	      	} else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 1){
	      		category3 = "등심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 2){
	      		category3 = "안심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 3){
	      		category3 = "갈비";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 4){
	      		category3 = "채끝";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 5){
	      		category3 = "목심";
	      	}else if((cate.charAt(1)-48) == 2 && (cate.charAt(2)-48) == 6){
	      		category3 = "부채살";
	      	}
	      	ndto.setCategory1(category1);
	      	ndto.setCategory2(category2);
	      	ndto.setCategory3(category3);

	      	if(seid != null) {
	      		service.pick_p_numCNT(ppic_m_id, ppic_p_num);
		    }
	      	int r_p_num = service.reviewAllCNT(ndto.getP_num());
	      	ndto.setReviewAllCNT(r_p_num);
	      }
      model.addAttribute("newProduct", newProduct);
      
      
      if(seid != null) {
          m_id = (String)seid.getName(); 
          model.addAttribute("m_id", m_id);
       }
      
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

      return "main/main2";
   }
   
   @RequestMapping("meatYou")
   public String meatYou() {
      return "main/meatYou";
   }
   
   @RequestMapping("customOrder")
   public String customOrder(String pre_m_id, Model model) {
	   	PreferDTO dto = service.customOrderCategory(pre_m_id , model);
		int result=0;
		int category1 = dto.getPre0_response();
		int category2 = dto.getPre1_response();
		if(category1 == 100) {
			category1 = 110;
		}else {
			category1 = 120;
		}
		result = category1+category2;
		dto.setPre_category(result);
		
		model.addAttribute("dto", dto);
      return "main/customOrder";
   }
   

}