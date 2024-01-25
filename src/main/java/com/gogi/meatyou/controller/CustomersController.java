
package com.gogi.meatyou.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.security.Principal;
import java.security.Provider.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.CustomersService;
import com.gogi.meatyou.test.KakaoApproveResponse;


@Controller
@RequestMapping("/customers/*")
public class CustomersController {

	@Autowired
	private CustomersService service;

	@RequestMapping("customer") // 홈
	public String home(Principal pc) {
		
		return "customer/customer";
	}

	@RequestMapping("itemUpdate") // 상품등록
	public String update(Principal pc) {

		return "customer/itemUpdate";
	}

	

	@RequestMapping("itemList") // 등록한 상품목록
	public String itemList(Model model, Principal pc,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();
		service.list(model, id, pageNum); // 아이디값 넘기기
		return "customer/itemList";
	}

	@RequestMapping("/statusChange") // 상품목록페이지에서 변경하면 돌아오는곳설정
	public String statusChange(Model model, Principal pc, int p_status, int p_num, int pageNum, ProductDTO productdto) {
		model.addAttribute("pageNum", pageNum);
		String id = pc.getName();

		int co_num = p_status;
		if (p_status != 0 && p_status != 2 && p_status != 3) {
			co_num = p_status;
			p_status = 1;
		}
		productdto.setP_m_id(id); // 아이디값
		productdto.setP_status(p_status); // 변경된 상품 상태값
		productdto.setP_num(p_num); // 상품번호값
		productdto.setCo_num(co_num); // 상품번호값 1번일떄만씀

		service.statusChange(model, productdto); // 회원의 판매상태를 변경
		return "redirect:/customers/itemList?pageNum=" + pageNum;
	}

	@RequestMapping("itemListOut") // 판매종료된 상품목록
	public String itemListOut(Model model, Principal pc,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();
		service.listout(model, id, pageNum); // 아이디값 넘기기
		return "customer/itemListOut";
	}

	@RequestMapping("/statusChangeout") // 판매종료된 상품목록 페이지에서 변경하면 돌아오는곳설정
	public String statusChangeout(Principal pc, int p_status, int p_num, ProductDTO productdto, int pageNum) {
		String id = pc.getName();
		productdto.setP_m_id(id); // 변경된 상품 상태값
		productdto.setP_num(p_num); // 변경된 상품 상태값
		productdto.setP_status(p_status); // 변경된 상품 상태값

		service.statusChangeouut(productdto); // 회원의 판매상태를 변경
		return "redirect:/customers/itemListOut?pageNum=" + pageNum;
	}






	// 여기는 재고현황파악

	@RequestMapping("stock") // 전체 상품 재고현황
	public String stock(Model model, Principal pc, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();
		service.stocklist(model, id, pageNum); // 아이디값 넘기기
		return "customer/stock";
	}

	@RequestMapping("onStock") // 판매중인 상품 재고현황
	public String onStock(Model model, Principal pc, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();
		service.onStock(model, id, pageNum);
		return "customer/onStock";
	}

	@RequestMapping("stockPro") // 상품 전체목록 중 재고 변경
	public String stockPro(PDetailDTO pdetaildto) {
		service.stockPro(pdetaildto);
		return "redirect:/customers/stock";
	}

	@RequestMapping("stockOnPro") // 판매중인 상품중 중 재고 변경
	public String stockOnPro(PDetailDTO pdetaildto) {
		service.stockOnPro(pdetaildto);
		return "redirect:/customers/onStock";
	}

	// 유료결제란
	@RequestMapping("pay") // 유료결제
	public String pay(Model model, Principal pc, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();
		model.addAttribute("pageNum", pageNum);

		service.pay(model, id);
		return "customer/pay";
	}

	@RequestMapping("payOne") // 유료결제
	public String payOne(Model model, Principal pc, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();
		model.addAttribute("pageNum", pageNum);

		service.payOne(model, id, pageNum);
		return "customer/payOne";
	}

	@RequestMapping("payTwo") // 유료결제
	public String payTwo(Model model, Principal pc, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();
		model.addAttribute("pageNum", pageNum);

		service.payTwo(model, id, pageNum);
		return "customer/payTwo";
	}

	@RequestMapping("itemplus") // 품목확장 유료결제 페이지
	public String itemplus(Model model, Principal pc) {
		service.geterco_num(model);
		
		String quantity = "1";
		String id = pc.getName();
		model.addAttribute("id", id);		
		model.addAttribute("quantity", quantity);
		return "customer/itemplus";
	}

	@RequestMapping("powerlink") // 파워링크 유료결제 페이지
	public String powerlink(Model model, Principal pc) {
		String id = pc.getName();
		service.powerlist(model, id); // 아이디값 넘기기
		return "customer/powerlink";
	}

	@RequestMapping("powerlinkpay") 
		public String powerlinkpay(Model model, int p_num, Principal pc, int co_num, int clickpay, String p_name) { 
			service.powerlink(model, p_num, clickpay);
			
			model.addAttribute("p_name", p_name);
			return "customer/powerlinkpay";
		}

	
	
	
	
	
	
	@RequestMapping("profit") // 매출현황
	public String profit(Model model, @RequestParam(value = "check", defaultValue = "0") int check, String daterange,
			Principal pc) {
		String id = pc.getName();

		// 현재 날짜
		Date currentDate = new Date();
		// check에 따라서 날짜를 계산
		Date targetDate = service.calculateTargetDate(currentDate, check);
		// SimpleDateFormat을 사용하여 원하는 형식으로 날짜를 문자열로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
		String formattedDate = sdf.format(targetDate);
		model.addAttribute("currentMonth", targetDate.getMonth() + 1);
		model.addAttribute("currentYear", targetDate.getYear() + 1900);

		if (check <= 0) {
			service.getprofit(model, check, id);
		} else {
			String start = daterange.substring(0, 10);
			String end = daterange.substring(13, 23);
			// service.getCheckprofit(model,check,start,end,id); 아직 안함
		}
		return "customer/profit";
	}

	@RequestMapping("profitItem") // 판매상품현황
	public String profitItem(Model model, @RequestParam(value = "check", defaultValue = "0") int check,
			String daterange, Principal pc, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();

		// 현재 날짜
		Date currentDate = new Date();
		// check에 따라서 날짜를 계산
		Date targetDate = service.calculateTargetDate(currentDate, check);
		// SimpleDateFormat을 사용하여 원하는 형식으로 날짜를 문자열로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
		String formattedDate = sdf.format(targetDate);
		model.addAttribute("currentMonth", targetDate.getMonth() + 1);
		model.addAttribute("currentYear", targetDate.getYear() + 1900);

		if (check <= 0) {
			service.getProfitItem(model, check, id, pageNum);
		} else {
			String start = daterange.substring(0, 10);
			String end = daterange.substring(13, 23);
			// service.getCheckProfitItem(model,check,start,end,id); 아직안함
		}
		return "customer/profitItem";
	}

	@RequestMapping("consumerList") // 구매회원
	public String consumerList(Model model, @RequestParam(value = "check", defaultValue = "0") int check,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Principal pc) {
		String id = pc.getName();

		// 현재 날짜
		Date currentDate = new Date();
		// check에 따라서 날짜를 계산
		Date targetDate = service.calculateTargetDate(currentDate, check);
		// SimpleDateFormat을 사용하여 원하는 형식으로 날짜를 문자열로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
		String formattedDate = sdf.format(targetDate);
		model.addAttribute("currentMonth", targetDate.getMonth() + 1);
		model.addAttribute("currentYear", targetDate.getYear() + 1900);

		service.consumerList(model, check, pageNum, id);
		return "customer/consumerList";
	}

	@RequestMapping("cusCoupon") // 쿠폰제공하는 페이지
	public String cusCoupon(Model model, String p_m_id, Principal pc) {
		String id = pc.getName();

		service.companynum(model, id);
		service.itemList(model, id);

		model.addAttribute("p_m_id", p_m_id);
		model.addAttribute("id", id);
		return "customer/cusCoupon";
	}

	@RequestMapping("cusCouponPro") // 쿠폰제공하는 페이지
	public String cusCouponPro(Model model, String p_m_id, Principal pc, int point, CouponDTO coupondto, int companynum,
			int p_status, int couponUse) {
		String id = pc.getName();

		service.cusCouponPro(model, p_m_id, id, point, companynum, coupondto, p_status, couponUse);
		return "redirect:/customers/CouponList";
	}

	@RequestMapping("CouponList") // 쿠폰제공한 페이지
	public String CouponList(Model model, Principal pc,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		String id = pc.getName();

		service.couponList(model, id, pageNum); // 쿠폰 제공한 리스트
		return "customer/CouponList";
	}

	@RequestMapping("deliverout") // 구매취소
	public String deliver(Model model, @RequestParam(value = "check", defaultValue = "0") int check,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Principal pc) {
		String id = pc.getName();

		// 현재 날짜
		Date currentDate = new Date();
		// check에 따라서 날짜를 계산
		Date targetDate = service.calculateTargetDate(currentDate, check);
		// SimpleDateFormat을 사용하여 원하는 형식으로 날짜를 문자열로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
		String formattedDate = sdf.format(targetDate);
		model.addAttribute("currentMonth", targetDate.getMonth() + 1);
		model.addAttribute("currentYear", targetDate.getYear() + 1900);

		service.deliverout(model, check, pageNum, id);

		return "customer/deliverout";
	}

	@RequestMapping("delivering") // 결제완료, 배송중, 배송완료 ,
	public String delivering(Model model, @RequestParam(value = "check", defaultValue = "0") int check,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Principal pc) {
		String id = pc.getName();

		// 현재 날짜
		Date currentDate = new Date();
		// check에 따라서 날짜를 계산
		Date targetDate = service.calculateTargetDate(currentDate, check);
		// SimpleDateFormat을 사용하여 원하는 형식으로 날짜를 문자열로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
		String formattedDate = sdf.format(targetDate);
		model.addAttribute("currentMonth", targetDate.getMonth() + 1);
		model.addAttribute("currentYear", targetDate.getYear() + 1900);

		service.delivering(model, check, pageNum, id);

		return "customer/delivering";
	}

	@RequestMapping("deliverStatus") // 결제완료, 배송중, 배송완료 ,
	public String deliverStatus(Model model, int order_num, int order_status) { // 상품번호, 배송현황

		service.deliverStatus(order_num, order_status);
		return "redirect:/customers/delivering";
	}

	
	@RequestMapping("sajin") // 사진
	public String sajin(Model model, ProductDTO productdto, MultipartFile thumbs,  HttpServletRequest request, Principal pc) {
		String id = pc.getName();
		productdto.setP_m_id(id);
		 String filePath = request.getServletContext().getRealPath("/resources/file/product/");
		 service.fileUpload(model, thumbs, filePath, productdto);

		 String s = "cuser24.png";
		 model.addAttribute("s",s);
		return "customer/sajin";
	}
	
	@RequestMapping("cusQna") // 문의하기
	public String cusQna() {
		return "customer/cusQna";
	}
	
	
	//아래 에디터
	
	
	
	@RequestMapping("/productReg")
	public String productReg(HttpServletRequest req,  Principal pc, ProductDTO productdto,MultipartFile thumbs) {
		String realPath = req.getServletContext().getRealPath("/resources/file/product/");
		String id = pc.getName();
		productdto.setP_m_id(id);
		System.out.println("====++"+productdto.getP_num());
		System.out.println(productdto.getP_m_id());
		System.out.println(productdto.getP_name());
		System.out.println(productdto.getP_category());
		System.out.println(productdto.getP_s_category());
		System.out.println(thumbs.getOriginalFilename());
		System.out.println(productdto.getP_price());
		System.out.println(productdto.getStartdate());
		System.out.println(productdto.getEnddate());
		service.productReg(productdto,realPath,thumbs);
		
		return "redirect:/customers/itemList";
	}
	@RequestMapping("/productUpdate")
	public String productUpdate(HttpServletRequest req,Model model, int num) {
		String realPath = req.getServletContext().getRealPath("/resources/file/product/");
		service.productUpdate(realPath, num, model);
		return "customer/itemRevise";
	}
	@RequestMapping("/productUpdateReg")
	public String productUpdateReg(HttpServletRequest req, ProductDTO productdto) {
		String realPath=req.getServletContext().getRealPath("/resources/file/product/");
		service.productUpdateReg(realPath,productdto);
		return "redirect:/customers/itemList";
	}
	

	
	@RequestMapping(value="/uploadImageFile", produces = "application/json; charset=utf8")
    @ResponseBody
    public String uploadImageFile(@RequestParam("file") MultipartFile multipartFile,
            HttpServletRequest req) {
		String realPath=req.getServletContext().getRealPath("/resources/file/product/");
		return service.productImgUpload(multipartFile, realPath);
	}
	@RequestMapping(value = "/deleteImageFile", produces = "application/json; charset=utf8")
    public String deleteImageFile(@RequestParam("file") String fileName,HttpServletRequest req) {
		String realPath=req.getServletContext().getRealPath("/resources/file/product/");
		service.productImgDel(fileName, realPath);
		return "redirect:/customers/itemList";
	}
	@RequestMapping("/productContent")
	public String productContent(int num,Model model) {
		service.productContent(model, num);
		return "customer/content";
	}
	@RequestMapping("/productDelete")
	public String productDelete(int num) {
		service.productDelete(num);
		return "redirect:/customers/itemList";
	}
	
	
	
	

}