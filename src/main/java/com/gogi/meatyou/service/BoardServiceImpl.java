package com.gogi.meatyou.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.CusFileDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.bean.PQuestionDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.QnADTO;
import com.gogi.meatyou.repository.BoardMapper;
import com.google.gson.JsonObject;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	   private HashMap boardListMap;

	@Override
	public void userQnaList(Model model, int pageNum, int p_num) {
		  int pageSize = 10;
	      int startRow = (pageNum - 1) * pageSize + 1;
	      int endRow = pageNum * pageSize;
	      int count = mapper.userQnacount(); //질문갯수  
	      List<PQuestionDTO> list = Collections.EMPTY_LIST;
	      
	      if (count > 0) {
	    	  boardListMap.put("start", startRow);
	    	  boardListMap.put("end", endRow);	        
	    	  boardListMap.put("pq_p_num", p_num);	        
	         list = mapper.userQnalist(boardListMap); 
	      }
	      
	      int answerCheck = 0;
	      
	      
	      model.addAttribute("list", list);
	      model.addAttribute("count", count);
	      model.addAttribute("pageNum", pageNum);
	      model.addAttribute("pageSize", pageSize);

	      // page
	      int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
	      int startPage = (int) (pageNum / 10) * 10 + 1;
	      int pageBlock = 10;
	      int endPage = startPage + pageBlock - 1;
	      if (endPage > pageCount) {
	         endPage = pageCount;
	      }
	      model.addAttribute("pageCount", pageCount);
	      model.addAttribute("startPage", startPage);
	      model.addAttribute("pageBlock", pageBlock);
	      model.addAttribute("endPage", endPage);
	      
	      
	      
	   }
	
	@Override
	public void getuserQnacotent(Model model, PQuestionDTO pquestiondto) {  // 소비자 글내용 가져오기		
		PQuestionDTO content = mapper.getuserQnacotent(pquestiondto);
				
		model.addAttribute("content", content);
	}
	
	@Override
	public void getcuserQnacotent(Model model, PQuestionDTO pquestiondto) {  //판매자 답글 가져오기
		PQuestionDTO cuserContent = mapper.getCuserContent(pquestiondto);
		int answerCount = mapper.getAnswerCount(pquestiondto); // 답변한 갯수 1이상이면 안나오게 하려고

		model.addAttribute("answerCount", answerCount);
		model.addAttribute("cuserContent", cuserContent);
	}
	
	@Override
	public String getPidPnum(int pq_p_num, int pq_ref) {
		String p_m_id = mapper.getPidPnum(pq_p_num, pq_ref);// 상품 번호와 판매자가 일치하는지 가져오기 위해			
		return p_m_id;
	}
	
	@Override
	public void wirteAnswerd(PQuestionDTO pquestiondto) { //답변등록
		mapper.wirteAnswerd(pquestiondto);
	}
	
	
	//아래는 문의 등록
	
	@Override  
	public void boardReg(PQuestionDTO pquestiondto, String realPath) { //내용등록
		int board_num = mapper.getBoardNEXTNum(); //문의 등록시 다음번호
		pquestiondto.setPq_num(board_num);
		
		pquestiondto.setPq_content(pquestiondto.getPq_content().replaceAll("/temporary", "/" + board_num));
		// temp 폴더 안의 이미지를 게시글 저장소로 이동
		String path_folder1 = realPath + "/temporary/";
        String path_folder2 = realPath + "/"+board_num+"/";
        
        mapper.userQnaUp(pquestiondto);//문의내용 등록      
        fileUpload(path_folder1, path_folder2,board_num);
        deleteFolder(path_folder1);
		
	}

	private void fileUpload(String path_folder1, String path_folder2,int num) {
        // path_folder1에서 path_folder2로 파일을 복사하는 함수입니다.
     
        File folder1;
        File folder2;
        folder1 = new File(path_folder1);
        folder2 = new File(path_folder2);

        // 복사할 폴더들이 존재하지 않으면 생성합니다.
        if (!folder1.exists())
            folder1.mkdirs();
        if (!folder2.exists())
            folder2.mkdirs();

        // 폴더1에서 파일 목록을 가져옵니다.
        File[] target_file = folder1.listFiles();
        for (File file : target_file) {
            // 복사 대상 파일의 경로와 이름을 설정합니다.
            File temp = new File(folder2.getAbsolutePath() + File.separator + file.getName());

            if (file.isDirectory()) {
                temp.mkdir();
            } else {
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                    // 파일 복사를 위해 FileInputStream과 FileOutputStream을 생성합니다.
                    fis = new FileInputStream(file);
                    fos = new FileOutputStream(temp);

                    byte[] b = new byte[4096];
                    int cnt = 0;
                    while ((cnt = fis.read(b)) != -1) {
                        // 버퍼를 사용하여 파일 내용을 읽고 복사합니다.
                        fos.write(b, 0, cnt);
                    }
                    NoticeFileDTO dto = new NoticeFileDTO();                          
                    if(num!=0) {                           
                       dto.setNf_n_num(num);
                       dto.setNf_filename(file.getName());
                       dto.setNf_category(30);
                       mapper.boardFileReg(dto);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // FileInputStream과 FileOutputStream을 닫습니다.
                    try {
                        fis.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
	
	
	// 하위 폴더 삭제
    private void deleteFolder(String path) {
        // 주어진 경로에 있는 폴더와 파일을 재귀적으로 삭제하는 함수입니다.

        File folder = new File(path);
        try {
            if (folder.exists()) {
                File[] folder_list = folder.listFiles();
                for (int i = 0; i < folder_list.length; i++) {
                    if (folder_list[i].isFile())
                        // 파일인 경우, 파일을 삭제합니다.
                        folder_list[i].delete();
                    else
                        // 폴더인 경우, 재귀적으로 폴더 내부의 파일 및 폴더를 삭제합니다.
                        deleteFolder(folder_list[i].getPath());
                    // 파일이나 폴더를 삭제합니다.
                    folder_list[i].delete();
                }
                // 폴더를 삭제합니다.
                folder.delete();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
	
	
	

	@Override
	public String productImgUpload(MultipartFile multipartFile, String realPath) {
		 JsonObject jsonObject = new JsonObject();

         // 이미지 파일이 저장될 경로 설정
         String contextRoot = realPath + "temporary/";
         String fileRoot = contextRoot;

         // 업로드된 파일의 원본 파일명과 확장자 추출
         String originalFileName = multipartFile.getOriginalFilename();
         String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

         // 새로운 파일명 생성 (고유한 식별자 + 확장자)
         String savedFileName = UUID.randomUUID() + extension;

         // 저장될 파일의 경로와 파일명을 나타내는 File 객체 생성
         File targetFile = new File(fileRoot + savedFileName);

         try {
             // 업로드된 파일의 InputStream 얻기
             java.io.InputStream fileStream = multipartFile.getInputStream();

             // 업로드된 파일을 지정된 경로에 저장
             FileUtils.copyInputStreamToFile(fileStream, targetFile);

             // JSON 객체에 이미지 URL과 응답 코드 추가
             jsonObject.addProperty("url", "/resources/file/board/temporary/" + savedFileName);
             jsonObject.addProperty("responseCode", "success");
         } catch (IOException e) {
             // 파일 저장 중 오류가 발생한 경우 해당 파일 삭제 및 에러 응답 코드 추가
             FileUtils.deleteQuietly(targetFile);
             jsonObject.addProperty("responseCode", "error");
             e.printStackTrace();
         }

         // JSON 객체를 문자열로 변환하여 반환
         String a = jsonObject.toString();
         return a;
  	}
	
	
	
	

	@Override
	public void productImgDel(String fileName, String realPath) {
		 // 폴더 위치
        String filePath = realPath + "temporary/";

        // 해당 파일 삭제
        deleteFile(filePath, fileName);
		
	}
	
	// 파일 하나 삭제
    private void deleteFile(String filePath, String fileName) {
        // 주어진 파일 경로와 이름을 기반으로 파일 경로 객체 생성
        Path path = Paths.get(filePath, fileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	

	@Override
	public void consumerQnaList(Model model, int pageNum) { //소비자 관리자
		 int pageSize = 10;
	      int startRow = (pageNum - 1) * pageSize + 1;
	      int endRow = pageNum * pageSize;
	      int count = mapper.consumerQnacount(); //질문갯수  
	      List<QnADTO> list = Collections.EMPTY_LIST;
	      
	      if (count > 0) {
	    	  boardListMap.put("start", startRow);
	    	  boardListMap.put("end", endRow);	                
	         list = mapper.consumerQnalist(boardListMap); 
	      }
	      
	      int answerCheck = 0;
	      
	      
	      model.addAttribute("list", list);
	      model.addAttribute("count", count);
	      model.addAttribute("pageNum", pageNum);
	      model.addAttribute("pageSize", pageSize);

	      // page
	      int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
	      int startPage = (int) (pageNum / 10) * 10 + 1;
	      int pageBlock = 10;
	      int endPage = startPage + pageBlock - 1;
	      if (endPage > pageCount) {
	         endPage = pageCount;
	      }
	      model.addAttribute("pageCount", pageCount);
	      model.addAttribute("startPage", startPage);
	      model.addAttribute("pageBlock", pageBlock);
	      model.addAttribute("endPage", endPage);	
	}
	
	
	@Override
	public void contentView(Model model, QnADTO qnadto, String id) {
		QnADTO content = mapper.getcontent(qnadto);
		model.addAttribute("content", content); // 문의 내용 가져오기
		
		QnADTO contentView = mapper.getcontentView(qnadto);
		model.addAttribute("contentView",contentView); // 문의 내용 댓글 가져오기
		
		int answerCount = mapper.answerCount(qnadto); // 댓글 여부 확인 
		model.addAttribute("answerCount", answerCount); // 
		
		int m_Status = mapper.m_Status(id); // 회원 등급 가져오기
		model.addAttribute("m_Status", m_Status); // 
	}

	@Override
	public void insertanswer(QnADTO qnadto) {
		mapper.insertanswer(qnadto);
		
	}

	@Override
	public void questionReg(QnADTO qnadto, String realPath) {
		int ma_num = mapper.getMaNEXTNum(); //문의 등록시 다음번호
		qnadto.setMa_num(ma_num);
		
		qnadto.setMa_content(qnadto.getMa_content().replaceAll("/temporary", "/" + ma_num));
		// temp 폴더 안의 이미지를 게시글 저장소로 이동
		String path_folder1 = realPath + "/temporary/";
        String path_folder2 = realPath + "/"+ma_num+"/";
        
        mapper.CAQnaUp(qnadto);//문의내용 등록      
        fileUpload2(path_folder1, path_folder2,ma_num);
        deleteFolder2(path_folder1);
	
	}

	@Override
	public String productImgCAUpload(MultipartFile multipartFile, String realPath) {
		 JsonObject jsonObject = new JsonObject();

         // 이미지 파일이 저장될 경로 설정
         String contextRoot = realPath + "temporary/";
         String fileRoot = contextRoot;

         // 업로드된 파일의 원본 파일명과 확장자 추출
         String originalFileName = multipartFile.getOriginalFilename();
         String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

         // 새로운 파일명 생성 (고유한 식별자 + 확장자)
         String savedFileName = UUID.randomUUID() + extension;

         // 저장될 파일의 경로와 파일명을 나타내는 File 객체 생성
         File targetFile = new File(fileRoot + savedFileName);

         try {
             // 업로드된 파일의 InputStream 얻기
             java.io.InputStream fileStream = multipartFile.getInputStream();

             // 업로드된 파일을 지정된 경로에 저장
             FileUtils.copyInputStreamToFile(fileStream, targetFile);

             // JSON 객체에 이미지 URL과 응답 코드 추가
             jsonObject.addProperty("url", "/resources/file/QnAboard/temporary/" + savedFileName);
             jsonObject.addProperty("responseCode", "success");
         } catch (IOException e) {
             // 파일 저장 중 오류가 발생한 경우 해당 파일 삭제 및 에러 응답 코드 추가
             FileUtils.deleteQuietly(targetFile);
             jsonObject.addProperty("responseCode", "error");
             e.printStackTrace();
         }

         // JSON 객체를 문자열로 변환하여 반환
         String a = jsonObject.toString();
         return a;
	}

	@Override
	public void productImgCADel(String fileName, String realPath) {
		 // 폴더 위치
        String filePath = realPath + "temporary/";

        // 해당 파일 삭제
        deleteFile(filePath, fileName);
	}

	
	private void fileUpload2(String path_folder1, String path_folder2,int num) {
        // path_folder1에서 path_folder2로 파일을 복사하는 함수입니다.
     
        File folder1;
        File folder2;
        folder1 = new File(path_folder1);
        folder2 = new File(path_folder2);

        // 복사할 폴더들이 존재하지 않으면 생성합니다.
        if (!folder1.exists())
            folder1.mkdirs();
        if (!folder2.exists())
            folder2.mkdirs();

        // 폴더1에서 파일 목록을 가져옵니다.
        File[] target_file = folder1.listFiles();
        for (File file : target_file) {
            // 복사 대상 파일의 경로와 이름을 설정합니다.
            File temp = new File(folder2.getAbsolutePath() + File.separator + file.getName());

            if (file.isDirectory()) {
                temp.mkdir();
            } else {
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                    // 파일 복사를 위해 FileInputStream과 FileOutputStream을 생성합니다.
                    fis = new FileInputStream(file);
                    fos = new FileOutputStream(temp);

                    byte[] b = new byte[4096];
                    int cnt = 0;
                    while ((cnt = fis.read(b)) != -1) {
                        // 버퍼를 사용하여 파일 내용을 읽고 복사합니다.
                        fos.write(b, 0, cnt);
                    }
                    NoticeFileDTO dto = new NoticeFileDTO();                          
                    if(num!=0) {                           
                       dto.setNf_n_num(num);
                       dto.setNf_filename(file.getName()); 위에 다바꿔야함
                       dto.setNf_category(30);
                       mapper.여기아직안바꿈FileReg(dto);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // FileInputStream과 FileOutputStream을 닫습니다.
                    try {
                        fis.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
	
	
	// 하위 폴더 삭제
    private void deleteFolder2(String path) {
        // 주어진 경로에 있는 폴더와 파일을 재귀적으로 삭제하는 함수입니다.

        File folder = new File(path);
        try {
            if (folder.exists()) {
                File[] folder_list = folder.listFiles();
                for (int i = 0; i < folder_list.length; i++) {
                    if (folder_list[i].isFile())
                        // 파일인 경우, 파일을 삭제합니다.
                        folder_list[i].delete();
                    else
                        // 폴더인 경우, 재귀적으로 폴더 내부의 파일 및 폴더를 삭제합니다.
                        deleteFolder(folder_list[i].getPath());
                    // 파일이나 폴더를 삭제합니다.
                    folder_list[i].delete();
                }
                // 폴더를 삭제합니다.
                folder.delete();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void sellerQnaList(Model model, int pageNum) {
		
	}

	
	
	
	


	

	


	
	
}
