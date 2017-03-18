package org.march.authentication.login;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.march.authentication.constract.ValidationConstract;
import org.march.authentication.dao.AuthenticationDao;
import org.march.authentication.token.MD5Utils;
import org.march.authentication.token.TokenHelper;
import org.march.authentication.utils.WebUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class LoginServlet extends HttpServlet {

	private AuthenticationDao authenticationDao;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6649293064045420713L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("internal invoke");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginName = "";
		String password = "";
		if(WebUtils.isRemembered(req)){
			loginName = WebUtils.getValueFromCookie(req, "rmbn");
			password = WebUtils.getValueFromCookie(req, "rmbp");
		}else{
			loginName = req.getParameter("loginName");
			try {
				password = MD5Utils.desEncrypt(req.getParameter("loginPassword"), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean isRemember = false;
		if(req.getParameter("rememberMe") != null){
			if("on".equalsIgnoreCase(req.getParameter("rememberMe"))){
				isRemember = true;
			}
		}
		boolean isValid = false;
		try {
			isValid = authenticationDao.isValidUser(loginName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(isValid){
			//process remember login name and password
			if(isRemember){
				Cookie cn = new Cookie("rmbn",loginName);
				cn.setMaxAge(60 * 60 * 24 * 7);
				resp.addCookie(cn);
				Cookie cp = new Cookie("rmbp",password);
				cp.setMaxAge(60 * 60 * 24 * 7);
				resp.addCookie(cp);
			}
			Long loginTime = System.currentTimeMillis();
			String token = TokenHelper.createToken(loginName, loginTime);
			HttpSession session = req.getSession();
			session.setAttribute(ValidationConstract.LOGIN_NAME, loginName);
			session.setAttribute(ValidationConstract.USER_TOKEN, token);
			Cookie tokenC = new Cookie(ValidationConstract.USER_TOKEN, token);
			tokenC.setMaxAge(ValidationConstract.TOKEN_EXPIRE);
			resp.addCookie(tokenC);
			String tempURI = WebUtils.getValueFromCookie(req, ValidationConstract.PREVIOUS_URI);
			if(tempURI != null){
				Cookie c = WebUtils.getCookie(req, ValidationConstract.PREVIOUS_URI);
				c.setValue(null);
				c.setMaxAge(0);
				req.getRequestDispatcher(tempURI).forward(req, resp);
			}else{
				req.getRequestDispatcher(ValidationConstract.INDEX_PAGE).forward(req, resp);
			}
		}else{
			System.out.println("login info is incorrect");
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()); 
		if(ac.getBean("authenticationDao") != null)
			authenticationDao = (AuthenticationDao) ac.getBean("authenticationDao");
	}

	
	
}
