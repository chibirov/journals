package com.intesys.trial.journals.service;

import org.springframework.security.core.authority.AuthorityUtils;

import com.intesys.trial.journals.model.Role;
import com.intesys.trial.journals.model.Subscription;
import com.intesys.trial.journals.model.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private User user;
	private Subscription subscription;

	public CurrentUser(User user) {
		super(user.getLoginName(), user.getPwd(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return user.getId();
	}

	public Role getRole() {
		return user.getRole();
	}

}