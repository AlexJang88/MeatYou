package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.ProductDTO;

public interface MainMapper {
 
   // 메인 글 카운트
   public int cusCount();
   
   // 메인 파워링크 리스트
   public List<ProductDTO> mainCUS();
   
   // 메인 소고기 베스트
   public List<ProductDTO> meatBest();
   
   // 메인 돼지고기 베스트
   public List<ProductDTO> forkBest();
   
   // 신상품 리스트 카운트
   public int newProductCNT();
   
   // 메인 신상품 베스트
   public List<ProductDTO> newProductBest();

   // 서칭 리스트
   public List<ProductDTO> searchList(HashMap map);
   
   // 서칭 리스트 카운트
   public int searchCount(@Param("searchOption") String searchOption, @Param("search") String search);
   
   // 서칭 리스트 가격순 정렬
   public List<ProductDTO> searchPrice(HashMap map);
   
   // 서칭 리스트 판매량순 정렬
   public List<ProductDTO> searchSale(HashMap map);
   
   // 메인 상단바 소고기, 돼지고기 리스트 카운트
   public int mainMeatCount(@Param("category") int category);
   
   // 메인 상단바 소고기, 돼지고기 리스트 (가격순, 판매량순 정렬 포함)
   // hashmap 사용
   public List<ProductDTO> mainMeat(HashMap map);
   
   // 메인 상단바 소고기, 돼지고기 리스트 (가격순, 판매량순 정렬 포함)
   // @Param 사용
//   public List<ProductDTO> mainMeat(@Param("start")int startRow, @Param("end")int endRow, @Param("category")int category, 
//         @Param("price")String price, @Param("sale")String sale, @Param("reg")String reg);

   public int powCount();
   
   public List<ProductDTO> poLinkList(HashMap map);

   // 메인 신상품
   public List<ProductDTO> newProduct(HashMap map);
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}