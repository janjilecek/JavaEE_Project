package org.pis.backend.controller.room;

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

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.pis.service.RoomManager; 

@ManagedBean(name="roomRemove")
@RequestScoped
public class RoomRemoveController {
	@EJB
	private RoomManager roomManager;
	
	@ManagedProperty("#{param.id}")
	private Integer id;
	
	FacesContext c;
	
	private Mistnost room;
	public Mistnost getRoom() {
		if (room == null) {
			room = roomManager.findById(id);
		}
		return room;
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
			roomManager.remove(getRoom());
			c.addMessage(null, new FacesMessage("Room was successfully deleted."));
			//redirect("list.xhtml");
			c.getExternalContext().getFlash().setKeepMessages(true);
			return "list.xhtml?faces-redirect=true";
		} 
		catch (Exception e) {
			c.addMessage(null,  new FacesMessage("Could not delete room"));
			e.printStackTrace();
			return "list.xhtml?faces-redirect=true";
		}
	}
}