package com.gogi.meatyou.repository;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.OtherProductDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductDetailDTO;
import com.gogi.meatyou.bean.ReviewDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;

public interface MainMapper {
 

   public int cusCount();
   

   public List<ProductDTO> mainCUS();
   

   public List<ProductDTO> meatBest();
   

   public List<ProductDTO> forkBest();
   

   public int newProductCNT();
   

   public List<ProductDTO> newProductBest();


   public List<ProductDTO> searchList(HashMap map);


   public int searchCount(@Param("searchOption") String searchOption, @Param("search") String search);
   public int searchSaleCNT(@Param("searchOption") String searchOption, @Param("search") String search);
   public int searchStarCount(@Param("searchOption") String searchOption, @Param("search") String search);
   public int searchReviewCount(@Param("searchOption") String searchOption, @Param("search") String search);
   
   public List<ProductDTO> searchPrice(HashMap map);
   public List<ProductDTO> searchSale(HashMap map);
   public List<ProductDTO> searchListStar(HashMap map);
   public List<ProductDTO> searchListReview(HashMap map);
   
   public int mainMeatCount(@Param("category") int category);
   public List<ProductDTO> mainMeat(HashMap map);
   public List<ProductDTO> mainMeatSort(HashMap map);
   public List<ProductDTO> mainMeatReview(HashMap map);
   
   public int mainMeatKOR_CNT(@Param("category") int category);
   public int mainMeatEX_CNT(@Param("category") int category);
   public int mainForkKOR_CNT(@Param("category") int category);
   public int mainForkEX_CNT(@Param("category") int category);
   public List<ProductDTO> mainMeatKOR(HashMap map);
   public List<ProductDTO> mainMeatEX(HashMap map);
   public List<ProductDTO> mainForkKOR(HashMap map);
   public List<ProductDTO> mainForkEX(HashMap map);
   
   public int setMenuCNT(int p_s_category);
   public int setMenuSortCNT();
   public int setMenuSalesCNT();
   public List<ProductDetailDTO> setMenu(HashMap map);
   public List<ProductDetailDTO> setMenuPrice(HashMap map);
   public List<ProductDetailDTO> setMenuSales(HashMap map);
   public List<ProductDetailDTO> setMenuStars(HashMap map);
   public List<ProductDetailDTO> setMenuReviews(HashMap map);
   public List<ProductDetailDTO> setMenuNews(HashMap map);
   
   public int giftSetCNT();
   public int giftSetSalesCNT();
   public List<ProductDetailDTO> giftSet(HashMap map);
   public List<ProductDetailDTO> giftSetPrice(HashMap map);
   public List<ProductDetailDTO> giftSetSales(HashMap map);
   public List<ProductDetailDTO> giftSetStars(HashMap map);
   public List<ProductDetailDTO> giftSetReviews(HashMap map);
   public List<ProductDetailDTO> giftSetNews(HashMap map);
   
   public int powCount();
   
   public List<ProductDTO> poLinkList(HashMap map);
   public List<ProductDTO> poLinkSort(HashMap map);


   public List<ProductDTO> newProduct(HashMap map);

   public ProductDetailDTO productDetail(ProductDetailDTO dto);
   
   public int otherProductCount(String p_m_id);
   public List<OtherProductDetailDTO> otherProductDetail(OtherProductDetailDTO dto);
   
   public List<ReviewDTO> reviewAll(int p_num);

   public int mOderCount(@Param("order_m_id")String mid, @Param("order_p_num")int p_num);
   public int reviewCount(@Param("r_m_id")String mid , @Param("r_p_num")int p_num);
   
   public int reviewAllCNT(int r_p_num);
   
   public int reviewStar(@Param("r_p_num")int p_num);
   public int star1(int r_p_num);
   public int star2(int r_p_num);
   public int star3(int r_p_num);
   public int star4(int r_p_num);
   public int star5(int r_p_num);
    
   public double star1Per(int r_p_num);
   public double star2Per(int r_p_num);
   public double star3Per(int r_p_num);
   public double star4Per(int r_p_num);
   public double star5Per(int r_p_num);
   
   public double reviewSum(int p_num);
   public void reviewInsert(ReviewDTO dto);
   
   public int reviewAllPagingCNT(int p_num);
   public List<ReviewDTO> reviewAllPaging(HashMap map);

   public int ShoppingCartCNT(ShoppingCartDTO dto);
   public int ShoppingCartCNT2(String shop_m_id);
   
   public void ShoppingCartInsert(ShoppingCartDTO dto);
   public void ShoppingCartInsert2(@Param("shop_m_id")String m_id, @Param("shop_p_num")int p_num, @Param("shop_quantity")int shop_quantity);

   public int pickCNT(String ppic_m_id);
   public int pickCNTMain(PPicDTO dto);
   public int pick_p_numCNT(@Param("ppic_m_id")String ppic_m_id, @Param("ppic_p_num")int ppic_p_num);
   public int pick_p_numCNTMain(@Param("ppic_m_id")String ppic_m_id, @Param("ppic_p_num")int ppic_p_num);
   public void pickInsert(PPicDTO dto);
   public void pickInsertMain(@Param("ppic_m_id")String ppic_m_id, @Param("ppic_p_num")int ppic_p_num);
   public void pickDelete(PPicDTO dto);
   public void pickDeleteMain(@Param("ppic_m_id")String ppic_m_id, @Param("ppic_p_num")int ppic_p_num);

   public int pCategory(int p_num);

   public int getStatus(String id);// 지환 설문조사

}