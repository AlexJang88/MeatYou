package com.gogi.meatyou.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.bean.ReckonDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.gogi.meatyou.repository.AdminMapper;
import com.google.gson.JsonObject;

@Service
public class AdminServiceImpl implements AdminService {
   
	private int imgcnt=0;
	
   @Autowired
   private HashMap adminMap;
   
   @Autowired
   private ArrayList<String> oldname;
   
   @Autowired
   private NoticeFileDTO noticefiledto;
   
   @Autowired
   private AdminService adminServiceImpl;

//   @Override
//   public void list(int pageNum, Model model) {
//      
//
//   }

   @Autowired
   private AdminMapper mapper;

   private HttpURLConnection conn;

   public void memberList(int check, Model model, int pageNum) {
      int count=0;
      int pageSize = 10;
      int startRow = (pageNum - 1) * pageSize + 1;
      int endRow = pageNum * pageSize;
      List<MemberDTO> list = Collections.EMPTY_LIST;
      if (check == 1) {
         count = mapper.memCount();
         if (count > 0) {
            adminMap.put("start", startRow);
            adminMap.put("end", endRow);
            list = mapper.memberList(adminMap);
         }

         
      } else if (check == 2) {
         count = mapper.cusCount();

         if (count > 0) {
            adminMap.put("start", startRow);
            adminMap.put("end", endRow);
            list = mapper.customList(adminMap);
         }

      }else if(check==3) {
         count = mapper.cusWaitCount();
         
         if (count > 0) {
            adminMap.put("start", startRow);
            adminMap.put("end", endRow);
            list = mapper.cusWaitList(adminMap);
         }

      }else if(check==4) {
         count = mapper.cusPaidCount();
         
         if (count > 0) {
            adminMap.put("start", startRow);
            adminMap.put("end", endRow);
            list = mapper.cusPaidList(adminMap);
         }
      }
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
      model.addAttribute("check", check);
   }

   @Override
   public List<MemberDTO> customList(HashMap hashmap) {
      return mapper.customList(hashmap);
   }

   @Override
   public List<MemberDTO> memberList(HashMap hashmap) {
      return mapper.memberList(hashmap);
   }

   @Override
   public int cusCount() {
      // TODO Auto-generated method stub
      return mapper.cusCount();
   }

   @Override
   public int memCount() {
      // TODO Auto-generated method stub
      return mapper.memCount();
   }

   @Override
   public List<String> goodMember() {
      // TODO Auto-generated method stub
      return mapper.goodMember();
   }

   @Override
   public List<String> bestMember() {
      return mapper.bestMember();
   }

   @Override
   public void goodMemberUpdate(List<String> id) {
      if (goodMember().size() > 0) {
         mapper.goodMemberUpdate(mapper.goodMember());
      }
   }

   @Override
   public void bestMemberUpdate(List<String> id) {
      if (bestMember().size() > 0) {
         mapper.bestMemberUpdate(mapper.bestMember());
      }
   }

   @Override
   public MemberDTO test(String m_id) {

      return mapper.test(m_id);
   }

   /*
    * @Scheduled(cron="* * * * * *") 占쏙옙 占쏙옙 占시곤옙 占쏙옙 占쏙옙 占쏙옙占쏙옙
    */

   @Scheduled(cron = "0 0 0 1 * *")
   public void autoMemberUpdate() {
      if (goodMember().size() > 0) {
         mapper.goodMemberUpdate(mapper.goodMember());
      }
      if (bestMember().size() > 0) {
         mapper.bestMemberUpdate(mapper.bestMember());
      }
   }

   @Override
   public void apiTest(Model model){
      StringBuilder urlBuilder = new StringBuilder("http://211.237.50.150:7080/openapi"); 
      //StringBuilder urlBuilder = new StringBuilder("http://211.237.50.150:7080/openapi/sample/xml/Grid_20151204000000000316_1/1/5");
       urlBuilder.append("/1c9a14382163bb7dc822492a3dca9b9a8841b3782755afedd33d3b5879c98e94");
       urlBuilder.append("/xml");   
       urlBuilder.append("/Grid_20151204000000000316_1/1/5");   
      // urlBuilder.append("?API-KEY="); // API  궎瑜   븞 쟾 븳 怨녹뿉 꽌 遺덈윭 삤 룄濡   닔 젙 븯 꽭 슂.
        //  urlBuilder.append("&START_INDEX=1");
        //  urlBuilder.append("&END_INDEX=10");
        //  urlBuilder.append("&TYPE=json");

          HttpURLConnection conn = null;
          try {
              URL url = new URL(urlBuilder.toString());
              conn = (HttpURLConnection) url.openConnection();
              //  뿰寃   꽕 젙 (硫붿꽌 뱶,  뿤 뜑,  벑)
              conn.setRequestMethod("GET");
              conn.setRequestProperty("Content-type","application/json");
              //  뿰寃고븯怨   뜲 씠 꽣瑜   씫 뒿 땲 떎.
              //  삁: int responseCode = conn.getResponseCode();
              BufferedReader rd;
              if(conn.getResponseCode()>=200 && conn.getResponseCode()<=300) {
                 rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
              }else {
                 rd=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
              }
              StringBuilder sb = new StringBuilder();
              String line;
              while((line=rd.readLine())!=null) {
                 sb.append(line);
              }
              model.addAttribute("test", sb);
              rd.close();
              // 異붽  쟻 쑝濡   쓳 떟 쓣 泥섎━ 븯 뒗 肄붾뱶瑜   뿬湲곗뿉 異붽  븯 꽭 슂.
          } catch (MalformedURLException e) {
              // URL  삎 떇 씠  옒紐삳맂 寃쎌슦 쓽  삁 쇅 泥섎━
              e.printStackTrace();
          } catch (IOException e) {
              //  뿰寃곗쓣  뿬 뒗 怨쇱젙 뿉 꽌 I/O  삁 쇅 泥섎━
              e.printStackTrace();
          } finally {
              if (conn != null) {
                  conn.disconnect();
              }
          }
      //HttpURLConnection혻conn혻=혻(HttpURLConnection)url.openConnection();혻혻혻혻혻
      //   혻혻혻conn.setRequestMethod("GET");혻혻혻혻혻
      //   혻혻혻conn.setRequestProperty("Content-type","application/json");혻혻혻혻혻혻
      //   혻혻System.out.println("Response혻code:혻"혻+혻conn.getResponseCode());혻혻혻혻
      //   혻혻혻혻BufferedReader혻rd;혻혻혻
      //   혻혻혻혻혻if(conn.getResponseCode()혻>=혻200혻&&혻conn.getResponseCode()혻<=혻300)혻{혻혻혻혻혻혻
      //      혻혻혻혻혻혻rd혻=혻new혻BufferedReader(new혻InputStreamReader(conn.getInputStream()));혻혻혻혻혻혻
      //      혻혻}혻else혻{혻혻혻혻혻혻혻혻
      //         혻혻혻혻rd혻=혻new혻BufferedReader(new혻InputStreamReader(conn.getErrorStream()));혻혻혻혻
      //         혻혻혻혻}혻혻혻혻
      //   혻혻혻혻StringBuilder혻sb혻=혻new혻StringBuilder();혻혻혻혻혻혻
      //   혻혻String혻line;혻혻혻혻혻혻혻
      //   혻while혻((line혻=혻rd.readLine())혻!=혻null)혻{혻혻혻혻혻
      //      혻혻혻혻혻혻혻sb.append(line);혻혻혻혻혻혻혻
      //      혻}혻혻혻혻혻혻혻
      //   혻rd.close();혻혻혻혻혻혻
      //   혻혻conn.disconnect();혻혻혻혻혻혻
      //   혻혻System.out.println(sb.toString());
      
      
   //   StringBuilder혻urlBuilder혻=혻new혻StringBuilder("http://211.237.50.150:7080/openapi/sample/xml/Grid_20220823000000000636_1/1/5");혻/*URL*/혻혻혻혻혻혻혻혻
   //   urlBuilder.append("?"혻+혻URLEncoder.encode("API-KEY","UTF-8")혻+혻"=1c9a14382163bb7dc822492a3dca9b9a8841b3782755afedd33d3b5879c98e94");혻/*Service혻Key*/혻
   //   혻혻혻혻혻혻혻urlBuilder.append("&"혻+혻URLEncoder.encode("START_INDEX","UTF-8")혻+혻"="혻+혻URLEncoder.encode("1",혻"UTF-8"));혻/* 븳혻 럹 씠吏 혻寃곌낵혻 닔*/혻혻혻혻혻혻
   //   혻혻urlBuilder.append("&"혻+혻URLEncoder.encode("END_INDEX","UTF-8")혻+혻"="혻+혻URLEncoder.encode("10",혻"UTF-8"));혻/* 럹 씠吏 혻踰덊샇*/혻혻혻
   //   혻혻혻혻혻urlBuilder.append("&"혻+혻URLEncoder.encode("TYPE","UTF-8")혻+혻"="혻+혻URLEncoder.encode("json",혻"UTF-8"));혻/*痢≪젙 냼혻 씠由 */혻혻혻혻혻혻
   //   혻혻HttpURLConnection혻conn혻=혻(HttpURLConnection)혻url.openConnection();혻혻혻혻혻
   //   혻혻혻conn.setRequestMethod("GET");혻혻혻혻혻
   //   혻혻혻conn.setRequestProperty("Content-type",혻"application/json");혻혻혻혻혻혻
   //   혻혻System.out.println("Response혻code:혻"혻+혻conn.getResponseCode());혻혻혻혻
   //   혻혻혻혻BufferedReader혻rd;혻혻혻
   //   혻혻혻혻혻if(conn.getResponseCode()혻>=혻200혻&&혻conn.getResponseCode()혻<=혻300)혻{혻혻혻혻혻혻
   //      혻혻혻혻혻혻rd혻=혻new혻BufferedReader(new혻InputStreamReader(conn.getInputStream()));혻혻혻혻혻혻
   //      혻혻}혻else혻{혻혻혻혻혻혻혻혻
   //         혻혻혻혻rd혻=혻new혻BufferedReader(new혻InputStreamReader(conn.getErrorStream()));혻혻혻혻
   //         혻혻혻혻}혻혻혻혻
   //   혻혻혻혻StringBuilder혻sb혻=혻new혻StringBuilder();혻혻혻혻혻혻
   //   혻혻String혻line;혻혻혻혻혻혻혻
   //   혻while혻((line혻=혻rd.readLine())혻!=혻null)혻{혻혻혻혻혻
   //      혻혻혻혻혻혻혻sb.append(line);혻혻혻혻혻혻혻
   //      혻}혻혻혻혻혻혻혻
   //   혻rd.close();혻혻혻혻혻혻
   //   혻혻conn.disconnect();혻혻혻혻혻혻
   //   혻혻System.out.println(sb.toString());
   }

   @Override
   public void statChange(MemberDTO dto) {
      mapper.statChange(dto);
   }

   @Override
   public void getSales(Model model,int check) {
      model.addAttribute("ps", mapper.getProductSalse(check));
      model.addAttribute("pc", mapper.getProductComm(check));
      model.addAttribute("pi", mapper.getPaidItem(check));
      model.addAttribute("pa", mapper.getPaidAdv(check));
      model.addAttribute("uc", mapper.getUsedCoupon(check));
      model.addAttribute("pt", mapper.getPaidAdv(check)+mapper.getPaidItem(check)+mapper.getProductSalse(check)+mapper.getProductComm(check)-mapper.getUsedCoupon(check));
      model.addAttribute("check",check);
   }

   @Override
   public void getCheckSalse(Model model,int check,String start,String end) {
      String[] startarr = start.split("/");
      String[] endarr=end.split("/");
      start = startarr[2]+"-"+startarr[0]+"-"+startarr[1];
      end = endarr[2]+"-"+endarr[0]+"-"+endarr[1];
      adminMap.put("start", start);
      adminMap.put("end",end);
      model.addAttribute("ps", mapper.getCheckProductSalse(adminMap));
      model.addAttribute("pc", mapper.getCheckProductComm(adminMap));
      model.addAttribute("pi", mapper.getCheckPaidItem(adminMap));
      model.addAttribute("pa", mapper.getCheckPaidAdv(adminMap));
      model.addAttribute("uc", mapper.getCheckUsedCoupon(adminMap));
      model.addAttribute("pt", mapper.getCheckPaidAdv(adminMap)+mapper.getCheckPaidItem(adminMap)+mapper.getCheckProductSalse(adminMap)+mapper.getCheckProductComm(adminMap)-mapper.getCheckUsedCoupon(adminMap));
      model.addAttribute("check",check);
   }

   @Override
   public void getReckon(Model model, int pageNum, String year, String month) {
      
      String CheckMonth = year+"-"+month;
      int count=0;
      int pageSize = 10;
      int startRow = (pageNum - 1) * pageSize + 1;
      int endRow = pageNum * pageSize;
      List<ReckonDTO> list = Collections.EMPTY_LIST;
         adminMap.put("CheckMonth",CheckMonth);
         count = mapper.getReckCount(adminMap);
         if (count > 0) {
            adminMap.put("start", startRow);
            adminMap.put("end", endRow);
            
            list = mapper.getReckon(adminMap);
         }
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
public int noticeMaxnum() {
	return mapper.noticeMaxnum();
}

@Override
public NoticeDTO getNotice() {
	return mapper.getNotice();
}

@Override
public int noticeReg(HttpServletRequest req, HttpServletResponse resp, Model model, NoticeDTO dto) {
	 HttpSession session = req.getSession();
     // �ۼ��� �Խù� �� ���� �ֱ� �Խù� ��������
     NoticeDTO board = mapper.getNotice();
     String realpath = req.getServletContext().getRealPath("/resources/file/notice/"+(noticeMaxnum()+1)+"/");
     for(int i=0;i<oldname.size();i++) {
    	 if(!dto.getN_content().contains(oldname.get(i))) {
    		 File f = new File(realpath+oldname.get(i));
    		 if(f.exists()) {
    			 f.delete();
    		 }
    	 }else {
    		 noticefiledto.setNf_filename(oldname.get(i));
    		 noticefiledto.setNf_n_num(mapper.noticeMaxnum()+1);
    		 mapper.noticeFileUpload(noticefiledto);
    	 }
     }
     mapper.noticeReg(dto);
     imgcnt=0;
    oldname.clear();
	return 0;
}

@Override
public String uploadSummerImgFile(MultipartFile multipartFile, HttpServletRequest request) {
	// JSON ��ü ����
			JsonObject jsonObject = new JsonObject();

			// �̹��� ������ ����� ��� ����
			String path = request.getServletContext().getRealPath("/resources/file/notice/"+(noticeMaxnum()+1)+"/");
			
			// ���ε�� ������ ���� ���ϸ�� Ȯ���� ����
			String originalFileName = multipartFile.getOriginalFilename();
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

			// ���ο� ���ϸ� ���� (������ �ĺ��� + Ȯ����)
			int n_num = mapper.noticeMaxnum();
			imgcnt+=1;
			String savedFileName = "notice_"+(n_num+1)+"_"+imgcnt+extension;
			
			oldname.add(savedFileName);

			// ����� ������ ��ο� ���ϸ��� ��Ÿ���� File ��ü ����
			File targetFile = new File(path + savedFileName);
			
			try {
				if(!targetFile.exists()) {
					targetFile.mkdir();
				}
				// ���ε�� ������ InputStream ���
				java.io.InputStream fileStream = multipartFile.getInputStream();

				// ���ε�� ������ ������ ��ο� ����
				FileUtils.copyInputStreamToFile(fileStream, targetFile);

				// JSON ��ü�� �̹��� URL�� ���� �ڵ� �߰�
				jsonObject.addProperty("url", "/resources/file/notice/" +(noticeMaxnum()+1)+"/"+ savedFileName);
				jsonObject.addProperty("responseCode", "success");
			} catch (IOException e) {
				// ���� ���� �� ������ �߻��� ��� �ش� ���� ���� �� ���� ���� �ڵ� �߰�
				FileUtils.deleteQuietly(targetFile);
				jsonObject.addProperty("responseCode", "error");
				e.printStackTrace();
			}
			// JSON ��ü�� ���ڿ��� ��ȯ�Ͽ� ��ȯ
			String a = jsonObject.toString();
	return a;
}

@Override
public void noticeList(Model model,int pageNum) {
    int count=0;
    int pageSize = 10;
    int startRow = (pageNum - 1) * pageSize + 1;
    int endRow = pageNum * pageSize;
    List<NoticeDTO> list = Collections.EMPTY_LIST;
       count = mapper.getNoticeCount();
       if (count > 0) {
          adminMap.put("start", startRow);
          adminMap.put("end", endRow);
          
          list = mapper.noticeList(adminMap);
       }
       System.out.println("====title"+list.get(0).getN_title());
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
public void getNoticeContent(Model model,NoticeDTO dto,NoticeFileDTO fdto) {
	 dto = mapper.noticeContent(dto);
	 List<NoticeFileDTO> list = mapper.noticeFileUpdate(dto.getN_num());
	 model.addAttribute("dto", dto);
	 model.addAttribute("list",list);
	 
}

@Override
public String updateSummerImgFile(MultipartFile multipartFile, HttpServletRequest request,int n_num) {
	// JSON ��ü ����
				JsonObject jsonObject = new JsonObject();
				
				List<NoticeFileDTO> olddto= mapper.noticeFileUpdate(n_num);
				ArrayList<String> fname=new ArrayList<String>();
				
				for(NoticeFileDTO dto : olddto) {
					oldname.add(dto.getNf_filename());
				}
				
				// �̹��� ������ ����� ��� ����
				String path = request.getServletContext().getRealPath("/resources/file/notice/"+n_num+"/");
				
				// ���ε�� ������ ���� ���ϸ�� Ȯ���� ����
				String originalFileName = multipartFile.getOriginalFilename();
				String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

				// ���ο� ���ϸ� ���� (������ �ĺ��� + Ȯ����)
				imgcnt=oldname.size()+1;
				String savedFileName = "notice_"+n_num+"_"+imgcnt+extension;
				
				oldname.add(savedFileName);

				// ����� ������ ��ο� ���ϸ��� ��Ÿ���� File ��ü ����
				File targetFile = new File(path + savedFileName);
				
				try {
					if(!targetFile.exists()) {
						targetFile.mkdir();
					}
					// ���ε�� ������ InputStream ���
					java.io.InputStream fileStream = multipartFile.getInputStream();

					// ���ε�� ������ ������ ��ο� ����
					FileUtils.copyInputStreamToFile(fileStream, targetFile);

					// JSON ��ü�� �̹��� URL�� ���� �ڵ� �߰�
					jsonObject.addProperty("url", "/resources/file/notice/" +n_num+"/"+ savedFileName);
					jsonObject.addProperty("responseCode", "success");
				} catch (IOException e) {
					// ���� ���� �� ������ �߻��� ��� �ش� ���� ���� �� ���� ���� �ڵ� �߰�
					FileUtils.deleteQuietly(targetFile);
					jsonObject.addProperty("responseCode", "error");
					e.printStackTrace();
				}
				// JSON ��ü�� ���ڿ��� ��ȯ�Ͽ� ��ȯ
		 		String a = jsonObject.toString();
	return a;
}
@Override
public int noticeupdate(HttpServletRequest req, HttpServletResponse resp, Model model, NoticeDTO dto) {
	 HttpSession session = req.getSession();
     // �ۼ��� �Խù� �� ���� �ֱ� �Խù� ��������
     NoticeDTO board = mapper.getNotice();
     String realpath = req.getServletContext().getRealPath("/resources/file/notice/"+dto.getN_num()+"/");
     	mapper.noticeFileDelete(dto.getN_num());
     for(int i=0;i<oldname.size();i++) {
    	 if(!dto.getN_content().contains(oldname.get(i))) {
    		 File f = new File(realpath+oldname.get(i));
    		 if(f.exists()) {
    			 f.delete();
    		 }
    	 }else {
    		 noticefiledto.setNf_filename(oldname.get(i));
    		 noticefiledto.setNf_n_num(dto.getN_num());
    		 mapper.noticeFileUpload(noticefiledto);
    	 }
     }
     mapper.noticeReg(dto);
     imgcnt=0;
    oldname.clear();
	return 0;
}

@Override
public void noticedelete(int n_num) {
	mapper.noticedelete(n_num);
	mapper.noticeFileDelete(n_num);
}

	


}