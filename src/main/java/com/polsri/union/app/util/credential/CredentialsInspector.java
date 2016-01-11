package com.polsri.union.app.util.credential;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;

public class CredentialsInspector {

	public static final String generatePassword(String password) throws Exception {
		return new BCryptPasswordEncoder().encode(password);
	}

	public static final boolean isPasswordMacthes(String password, String hashedPassword) throws Exception {
		return new BCryptPasswordEncoder().matches(password, hashedPassword);
	}
        
        public static final String decodeBasicAuthToken(String basicToken){
            String result="";
            if(basicToken!=null){
                if(basicToken.contains("Basic")){
                    basicToken=basicToken.replace("Basic", "").trim();
                    byte[] authToken=Base64.decode(basicToken.getBytes());
                    result=new String(authToken);
                }
            }
            return result;
        }
}
