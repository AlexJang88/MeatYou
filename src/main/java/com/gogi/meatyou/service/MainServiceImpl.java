package com.gogi.meatyou.service;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.OtherProductDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductDetailDTO;
import com.gogi.meatyou.bean.ReviewDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.repository.MainMapper;

@Service
public class MainServiceImpl implements MainService {
   
   @Autowired
   private MainMapper mapper;

   @Autowired
   private HashMap searchMap;

   
   
   @Override
   public int cusCount() {
      int result = 0;
      result = mapper.cusCount();
      return result;
   }
   
   @Override
   public List<ProductDTO> mainCUS() {
      return mapper.mainCUS();
   }
   
   @Override
   public List<ProductDTO> meatBest() {
      return mapper.meatBest();
   }

   @Override
   public List<ProductDTO> forkBest() {
      return mapper.forkBest();
   }

   @Override
   public List<ProductDTO> searchList(Principal seid, int pageNum, Model model, String desc, String searchOption, String search) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   
	   int pageSize = 6; 
       	int startRow = (pageNum - 1) * pageSize + 1;
       	int endRow = pageNum * pageSize;
       	int count = mapper.searchCount(searchOption, search);
       
       	List<ProductDTO> searchList = Collections.EMPTY_LIST;
       	if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("desc", desc);
          searchMap.put("searchOption", searchOption);
          searchMap.put("search", search);
          searchList = mapper.searchList(searchMap);
       	}
       	model.addAttribute("searchList", searchList);
       	model.addAttribute("pageSize", pageSize);
       	model.addAttribute("pageNum", pageNum);
       	model.addAttribute("count", count);
       	model.addAttribute("searchOption", searchOption);
       	model.addAttribute("search", search);
       
     //page
       	int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
      
       	int startPage = (int)(pageNum/10)*10+1;
       	int pageBlock=10;
       	int endPage = startPage + pageBlock-1;
       	if (endPage > pageCount) {
       		endPage = pageCount;
       	}
       	model.addAttribute("pageCount", pageCount);
       	model.addAttribute("startPage", startPage);
       	model.addAttribute("pageBlock", pageBlock);
       	model.addAttribute("endPage", endPage);

      
       	for(ProductDTO rdto : searchList) {
       		int p_num = rdto.getP_num();
       		double result=0;
       		double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
   			}
    	  	String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);

	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
	}
	return mapper.searchList(searchMap);
       
      
   }
   
   
   @Override
   public List<ProductDTO> searchListStar(Principal seid, ProductDTO dto, int pageNum, Model model , String searchOption, String search) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.searchStarCount(searchOption, search);
       
       List<ProductDTO> searchListStar = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("searchOption", searchOption);
          searchMap.put("search", search);
          searchListStar = mapper.searchListStar(searchMap);
       }

       model.addAttribute("searchListStar", searchListStar);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       model.addAttribute("searchOption", searchOption);
       model.addAttribute("search", search);
       
     //page
       int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
      
       int startPage = (int)(pageNum/10)*10+1;
       int pageBlock=10;
       int endPage = startPage + pageBlock-1;
       if (endPage > pageCount) {
          endPage = pageCount;
       }
      model.addAttribute("pageCount", pageCount);
      model.addAttribute("startPage", startPage);
      model.addAttribute("pageBlock", pageBlock);
      model.addAttribute("endPage", endPage);

      
       for(ProductDTO rdto : searchListStar) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
	return mapper.searchListStar(searchMap);
   }
   
   @Override
   public List<ProductDTO> searchListReview(Principal seid, ProductDTO dto, int pageNum, Model model , String searchOption, String search) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.searchReviewCount(searchOption, search);
       
       List<ProductDTO> searchListReview = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("searchOption", searchOption);
          searchMap.put("search", search);
          searchListReview = mapper.searchListReview(searchMap);
       }

       model.addAttribute("searchListReview", searchListReview);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       model.addAttribute("searchOption", searchOption);
       model.addAttribute("search", search);
       
     //page
       int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
      
       int startPage = (int)(pageNum/10)*10+1;
       int pageBlock=10;
       int endPage = startPage + pageBlock-1;
       if (endPage > pageCount) {
          endPage = pageCount;
       }
      model.addAttribute("pageCount", pageCount);
      model.addAttribute("startPage", startPage);
      model.addAttribute("pageBlock", pageBlock);
      model.addAttribute("endPage", endPage);

      
       for(ProductDTO rdto : searchListReview) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
	return mapper.searchListReview(searchMap);
   }
   
   @Override
   public List<ProductDTO> searchPrice(Principal seid, int pageNum, Model model, String price, String searchOption, String search) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.searchCount(searchOption, search);
       List<ProductDTO> searchPrice = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("price", price);
          searchMap.put("searchOption", searchOption);
          searchMap.put("search", search);
          searchPrice = mapper.searchPrice(searchMap);
       }
       model.addAttribute("searchPrice", searchPrice);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       model.addAttribute("searchOption", searchOption);
       model.addAttribute("search", search);
       
       for(ProductDTO rdto : searchPrice) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);

	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
	return searchPrice;
   }
   
   @Override
   public List<ProductDTO> searchSale(Principal seid, int pageNum, Model model, String searchOption, String search) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.searchSaleCNT(searchOption, search);
       List<ProductDTO> searchSale = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);

          searchMap.put("searchOption", searchOption);
          searchMap.put("search", search);
          searchSale = mapper.searchSale(searchMap);
       }
       model.addAttribute("searchSale", searchSale);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       model.addAttribute("searchOption", searchOption);
       model.addAttribute("search", search);
       
       for(ProductDTO rdto : searchSale) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
   			
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
      int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        } 
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
	return mapper.searchSale(searchMap);
   }

   @Override
   public void mainMeat(Principal seid, int pageNum, Model model, String price , int category, String sale, String reg, String news, String star) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.mainMeatCount(category);
       List<ProductDTO> mainMeat = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("category", category);
          searchMap.put("price", price);
          searchMap.put("sale", sale);
          searchMap.put("reg", reg);
          searchMap.put("news", news);
          mainMeat = mapper.mainMeat(searchMap);
       }
       model.addAttribute("mainMeat", mainMeat);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
       for(ProductDTO rdto : mainMeat) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num", p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
       
       for(ProductDTO rdto : mainMeat) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
   			}
       }
   }
   
   @Override
   public void mainMeatKOR(Principal seid, int pageNum, Model model, String price , int category, String sale, String reg, String news, String star, String review) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.mainMeatKOR_CNT(category);
       List<ProductDTO> mainMeatKOR = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("category", category);
          searchMap.put("price", price);
          searchMap.put("sale", sale);
          searchMap.put("reg", reg);
          searchMap.put("news", news);
          searchMap.put("review", review);
          searchMap.put("star", star);
          mainMeatKOR = mapper.mainMeatKOR(searchMap);
       }
       model.addAttribute("mainMeatKOR", mainMeatKOR);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
       model.addAttribute("price", price);
       model.addAttribute("sale", sale);
       model.addAttribute("news", news);
       model.addAttribute("review", review);
       model.addAttribute("star", star);
       for(ProductDTO rdto : mainMeatKOR) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num", p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
       
       for(ProductDTO rdto : mainMeatKOR) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
   			}
       }
   }

   @Override
   public void mainMeatEX(Principal seid, int pageNum, Model model, String price , int category, String sale, String reg, String news, String star, String review) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.mainMeatEX_CNT(category);
       List<ProductDTO> mainMeatKOR = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("category", category);
          searchMap.put("price", price);
          searchMap.put("sale", sale);
          searchMap.put("reg", reg);
          searchMap.put("news", news);
          searchMap.put("review", review);
          searchMap.put("star", star);
          mainMeatKOR = mapper.mainMeatKOR(searchMap);
       }
       model.addAttribute("mainMeatEX", mainMeatKOR);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
       model.addAttribute("price", price);
       model.addAttribute("sale", sale);
       model.addAttribute("news", news);
       model.addAttribute("review", review);
       model.addAttribute("star", star);
       for(ProductDTO rdto : mainMeatKOR) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num", p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
       
       for(ProductDTO rdto : mainMeatKOR) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
   			}
       }
   }
   
   @Override
   public void mainForkKOR(Principal seid, int pageNum, Model model, String price , int category, String sale, String reg, String news, String star, String review) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.mainMeatKOR_CNT(category);
       List<ProductDTO> mainMeatKOR = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("category", category);
          searchMap.put("price", price);
          searchMap.put("sale", sale);
          searchMap.put("reg", reg);
          searchMap.put("news", news);
          searchMap.put("review", review);
          searchMap.put("star", star);
          mainMeatKOR = mapper.mainMeatKOR(searchMap);
       }
       model.addAttribute("mainForkKOR", mainMeatKOR);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);

       model.addAttribute("price", price);
       model.addAttribute("sale", sale);
       model.addAttribute("news", news);
       model.addAttribute("review", review);
       model.addAttribute("star", star);       
       for(ProductDTO rdto : mainMeatKOR) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num", p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
       
       for(ProductDTO rdto : mainMeatKOR) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
   			}
       }
   }
   
   @Override
   public void mainForkEX(Principal seid, int pageNum, Model model, String price , int category, String sale, String reg, String news, String star, String review) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.mainMeatKOR_CNT(category);
       List<ProductDTO> mainMeatKOR = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("category", category);
          searchMap.put("price", price);
          searchMap.put("sale", sale);
          searchMap.put("reg", reg);
          searchMap.put("news", news);
          searchMap.put("review", review);
          searchMap.put("star", star);
          mainMeatKOR = mapper.mainMeatKOR(searchMap);
       }
       model.addAttribute("mainForkEX", mainMeatKOR);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
       model.addAttribute("price", price);
       model.addAttribute("sale", sale);
       model.addAttribute("news", news);
       model.addAttribute("review", review);
       model.addAttribute("star", star);
       for(ProductDTO rdto : mainMeatKOR) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num", p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
       
       for(ProductDTO rdto : mainMeatKOR) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
   			}
       }
   }


   @Override
   public void mainMeatSort(Principal seid, int pageNum, Model model, int category, String star) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.mainMeatCount(category);
       List<ProductDTO> mainMeatSort = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("category", category);
          searchMap.put("star", star);
          mainMeatSort = mapper.mainMeatSort(searchMap);
       }
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
       for(ProductDTO rdto : mainMeatSort) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num", p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
       
       for(ProductDTO rdto : mainMeatSort) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
   			sum = mapper.reviewSum(p_num);
   			result = sum / (double)recount;
   			result = Double.parseDouble(String.format("%.1f", result));
   			rdto.setStar(result);
   			model.addAttribute("p_num",p_num);
   			}
       }
       model.addAttribute("mainMeatSort", mainMeatSort);
   }
   
   @Override
   public void mainMeatReview(Principal seid, int pageNum, Model model, int category, String star) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
	   int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.mainMeatCount(category);
       List<ProductDTO> mainMeatReview = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("category", category);
          searchMap.put("star", star);
          mainMeatReview = mapper.mainMeatReview(searchMap);
       }
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
       for(ProductDTO rdto : mainMeatReview) {
    	   	int p_num = rdto.getP_num();
    	   	double result=0;
    	   	double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
   			sum = mapper.reviewSum(p_num);
   			result = sum / (double)recount;
   			result = Double.parseDouble(String.format("%.1f", result));
   			rdto.setStar(result);
   			model.addAttribute("p_num", p_num);
   			
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
   			}
       }
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
       
       for(ProductDTO rdto : mainMeatReview) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
   			sum = mapper.reviewSum(p_num);
   			result = sum / (double)recount;
   			result = Double.parseDouble(String.format("%.1f", result));
   			rdto.setStar(result);
   			model.addAttribute("p_num",p_num);
   			}
       }
       model.addAttribute("mainMeatReview", mainMeatReview);
   }
 
	@Override
	public void setMenu(Principal seid, Model model, int p_s_category,int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.setMenuCNT(p_s_category);
	       List<ProductDetailDTO> setMenu = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          searchMap.put("p_s_category", p_s_category);
	          setMenu = mapper.setMenu(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : setMenu) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : setMenu) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("setMenu", setMenu);
	   }
	
	@Override
	public void setMenuSales(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.setMenuSalesCNT();
	       List<ProductDetailDTO> setMenuSales = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          setMenuSales = mapper.setMenuSales(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : setMenuSales) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : setMenuSales) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("setMenuSales", setMenuSales);
	   }
	
	@Override
	public void setMenuStars(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.setMenuSortCNT();
	       List<ProductDetailDTO> setMenuStars = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          setMenuStars = mapper.setMenuStars(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : setMenuStars) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : setMenuStars) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("setMenuStars", setMenuStars);
	   }
	
	@Override
	public void setMenuReviews(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.setMenuSortCNT();
	       List<ProductDetailDTO> setMenuReviews = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          setMenuReviews = mapper.setMenuReviews(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : setMenuReviews) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : setMenuReviews) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("setMenuReviews", setMenuReviews);
	   }
	
	@Override
	public void setMenuNews(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.setMenuSortCNT();
	       List<ProductDetailDTO> setMenuNews = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          setMenuNews = mapper.setMenuNews(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : setMenuNews) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : setMenuNews) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("setMenuNews", setMenuNews);
	   }
	
	@Override
	public void setMenuPrice(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.setMenuSortCNT();
	       List<ProductDetailDTO> setMenuPrice = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          setMenuPrice = mapper.setMenuPrice(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : setMenuPrice) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : setMenuPrice) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("setMenuPrice", setMenuPrice);
	   }
	
	@Override
	public void giftSetPrice(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.giftSetCNT();
	       List<ProductDetailDTO> giftSetPrice = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          giftSetPrice = mapper.giftSetPrice(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : giftSetPrice) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : giftSetPrice) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("giftSetPrice", giftSetPrice);
	   }
	
	@Override
	public void giftSetSales(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.giftSetSalesCNT();
	       List<ProductDetailDTO> giftSetSales = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          giftSetSales = mapper.giftSetSales(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : giftSetSales) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : giftSetSales) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("giftSetSales", giftSetSales);
	   }
	
	@Override
	public void giftSetStars(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.giftSetCNT();
	       List<ProductDetailDTO> giftSetStars = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          giftSetStars = mapper.giftSetStars(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : giftSetStars) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : giftSetStars) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("giftSetStars", giftSetStars);
	   }
	
	@Override
	public void giftSetReviews(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.giftSetCNT();
	       List<ProductDetailDTO> giftSetReviews = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          giftSetReviews = mapper.giftSetReviews(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : giftSetReviews) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : giftSetReviews) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("giftSetReviews", giftSetReviews);
	   }
	
	@Override
	public void giftSetNews(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.giftSetCNT();
	       List<ProductDetailDTO> giftSetNews = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          giftSetNews = mapper.giftSetNews(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : giftSetNews) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : giftSetNews) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("giftSetNews", giftSetNews);
	   }
	
	@Override
	public void giftSet(Principal seid, Model model, int pageNum) {
		   String m_id = "";
		   if(seid != null) {
			   	m_id = (String)seid.getName(); 
			   	model.addAttribute("m_id", m_id);
		   }
		   int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.giftSetCNT();
	       List<ProductDetailDTO> giftSet = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          giftSet = mapper.giftSet(searchMap);
	       }
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("count", count);
	       
	       for(ProductDetailDTO rdto : giftSet) {
	    	   	int p_num = rdto.getP_num();
	    	   	double result=0;
	    	   	double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
		   			sum = mapper.reviewSum(p_num);
		   			result = sum / (double)recount;
		   			result = Double.parseDouble(String.format("%.1f", result));
		   			rdto.setStar(result);
		   			model.addAttribute("p_num", p_num);
	   			}
	   			String category1="";
	    		String category2="";
	    		String category3="";
	    	
		      	String cate = rdto.getP_category()+"";
		
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
		      	rdto.setCategory1(category1);
		      	rdto.setCategory2(category2);
		      	rdto.setCategory3(category3);
		      	
		      	String ppic_m_id = m_id;
		      	int ppic_p_num = rdto.getP_num();
		      	rdto.setPpic_m_id(ppic_m_id);
		      	rdto.setPpic_p_num(ppic_p_num);
		      	
		      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
		      	rdto.setReviewAllCNT(r_p_num);
	       }
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        }
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	       
	       for(ProductDetailDTO rdto : giftSet) {
	    	   int p_num = rdto.getP_num();
	    	   double result=0;
	    	   double sum =0.0;
	   		  	int recount = mapper.reviewAllCNT(p_num);
	   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num",p_num);
	   			}
	       }
	       model.addAttribute("giftSet", giftSet);
	   }

   @Override
   public List<ProductDTO> newProductBest() {
      return mapper.newProductBest();
   }
   

   @Override
   public void poLinkList(Principal seid, int pageNum, Model model, String price, String sale, String star, String review, String news) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
      int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.powCount();
       List<ProductDTO> poLinkList = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          searchMap.put("price", price);
          searchMap.put("sale", sale);
          searchMap.put("star", star);
          searchMap.put("review", review);
          searchMap.put("news", news);
          poLinkList = mapper.poLinkList(searchMap);
       }
       model.addAttribute("poLinkSort", poLinkList);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       model.addAttribute("price", price);
       model.addAttribute("sale", sale);
       model.addAttribute("star", star);
       model.addAttribute("review", review);
       model.addAttribute("news", news);
       
       for(ProductDTO rdto : poLinkList) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num", p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
   }


   @Override
   public void newProduct(Principal seid, int pageNum, Model model) {
	   String m_id = "";
	   if(seid != null) {
		   	m_id = (String)seid.getName(); 
		   	model.addAttribute("m_id", m_id);
	   }
      int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.newProductCNT();
       List<ProductDTO> newProduct = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          newProduct = mapper.newProduct(searchMap);
       }
       model.addAttribute("newProduct", newProduct);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
       for(ProductDTO rdto : newProduct) {
    	   int p_num = rdto.getP_num();
    	   double result=0;
    	   double sum =0.0;
   		  	int recount = mapper.reviewAllCNT(p_num);
   			if(recount > 0) {
	   			sum = mapper.reviewSum(p_num);
	   			result = sum / (double)recount;
	   			result = Double.parseDouble(String.format("%.1f", result));
	   			rdto.setStar(result);
	   			model.addAttribute("p_num", p_num);
   			}
   			String category1="";
    		String category2="";
    		String category3="";
    	
	      	String cate = rdto.getP_category()+"";
	
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
	      	rdto.setCategory1(category1);
	      	rdto.setCategory2(category2);
	      	rdto.setCategory3(category3);
	      	
	      	String ppic_m_id = m_id;
	      	int ppic_p_num = rdto.getP_num();
	      	rdto.setPpic_m_id(ppic_m_id);
	      	rdto.setPpic_p_num(ppic_p_num);
	      	
	      	int r_p_num = mapper.reviewAllCNT(rdto.getP_num());
	      	rdto.setReviewAllCNT(r_p_num);
       }
       
       //page
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
       
        int startPage = (int)(pageNum/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
           endPage = pageCount;
        }
       model.addAttribute("pageCount", pageCount);
       model.addAttribute("startPage", startPage);
       model.addAttribute("pageBlock", pageBlock);
       model.addAttribute("endPage", endPage);
   } 

@Override
public ProductDetailDTO productDetail(ProductDetailDTO dto, Model model) {
	return mapper.productDetail(dto);
	}

	@Override
	public int otherProductCount(String p_m_id) {
		return mapper.otherProductCount(p_m_id);
	}

	@Override
	public List<OtherProductDetailDTO> otherProductDetail(OtherProductDetailDTO dto, Model model) {
		return mapper.otherProductDetail(dto);
	}

	@Override
	public List<ReviewDTO> reviewAll(int p_num) {
		return mapper.reviewAll(p_num);
	}

	@Override
	public void reviewCNT(String mid, int p_num, Model model) {
		int order = 0;
		int review = 0;
		int result = 0;
		order = mapper.mOderCount(mid, p_num);
		review = mapper.reviewCount(mid , p_num);
		if(order >= 1) {
			result = order - review;
		} else {
			result = -1;
		}
		model.addAttribute("result" , result);
	}
	
	@Override
	public void reviewInsert(ReviewDTO dto) {
		mapper.reviewInsert(dto);
	}

	@Override
	public double reviewStar(int p_num) {
		double result=0;
		double sum =0.0;
		int count = mapper.reviewAllCNT(p_num);
		if(count > 0) {
			sum = mapper.reviewSum(p_num);
			result = sum / (double)count;
			result = Double.parseDouble(String.format("%.1f", result));
		}
		return result;
	}

	@Override
	public int star1(int r_p_num) {
		return mapper.star1(r_p_num);
	}
	
	@Override
	public int star2(int r_p_num) {
		return mapper.star2(r_p_num);
	}
	
	@Override
	public int star3(int r_p_num) {
		return mapper.star3(r_p_num);
	}
	
	@Override
	public int star4(int r_p_num) {
		return mapper.star4(r_p_num);
	}
	
	@Override
	public int star5(int r_p_num) {
		return mapper.star5(r_p_num);
	}

	@Override
	public int reviewAllCNT(int r_p_num) {
		return mapper.reviewAllCNT(r_p_num);
	}

	@Override
	public void reviewAllPaging(Model model, int p_num, int pageNum) {
	       int pageSize = 6; 
	       int startRow = (pageNum - 1) * pageSize + 1;
	       int endRow = pageNum * pageSize;
	       int count = mapper.reviewAllPagingCNT(p_num);
	       List<ReviewDTO> reviewAllPaging = Collections.EMPTY_LIST;
	       if(count > 0) {
	          searchMap.put("start", startRow);
	          searchMap.put("end", endRow);
	          searchMap.put("p_num", p_num);
	          reviewAllPaging = mapper.reviewAllPaging(searchMap);
	       }
	       model.addAttribute("reviewAllPaging", reviewAllPaging);
	       model.addAttribute("pageSize", pageSize);
	       model.addAttribute("pageNum", pageNum);
	       model.addAttribute("rcount", count);
	       model.addAttribute("p_num", p_num);
	       
	       //page
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	       
	        int startPage = (int)(pageNum/10)*10+1;
	        int pageBlock=10;
	        int endPage = startPage + pageBlock-1;
	        if (endPage > pageCount) {
	           endPage = pageCount;
	        } 
	       model.addAttribute("pageCount", pageCount);
	       model.addAttribute("startPage", startPage);
	       model.addAttribute("pageBlock", pageBlock);
	       model.addAttribute("endPage", endPage);
	   }

	@Override
	public double star1Per(int r_p_num) {
		double reviewCnt=0.0;
		double star1Per=0.0;
		double result=0.0;
		reviewCnt = mapper.reviewAllCNT(r_p_num);
		star1Per = mapper.star1(r_p_num);
		result = (star1Per*100)/reviewCnt;
		return result;
	}

	@Override
	public double star2Per(int r_p_num) {
		double reviewCnt=0.0;
		double star2Per=0.0;
		double result=0.0;
		reviewCnt = mapper.reviewAllCNT(r_p_num);
		star2Per = mapper.star2(r_p_num);
		result = (star2Per*100)/reviewCnt;
		return result;
	}

	@Override
	public double star3Per(int r_p_num) {
		double reviewCnt=0.0;
		double star3Per=0.0;
		double result=0.0;
		reviewCnt = mapper.reviewAllCNT(r_p_num);
		star3Per = mapper.star3(r_p_num);
		result = (star3Per*100)/reviewCnt;
		return result;
	}

	@Override
	public double star4Per(int r_p_num) {
		double reviewCnt=0.0;
		double star4Per=0.0;
		double result=0.0;
		reviewCnt = mapper.reviewAllCNT(r_p_num);
		star4Per = mapper.star4(r_p_num);
		result = (star4Per*100)/reviewCnt;
		return result;
	}

	@Override
	public double star5Per(int r_p_num) {
		double reviewCnt=0.0;
		double star5Per=0.0;
		double result=0.0;
		reviewCnt = mapper.reviewAllCNT(r_p_num);
		star5Per = mapper.star5(r_p_num);
		result = (star5Per*100)/reviewCnt;
		return result;
	}

	@Override
	public int ShoppingCartCNT2(String shop_m_id) {
		return mapper.ShoppingCartCNT2(shop_m_id);
	}
	
	@Override
	public int ShoppingCartInsert(Model model, ShoppingCartDTO dto) {
		int CartCNT = mapper.ShoppingCartCNT(dto);
		mapper.ShoppingCartInsert(dto);
		model.addAttribute("dto" , dto);
		model.addAttribute("CartCNT" , CartCNT);
		return CartCNT;
	}
	
	@Override
	public int ShoppingCartInsert2(Model model, String m_id, int p_num, int shop_quantity) {
		int CartCNT = mapper.ShoppingCartCNT2(m_id);
		mapper.ShoppingCartInsert2(m_id , p_num, shop_quantity);
		model.addAttribute("m_id" , m_id);
		model.addAttribute("p_num" , p_num);
		model.addAttribute("shop_quantity" , shop_quantity);
		model.addAttribute("CartCNT" , CartCNT);
		return CartCNT;
	}

	@Override
	public void pickInsert(Model model, PPicDTO dto, String ppic_m_id, int ppic_p_num) {
		int result;
		result = mapper.pick_p_numCNT(ppic_m_id, ppic_p_num);
		if(result == 0) {
			mapper.pickInsert(dto);
		} else {
			mapper.pickDelete(dto);
		}
		model.addAttribute("dto" , dto);
		model.addAttribute("pResult" , result);
	}
	
	@Override
	public void pickInsertMain(Model model, String ppic_m_id, int ppic_p_num) {
		int result;
		result = mapper.pick_p_numCNT(ppic_m_id, ppic_p_num);
		if(result == 0) {
			mapper.pickInsertMain(ppic_m_id, ppic_p_num);
		} else {
			mapper.pickDeleteMain(ppic_m_id, ppic_p_num);
		}
		model.addAttribute("mainResult" , result);
	}

	@Override
	public int pickCNT(String ppic_m_id) {
		return mapper.pickCNT(ppic_m_id);
	}

	@Override
	public int pick_p_numCNT(String ppic_m_id, int ppic_p_num) {
		return mapper.pick_p_numCNT(ppic_m_id , ppic_p_num);
	}

	@Override
	public int pCategory(int p_num) {
		return mapper.pCategory(p_num);
	}

	@Override
	public void quantity(int co_p_num) {
		mapper.quantity(co_p_num);
	}

}