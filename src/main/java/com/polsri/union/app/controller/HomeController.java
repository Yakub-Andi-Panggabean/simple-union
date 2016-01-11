package com.polsri.union.app.controller;

import com.polsri.union.app.util.credential.CredentialsInspector;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = HomeController.BASE_PATH)
public class HomeController {

	public static final String BASE_PATH = "/";
	public static final String BASE_PATH_HOME = BASE_PATH + "home";
        public static final String BASE_PATH_CHECK = BASE_PATH + "check";

	@RequestMapping(method = RequestMethod.GET)
	public String showIndex(Model model) {
		return "index";
	}

	@RequestMapping(value = BASE_PATH_HOME, method = RequestMethod.GET)
	public String showHomepage(Model model) {
		return "home";
	} 
        
        @RequestMapping(value = BASE_PATH_CHECK,method = RequestMethod.GET)
        @ResponseBody
        public String checkRequestHolder(HttpServletRequest request,HttpServletResponse response){
            String req=request.getHeader("Authorization");
            String json="{\"auth\":\""+CredentialsInspector.decodeBasicAuthToken(req)+"\"}";
            return json;
        }
}
