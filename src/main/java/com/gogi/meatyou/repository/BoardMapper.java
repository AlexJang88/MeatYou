package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.bean.PQuestionDTO;

public interface BoardMapper {
	public int userQnacount();//소비자 판매자 질문 갯수
	public List<PQuestionDTO> userQnalist(HashMap boardListMap); // 문의한 글 제목, 등급 반복문
	public PQuestionDTO getuserQnacotent(PQuestionDTO pquestiondto); //소비자문의내용 가져오기
	public PQuestionDTO getCuserContent(PQuestionDTO pquestiondto); //판매자 답글 가져오기
	public String getPidPnum( @Param ("pq_p_num") int pq_p_num, @Param ("pq_ref") int pq_ref); // 상품 번호와 판매자가 일치하는지 가져오기 위해
	public int getAnswerCount(PQuestionDTO pquestiondto);// 답변한 갯수 1이상이면 안나오게 하려고
	
	public void wirteAnswerd(PQuestionDTO pquestiondto); // 답변 등록
	public int getBoardNEXTNum(); //문의 등록시 다음번호
	public void userQnaUp(PQuestionDTO pquestiondto); //문의내용 디비 등록
	public void boardFileReg(NoticeFileDTO dto);//파일업로드
}
