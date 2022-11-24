package com.ttps.spring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.ttps.spring.service.TokenService;

@WebFilter(filterName="jwt-auth-filter", urlPatterns = "/api/*")
public class JWTFilter implements Filter {
	private FilterConfig filterConfig;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		HttpServletRequest req = (HttpServletRequest) request;
		if (HttpMethod.OPTIONS.matches(req.getMethod())) {
	         chain.doFilter(request, response);
	         return;
		}
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (token == null || (! TokenService.validar(token))) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.setStatus(HttpStatus.FORBIDDEN.value());
			return;
		}
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}
}
