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
		
		System.out.println(">>>>> Authorization Code: " + request.getParameter("code"));
		System.out.println(">>>>> Authentication Exception Message: " + exception.getMessage());
		System.out.println(">>>>> Authentication Exception Cause: " + exception.getCause());

		System.out.println(">>>>> Request URI: " + request.getRequestURI());
		System.out.println(">>>>> Request Method: " + request.getMethod());
		System.out.println(">>>>> Client IP: " + request.getRemoteAddr());
		System.out.println(">>>>> Request Parameters: " + request.getParameterMap());
		
		request.getHeaderNames().asIterator().forEachRemaining(header -> {
		    System.out.println(">>>>> Header: " + header + " = " + request.getHeader(header));
		});
		
	    Gson gson = new Gson();
	    
	    String jsonStr = gson.toJson(Map.of("error", "ERROR_LOGIN"));

	    response.setContentType("application/json");
	    PrintWriter printWriter = response.getWriter();
	    printWriter.println(jsonStr);
	    System.out.println("대실패");
	    printWriter.close();   
	}

}
