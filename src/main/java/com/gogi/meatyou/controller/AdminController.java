package com.gogi.meatyou.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.meatyou.bean.DiseaseDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.bean.QnADTO;
import com.gogi.meatyou.repository.AdminMapper;
import com.gogi.meatyou.service.AdminService;
import com.gogi.meatyou.service.AdminServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
	private AdminService adminServicImpl;

	@Autowired
	private HashMap adminMap;

	@RequestMapping("/chart")
	public String chart(Principal pc, Model model) {
		String sid = pc.getName();
		model.addAttribute("sid", sid);
		return "admin/chart";
	}

	@RequestMapping("/main")
	public String main(Model model) {
		adminServicImpl.getSales(model, 0);
		return "admin/main";
	}

	@RequestMapping("/customLogin")
	public String login() {
		return "main/login";
	}

	@RequestMapping("/accessError")
	public String accessError(Authentication auth) {
		return "Error";
	}

	@RequestMapping("/memberlist")
	public String memberlist(int check, Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		adminServicImpl.memberList(check, model, pageNum);
		return "admin/memberList";
	}

	@RequestMapping("/statChange")
	public String statChange(int check, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model,
			MemberDTO dto) {
		adminServicImpl.statChange(dto);

		return "redirect:/admin/memberlist?check=" + check + "&pageNum=" + pageNum;
	}

 

	@RequestMapping("/sales")
	public String sales(Model model, @RequestParam(value = "check", defaultValue = "0") int check, String daterange) {
//���� ��¥
		Date currentDate = new Date();
		// check�� ���� ��¥�� ���
		Date targetDate = adminServicImpl.calculateTargetDate(currentDate, check);
		// SimpleDateFormat�� ����Ͽ� ���ϴ� �������� ��¥�� ���ڿ��� ��ȯ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
		String formattedDate = sdf.format(targetDate);
		model.addAttribute("currentMonth", targetDate.getMonth() + 1);
		model.addAttribute("currentYear", targetDate.getYear() + 1900);
		if (check <= 0) {
			adminServicImpl.getSales(model, check);
		} else {
			String start = daterange.substring(0, 10);
			String end = daterange.substring(13, 23);
			adminServicImpl.getCheckSalse(model, check, start, end);
		}
		return "admin/sales";
	}

	@RequestMapping("/reckon")
	public String reckon(Model model, @RequestParam(value = "start", defaultValue = "2023") String start,
			@RequestParam(value = "end", defaultValue = "12") String end,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

		adminServicImpl.getReckon(model, pageNum, start, end);

		return "admin/reckon";
	}

	@RequestMapping(value = "/productList", produces = "application/json; charset=utf8")
	public String productList() {
		return "admin/productList";
	}

	@RequestMapping(value = "/serchProductList", produces = "application/json; charset=utf8")
	@ResponseBody
	public String serchProductList(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			String keyword, @RequestParam(value = "searchOpt", defaultValue = "1") String searchOpt,
			@RequestParam(value = "cate1", defaultValue = "1") int cate1,
			@RequestParam(value = "cate2", defaultValue = "1") int cate2,
			@RequestParam(value = "cate3", defaultValue = "1") int cate3,
			@RequestParam(value = "pstatus", defaultValue = "1") int pstatus) {
		return adminServicImpl.getSearchProductList(pageNum, keyword, searchOpt, cate1, cate2, cate3, pstatus, model);
	}

	@RequestMapping("/memo")
	public String memo(Model model, int p_num, @RequestParam(value = "check", defaultValue = "1") int check) {
		model.addAttribute("p_num", p_num);
		model.addAttribute("check", check);
		return "admin/memo";
	}

	@RequestMapping("/pschange")
	   public String pschange(int p_num, String memo, @RequestParam(value = "check", defaultValue = "2")int check) {
	      if (check == 1) {
	         adminServicImpl.StopProduct(p_num, memo);
	      } else if (check != 1) {
	         adminServicImpl.ReleaseIssue(p_num);
	      }
	      return "admin/productList";
	   }
	@RequestMapping("/reportForm")
	public String reportForm(Model model,int p_num) {
		model.addAttribute("p_num",p_num);
		return "admin/reportForm";
	}
	
	@RequestMapping("/reportReg")
	public String reportReg(Principal pc,HttpServletRequest req, HttpServletResponse resp, Model model, QnADTO dto,int category) {
		String realPath = req.getServletContext().getRealPath("/resources/file/report/");
		dto.setMa_m_id(pc.getName());
		adminServicImpl.reportReg(realPath, model, dto);
		return "redirect:/main/main";
	}
	
	@RequestMapping("/rePortForm")
	public String rePortForm(Model model
			 ) {
		
		return "admin/reportForm";
	}
	@RequestMapping("/reportList")
	public String reportList(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,@RequestParam(value = "check", defaultValue = "1")int check) {
		adminServicImpl.reportList(pageNum, model, check);
		
		return "admin/reportList";
	}
	@RequestMapping("/reportContent")
	public String reportContent(Model model,int ma_num) {
			adminServicImpl.reportContent(model, ma_num);
		return "admin/reportContent";
	}
	@RequestMapping("/reportReply")
	public String reportReply(QnADTO dto) {
			adminServicImpl.reportReply(dto);
		return "redirect:/admin/reprotContent?ma_num"+dto.getMa_num();
	}

	@RequestMapping(value = "/uploadReportImageFile", produces = "application/json; charset=utf8")
	@PreAuthorize("permitAll()")
	@ResponseBody
	public String uploadReportImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
		String realPath = request.getServletContext().getRealPath("/resources/file/report/");

		return adminServicImpl.uploadReportImageFile(multipartFile, realPath);
	}

	@RequestMapping("/noticeForm")
	public String boardform() {
		return "admin/noticeForm";
	}

	@RequestMapping("/noticeList")
	public String boardList(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		adminServicImpl.noticeList(model, pageNum);
		return "admin/noticeList";
	}

	@RequestMapping("/noticeReg")
	public String boardReg(HttpServletRequest req, NoticeDTO dto, Model model) {
		String realPath = req.getServletContext().getRealPath("/resources/file/notice/");
		adminServicImpl.noticeReg(realPath, dto);
		return "redirect:/admin/noticeList";
	}

	@RequestMapping("/noticeUpdate")
	public String boardUpdate(HttpServletRequest req, Model model, int num) {
		String realPath = req.getServletContext().getRealPath("/resources/file/notice/");
		adminServicImpl.noticeUpdate(realPath, num, model);
		return "admin/noticeupdateForm";
	}

	@RequestMapping("/noticeUpdateReg")
	public String boardDelUpdate(HttpServletRequest req, NoticeDTO dto, MultipartFile thumbnail) {
		String realPath = req.getServletContext().getRealPath("/resources/file/notice/");
		adminServicImpl.noticeUpdateReg(realPath, dto);
		return "redirect:/admin/noticeContent?num=" + dto.getN_num();
	}

	@RequestMapping(value = "/uploadImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest req) {
		String realPath = req.getServletContext().getRealPath("/resources/file/notice/");
		return adminServicImpl.noticeImgUpload(multipartFile, realPath);
	}

	@RequestMapping(value = "/deleteImageFile", produces = "application/json; charset=utf8")
	public String deleteSummernoteImageFile(@RequestParam("file") String fileName, HttpServletRequest req) {
		String realPath = req.getServletContext().getRealPath("/resources/file/notice/");
		adminServicImpl.noticeImgDel(fileName, realPath);
		return "redirect:/admin/noticeList";
	}

	@RequestMapping("/noticeContent")
	public String aprvContent(int num, Model model) {
		adminServicImpl.noticeContent(model, num);
		return "admin/noticeContent";
	}

	@RequestMapping("/noticeDelete")
	public String boardDelete(int num, HttpServletRequest req) {
		String realPath = req.getServletContext().getRealPath("/resourecs/file/notice/" + num + "/");
		adminServicImpl.noticeDelete(num, realPath);
		return "redirect:/admin/noticeList";
	}
	@RequestMapping("/getChartData")
	@ResponseBody
	public String getChartData(String period) {
		
		return adminServicImpl.getChartData(period);
	}
	
	@RequestMapping("/check")
	public String check(Model model) {
		model.addAttribute("apiKey","wBStzrx7b1p8B9XqfLWLBMa0q7HCWqRMC7%2F2o%2BG1CWfp2gW%2FffWQ8H81TDthbbN%2FU%2FqtGmiOtMUvFtzKeHPiuQ%3D%3D");
		
		return "admin/businessNum";
	}
	
	@RequestMapping(value="/api",produces = "application/text; charset=UTF-8")
	@CrossOrigin(origins = "*", methods = RequestMethod.GET)
	public @ResponseBody String itemCategoryAPI(Model model,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		return adminServicImpl.getPriceinfo();
	}
	@RequestMapping("item")
	public String item() {
		return "admin/itemCategoryAPI";
	}
	
	@RequestMapping("/diapi")
	public String diapi(Model model) {
		model.addAttribute("key", "1c9a14382163bb7dc822492a3dca9b9a8841b3782755afedd33d3b5879c98e94");
		return "admin/disease";
	}
	
	@RequestMapping(value="/dapi", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> dapi(@RequestBody Map<String, Object> requestData) {
	    System.out.println("data====" + requestData); // 데이터가 제대로 수신되는지 확인
	    Map<String, Object> result = new HashMap<>();
	    List<DiseaseDTO> list = new ArrayList<DiseaseDTO>();;
	    try {
	    	 Map<String, Object> data = (Map<String, Object>) requestData.get("data");
	         Map<String, Object> gridData = (Map<String, Object>) data.get("Grid_20151204000000000316_1");
	         List<Map<String, Object>> rows = (List<Map<String, Object>>) gridData.get("row");
	         // 각 행의 정보 출력
	         if(rows.isEmpty()) {
	        	 System.out.println("없음");
	         }else {
	         for (Map<String, Object> row : rows) {
	        	 String cessation =(String)row.get("CESSATION_DE");
	        	 String check = (String)row.get("LVSTCKSPC_NM"); 
	             DiseaseDTO dto = new DiseaseDTO();
	             if(check.contains("소")||check.contains("돼지")) {
	            	 if(cessation.length()<0 || cessation.equals("") || cessation.isEmpty()) {
	            		 dto.setCESSATION_DE("0");
		        	 }else {
	            	 dto.setCESSATION_DE((String)row.get("CESSATION_DE"));}
		             dto.setDGNSS_ENGN_CODE((String)row.get("DGNSS_ENGN_CODE"));
		             dto.setDGNSS_ENGN_NM((String)row.get("DGNSS_ENGN_NM"));
		             dto.setFARM_LOCPLC((String)row.get("FARM_LOCPLC"));
		             dto.setFARM_LOCPLC_LEGALDONG_CODE((String)row.get("FARM_LOCPLC_LEGALDONG_CODE"));
		             dto.setFARM_NM((String)row.get("FARM_NM"));
		             dto.setICTSD_OCCRRNC_NO((String)row.get("ICTSD_OCCRRNC_NO"));
		             dto.setLKNTS_NM((String)row.get("LKNTS_NM"));
		             dto.setLVSTCKSPC_CODE((String)row.get("LVSTCKSPC_CODE"));
		             dto.setLVSTCKSPC_NM((String)row.get("LVSTCKSPC_NM"));
		             dto.setOCCRRNC_DE((String)row.get("OCCRRNC_DE"));
		             dto.setOCCRRNC_LVSTCKCNT((int)row.get("OCCRRNC_LVSTCKCNT"));
		             list.add(dto);
	         	}
	         }
		        if(list!=null || list.size()>0 || !list.isEmpty()) {
		        	adminServicImpl.updatedi(list);
		       	}
	         }
	         result.put("result", true);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        result.put("result", false);
	    }
	    return result;
	}

	
	
	@RequestMapping("/apiTest")
	 public String apiTest(Model model) {
	 adminServicImpl.apiTest(model);
	 return "admin/apiTest";
	 }
}