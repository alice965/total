package org.itbank.app.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
@SuppressWarnings("serial")
public class LoginAuthFilter extends HttpFilter  {
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request.getSession().getAttribute("auth") == null) {
			String uri = request.getRequestURI();
			if(uri.startsWith("/my") ) {
				String red= request.getRequestURI();
				if(request.getQueryString()!=null) {
					red +="?"+request.getQueryString();
				}
				response.sendRedirect("/login?redirect="+red);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
		
	}

}
