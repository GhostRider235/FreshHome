package com.FreshHome.app.utilities;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
public class SuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> autoridades = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		if (autoridades.contains("ROLE_EMPLOYEE")) {
			response.sendRedirect(request.getContextPath()+"/app/employee");
		} else if (autoridades.contains("ROLE_CLIENT")) {
			response.sendRedirect(request.getContextPath()+"/app/client");
		} else if (autoridades.contains("ROLE_ADMIN")) {
			response.sendRedirect(request.getContextPath()+"/admin");
		}
		
	}

}
