package com.gogi.meatyou.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.gogi.meatyou.bean.AdminProductDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.bean.QnADTO;
import com.gogi.meatyou.bean.ReckonDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.gogi.meatyou.repository.AdminMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class AdminServiceImpl implements AdminService {
   
	private int imgcnt=0;
	private int check=0;
	
   @Autowired
   private HashMap adminMap;
   
   @Autowired
   private ArrayList<String> adminoldname;
   
   @Autowired
   private NoticeFileDTO noticefiledto;
   

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

//   @Override
//   public MemberDTO test(String m_id) {
//
//      return mapper.test(m_id);
//   }

   /*
    * @Scheduled(cron="* * * * * *") �뜝�룞�삕 �뜝�룞�삕 �뜝�떆怨ㅼ삕 �뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
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

//   @Override
//   public void apiTest(Model model){
//      StringBuilder urlBuilder = new StringBuilder("http://211.237.50.150:7080/openapi"); 
//      //StringBuilder urlBuilder = new StringBuilder("http://211.237.50.150:7080/openapi/sample/xml/Grid_20151204000000000316_1/1/5");
//       urlBuilder.append("/1c9a14382163bb7dc822492a3dca9b9a8841b3782755afedd33d3b5879c98e94");
//       urlBuilder.append("/xml");   
//       urlBuilder.append("/Grid_20151204000000000316_1/1/5");   
//      // urlBuilder.append("?API-KEY="); // API  沅롧몴   釉� �읈 釉� �⑤끃肉� 苑� �겫�뜄�쑎 �궎 猷꾣에   �땾 �젟 釉� 苑� �뒄.
//        //  urlBuilder.append("&START_INDEX=1");
//        //  urlBuilder.append("&END_INDEX=10");
//        //  urlBuilder.append("&TYPE=json");
//
//          HttpURLConnection conn = null;
//          try {
//              URL url = new URL(urlBuilder.toString());
//              conn = (HttpURLConnection) url.openConnection();
//              //  肉겼칰   苑� �젟 (簾ル뗄苑� 諭�,  肉� �쐭,  踰�)
//              conn.setRequestMethod("GET");
//              conn.setRequestProperty("Content-type","application/json");
//              //  肉겼칰怨좊릭��   �쑓 �뵠 苑ｇ몴   �뵭 �뮸 �빍 �뼄.
//              //  �굙: int responseCode = conn.getResponseCode();
//              BufferedReader rd;
//              if(conn.getResponseCode()>=200 && conn.getResponseCode()<=300) {
//                 rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//              }else {
//                 rd=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//              }
//              StringBuilder sb = new StringBuilder();
//              String line;
//              while((line=rd.readLine())!=null) {
//                 sb.append(line);
//              }
//              model.addAttribute("test", sb);
//              rd.close();
//              // �빊遺�  �읅 �몵嚥�   �벓 �뼗 �뱽 力μ꼶�봺 釉� �뮉 �굜遺얜굡�몴   肉ф묾怨쀫퓠 �빊遺�  釉� 苑� �뒄.
//          } catch (MalformedURLException e) {
//              // URL  �굨 �뻼 �뵠  �삋榮먯궠留� 野껋럩�뒭 �벥  �굙 �뇚 力μ꼶�봺
//              e.printStackTrace();
//          } catch (IOException e) {
//              //  肉겼칰怨쀬뱽  肉� �뮉 �⑥눘�젟 肉� 苑� I/O  �굙 �뇚 力μ꼶�봺
//              e.printStackTrace();
//          } finally {
//              if (conn != null) {
//                  conn.disconnect();
//              }
//          }
      //HttpURLConnection�샍conn�샍=�샍(HttpURLConnection)url.openConnection();�샍�샍�샍�샍�샍
      //   �샍�샍�샍conn.setRequestMethod("GET");�샍�샍�샍�샍�샍
      //   �샍�샍�샍conn.setRequestProperty("Content-type","application/json");�샍�샍�샍�샍�샍�샍
      //   �샍�샍System.out.println("Response�샍code:�샍"�샍+�샍conn.getResponseCode());�샍�샍�샍�샍
      //   �샍�샍�샍�샍BufferedReader�샍rd;�샍�샍�샍
      //   �샍�샍�샍�샍�샍if(conn.getResponseCode()�샍>=�샍200�샍&&�샍conn.getResponseCode()�샍<=�샍300)�샍{�샍�샍�샍�샍�샍�샍
      //      �샍�샍�샍�샍�샍�샍rd�샍=�샍new�샍BufferedReader(new�샍InputStreamReader(conn.getInputStream()));�샍�샍�샍�샍�샍�샍
      //      �샍�샍}�샍else�샍{�샍�샍�샍�샍�샍�샍�샍�샍
      //         �샍�샍�샍�샍rd�샍=�샍new�샍BufferedReader(new�샍InputStreamReader(conn.getErrorStream()));�샍�샍�샍�샍
      //         �샍�샍�샍�샍}�샍�샍�샍�샍
      //   �샍�샍�샍�샍StringBuilder�샍sb�샍=�샍new�샍StringBuilder();�샍�샍�샍�샍�샍�샍
      //   �샍�샍String�샍line;�샍�샍�샍�샍�샍�샍�샍
      //   �샍while�샍((line�샍=�샍rd.readLine())�샍!=�샍null)�샍{�샍�샍�샍�샍�샍
      //      �샍�샍�샍�샍�샍�샍�샍sb.append(line);�샍�샍�샍�샍�샍�샍�샍
      //      �샍}�샍�샍�샍�샍�샍�샍�샍
      //   �샍rd.close();�샍�샍�샍�샍�샍�샍
      //   �샍�샍conn.disconnect();�샍�샍�샍�샍�샍�샍
      //   �샍�샍System.out.println(sb.toString());
      
      
   //   StringBuilder�샍urlBuilder�샍=�샍new�샍StringBuilder("http://211.237.50.150:7080/openapi/sample/xml/Grid_20220823000000000636_1/1/5");�샍/*URL*/�샍�샍�샍�샍�샍�샍�샍�샍
   //   urlBuilder.append("?"�샍+�샍URLEncoder.encode("API-KEY","UTF-8")�샍+�샍"=1c9a14382163bb7dc822492a3dca9b9a8841b3782755afedd33d3b5879c98e94");�샍/*Service�샍Key*/�샍
   //   �샍�샍�샍�샍�샍�샍�샍urlBuilder.append("&"�샍+�샍URLEncoder.encode("START_INDEX","UTF-8")�샍+�샍"="�샍+�샍URLEncoder.encode("1",�샍"UTF-8"));�샍/* 釉논샍 �읂 �뵠�릯 �샍野껉퀗�궢�샍 �땾*/�샍�샍�샍�샍�샍�샍
   //   �샍�샍urlBuilder.append("&"�샍+�샍URLEncoder.encode("END_INDEX","UTF-8")�샍+�샍"="�샍+�샍URLEncoder.encode("10",�샍"UTF-8"));�샍/* �읂 �뵠�릯 �샍甕곕뜇�깈*/�샍�샍�샍
   //   �샍�샍�샍�샍�샍urlBuilder.append("&"�샍+�샍URLEncoder.encode("TYPE","UTF-8")�샍+�샍"="�샍+�샍URLEncoder.encode("json",�샍"UTF-8"));�샍/*�뿢�돦�젟 �꺖�샍 �뵠�뵳 */�샍�샍�샍�샍�샍�샍
   //   �샍�샍HttpURLConnection�샍conn�샍=�샍(HttpURLConnection)�샍url.openConnection();�샍�샍�샍�샍�샍
   //   �샍�샍�샍conn.setRequestMethod("GET");�샍�샍�샍�샍�샍
   //   �샍�샍�샍conn.setRequestProperty("Content-type",�샍"application/json");�샍�샍�샍�샍�샍�샍
   //   �샍�샍System.out.println("Response�샍code:�샍"�샍+�샍conn.getResponseCode());�샍�샍�샍�샍
   //   �샍�샍�샍�샍BufferedReader�샍rd;�샍�샍�샍
   //   �샍�샍�샍�샍�샍if(conn.getResponseCode()�샍>=�샍200�샍&&�샍conn.getResponseCode()�샍<=�샍300)�샍{�샍�샍�샍�샍�샍�샍
   //      �샍�샍�샍�샍�샍�샍rd�샍=�샍new�샍BufferedReader(new�샍InputStreamReader(conn.getInputStream()));�샍�샍�샍�샍�샍�샍
   //      �샍�샍}�샍else�샍{�샍�샍�샍�샍�샍�샍�샍�샍
   //         �샍�샍�샍�샍rd�샍=�샍new�샍BufferedReader(new�샍InputStreamReader(conn.getErrorStream()));�샍�샍�샍�샍
   //         �샍�샍�샍�샍}�샍�샍�샍�샍
   //   �샍�샍�샍�샍StringBuilder�샍sb�샍=�샍new�샍StringBuilder();�샍�샍�샍�샍�샍�샍
   //   �샍�샍String�샍line;�샍�샍�샍�샍�샍�샍�샍
   //   �샍while�샍((line�샍=�샍rd.readLine())�샍!=�샍null)�샍{�샍�샍�샍�샍�샍
   //      �샍�샍�샍�샍�샍�샍�샍sb.append(line);�샍�샍�샍�샍�샍�샍�샍
   //      �샍}�샍�샍�샍�샍�샍�샍�샍
   //   �샍rd.close();�샍�샍�샍�샍�샍�샍
   //   �샍�샍conn.disconnect();�샍�샍�샍�샍�샍�샍
   //   �샍�샍System.out.println(sb.toString());
//   }

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
	   try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMM",Locale.KOREAN);
			Calendar cal = Calendar.getInstance();
			Date sMonth = sf.parse("yyyyMM");
			cal.setTime(sMonth);
			cal.add(Calendar.MONTH,-1);
			String beforeMonth = sf.format(cal.getTime());
			 model.addAttribute("month",beforeMonth);
			}catch (ParseException e) {
				e.printStackTrace();
			}
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
public int noticeReg(HttpServletRequest req, HttpServletResponse resp, Model model, NoticeDTO dto) {
	 HttpSession session = req.getSession();
     // 占쌜쇽옙占쏙옙 占쌉시뱄옙 占쏙옙 占쏙옙占쏙옙 占쌍깍옙 占쌉시뱄옙 占쏙옙占쏙옙占쏙옙占쏙옙
	 int num=mapper.getNoticeNum();
	 String realpath = req.getServletContext().getRealPath("/resources/file/notice/"+num+"/");
     for(int i=0;i<adminoldname.size();i++) {
    	 if(!dto.getN_content().contains(adminoldname.get(i))) {
    		 File f = new File(realpath+adminoldname.get(i));
    		 if(f.exists()) {
    			 f.delete();
    		 }
    	 }else {
    		 noticefiledto.setNf_filename(adminoldname.get(i));
    		 noticefiledto.setNf_n_num(num);
    		 mapper.noticeFileUpload(noticefiledto);
    	 }
     }
     mapper.noticeReg(dto);
     imgcnt=0;
    adminoldname.clear();
	return 0;
}

@Override
public String uploadSummerImgFile(MultipartFile multipartFile, HttpServletRequest request) {
			JsonObject jsonObject = new JsonObject();
			
			int n_num = mapper.getNoticeNum();
			String path = request.getServletContext().getRealPath("/resources/file/notice/"+n_num+"/");
			
			String originalFileName = multipartFile.getOriginalFilename();
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			
			imgcnt+=1;
			String savedFileName = "notice_"+n_num+"_"+imgcnt+extension;
			
			adminoldname.add(savedFileName);

			File targetFile = new File(path + savedFileName);
			
			try {
				if(!targetFile.exists()) {
					targetFile.mkdir();
				}
				java.io.InputStream fileStream = multipartFile.getInputStream();

				FileUtils.copyInputStreamToFile(fileStream, targetFile);

				jsonObject.addProperty("url", "/resources/file/notice/"+n_num+"/"+ savedFileName);
				jsonObject.addProperty("responseCode", "success");
			} catch (IOException e) {
				FileUtils.deleteQuietly(targetFile);
				jsonObject.addProperty("responseCode", "error");
				e.printStackTrace();
			}
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
public void getNoticeContent(Model model,NoticeDTO dto) {
	 imgcnt=0;
	 adminoldname.clear();
	 dto = mapper.noticeContent(dto);
	 model.addAttribute("dto", dto);
	 List<NoticeFileDTO> olddto= mapper.getNoticeFiles(dto.getN_num());
		for(NoticeFileDTO ndto : olddto) {
			adminoldname.add(ndto.getNf_filename());
			}
		int temp=0;
		for(int i=0; i<adminoldname.size(); i++ ) {
			int start= adminoldname.get(i).lastIndexOf("_")+1;
			int end = adminoldname.get(i).lastIndexOf(".");
			int result = Integer.parseInt(adminoldname.get(i).substring(start,end));
			System.out.println("substring ====== " + result);
			if(result>temp) {
				temp = result;
			}
			
		}
		imgcnt=temp+1;
		}

@Override
public String updateSummerImgFile(MultipartFile multipartFile, HttpServletRequest request,int n_num) {
				JsonObject jsonObject = new JsonObject();
				ArrayList<String> fname=new ArrayList<String>();
				System.out.println("imgcnt=====updateimg lastnum" + imgcnt);
				
				String path = request.getServletContext().getRealPath("/resources/file/notice/"+n_num+"/");
				
				String originalFileName = multipartFile.getOriginalFilename();
				String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				
				String savedFileName = "notice_"+n_num+"_"+imgcnt+extension;
				
				adminoldname.add(savedFileName);

				File targetFile = new File(path + savedFileName);
				
				try {
					if(!targetFile.exists()) {
						targetFile.mkdir();
					}
					multipartFile.transferTo(targetFile);
					

					jsonObject.addProperty("url", "/resources/file/notice/"+n_num+"/"+ savedFileName);
					jsonObject.addProperty("responseCode", "success");
				} catch (IOException e) {
					jsonObject.addProperty("responseCode", "error");
					e.printStackTrace();
				}
		 		String a = jsonObject.toString();
	return a;
}

@Override
public int noticeupdate(HttpServletRequest req, HttpServletResponse resp, Model model, NoticeDTO dto) {
	 HttpSession session = req.getSession();
     
    
     String realpath = req.getServletContext().getRealPath("/resources/file/notice/"+dto.getN_num()+"/");
     mapper.noticeFileDelete(dto.getN_num());
     for(int i=0;i<adminoldname.size();i++) {
    	 if(!dto.getN_content().contains(adminoldname.get(i))) {
    		 File f = new File(realpath+adminoldname.get(i));
    		 if(f.exists()) {
    			 f.delete();
    		 }
    	 }else {
    		 noticefiledto.setNf_filename(adminoldname.get(i));
    		 noticefiledto.setNf_n_num(dto.getN_num());
    		 mapper.noticeFileUpload(noticefiledto);
    	 }
     }
     mapper.noticeUpdate(dto);
     imgcnt=0;
    adminoldname.clear();
	return 0;
}
@Override
public void noticedelete(int n_num,HttpServletRequest req) {
	mapper.noticedelete(n_num);
	String path = req.getServletContext().getRealPath("/resources/file/notice/"+n_num);
	File folder = new File(path);
	try {
	    while(folder.exists()) {
		File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
				
		for (int j = 0; j < folder_list.length; j++) {
			folder_list[j].delete(); //파일 삭제 
			System.out.println("파일이 삭제되었습니다.");
		}
		if(folder_list.length == 0 && folder.isDirectory()){ 
			folder.delete(); //대상폴더 삭제
			System.out.println("폴더가 삭제되었습니다.");
		}
            }
	 } catch (Exception e) {
		e.getStackTrace();
	}
	mapper.noticeFileDelete(n_num);
}
@Override
public int getNoticeNum() {
	return mapper.getNoticeNum();
}

@Override
public int noticeMaxnum() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public NoticeDTO getNotice() {
	// TODO Auto-generated method stub
	return null;
}

//@Override
//public void getAdminProductList(int pageNum,String keyword,String searchOpt,int cate1,int cate2,int cate3,int pstatus,Model model) {
//	adminMap.clear();
//	adminMap.put("keyword", keyword);
//	adminMap.put("searchOpt", searchOpt);
//	adminMap.put("cate1", cate1);
//	adminMap.put("cate2", cate2);
//	adminMap.put("cate3", cate3);
//	 int count=0;
//	    int pageSize = 10;
//	    int startRow = (pageNum - 1) * pageSize + 1;
//	    int endRow = pageNum * pageSize;
//	    List<AdminProductDTO> list = Collections.EMPTY_LIST;
//	       count = mapper.adminProductCount(adminMap);
//	       if (count > 0) {
//	          adminMap.put("start", startRow);
//	          adminMap.put("end", endRow);
//	          list = mapper.adminProductList(adminMap);
//	       }
//	       model.addAttribute("count", count);
//	       model.addAttribute("pageNum", pageNum);
//	       model.addAttribute("pageSize", pageSize);
//
//	       // page
//	       int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
//	       int startPage = (int) (pageNum / 10) * 10 + 1;
//	       int pageBlock = 10;
//	       int endPage = startPage + pageBlock - 1;
//	       if (endPage > pageCount) {
//	          endPage = pageCount;
//	       }
//	       model.addAttribute("keyword", keyword);
//	       model.addAttribute("searchOpt", searchOpt);
//	       model.addAttribute("cate1", cate1);
//	       model.addAttribute("cate2", cate2);
//	       model.addAttribute("cate3", cate3);
//	       model.addAttribute("list", list);
//	       model.addAttribute("pageCount", pageCount);
//	       model.addAttribute("startPage", startPage);
//	       model.addAttribute("pageBlock", pageBlock);
//	       model.addAttribute("endPage", endPage);
//	       
//	}

		
@Override
public String getSearchProductList(int pageNum, String keyword, String searchOpt, int cate1, int cate2,int cate3,int pstatus,Model model) {
	JsonObject jsonObject = new JsonObject();
	adminMap.clear();
	if(keyword.equals("550e8400-e29b-41d4-a716-446655440000")) {
		keyword="";
	}
	adminMap.put("keyword", keyword);
	adminMap.put("searchOpt", searchOpt);
	adminMap.put("cate1", cate1);
	adminMap.put("cate2", cate2);
	adminMap.put("cate3", cate3);
	adminMap.put("pstatus", pstatus);
	 int count=0;
	    int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    List<AdminProductDTO> list = Collections.EMPTY_LIST;
	       count = mapper.adminProductCount(adminMap);
	       if (count > 0) {
	          adminMap.put("start", startRow);
	          adminMap.put("end", endRow);
	          list = mapper.adminProductList(adminMap);
	       }
	       if(keyword.length()<1) {
	    	   keyword="550e8400-e29b-41d4-a716-446655440000";
	       }
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
	       JsonObject pageData = new JsonObject();
	       pageData.addProperty("pageNum", pageNum);
	       pageData.addProperty("pageCount", pageCount);
	       pageData.addProperty("startPage", startPage);
	       pageData.addProperty("endPage", endPage);
	       pageData.addProperty("keyword", keyword);
	       pageData.addProperty("searchOpt", searchOpt);
	       pageData.addProperty("cate1", cate1);
	       pageData.addProperty("cate2", cate2);
	       pageData.addProperty("cate3", cate3);
	       pageData.addProperty("pstatus", pstatus);
	       jsonObject.add("pageData", pageData);
	       jsonObject.add("products", new Gson().toJsonTree(list));
	       return jsonObject.toString();
}
@Override
public Date calculateTargetDate(Date currentDate, int check) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(currentDate);
    cal.add(Calendar.MONTH, check);
    return cal.getTime();
}

@Override
public void StopProduct(int p_num, String memo) {
	adminMap.clear();
	adminMap.put("memo", memo);
	adminMap.put("pd_p_num", p_num);
	mapper.pdstatChange(adminMap);
}

@Override
public void ReleaseIssue(int p_num) {
	mapper.releaseIssue(p_num);
}

@Override
public String uploadReportImageFile(MultipartFile multipartFile, HttpServletRequest request) {
	
	JsonObject jsonObject = new JsonObject();
	ArrayList<String> fname=new ArrayList<String>();
	
	System.out.println("imgcnt=====updateimg lastnum" + imgcnt);
	if(imgcnt==0) {
		mapper.QnAnextval();
	}
	int ma_num = mapper.getQnAnumber();
	adminMap.put("ma_num", ma_num);
	
	String path = request.getServletContext().getRealPath("/resources/file/QnA/"+ma_num+"/");
	
	String originalFileName = multipartFile.getOriginalFilename();
	String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
	
	String savedFileName = "notice_"+ma_num+"_"+(++imgcnt)+extension;
	
	adminoldname.add(savedFileName);

	File targetFile = new File(path + savedFileName);
	
	try {
		if(!targetFile.exists()) {
			targetFile.mkdir();
		}
		multipartFile.transferTo(targetFile);
		jsonObject.addProperty("url", "/resources/file/notice/"+ma_num+"/"+ savedFileName);
		jsonObject.addProperty("responseCode", "success");
	} catch (IOException e) {
		jsonObject.addProperty("responseCode", "error");
		e.printStackTrace();
	}
		String a = jsonObject.toString();
	return a;
}

@Override
public int reportReg(HttpServletRequest req, HttpServletResponse resp, Model model, QnADTO dto) {
	HttpSession session = req.getSession();
    // 占쌜쇽옙占쏙옙 占쌉시뱄옙 占쏙옙 占쏙옙占쏙옙 占쌍깍옙 占쌉시뱄옙 占쏙옙占쏙옙占쏙옙占쏙옙
	 int ma_num=(Integer)(adminMap.get("ma_num"));
	 String realpath = req.getServletContext().getRealPath("/resources/file/QnA/"+ma_num+"/");
    for(int i=0;i<adminoldname.size();i++) {
   	 if(!dto.getMa_content().contains(adminoldname.get(i))) {
   		 File f = new File(realpath+adminoldname.get(i));
   		 if(f.exists()) {
   			 f.delete();
   		 }
   	 }else {
   		 noticefiledto.setNf_filename(adminoldname.get(i));
   		 noticefiledto.setNf_n_num(ma_num);
   		 mapper.ReportFileUpload(noticefiledto);
   	 }
    }
    dto.setMa_num(ma_num);
    mapper.reportReg(dto);
    imgcnt=0;
   adminoldname.clear();
   adminMap.clear();
	return 0;
}
@Scheduled(cron = "0 0 0 1 * *")
public void autoReckonUpdate() {
	try {
	SimpleDateFormat sf = new SimpleDateFormat("yyyyMM",Locale.KOREAN);
	Calendar cal = Calendar.getInstance();
	Date sMonth = sf.parse("yyyyMM");
	cal.setTime(sMonth);
	cal.add(Calendar.MONTH,-1);
	String beforeMonth = sf.format(cal.getTime());
	}catch (ParseException e) {
		e.printStackTrace();
	}
	
}

	


}