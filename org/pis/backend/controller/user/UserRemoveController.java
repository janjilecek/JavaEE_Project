package org.pis.backend.controller.user;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.ejb.EJB;

import model.Mistnost;
import model.Uzivatel;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.pis.service.RoomManager;
import org.pis.service.UserManagerInternal; 

@ManagedBean(name="userRemove")
@RequestScoped
public class UserRemoveController {
	@EJB
	private UserManagerInternal userManager;
	
	@ManagedProperty("#{param.id}")
	private Integer id;
	
	FacesContext c;
	
	private Uzivatel uzivatel;
	public Uzivatel getUzivatel() {
		if (uzivatel == null) {
			uzivatel = userManager.findById(id);
			
		}
		return uzivatel;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String actionRemove() {
		c = FacesContext.getCurrentInstance();
		try {
			userManager.remove(getUzivatel());
			c.addMessage(null, new FacesMessage("User was successfully deleted."));
			//redirect("list.xhtml");
			c.getExternalContext().getFlash().setKeepMessages(true);
			return "list.xhtml?faces-redirect=true";
		} 
		catch (Exception e) {
			c.addMessage(null,  new FacesMessage("Could not delete user."));
			e.printStackTrace();
			return "list.xhtml?faces-redirect=true";
		}
	}
}