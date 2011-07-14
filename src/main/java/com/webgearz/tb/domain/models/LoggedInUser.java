package com.webgearz.tb.domain.models;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedInUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 518324267232017143L;
	private String userEmail;
	private String password;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
	
	private String userid;
	
	public LoggedInUser(User user){
		this.userEmail = user.getEmail();
		this.password = user.getPassword();
		this.userid = user.getId();
		this.grantedAuthorities.add(new GrantedAuthorityImpl(AuthenticatedVoter.IS_AUTHENTICATED_FULLY.toString()));
	}
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserid() {
		return userid;
	}
	
	@Override
	public String toString(){
		return "{UserDetails--> " + userEmail+","+userid+"}";
	}
	
	public void setUsername(String username){
		this.userEmail = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}
	public Collection<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	
}
