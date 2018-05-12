package br.com.neja.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class HeaderExposureFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletResponse res =(HttpServletResponse)response;
		res.addHeader("access-control-expose-headers", "location");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	
	
	
}
