package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	   

	   public List<ProductDTO> searchPrice(HashMap map);
	   

	   public List<ProductDTO> searchSale(HashMap map);
	   

	   public int mainMeatCount(@Param("category") int category);
	   
	   public List<ProductDTO> mainMeat(HashMap map);
	   
	   
	//   public List<ProductDTO> mainMeat(@Param("start")int startRow, @Param("end")int endRow, @Param("category")int category, 
//	         @Param("price")String price, @Param("sale")String sale, @Param("reg")String reg);

	   public int powCount();
	   
	   public List<ProductDTO> poLinkList(HashMap map);


	   public List<ProductDTO> newProduct(HashMap map);

	   public ProductDetailDTO productDetail(ProductDetailDTO dto);
	   public List<ProductDetailDTO> otherProductDetail(ProductDetailDTO dto);

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
	   
	   public int pickCNT(String ppic_m_id);
	   public int pick_p_numCNT(@Param("ppic_m_id")String ppic_m_id, @Param("ppic_p_num")int ppic_p_num);
	   
	   public void pickInsert(PPicDTO dto);
	   public void pickDelete(PPicDTO dto);
	   
	   
	}