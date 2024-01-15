package com.gogi.meatyou.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
   public void searchList(int pageNum, Model model, String desc, String searchOption, String search) {
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
   }
   
   
   @Override
   public void searchPrice(int pageNum, Model model, String price, String searchOption, String search) {
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
   public void searchSale(int pageNum, Model model, String searchOption, String search) {
      int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.searchCount(searchOption, search);
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
   public void mainMeat(int pageNum, Model model, String price , int category, String sale, String reg) {
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
          mainMeat = mapper.mainMeat(searchMap);
       }
       model.addAttribute("mainMeat", mainMeat);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
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
   
//   @Override
//   public void mainMeat(int pageNum, Model model, String price , int category, String sale, String reg) {
//      int pageSize = 6; 
//       int startRow = (pageNum - 1) * pageSize + 1;
//       int endRow = pageNum * pageSize;
//       int count = mapper.mainMeatCount(category);
//       List<ProductDTO> mainMeat = Collections.EMPTY_LIST;
//       if(count > 0) {
//          mainMeat = mapper.mainMeat(startRow, endRow, category, price, sale, reg);
//       }
//       model.addAttribute("mainMeat", mainMeat);
//       model.addAttribute("pageSize", pageSize);
//       model.addAttribute("pageNum", pageNum);
//       model.addAttribute("count", count);
//       
//       
//       //page
//        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
//       
//        int startPage = (int)(pageNum/10)*10+1;
//      int pageBlock=10;
//        int endPage = startPage + pageBlock-1;
//        if (endPage > pageCount) {
//           endPage = pageCount;
//        }
//       model.addAttribute("pageCount", pageCount);
//       model.addAttribute("startPage", startPage);
//       model.addAttribute("pageBlock", pageBlock);
//       model.addAttribute("endPage", endPage);
//   }
   

   @Override
   public List<ProductDTO> newProductBest() {
      return mapper.newProductBest();
   }
   

   @Override
   public void poLinkList(int pageNum, Model model) {
      int pageSize = 6; 
       int startRow = (pageNum - 1) * pageSize + 1;
       int endRow = pageNum * pageSize;
       int count = mapper.powCount();
       List<ProductDTO> poLinkList = Collections.EMPTY_LIST;
       if(count > 0) {
          searchMap.put("start", startRow);
          searchMap.put("end", endRow);
          poLinkList = mapper.poLinkList(searchMap);
       }
       model.addAttribute("poLinkList", poLinkList);
       model.addAttribute("pageSize", pageSize);
       model.addAttribute("pageNum", pageNum);
       model.addAttribute("count", count);
       
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
   public void newProduct(int pageNum, Model model) {
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
   dto = mapper.productDetail(dto);
   
   String category1="";
   String category2="";
   String category3="";
   
   System.out.println("dto.getP_category() : "+dto);
   String cate = dto.getP_category()+"";

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

   model.addAttribute("category1", category1);
   model.addAttribute("category2", category2);
   model.addAttribute("category3", category3);

   return mapper.productDetail(dto);
   }


   @Override
   public List<ProductDetailDTO> otherProductDetail(ProductDetailDTO dto, Model model) {
      String category1="";
      String category2="";
      String category3="";
      
      String cate = dto.getP_category()+"";

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

      model.addAttribute("category1", category1);
      model.addAttribute("category2", category2);
      model.addAttribute("category3", category3);

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
          model.addAttribute("count", count);
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
   public void pickInsert(Model model, PPicDTO dto) {
      mapper.pickInsert(dto);
      model.addAttribute("dto" , dto);
   }

   @Override
   public int pickCNT(String ppic_m_id) {
      return mapper.pickCNT(ppic_m_id);
   }










}