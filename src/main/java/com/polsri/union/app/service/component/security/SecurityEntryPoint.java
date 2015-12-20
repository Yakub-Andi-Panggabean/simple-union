package com.polsri.union.app.service.component.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class SecurityEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationExeption) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setHeader("WWW-Authenticate", "FormBased");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("Application/json");
		response.getWriter()
				.println("{\"status\":\"" + HttpServletResponse.SC_UNAUTHORIZED + "\",\"message\":\"Unauthorized\"}");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("yakub_macho");
		super.afterPropertiesSet();
	}

}
