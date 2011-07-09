package com.webgearz.tb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.webgearz.tb.client.model.JSONView;
import com.webgearz.tb.domain.models.DivContent;
import com.webgearz.tb.domain.models.User;
import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.exceptions.WebgearzException;
import com.webgearz.tb.services.DivContentService;
import com.webgearz.tb.services.UserService;

@Controller
@SessionAttributes("userid")
public class DivController {
	
	private DivContentService divContentService;
	private UserService userService;
	private static final Log LOG = LogFactory.getLog(DivController.class);
	
	@RequestMapping(value="/cms/getDivContent",method = RequestMethod.GET)
	public ModelAndView getDivContent(@RequestParam("domainId")final String domainId,
			final String divId){
	
		ModelAndView mav = new ModelAndView();
		mav.setView(new JSONView());
		String divContent = divContentService.getDivContent(domainId, divId);
		mav.getModel().put("divContent", divContent);
		LOG.debug("Hit the div Controller get divcontent end point");
		LOG.debug("Returning div Content == " + divContent);
		return mav;
		
	}
	//FIXME the security logic should be in a separate class
	@RequestMapping(value="/cms/storeDivContent",method = RequestMethod.POST)
	public ModelAndView storeDivContent(@ModelAttribute("userid")final String userid,
			@RequestParam("domainId") final String domainId,@RequestParam("divId")final String divId,@RequestParam("divContents") final String divContentString){
		DivContent divElement = getDivContent(divContentString,domainId,divId);
		User user = userService.findUser(userid);
		Assert.notNull(user);
		if(!checkUserOwnsDomain(user,domainId))
			throw new WebgearzException("User doesnt have access to edit template");
	
		divContentService.storeDivContent(divElement);
		ModelAndView mav = new ModelAndView();
		mav.setView(new JSONView());
		mav.getModel().put("msg", "ok");
		return mav;
	}

	private boolean checkUserOwnsDomain(User user, String domainId) {
		for(UserDomain userDomain : user.getUserDomains()){
			if(userDomain.getId().equals(domainId))
				return true;
		}
		return false;
		
	}
	@Autowired(required=true)
	public void setDivContentService(DivContentService divContentService) {
		this.divContentService = divContentService;
	}

	public DivContentService getDivContentService() {
		return divContentService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserService getUserService() {
		return userService;
	}
	private DivContent getDivContent(String divContentString,String domainId,String divId){
		DivContent divContent = new DivContent();
		divContent.setDivContents(divContentString);
		divContent.setDomainId(domainId);
		divContent.setDivId(divId);
		return divContent;
	}

}
class DivContentCreateForm{
	private String domainName;
	private String divId;
	private String divContentString;
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	
	public DivContent getDivContent(){
		DivContent divContent = new DivContent();
		divContent.setDivContents(divContentString);
		divContent.setDomainId(domainName);
		divContent.setDivId(divId);
		return divContent;
	}
	public String getDivContentString() {
		return divContentString;
	}
	public void setDivContentString(String divContentString) {
		this.divContentString = divContentString;
	}
}
