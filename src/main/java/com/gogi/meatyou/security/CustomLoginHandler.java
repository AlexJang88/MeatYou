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
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomLoginHandler implements AuthenticationSuccessHandler {

private static final Logger logger = LoggerFactory.getLogger(CustomLoginHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();  // 占쏙옙占쏙옙占� 占싱몌옙 占쏙옙占쏙옙占쏙옙占쏙옙

        String rdir = determineTargetUrl(authentication);  // 占쏙옙占쏙옙占싱뤄옙트 占쌍쇽옙 占쏙옙占쏙옙

        response.sendRedirect(rdir);
    }

    private String determineTargetUrl(Authentication authentication) {
        if (hasRole(authentication, "ROLE_ADMIN")) {
            return "/admin/main";
        } else if (hasRole(authentication, "ROLE_MEMBER")) {
            String username = authentication.getName();
            return "/main/main" ;
        } else if (hasRole(authentication, "ROLE_SELLER")) {
        	return "/customers/customer";
        } else {
            return "/main/main";
        }
    }

    private boolean hasRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }
}