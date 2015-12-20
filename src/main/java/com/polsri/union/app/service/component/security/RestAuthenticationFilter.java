package com.polsri.union.app.service.component.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

public class RestAuthenticationFilter extends GenericFilterBean {

	private AuthenticationManager authenticationManager;
	private AuthenticationEntryPoint authenticationEntryPoint;

	public RestAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = new SecurityEntryPoint();
	}

	public RestAuthenticationFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String authorization = req.getHeader("Authorization");

		try {
			System.out.println("uri --> " + req.getRequestURI());
			if (req.getRequestURI().contains("api")) {
				if (authorization == null) {
					res.setStatus(HttpServletResponse.SC_FORBIDDEN);
					res.setContentType("Application/json");
					res.getWriter().println(
							"{\"status\":\"" + HttpServletResponse.SC_FORBIDDEN + "\",\"message\":\"Forbidden\"}");
					return;
				}
				String[] credentials = decodeHeader(authorization);
				assert credentials.length == 2;
				Authentication authentication = new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);
				// Request the authentication manager to authenticate the token
				Authentication successfulAuthentication = authenticationManager.authenticate(authentication);
				// Pass the successful token to the SecurityHolder where it can
				// be
				// retrieved by this thread at any stage.
				SecurityContextHolder.getContext().setAuthentication(successfulAuthentication);
				// Continue with the Filters
			}
			chain.doFilter(request, response);
		} catch (AuthenticationException authenticationException) {
			// If it fails clear this threads context and kick off the
			// authentication entry point process.
			SecurityContextHolder.clearContext();
			authenticationEntryPoint.commence(req, res, authenticationException);
		}
	}

	public String[] decodeHeader(String authorization) {
		// Decode the Auth Header to get the username and password
		try {
			byte[] decoded = Base64.decode(authorization.substring(6).getBytes("UTF-8"));
			String credentials = new String(decoded);
			return credentials.split(":");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

}
