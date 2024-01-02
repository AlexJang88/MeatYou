package com.gogi.meatyou.service;

import java.util.List;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.ProductDTO;

public interface MainService {

	public int cusCount();
	
	public List<ProductDTO> mainCUS();
	
	public List<ProductDTO> meatBest();
	
	public List<ProductDTO> forkBest();
	
	public List<ProductDTO> newProduct();
	
	public void searchList(int pageNum, Model model , String desc, String searchOption, String search);
	
	public void searchPrice(int pageNum, Model model , String price, String searchOption, String search);
	
	public void searchSale(int pageNum, Model model , String searchOption, String search);
	
	public List<ProductDTO> mainMeat();
	
	public List<ProductDTO> mainFork();
}
