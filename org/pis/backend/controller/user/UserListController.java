package org.pis.backend.controller.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Mistnost;
import model.Uzivatel;

import org.pis.service.UserManagerInternal;

@ManagedBean(name="userList")
@ViewScoped
public class UserListController {

	@EJB
	private UserManagerInternal userManager;

	private List<Uzivatel> users;
	
	@PostConstruct
	public void init() {
		users = userManager.findAll();
	}
	
	public List<Uzivatel> getUsers() {
		return users;
	}

}
