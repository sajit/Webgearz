package com.webgearz.security.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.util.Assert;

import com.webgearz.security.service.SecurityService;
import com.webgearz.tb.domain.models.LoggedInUser;


public class WGAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler 
       implements AuthenticationSuccessHandler {
	
	private static final Log log = LogFactory.getLog(WGAuthenticationSuccessHandler.class);
	
	private SecurityService securityService;
	
	
	public WGAuthenticationSuccessHandler(String defaultTargetUrl){
		
		log.debug("Default Target URL " + defaultTargetUrl);
		setDefaultTargetUrl(defaultTargetUrl);
	}
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.debug("In  onSuccess AuthHandler");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Assert.notNull(auth);
		log.debug("Principal name " + auth.getName());
		log.debug("Authentication " + auth);
		LoggedInUser userDetails = (LoggedInUser)securityService.getUserDetailsObject(auth.getName());
		request.getSession().setAttribute("userid", userDetails.getUserid());
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	public SecurityService getSecurityService() {
		return securityService;
	}


}
