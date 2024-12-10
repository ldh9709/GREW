package com.itwill.jpa.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.nimbusds.jose.shaded.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class APILoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

	    Gson gson = new Gson();
	    
	    String jsonStr = gson.toJson(Map.of("error", "ERROR_LOGIN"));

	    response.setContentType("application/json");
	    PrintWriter printWriter = response.getWriter();
	    printWriter.println(jsonStr);
	    System.out.println("대실패");
	    printWriter.close();   
	}

}
