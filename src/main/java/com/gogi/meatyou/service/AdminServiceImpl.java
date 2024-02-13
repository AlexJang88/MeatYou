package com.gogi.meatyou.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import java.util.UUID;
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
import com.gogi.meatyou.bean.ChartDTO;
import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.DiseaseDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.bean.QnADTO;
import com.gogi.meatyou.bean.ReckonDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.gogi.meatyou.repository.AdminMapper;
import com.gogi.meatyou.repository.CustomersMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class AdminServiceImpl implements AdminService {


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
	
	@Autowired CustomersMapper cusmapper;

	private HttpURLConnection conn;

	public void memberList(int check, Model model, int pageNum) {
		int count = 0;
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

		} else if (check == 3) {
			count = mapper.cusWaitCount();

			if (count > 0) {
				adminMap.put("start", startRow);
				adminMap.put("end", endRow);
				list = mapper.cusWaitList(adminMap);
			}

		} else if (check == 4) {
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
   public void test() {
	   List<CouponDTO> goodList = getCouponMember(1002, 5000);
		mapper.autoCoupon(goodList);
   }

	/*
	 * @Scheduled(cron="* * * * * *") �뜝�룞�삕 �뜝�룞�삕 �뜝�떆怨ㅼ삕 �뜝�룞�삕 �뜝�룞�삕
	 * �뜝�룞�삕�뜝�룞�삕
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
	
	@Scheduled(cron = "0 0 0 1-31/14 * MON")
	public void autoCoupon() {
		List<CouponDTO> goodList = getCouponMember(1002, 5000);
		List<CouponDTO> bestList = getCouponMember(1003, 10000);
		mapper.autoCoupon(goodList);
		mapper.autoCoupon(bestList);
		
	}
	@Override
	public List<CouponDTO> getCouponMember(int m_status, int cp_price) {
		adminMap.put("m_status", m_status);
		adminMap.put("cp_price", cp_price);
		return mapper.getCouponMember(adminMap);
	}
	
	

	@Override
	public void statChange(MemberDTO dto) {
		mapper.statChange(dto);
	}

	@Override
	public void getSales(Model model, int check) {
		int productComm=mapper.getProductComm(check);
		int item=mapper.getPaidItem(check);
		int Adv=mapper.getPaidAdv(check);
		int coupon=mapper.getUsedCoupon(check);
		int total=productComm+item+Adv;
		int net_profit=total-coupon;
		
		
		model.addAttribute("productComm",productComm );
		model.addAttribute("item",item );
		model.addAttribute("Adv",Adv );
		model.addAttribute("coupon",coupon );
		model.addAttribute("total",total );
		model.addAttribute("net_profit", net_profit);
		
	}

	@Override
	public void getCheckSalse(Model model, int check, String start, String end) {
		String[] startarr = start.split("/");
		String[] endarr = end.split("/");
		start = startarr[2] + "-" + startarr[0] + "-" + startarr[1];
		end = endarr[2] + "-" + endarr[0] + "-" + endarr[1];
		adminMap.put("start", start);
		adminMap.put("end", end);
		model.addAttribute("ps", mapper.getCheckProductSalse(adminMap));
		model.addAttribute("pc", mapper.getCheckProductComm(adminMap));
		model.addAttribute("pi", mapper.getCheckPaidItem(adminMap));
		model.addAttribute("pa", mapper.getCheckPaidAdv(adminMap));
		model.addAttribute("uc", mapper.getCheckUsedCoupon(adminMap));
		model.addAttribute("pt",
				mapper.getCheckPaidAdv(adminMap) + mapper.getCheckPaidItem(adminMap)
						+ mapper.getCheckProductSalse(adminMap) + mapper.getCheckProductComm(adminMap)
						- mapper.getCheckUsedCoupon(adminMap));
		model.addAttribute("check", check);
	}

	@Override
	public void getReckon(Model model, int pageNum, String year, String month) {
		String CheckMonth = year + "-" + month;
		int count = 0;
		int pageSize = 10;
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		List<ReckonDTO> list = Collections.EMPTY_LIST;
		adminMap.put("CheckMonth", CheckMonth);
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
	public void noticeList(Model model, int pageNum) {
		int count = 0;
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
		System.out.println("====title" + list.get(0).getN_title());
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
	public String getSearchProductList(int pageNum, String keyword, String searchOpt, int cate1, int cate2, int cate3,
			int pstatus, Model model) {
		JsonObject jsonObject = new JsonObject();
		adminMap.clear();
		if (keyword.equals("550e8400-e29b-41d4-a716-446655440000")) {
			keyword = "";
		}
		adminMap.put("keyword", keyword);
		adminMap.put("searchOpt", searchOpt);
		adminMap.put("cate1", cate1);
		adminMap.put("cate2", cate2);
		adminMap.put("cate3", cate3);
		adminMap.put("pstatus", pstatus);
		int count = 0;
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
		if (keyword.length() < 1) {
			keyword = "550e8400-e29b-41d4-a716-446655440000";
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
	public void reportList(int pageNum, Model model,int check) {
		int count = 0;
		int pageSize = 10;
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		System.out.println("======check"+check);
		adminMap.put("check", check);
		List<QnADTO> list = Collections.EMPTY_LIST;
		count = mapper.reportCount(check);
		if (count > 0) {
			adminMap.put("start", startRow);
			adminMap.put("end", endRow);

			list = mapper.reportList(adminMap);
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
	public String uploadReportImageFile(MultipartFile multipartFile, String realPath) {

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
			jsonObject.addProperty("url", "/resources/file/report/temporary/" + savedFileName);
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
	public void reportReg(String realPath, Model model, QnADTO dto) {
// 기존 temp폴더에 저장된 이미지 표시를 위해 에디터에는 /temp로 경로가 지정되어 있다
		// 이를 마지막 게시글 다음 번호로 설정한다.
		int board_num = mapper.QnAnextval();
		System.out.println("===board_num");
		dto.setMa_content(dto.getMa_content().replaceAll("/temporary", "/" + board_num));

		// temp 폴더 안의 이미지를 게시글 저장소로 이동
		String path_folder1 = realPath + "/temporary/";
		String path_folder2 = realPath + "/" + board_num + "/";

		// 폴더 복사 함수
		fileUpload(path_folder1, path_folder2, board_num,40);
		deleteFolder(path_folder1);
		dto.setMa_num(board_num);
		mapper.reportReg(dto);
	}
	
	@Override
	public void reportContent(Model model, int num) {
		
		List<QnADTO> list = mapper.reportContent(num);
		model.addAttribute("reports", list);
		
	}
	@Override
	public void reportReply(QnADTO dto) {
			mapper.reportReply(dto);
			mapper.maStatChange(dto.getMa_num());
	}

	@Scheduled(cron = "0 0 0 1 * *")
	public void autoReckonUpdate() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String beforeMonth = sf.format(cal.getTime());
		beforeMonth = beforeMonth.substring(0, beforeMonth.lastIndexOf("-"));
		adminMap.put("CheckMonth", beforeMonth);
		List<ReckonDTO> dto = mapper.getReckon(adminMap);

	}
	@Scheduled(cron = "0 0 0 * * *")
	public void autoIssueCheck() {
		List<Integer> issue= mapper.getDiseaseIssueNum();
		List<Integer> noissue = mapper.getCancleDIsuueNum();
		adminMap.put("status", 1);
		adminMap.put("list", issue);
		adminMap.put("memo", "방역 이슈");
		mapper.DiseaseCheck(adminMap);
		adminMap.put("status", -1);
		adminMap.put("list", noissue);
		adminMap.put("memo", "null");
		mapper.DiseaseCheck(adminMap);
	}

	private void fileUpload(String path_folder1, String path_folder2, int num,int category) {
		// path_folder1에서 path_folder2로 파일을 복사하는 함수입니다.
		System.out.println("fileUpload====");
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
				// 대상이 폴더인 경우, 해당 폴더를 생성합니다.
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
					noticefiledto = new NoticeFileDTO();
					if (num != 0) {
						noticefiledto.setNf_category(category);
						noticefiledto.setNf_n_num(num);
						noticefiledto.setNf_filename(file.getName());
						mapper.noticeFileUpload(noticefiledto);
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

// 위치값으로 내부 파일 이름 가져오기
	private List<String> getFileNamesFromFolder(String folderName) {
		// 파일 이름을 저장할 리스트 생성
		List<String> fileNames = new ArrayList<>();

		// 주어진 폴더 경로를 기반으로 폴더 객체 생성
		File folder = new File(folderName);
		// 폴더 내의 파일들을 가져옴
		File[] files = folder.listFiles();
		if (files != null) {
			// 파일인 경우 파일 이름을 리스트에 추가
			for (File file : files) {
				if (file.isFile()) {
					fileNames.add(file.getName());
				}
			}
		}
		// 파일 이름을 담은 리스트 반환
		return fileNames;
	}

// 더미 파일 삭제
	private void removeDummyFiles(List<String> fileNames, String filePath, String contents) {
		// 주어진 파일 이름 리스트를 기반으로 파일을 삭제
		for (String fileName : fileNames) {
			// contents 문자열에 파일 이름이 포함되어 있지 않은 경우 파일 삭제
			if (!contents.contains(fileName)) {
				deleteFile(filePath, fileName);
				mapper.noticeUnitFileDelete(fileName);
			}
		}
	}

// 파일 하나 삭제
	private void deleteFile(String filePath, String fileName) {
		// 주어진 파일 경로와 이름을 기반으로 파일 경로 객체 생성
		Path path = Paths.get(filePath, fileName);
		try {
			// 파일 삭제
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int noticeReg(String realPath, NoticeDTO dto) {
// 기존 temp폴더에 저장된 이미지 표시를 위해 에디터에는 /temp로 경로가 지정되어 있다
		// 이를 마지막 게시글 다음 번호로 설정한다.
		int board_num = mapper.getNoticeNum();
		System.out.println("===board_num");
		dto.setN_content(dto.getN_content().replaceAll("/temporary", "/" + board_num));

		// temp 폴더 안의 이미지를 게시글 저장소로 이동
		String path_folder1 = realPath + "/temporary/";
		String path_folder2 = realPath + "/" + board_num + "/";

		// 폴더 복사 함수
		fileUpload(path_folder1, path_folder2, board_num,10);
		deleteFolder(path_folder1);
		dto.setN_num(board_num);
		mapper.noticeReg(dto);
		return 0;
	}

	@Override
	public void noticeUpdate(String realPath, int num, Model model) {

		// 폴더 내부 파일 삭제 함수
		deleteFolder(realPath + "temporary/");

		// 게시글 저장소에 있는 파일들을 temp 파일로 업로드
		// 게시글 수정중 변심으로 페이지 벗어놔도 원본 게시글의 이미지는 보존된다.
		String path_folder1 = realPath + num + "/";
		String path_folder2 = realPath + "temporary/";

		// temp로 임시저장
		fileUpload(path_folder1, path_folder2, 0,10);
		NoticeDTO board = mapper.noticeContent(num);
		// 본글에있던 이미지 경로를 temp로 바꿔준다
		board.setN_content(board.getN_content().replaceAll(num + "/", "temporary/"));
		model.addAttribute("dto", board);
		// ...
	}

	@Override
	public void noticeUpdateReg(String realPath, NoticeDTO dto) {
		dto.setN_content(dto.getN_content().replaceAll("temporary/", dto.getN_num() + "/"));

		// 본문에 안들어간 파일들 삭제(temp 폴더)
		String filePath = realPath + "temporary/";

		// 더미 파일 삭제함수 매개변수 : 파일 목록, 파일 경로, 검사할 본문
		removeDummyFiles(getFileNamesFromFolder(filePath), filePath, dto.getN_content());

		// 본글의 폴더 비우기
		filePath = realPath + dto.getN_num() + "/";
		for (String fileName : getFileNamesFromFolder(filePath)) {
			deleteFile(filePath, fileName);

			mapper.noticeFileDelete(dto.getN_num());
		}

		// temp 에서 저장된 데이터들 업로드
		String path_folder1 = realPath + "temporary/";
		String path_folder2 = realPath + dto.getN_num() + "/";

		fileUpload(path_folder1, path_folder2, dto.getN_num(),10);
		mapper.noticeUpdate(dto);
		deleteFolder(path_folder1);

	}

	@Override
	public String noticeImgUpload(MultipartFile multipartFile, String realPath) {
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
			jsonObject.addProperty("url", "/resources/file/notice/temporary/" + savedFileName);
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
	public void noticeImgDel(String fileName, String realPath) {

		// 폴더 위치
		String filePath = realPath + "temporary/";

		// 해당 파일 삭제
		deleteFile(filePath, fileName);
	}

	@Override
	public void noticeContent(Model model, int num) {
		NoticeDTO dto = mapper.noticeContent(num);
		model.addAttribute("dto", dto);

	}

	@Override
	public void noticeDelete(int num, String realPath) {
		mapper.noticeFileDelete(num);
		mapper.noticedelete(num);
		deleteFolder(realPath);

	}

	@Override
	public String getChartData(String period) {
		JsonObject jsonObject = new JsonObject();
		if(period==null || period.length()<1) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String beforeYear = sf.format(cal.getTime());
			beforeYear = beforeYear.substring(0, beforeYear.indexOf("-"));
			period=beforeYear;
		}
		period+="-01-01";	
			List<ChartDTO> list=Collections.EMPTY_LIST;

			ArrayList<Integer> net_profit = new ArrayList<Integer>();
			ArrayList<Integer> total_profit = new ArrayList<Integer>();
			ArrayList<Integer> co_pay = new ArrayList<Integer>();
			ArrayList<Integer> cp_price = new ArrayList<Integer>();
			 list= mapper.getChartData(period);
			for(ChartDTO dto : list) {
				net_profit.add(dto.getNet_profit());
				total_profit.add(dto.getTotal_profit());
				co_pay.add(dto.getCo_pay());
				cp_price.add(dto.getCp_price());
				
			}
			
			jsonObject.addProperty("getYear", period.substring(0,period.indexOf("-")));
			jsonObject.add("net_profit", new Gson().toJsonTree(net_profit));
			jsonObject.add("total_profit", new Gson().toJsonTree(total_profit));
			jsonObject.add("co_pay", new Gson().toJsonTree(co_pay));
			jsonObject.add("cp_price", new Gson().toJsonTree(cp_price));
			
		// JSON 객체를 문자열로 변환하여 반환
		String a = jsonObject.toString();
		return a;
	}
	
	@Scheduled(cron = "59 * * * * *")
	public void autoOrderConfirm() {
		cusmapper.AutoOrderConfirm();
	}
	
	@Override
	public String getPriceinfo() {
		String reqURL = "http://www.kamis.co.kr/service/price/xml.do";
		 String result = "";
		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("action=dailySalesList");
			
			sb.append("&p_cert_key=262ecfe0-6ae1-46c1-b97c-6988b68b30f2"); // REST_API키 본인이 발급받은 key 넣어주기
			sb.append("&p_cert_id=jaus0708@gamil.com"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

			sb.append("&p_returntype=json");
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
             String br_line = "";

             while ((br_line = br.readLine()) != null) {
                 result += new String(URLDecoder.decode(br_line, "UTF-8"));
             }
				br.close();
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void updatedi(DiseaseDTO dto) {
		mapper.diseaseAutoUpdate(dto);
		autoIssueCheck();
	}

	@Override
	public void apiTest(Model model) {
			
	}

	



	

	

}