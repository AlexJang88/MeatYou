package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.ProductDTO;

public interface MainMapper {

	public int cusCount();
	
	public List<ProductDTO> mainCUS();
	
	public List<ProductDTO> meatBest();
	
	public List<ProductDTO> forkBest();
	
	public List<ProductDTO> newProduct();

	public List<ProductDTO> searchList(HashMap map);
	
	public List<ProductDTO> searchPrice(HashMap map);
	
	public List<ProductDTO> searchSale(HashMap map);
	
	public int searchCount(@Param("searchOption") String searchOption,
			   @Param("search") String search);

	public List<ProductDTO> mainMeat();
	
	public List<ProductDTO> mainFork();

}
