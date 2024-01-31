package com.gogi.meatyou.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;











public class CustomLoginHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomLoginHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//Ä«Ä«¿À·Î±×ÀÎ¶§¸Å ³ÖÀº°Å  ½ÃÀÛ(1)
		logger.info("=========CustomLoginHandler=========");
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		logger.info("========="+authorities+"=========");
		//Ä«Ä«¿À·Î±×ÀÎ¶§¸Å ³ÖÀº°Å  ²ôÀÄ (1)
		
		List<String> roleNames = new ArrayList<String>();
		authentication.getAuthorities().forEach(au ->{
			roleNames.add(au.getAuthority());
		});
		
		/*   ï¿½ï¿½ï¿½ï¿½ï¿½Ö´ï¿½ï¿½ï¿½
		String  rdir = "/main/main";
		if(roleNames.contains("ROLE_ADMIN")) {
			rdir = "/admin/main";
		}else if(roleNames.contains("ROLE_MEMBER")) {
			rdir="/main/main";
		}
		response.sendRedirect(rdir);
	}*/
		
//	   2023-12-16	ï¿½ï¿½ï¿½ï¿½ 
		String  rdir = "/main/main";
		if(roleNames.contains("ROLE_ADMIN")) {
			rdir = "/admin/main";
		}else if(roleNames.contains("ROLE_MEMBER")) {
			rdir="/member/member";
		}else if(roleNames.contains("ROLE_SALLER")) {
			rdir="/member/saller";
		}
		response.sendRedirect(rdir);
	}
		
		

}