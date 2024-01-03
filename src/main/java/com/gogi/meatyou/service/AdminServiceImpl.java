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
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.repository.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private HashMap adminMap;

	@Autowired
	private AdminService adminServiceImpl;

//	@Override
//	public void list(int pageNum, Model model) {
//		
//
//	}

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
	 * @Scheduled(cron="* * * * * *") �� �� �ð� �� �� ����
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
		// urlBuilder.append("?API-KEY="); // API 키를 안전한 곳에서 불러오도록 수정하세요.
		  //  urlBuilder.append("&START_INDEX=1");
		  //  urlBuilder.append("&END_INDEX=10");
		  //  urlBuilder.append("&TYPE=json");

		    HttpURLConnection conn = null;
		    try {
		        URL url = new URL(urlBuilder.toString());
		        conn = (HttpURLConnection) url.openConnection();
		        // 연결 설정 (메서드, 헤더, 등)
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type","application/json");
		        // 연결하고 데이터를 읽습니다.
		        // 예: int responseCode = conn.getResponseCode();
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
		        // 추가적으로 응답을 처리하는 코드를 여기에 추가하세요.
		    } catch (MalformedURLException e) {
		        // URL 형식이 잘못된 경우의 예외 처리
		        e.printStackTrace();
		    } catch (IOException e) {
		        // 연결을 여는 과정에서 I/O 예외 처리
		        e.printStackTrace();
		    } finally {
		        if (conn != null) {
		            conn.disconnect();
		        }
		    }
		//HttpURLConnection conn = (HttpURLConnection)url.openConnection();     
		//	   conn.setRequestMethod("GET");     
		//	   conn.setRequestProperty("Content-type","application/json");      
		//	  System.out.println("Response code: " + conn.getResponseCode());    
		//	    BufferedReader rd;   
		//	     if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {      
		//		      rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));      
		//		  } else {        
		//			    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));    
		//			    }    
		//	    StringBuilder sb = new StringBuilder();      
		//	  String line;       
		//	 while ((line = rd.readLine()) != null) {     
		//		       sb.append(line);       
		//		 }       
		//	 rd.close();      
		//	  conn.disconnect();      
		//	  System.out.println(sb.toString());
		
		
	//	StringBuilder urlBuilder = new StringBuilder("http://211.237.50.150:7080/openapi/sample/xml/Grid_20220823000000000636_1/1/5"); /*URL*/        
	//	urlBuilder.append("?" + URLEncoder.encode("API-KEY","UTF-8") + "=1c9a14382163bb7dc822492a3dca9b9a8841b3782755afedd33d3b5879c98e94"); /*Service Key*/ 
	//	       urlBuilder.append("&" + URLEncoder.encode("START_INDEX","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/      
	//	  urlBuilder.append("&" + URLEncoder.encode("END_INDEX","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지 번호*/   
	//	     urlBuilder.append("&" + URLEncoder.encode("TYPE","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*측정소 이름*/      
	//	  HttpURLConnection conn = (HttpURLConnection) url.openConnection();     
	//	   conn.setRequestMethod("GET");     
	//	   conn.setRequestProperty("Content-type", "application/json");      
	//	  System.out.println("Response code: " + conn.getResponseCode());    
	//	    BufferedReader rd;   
	//	     if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {      
	//		      rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));      
	//		  } else {        
	//			    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));    
	//			    }    
	//	    StringBuilder sb = new StringBuilder();      
	//	  String line;       
	//	 while ((line = rd.readLine()) != null) {     
	//		       sb.append(line);       
	//		 }       
	//	 rd.close();      
	//	  conn.disconnect();      
	//	  System.out.println(sb.toString());
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


	

}
