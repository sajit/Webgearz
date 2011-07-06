package com.webgearz.tb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public ModelAndView storeDivContent(DivContentCreateForm form,@ModelAttribute("userid")final String userid){
		DivContent divElement = form.getDivContent();
		User user = userService.findUser(userid);
		List<String> userDomainNames = new ArrayList<String>();
		for(UserDomain userDomain : user.getUserDomains()){
			userDomainNames.add(userDomain.getDomainName());
		}
		if(!userDomainNames.contains(form.getDomainName())){
			throw new WebgearzException("User is not authorized to change div");
		}
		divContentService.storeDivContent(divElement);
		ModelAndView mav = new ModelAndView();
		mav.setView(new JSONView());
		return mav;
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
