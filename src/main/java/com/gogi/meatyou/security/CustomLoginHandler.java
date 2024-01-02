package com.gogi.meatyou.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		List<String> roleNames = new ArrayList<String>();
		//security-context.xml 설정된 USER -모든 권한 이름 가져오귀~
		authentication.getAuthorities().forEach(au ->{
			roleNames.add(au.getAuthority());
		});
		
		/*   원래있던거
		String  rdir = "/main/main";
		if(roleNames.contains("ROLE_ADMIN")) {
			rdir = "/admin/main";
		}else if(roleNames.contains("ROLE_MEMBER")) {
			rdir="/main/main";
		}
		response.sendRedirect(rdir);
	}*/
		
//	   2023-12-16	도준 
		String  rdir = "/member/all";
		if(roleNames.contains("ROLE_ADMIN")) {
			rdir = "/member/admin";
		}else if(roleNames.contains("ROLE_MEMBER")) {
			rdir="/member/member";
		}else if(roleNames.contains("ROLE_SALLER")) {
			rdir="/member/saller";
		}
		response.sendRedirect(rdir);
	}
		
		

}
