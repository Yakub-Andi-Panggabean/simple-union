package com.polsri.union.app.service.component.security;

import com.polsri.union.app.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class RestAuthenticationProvider implements AuthenticationProvider {

        @Autowired
        private UserService service;
    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

		String credentials = (String) auth.getCredentials();
		String principal = (String) auth.getPrincipal();

		System.out.println("principal --- > " + principal);
		System.out.println("credentials --- > " + credentials);

		//boolean isAuthenticated = credentials.equals("password") && principal.equals("admin");
                boolean isAuthenticated=service.isValidUser(principal, credentials);
		System.out.println("authenticated --- >" + isAuthenticated);

		if (!isAuthenticated) {
			throw new BadCredentialsException("get out of here");
		}

		return getAuthenticatedUser(credentials, principal);
	}

	private Authentication getAuthenticatedUser(String credentials, String principal) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("HERO"));
		return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
