package com.example.studyroom.common;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		try {
			String auth = request.getHeader("Authorization");
			if (auth.contains("admin")) {
				request.setAttribute("Id", 0);
				request.setAttribute("Role", "ROLE_ADMIN");
			} else {
				String substring = auth.substring(12, auth.length()-1);
				request.setAttribute("Id", substring);
				request.setAttribute("Role", "ROLE_USER");
			}
			filterChain.doFilter(request,response);
		} catch (Exception e) {
			log.warn("에러 발생: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "정상적인 토큰이 아닙니다");
		}
	}
}
