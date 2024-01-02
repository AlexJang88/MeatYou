package com.gogi.meatyou.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.ProductDTO;
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
	public List<ProductDTO> newProduct() {
		return mapper.newProduct();
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
	public List<ProductDTO> mainMeat() {
		return null;
	}

	@Override
	public List<ProductDTO> mainFork() {
		return null;
	}






}
