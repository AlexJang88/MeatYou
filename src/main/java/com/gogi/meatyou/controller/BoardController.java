package com.gogi.meatyou.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.PQuestionDTO;
import com.gogi.meatyou.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	 @RequestMapping("userQna") // 소비자 - 판매자
	   public String userQna(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, int p_num, Principal pc) {
		 	String id = pc.getName();
		 	model.addAttribute("id", id);
		 	model.addAttribute("pq_p_num", p_num);
		 	service.userQnaList(model, pageNum, p_num);
	      return "board/userQna/userQna";
	   }
	 
	 @RequestMapping("userQnaContent") // 소비자 - 판매자
	   public String userQnaContent(Model model, int pq_p_num, int pq_num, int pq_ref, PQuestionDTO pquestiondto, Principal pc) {
		 String id= pc.getName();
		 model.addAttribute("id", id);
		 String p_id = service.getPidPnum(pq_p_num, pq_ref); // 상품 번호와 판매자가 일치하는지 가져오기 위해
		 model.addAttribute("p_id", p_id);
		 
		 
		 
		 pquestiondto.setPq_num(pq_num);
		 pquestiondto.setPq_p_num(pq_p_num);
		 pquestiondto.setPq_ref(pq_ref);
		 
		 service.getuserQnacotent(model, pquestiondto);	//질문 내용 가져오기	
		 service.getcuserQnacotent(model, pquestiondto); // 판매자 답변 가져오기
	      return "board/userQna/userQnaContent";
	   }
	 
	 
	 @RequestMapping("userQnaContentPro") // 문의글에 대한 답글다는곳
	   public String userQnaContentPro(Model model, int pq_p_num, int pq_num, int pq_ref, PQuestionDTO pquestiondto, int pq_status, String pq_content, Principal pc) {		 
		 pquestiondto.setPq_m_id(pc.getName());
		 pquestiondto.setPq_p_num(pq_p_num);
		 pquestiondto.setPq_ref(pq_ref);
		 pquestiondto.setPq_content(pq_content);
		 
		 if(pq_status == 0 ) {
			 pquestiondto.setPq_status(1);
		 } else{
			 pquestiondto.setPq_status(4);
		 }
		service.wirteAnswerd(pquestiondto); // 답변등록
	      return "redirect:/board/userQnaContent?pq_p_num="+pq_p_num+"&pq_num="+pq_num+"&pq_ref="+pq_ref;
	   }
	 
	 
	 @RequestMapping("userquestion") // 문의글 쓰는곳
	   public String userquestion(Model model, Principal pc, int pq_p_num ) {
		 	String pq_m_id = pc.getName();
		 	model.addAttribute("pq_m_id", pq_m_id);
		 	model.addAttribute("pq_p_num", pq_p_num);
		 	
	      return "board/userQna/userquestion";
	   }
	 
	 
	 @RequestMapping("userquestionPro") // 문의글 쓰는곳
	   public String userquestionPro(Model model, HttpServletRequest req, Principal pc, int pq_p_num, int pq_status, PQuestionDTO pquestiondto ) {
		 	String realPath = req.getServletContext().getRealPath("/resources/file/board/");
		 	String pq_m_id = pc.getName();
		 	
		 	pquestiondto.setPq_m_id(pq_m_id); //아이디 지정
		 	pquestiondto.setPq_p_num(pq_p_num); //상품번호지정
		 	pquestiondto.setPq_status(pq_status); //상품 공개여부 지정
		 	
		 service.boardReg(pquestiondto,realPath); // 내용 디비 넣기
		 	
	      return "redirect:/board/userQna?p_num="+pq_p_num;
	   }
	 
	 
	 @RequestMapping(value="/uploadImageFile", produces = "application/json; charset=utf8")
	 @ResponseBody
	 public String uploadImageFile(@RequestParam("file") MultipartFile multipartFile,
	            HttpServletRequest req) {
	      String realPath=req.getServletContext().getRealPath("/resources/file/board/");
	      return service.productImgUpload(multipartFile, realPath);
	   }
	 
	 @RequestMapping(value = "/deleteImageFile", produces = "application/json; charset=utf8")
	    public String deleteImageFile(@RequestParam("file") String fileName,HttpServletRequest req, int pq_q_num) {
	      String realPath=req.getServletContext().getRealPath("/resources/file/board/");
	      service.productImgDel(fileName, realPath);
	      return "redirect:/board/userQna?p_num="+pq_q_num;
	   }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @RequestMapping("consumerQna") // 소비자 - 관리자
	   public String consumerQna(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		 	service.consumerQnaList(model, pageNum);
	      return "board/consumerQna/consumerQna";
	   }
	
	 @RequestMapping("sellerQna") // 판매자 - 관리자
	   public String home(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		 	service.sellerQnaList(model, pageNum);
	      return "board/sellerQna/sellerQna";
	   }
	 
}
