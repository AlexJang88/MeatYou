
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


@Controller
@RequestMapping("/customers/*")
public class CustomersController {

	@Autowired
	private CustomersService service;

	@RequestMapping("customer") // 홈
	public String home(Principal pc) {
		String id = pc.getName();
		return "customer/customer";
	}

	@RequestMapping("itemUpdate") // 상품등록
	public String update(Principal pc, HttpSession session) {

		return "customer/itemUpdate";
	}

	@RequestMapping("itemUpdateDetail") // 상품 디테일 등록 // 객체로 못넘겨서 세션으로 넘김
	public String itemUpdateDetail(HttpSession session, ProductDTO productdto, MultipartFile thumbs) {
		System.out.println("=============controller");
		session.setAttribute("thumb", thumbs);
		session.setAttribute("productdto", productdto);

		return "customer/itemUpdateDetail";
	}

	/*
	 * 이거는 일단 대기
	 * 
	 * @RequestMapping(value = "/uploadProductImageFile",
	 * produces="application/json; charset=utf8")
	 * 
	 * @RequestBody public String uploadProductImageFile(@RequestParam("file")
	 * MultipartFile multipartFile, HttpServletRequest request) { return ""; }
	 */

	@RequestMapping("itemUpdatePro") // 상품등록확인
	public String itemUpdatePro(HttpServletRequest request, HttpSession session, Principal pc, PDetailDTO pdetaildto,
			MultipartFile files) {
		String id = pc.getName();
		ProductDTO productdto = (ProductDTO) session.getAttribute("productdto");
		MultipartFile thumb = (MultipartFile) session.getAttribute("thumb");

		
		 

		// service.itemUpdate(productdto,pdetaildto, id); //각각의 테이블에 값을 넣어줌
		// 사진 경로에 사진을 넣음

		// session.removeAttribute("productdto"); //세션끈김
		// session.removeAttribute("thumb"); // 세션끈김

		return "redirect:/customers/customer";
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

	@RequestMapping("content") // 상품 정보보기
	public String content(Model model, int p_num) {
		model.addAttribute("p_num", p_num);
		return "customer/content";
	}

	// 여기는 정보수정
	@RequestMapping("itemRevise") // 상품 정보수정 (값 확인하기
	public String itemRevise(Model model, int p_num) {
		model.addAttribute("p_num", p_num);
		service.lister(model, p_num);
		return "customer/itemRevise";
	}

	@RequestMapping("itemRevisePro") // 상품 정보수정 프로페이지
	public String itemRevisePro(ProductDTO productdto, PDetailDTO pdetaildto) {
		service.updateitemPro(productdto, pdetaildto);
		return "redirect:/customers/itemList";
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
		String id = pc.getName();
		model.addAttribute("id", id);
		return "customer/itemplus";
	}

	@RequestMapping("powerlink") // 파워링크 유료결제 페이지
	public String powerlink(Model model, Principal pc) {
		String id = pc.getName();
		service.powerlist(model, id); // 아이디값 넘기기
		return "customer/powerlink";
	}

	@RequestMapping("powerlinkpay") 
		public String powerlinkpay(Model model, int p_num, Principal pc, int co_num, int clickpay) { 
			service.powerlink(model, p_num, clickpay);
			return "customer/powerlinkpay";
		}

	
	
	
	
	
	
	@RequestMapping("itemplusPro") // 품목결재 완료
	public String itemplusPro(CusOrderDTO cusorderDTO, Principal pc) {
		cusorderDTO.setCo_m_id(pc.getName());

		service.itempayFinish(cusorderDTO);
		return "customer/itemplusPro";
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
	public String sajin(Model model, ProductDTO productdto, MultipartFile thumbs, HttpServletRequest request) {
		 String filePath = request.getServletContext().getRealPath("/resources/file/product/");
		 service.fileUpload(model, thumbs, filePath);
		return "customer/sajin";
	}
	
	@RequestMapping("cusQna") // 문의하기
	public String cusQna() {
		return "customer/cusQna";
	}

}