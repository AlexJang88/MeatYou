package com.gogi.meatyou.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.repository.AdminMapper;
import com.gogi.meatyou.service.AdminService;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
	private AdminService adminServicImpl;

	@Autowired
	private HashMap adminMap;

	@RequestMapping("/chart")
	public String chart(Principal pc,Model model) {
		String sid = pc.getName();
		model.addAttribute("sid", sid);
		return "admin/chart";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/main")
	public String main() {
		return "admin/main";
	}

	@RequestMapping("/customLogin")
	public String login() {
		return "main/login";
	}

	@RequestMapping("/accessError")
	public String accessError(Authentication auth) {
		return "Error";
	}

	@RequestMapping("/memberlist")
	public String memberlist(int check, Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		adminServicImpl.memberList(check, model, pageNum);
		return "admin/memberList";
	}

	@RequestMapping("/statChange")
	public String statChange(int check, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model,
			MemberDTO dto) {
		adminServicImpl.statChange(dto);

		return "redirect:/admin/memberlist?check=" + check + "&pageNum=" + pageNum;
	}

	@RequestMapping("/test")
	public String test(Model model) {
		MemberDTO mem = adminServicImpl.test("test");
		model.addAttribute("mem", mem);
		return "test";
	}

	@RequestMapping("/apiTest")
	public String apiTest(Model model) {
		adminServicImpl.apiTest(model);
		return "admin/apiTest";
	}

	@RequestMapping("/sales")
	public String sales(Model model, @RequestParam(value = "check", defaultValue = "0") int check, String daterange) {
		if (check <= 0) {
			adminServicImpl.getSales(model, check);
		} else {
			String start = daterange.substring(0, 10);
			String end = daterange.substring(13, 23);
			adminServicImpl.getCheckSalse(model, check, start, end);
		}
		return "admin/sales";
	}

	@RequestMapping("/reckon")
	public String reckon(Model model, @RequestParam(value = "start", defaultValue = "2023") String start,
			@RequestParam(value = "end", defaultValue = "12") String end,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

		adminServicImpl.getReckon(model, pageNum, start, end);

		return "admin/reckon";
	}

	@RequestMapping("/noticeList")
	public String notice(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
			adminServicImpl.noticeList(model, pageNum);
		return "admin/noticeList";
	};

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/noticeForm")
	public String noticeForm() {
		return "admin/noticeForm";
	}

	@RequestMapping("/noticePro")
	public String noticePro(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request,
			String content) {
		String name = multipartFile.getOriginalFilename();

		return "admin/noticeForm";
	}

//	@RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
//	@ResponseBody
//	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
//			HttpServletRequest request) {
//		return adminServicImpl.uploadSummerImgFile(multipartFile, request);
//	}

//	@RequestMapping("/reg")
//	public String reg(HttpServletRequest req, HttpServletResponse resp, NoticeDTO dto, Model model) {
//		adminServicImpl.noticeReg(req, resp, model, dto);
//		
//		return "redirect:/admin/noticeList";
//	}
	
	@RequestMapping("/noticeContent")
	public String noticeContent(Model model,NoticeDTO dto,NoticeFileDTO fdto) {
		adminServicImpl.getNoticeContent(model,dto,fdto);
		
		return "admin/noticeContent";
	}
	@RequestMapping("/noticeupdateForm")
	public String noticeupdateForm(Model model,NoticeDTO dto,NoticeFileDTO fdto) {
		adminServicImpl.getNoticeContent(model,dto,fdto);
		
		return "admin/noticeupdateForm";
	}
	@RequestMapping("/noticeupdate")
	public String noticeupdate(HttpServletRequest req, HttpServletResponse resp, Model model, NoticeDTO dto) {
		adminServicImpl.noticeupdate(req, resp, model, dto);
		
		return "admin/noticeupdateForm";
	}
	
	@RequestMapping("/noticedelete")
	public String noticedelete(int n_num) {
		adminServicImpl.noticedelete(n_num);
		return "redirect:/admin/noticeList";
	}
	@RequestMapping(value = "/updateSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String updateSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request,int n_num) {
		System.out.println("====n_num : "+n_num);
		 int board_num=adminServicImpl.getNoticeNum()+1;
		return adminServicImpl.updateSummerImgFile(multipartFile, request,n_num);
	}
	//----------------------------------------------
	
	@RequestMapping("testform")
	public String testform(@RequestParam(value = "n_num", defaultValue = "0") int n_num,Model model) {
		model.addAttribute("n_num",n_num);
		return "admin/testform";
	}
	// �Խñ� ���ε� ��û ����
    @RequestMapping("reg")
    public String reg(HttpServletRequest req, HttpServletResponse resp, String content, Model model) {
        HttpSession session = req.getSession();
        int board_num=adminServicImpl.getNoticeNum()+1;
        if (session.getAttribute("true") != null) {
            String realPath = req.getServletContext().getRealPath("/resources/file");
        	// ���� temp������ ����� �̹��� ǥ�ø� ���� �����Ϳ��� /temp�� ��ΰ� �����Ǿ� �ִ� 
            // �̸� ������ �Խñ� ���� ��ȣ�� �����Ѵ�.
            
            content = content.replaceAll("/temp", "/" + board_num);

            // temp ���� ���� �̹����� �Խñ� ����ҷ� �̵�
            String path_folder1 = realPath + "/temp/";
            String path_folder2 = realPath + "/" + board_num + "/";

            // ���� ���� �Լ�
            fileUpload(path_folder1, path_folder2);

            
        }
        return "admin/noticeList";
    }

    // �Խñ� ���� ������ ��û
    @RequestMapping("update")
    public String update(HttpServletRequest req, Model model, String num) {
        HttpSession session = req.getSession();
        String realPath = req.getServletContext().getRealPath("/resources/file");
        // ...

        // ���� �̹��� ������ ���� ���� temp ������ ����ش�.
        String path = realPath + "/temp/";

        // ���� ���� ���� ���� �Լ�
        deleteFolder(path);

        // �Խñ� ����ҿ� �ִ� ���ϵ��� temp ���Ϸ� ���ε� 
        // �Խñ� ������ �������� ������ ������� ���� �Խñ��� �̹����� �����ȴ�.
        String path_folder1 = realPath + "/" +num + "/";
        String path_folder2 = realPath + "/temp/";

        // temp�� �ӽ�����
        fileUpload(path_folder1, path_folder2);
        NoticeDTO board = adminServicImpl.getNotice();
        // ���ۿ��ִ� �̹��� ��θ� temp�� �ٲ��ش�
        board.setN_content(board.getN_content().replaceAll("/" +num + "/", "/temp/"));
        model.addAttribute("board", board);
        return "admin/noticeList";
    }

    @RequestMapping("del_update")
    public String del_update(HttpServletRequest req, String num, String doit, String title, String content, String description,
            String category, MultipartFile thumbnail) {

        // ...
    	String realPath = req.getServletContext().getRealPath("/resources/file");
        // �������뿡 temp ������ �ٲ� �̹��� ��θ� ������� ���� 
        content = content.replaceAll("/temp", "/" + num);

        // ������ �ȵ� ���ϵ� ����(temp ����)
        String filePath = realPath + "/temp/";

        // ���� ���� �����Լ� �Ű����� : ���� ���, ���� ���, �˻��� ����
        removeDummyFiles(getFileNamesFromFolder(filePath), filePath, content);

        // ������ ���� ����
        filePath = realPath + "/" + num + "/";
        for (String fileName : getFileNamesFromFolder(filePath)) {
            deleteFile(filePath, fileName);
        }

        // temp ���� ����� �����͵� ���ε�
        String path_folder1 = realPath + "/temp/";
        String path_folder2 = realPath + "/" + num + "/";

        fileUpload(path_folder1, path_folder2);

        return "admin/noticeList";
    }

    // ���ӳ�Ʈ �̹��� ���ε� temp ����
    @RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
    @ResponseBody
    public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
            HttpServletRequest request) {
        // JSON ��ü ����
        JsonObject jsonObject = new JsonObject();
        String realPath =request.getServletContext().getRealPath("/resources/file");
        // �̹��� ������ ����� ��� ����
        String contextRoot = realPath + "/temp/";
        String fileRoot = contextRoot;

        // ���ε�� ������ ���� ���ϸ�� Ȯ���� ����
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // ���ο� ���ϸ� ���� (������ �ĺ��� + Ȯ����)
        String savedFileName = UUID.randomUUID() + extension;

        // ����� ������ ��ο� ���ϸ��� ��Ÿ���� File ��ü ����
        File targetFile = new File(fileRoot + savedFileName);

        try {
            // ���ε�� ������ InputStream ���
            java.io.InputStream fileStream = multipartFile.getInputStream();

            // ���ε�� ������ ������ ��ο� ����
            FileUtils.copyInputStreamToFile(fileStream, targetFile);

            // JSON ��ü�� �̹��� URL�� ���� �ڵ� �߰�
            jsonObject.addProperty("url", "/resources/temp/" + savedFileName);
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

    // ���ӳ�Ʈ �̹��� ���� temp����
    @RequestMapping(value = "/deleteSummernoteImageFile", produces = "application/json; charset=utf8")
    @ResponseBody
    public void deleteSummernoteImageFile(@RequestParam("file") String fileName,HttpServletRequest req) {
    	
        // ���� ��ġ
        String filePath = req.getServletContext().getRealPath("/resources/temp");

        // �ش� ���� ����
        deleteFile(filePath, fileName);
    }

    // ���� ���� ����
    private void fileUpload(String path_folder1, String path_folder2) {
        // path_folder1���� path_folder2�� ������ �����ϴ� �Լ��Դϴ�.

        File folder1;
        File folder2;
        folder1 = new File(path_folder1);
        folder2 = new File(path_folder2);

        // ������ �������� �������� ������ �����մϴ�.
        if (!folder1.exists())
            folder1.mkdirs();
        if (!folder2.exists())
            folder2.mkdirs();

        // ����1���� ���� ����� �����ɴϴ�.
        File[] target_file = folder1.listFiles();
        for (File file : target_file) {
            // ���� ��� ������ ��ο� �̸��� �����մϴ�.
            File temp = new File(folder2.getAbsolutePath() + File.separator + file.getName());

            if (file.isDirectory()) {
                // ����� ������ ���, �ش� ������ �����մϴ�.
                temp.mkdir();
            } else {
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                    // ���� ���縦 ���� FileInputStream�� FileOutputStream�� �����մϴ�.
                    fis = new FileInputStream(file);
                    fos = new FileOutputStream(temp);

                    byte[] b = new byte[4096];
                    int cnt = 0;
                    while ((cnt = fis.read(b)) != -1) {
                        // ���۸� ����Ͽ� ���� ������ �а� �����մϴ�.
                        fos.write(b, 0, cnt);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // FileInputStream�� FileOutputStream�� �ݽ��ϴ�.
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

    // ���� ���� ����
    private void deleteFolder(String path) {
        // �־��� ��ο� �ִ� ������ ������ ��������� �����ϴ� �Լ��Դϴ�.

        File folder = new File(path);
        try {
            if (folder.exists()) {
                File[] folder_list = folder.listFiles();
                for (int i = 0; i < folder_list.length; i++) {
                    if (folder_list[i].isFile())
                        // ������ ���, ������ �����մϴ�.
                        folder_list[i].delete();
                    else
                        // ������ ���, ��������� ���� ������ ���� �� ������ �����մϴ�.
                        deleteFolder(folder_list[i].getPath());
                    // �����̳� ������ �����մϴ�.
                    folder_list[i].delete();
                }
                // ������ �����մϴ�.
                folder.delete();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // ��ġ������ ���� ���� �̸� ��������
    private List<String> getFileNamesFromFolder(String folderName) {
        // ���� �̸��� ������ ����Ʈ ����
        List<String> fileNames = new ArrayList<>();

        // �־��� ���� ��θ� ������� ���� ��ü ����
        File folder = new File(folderName);
        // ���� ���� ���ϵ��� ������
        File[] files = folder.listFiles();
        if (files != null) {
            // ������ ��� ���� �̸��� ����Ʈ�� �߰�
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        // ���� �̸��� ���� ����Ʈ ��ȯ
        return fileNames;
    }

    // ���� ���� ����
    private void removeDummyFiles(List<String> fileNames, String filePath, String contents) {
        // �־��� ���� �̸� ����Ʈ�� ������� ������ ����
        for (String fileName : fileNames) {
            // contents ���ڿ��� ���� �̸��� ���ԵǾ� ���� ���� ��� ���� ����
            if (!contents.contains(fileName)) {
                deleteFile(filePath, fileName);
            }
        }
    }

    // ���� �ϳ� ����
    private void deleteFile(String filePath, String fileName) {
        // �־��� ���� ��ο� �̸��� ������� ���� ��� ��ü ����
        Path path = Paths.get(filePath, fileName);
        try {
            // ���� ����
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
