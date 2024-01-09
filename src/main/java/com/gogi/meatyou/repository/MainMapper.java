package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductDetailDTO;

public interface MainMapper {
 

   public int cusCount();
   

   public List<ProductDTO> mainCUS();
   

   public List<ProductDTO> meatBest();
   

   public List<ProductDTO> forkBest();
   

   public int newProductCNT();
   

   public List<ProductDTO> newProductBest();


   public List<ProductDTO> searchList(HashMap map);
   

   public int searchCount(@Param("searchOption") String searchOption, @Param("search") String search);
   

   public List<ProductDTO> searchPrice(HashMap map);
   

   public List<ProductDTO> searchSale(HashMap map);
   

   public int mainMeatCount(@Param("category") int category);
   
   public List<ProductDTO> mainMeat(HashMap map);
   
   
//   public List<ProductDTO> mainMeat(@Param("start")int startRow, @Param("end")int endRow, @Param("category")int category, 
//         @Param("price")String price, @Param("sale")String sale, @Param("reg")String reg);

   public int powCount();
   
   public List<ProductDTO> poLinkList(HashMap map);


   public List<ProductDTO> newProduct(HashMap map);

   public ProductDetailDTO productDetail(ProductDetailDTO dto);

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}