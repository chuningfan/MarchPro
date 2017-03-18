package org.march.authentication.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.march.authentication.constract.ValidationConstract;
import org.march.authentication.token.TokenHelper;
import org.march.authentication.utils.WebUtils;

public class SessionFilter implements Filter{

	private static String SERVER_PATH;
	
	public void destroy() {
		System.out.println("filter has been destroyed!");
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		HttpSession session = req.getSession();
		String URI = req.getRequestURI();
		if(URI.equalsIgnoreCase(SERVER_PATH + ValidationConstract.LOGIN_PAGE) || URI.equalsIgnoreCase(SERVER_PATH + ValidationConstract.LOGIN_PATTERN)){
			if(!WebUtils.isRemembered(req)){
				chain.doFilter(req, resp);
			}else{
				processRemember(req, resp);
			}
		}else{
			String loginName = (String) session.getAttribute(ValidationConstract.LOGIN_NAME);
			String token = (String) session.getAttribute(ValidationConstract.USER_TOKEN);
			if(token != null && TokenHelper.check(loginName, token)){
				if("GET".equalsIgnoreCase(req.getMethod())){
					String forwardURI = URI.replace(SERVER_PATH, "");
					resp.addCookie(new Cookie(ValidationConstract.PREVIOUS_URI, forwardURI));
				}
				chain.doFilter(req, resp);
			}else{
				if(!WebUtils.isRemembered(req)){
					resp.sendRedirect(SERVER_PATH + ValidationConstract.LOGIN_PAGE);
				}else{
					processRemember(req, resp);
				}
			}
		}
		
	}

	public void init(FilterConfig config) throws ServletException {
		SERVER_PATH = config.getServletContext().getContextPath();
	}
	
	
	private void processRemember(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.getRequestDispatcher(ValidationConstract.LOGIN_PATTERN).forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
