package com.gogi.meatyou.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.http.HttpServletRequest;

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
import com.gogi.meatyou.bean.ReckonDTO;
import java.io.File;
import com.gogi.meatyou.repository.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {
   @Autowired
   private HashMap adminMap;

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
              System.out.println("Response cod:"+conn.getResponseCode());
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
   public void noticeReg(MultipartFile multipartFile, HttpServletRequest request) {
      
      ObjectMapper ojm = new ObjectMapper();
      
      String realPath = "/Users/jang-uiseog/Documents/Spring/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps";
       //  이미지 파일이 저장될 경로 설정 
       String contextRoot = realPath + "/upload_image/image/fileupload/29/";
       String fileRoot = contextRoot;
       
       //  업로드된 파일의 원본 파일명과 확장자 추출
       String originalFileName = multipartFile.getOriginalFilename();
       String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
       
    // 새로운 파일명 생성 (고유한 식별자 + 확장자)옣 옄)
       String savedFileName = UUID.randomUUID() + extension;
       
    // 저장될 파일의 경로와 파일명을 나타내는 File 객체 생성       
       File targetFile = new File(fileRoot + savedFileName);
       
       try {
           // 업로드된 파일의 InputStream 얻기
           java.io.InputStream fileStream = multipartFile.getInputStream();
           
           // 업로드된 파일을 지정된 경로에 저장
           FileUtils.copyInputStreamToFile(fileStream, targetFile);
           
           // JSON 객체에 이미지 URL과 응답 코드 추가
           adminMap.put("url", "/upload_image/image/fileupload/29/"+savedFileName);
           adminMap.put("responseCode", "success");
       } catch (IOException e) {
           // 파일 저장 중 오류가 발생한 경우 해당 파일 삭제 및 에러 응답 코드 추가
           FileUtils.deleteQuietly(targetFile);
           adminMap.put("responseCode","error");
           e.printStackTrace();
       }
       try {
         String jsonStr = ojm.writeValueAsString(adminMap);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }
       
       // JSON 媛앹껜瑜  臾몄옄 뿴濡  蹂  솚 븯 뿬 諛섑솚
      // return jsonStr;
   }

   
   


   

}