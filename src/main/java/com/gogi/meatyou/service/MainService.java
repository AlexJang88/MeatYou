package com.gogi.meatyou.service;
 
import java.security.Principal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.OtherProductDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductDetailDTO;
import com.gogi.meatyou.bean.ReviewDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;

public interface MainService {

   public int cusCount();
   
   public List<ProductDTO> mainCUS();
   
   public List<ProductDTO> searchList(Principal seid, int pageNum, Model model , String desc, String searchOption, String search);
   public List<ProductDTO> searchPrice(Principal seid, int pageNum, Model model , String price, String searchOption, String search);
   public List<ProductDTO> searchSale(Principal seid, int pageNum, Model model , String searchOption, String search);
   public List<ProductDTO> searchListStar(Principal seid, ProductDTO dto, int pageNum, Model model , String searchOption, String search);
   public List<ProductDTO> searchListReview(Principal seid, ProductDTO dto, int pageNum, Model model , String searchOption, String search);
    
   public List<ProductDTO> meatBest();
   public List<ProductDTO> forkBest();
   public List<ProductDTO> newProductBest();
    
   //public void mainMeat(int pageNum, Model model, String price , int category, String sale, String reg);
   public void mainMeat(Principal seid, int pageNum, Model model, String price , int category, String sale, String reg, String news, String star);
   public void mainMeatSort(Principal seid, int pageNum, Model model, int category, String star);
   public void mainMeatReview(Principal seid, int pageNum, Model model, int category, String star);
   
   void poLinkList(Principal seid, int pageNum, Model model);
   
   public void newProduct(Principal seid, int pageNum, Model model);

   public ProductDetailDTO productDetail(ProductDetailDTO dto, Model model);
   
   public int otherProductCount(String p_m_id);
   public List<OtherProductDetailDTO> otherProductDetail(OtherProductDetailDTO odto, Model model);
   
   public List<ReviewDTO> reviewAll(int p_num);
   public double reviewStar(int r_p_num);
   
   public void reviewCNT(String mid, int p_num, Model model);
   public int reviewAllCNT(int r_p_num);
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
   
   public void reviewInsert(ReviewDTO dto);
   public void reviewAllPaging(Model model, int p_num, int pageNum);
   
   public int ShoppingCartCNT2(String shop_m_id);
   public int ShoppingCartInsert(Model model, ShoppingCartDTO dto);
   public int ShoppingCartInsert2(Model model, String m_id, int p_num, int shop_quantity);
   
   public int pickCNT(String ppic_m_id);
   public int pick_p_numCNT(String ppic_m_id, int ppic_p_num);
   public void pickInsert(Model model, PPicDTO dto, String ppic_m_id, int ppic_p_num);
   public void pickInsertMain(Model model, String ppic_m_id, int ppic_p_num);
   public int pCategory(int p_num);
}