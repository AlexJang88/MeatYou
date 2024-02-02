package com.gogi.meatyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gogi.meatyou.bean.BusinessNumDTO;

@RequestMapping("/business/*")
@Controller
public class BusinessNumController {
	
	
	@RequestMapping("/check")
	public String check(Model model) {
		model.addAttribute("apiKey","wBStzrx7b1p8B9XqfLWLBMa0q7HCWqRMC7%2F2o%2BG1CWfp2gW%2FffWQ8H81TDthbbN%2FU%2FqtGmiOtMUvFtzKeHPiuQ%3D%3D");
		return "admin/businessNum";
	}
	@RequestMapping("/itemCategoryAPI")
	public String itemCategoryAPI(Model model) {
		model.addAttribute("apiKey","262ecfe0-6ae1-46c1-b97c-6988b68b30f2");
		return "admin/itemCategoryAPI";
	}
	
	
}
