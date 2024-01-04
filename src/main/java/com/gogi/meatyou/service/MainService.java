package com.gogi.meatyou.service;
 
import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.ProductDTO;

public interface MainService {

   public int cusCount();
   
   public List<ProductDTO> mainCUS();
   
   public void searchList(int pageNum, Model model , String desc, String searchOption, String search);
   
   public void searchPrice(int pageNum, Model model , String price, String searchOption, String search);
   
   public void searchSale(int pageNum, Model model , String searchOption, String search);
   
   public List<ProductDTO> meatBest();
   
   public List<ProductDTO> forkBest();
   
   public List<ProductDTO> newProductBest();
   
   //public void mainMeat(int pageNum, Model model, String price , int category, String sale, String reg);
   public void mainMeat(int pageNum, Model model, String price , int category, String sale, String reg);

   void poLinkList(int pageNum, Model model);
   
   public void newProduct(int pageNum, Model model);
   
   
   
   
   
   
   
   
   
   
}