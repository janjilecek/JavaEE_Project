package org.pis.backend.controller.equipment;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.ejb.EJB;

import model.EvidenceTechniky;
import model.Mistnost;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.pis.service.RoomManager;
import org.pis.service.TechniqueManager; 

@ManagedBean(name="techRemove")
@RequestScoped
public class EquipmentRemoveController {
	@EJB
	private TechniqueManager tm;
	
	@ManagedProperty("#{param.id}")
	private Integer id;
	
	FacesContext c;
	
	private EvidenceTechniky technika;
	public EvidenceTechniky getTechnika() {
		if (technika == null) {
			technika = tm.findById(id);
		}
		return technika;
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
			tm.remove(getTechnika());
			c.addMessage(null, new FacesMessage("Tech equipment was successfully deleted."));
			c.getExternalContext().getFlash().setKeepMessages(true);
			return "list.xhtml?faces-redirect=true";
		} 
		catch (Exception e) {
			c.addMessage(null,  new FacesMessage("Could not delete tech equipment."));
			e.printStackTrace();
			return "list.xhtml?faces-redirect=true";
		}
	}
}