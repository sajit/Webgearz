package com.webgearz.tb.controllers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.webgearz.security.service.SecurityService;
import com.webgearz.tb.client.model.JSONView;
import com.webgearz.tb.domain.models.User;
import com.webgearz.tb.exceptions.WebgearzException;
import com.webgearz.tb.services.UserService;

@Controller
@SessionAttributes("userid")
public class UserController {

	private static final Log log = LogFactory.getLog(UserController.class);
	private UserService userService;
	private SecurityService securityService;
	@RequestMapping(value="/getRegistePage",method=RequestMethod.GET)
	public ModelAndView getRegistrationPage(){
		ModelAndView mav = new ModelAndView("user-details");
		return mav;
	}

	/**
	 * FIXME : Removing the authentication till I fix the spring security issue
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/createUser",method=RequestMethod.POST)
	public ModelAndView createUser(User user,HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setView(new JSONView());
		log.debug(user);
		User storeduser = userService.store(user);
		mav.getModel().put("user", storeduser);
		session.setAttribute("userid", storeduser.getId());
		securityService.addAuthenticatedUser(storeduser);
		mav.getModel().put("msg", "User created");
		
		return mav;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView getLoginPage(){
		ModelAndView mav = new ModelAndView("login");
		log.info("Returning login page");
		return mav;
		
	}
	
	
	@RequestMapping(value="/cms/user_dashboard",method=RequestMethod.GET)
	public ModelAndView getUserDashboard(@ModelAttribute("userid")String userid){
		ModelAndView mav = new ModelAndView("dashboard");
		User user = userService.findUser(userid);
		if(user==null)
			throw new WebgearzException("Not Authenticated User or Unknown user");
		mav.getModel().put("user", user);
		return mav;
		
	}
	
	

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	
	public SecurityService getSecurityService() {
		return securityService;
	}
	
}

