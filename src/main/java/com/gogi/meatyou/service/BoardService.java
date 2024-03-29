package com.gogi.meatyou.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.PQuestionDTO;
import com.gogi.meatyou.bean.QnADTO;

public interface BoardService {
	
	public void userQnaList(Model model, int pageNum, int p_num); //소비자-판매자 문의리스트
	public void consumerQnaList(Model model, int pageNum); //소비자-관리자 문의리스트
	public void sellerQnaList(Model model, int pageNum); //판매자-관리자 문의리스트
	
	public void getuserQnacotent(Model model, PQuestionDTO pquestiondto);//소비자 글내용 가져오기
	public void getcuserQnacotent(Model model, PQuestionDTO pquestiondto); // 판매자 답글 가져오기
	public String getPidPnum(int pq_p_num, int pq_ref);// 상품 번호와 판매자가 일치하는지 가져오기 위해
	
	public void wirteAnswerd(PQuestionDTO pquestiondto); //답변등록	
	
	public void boardReg(PQuestionDTO pquestiondto, String realPath); // 내용등록	
	public String productImgUpload(MultipartFile multipartFile, String realPath);	
	public void productImgDel(String fileName, String realPath);
	
	public void getIdStatus(Model model, String id);// 회원등급
	public void contentView(Model model, QnADTO qnadto, String id);// 내용 및 댓글보기
	public void insertanswer(QnADTO qnadto); // 답변등록
	public void questionReg(QnADTO qnadto, String realPath); //문의사항 등록
	public String productImgCAUpload(MultipartFile multipartFile, String realPath);	
	public void productImgCADel(String fileName, String realPath);
	
	public void sllerContentView(Model model, QnADTO qnadto, String id);
	public void insertAdminanswer(QnADTO qnadto); //답변등록
	public void questionSAReg(QnADTO qnadto, String realPath); //문의사항 등록
	public String productImgSAUpload(MultipartFile multipartFile, String realPath);
	public void productImgSADel(String fileName, String realPath);
}
