package org.pis.backend.controller.cvt;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Mistnost;

import org.pis.service.RoomManager;
import org.pis.service.RoomManagerException;
@ManagedBean(name="CVTRoomDetail")
@ViewScoped
public class CvtRoomDetailController {

	@EJB
	private RoomManager roomManager;
	
	private Integer id;
	
	public Mistnost getRoom() {
		Mistnost tmp = roomManager.findById(id);
		//if(tmp.getJeCvt() == false)
		//	return null;
		return tmp;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getId() {
		return id;
	}

}
