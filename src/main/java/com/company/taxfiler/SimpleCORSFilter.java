package com.company.taxfiler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleCORSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		StringBuffer requestUrl = request.getRequestURL();
		if (request.getQueryString() != null) {
			requestUrl.append(request.getQueryString());
		}

		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		
		String access = request.getHeader("Access-Control-Request-Headers");
		if (access == null) {
			access = "default";
		}
		response.setHeader("Access-Control-Allow-Headers", access);
		
		//added below recently
		response.setHeader("Accept", "*/*");
		response.setHeader("Content-Type", "*/*");
		response.setHeader("X-Requested-With", request.getHeader("X-Requested-With"));
		response.setHeader("Access-Control-Expose-Headers", "*");

		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}

		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

}
