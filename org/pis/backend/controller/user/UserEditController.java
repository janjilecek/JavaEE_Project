package org.pis.backend.controller.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.pis.service.Password;
import org.pis.service.RoomManager;
import org.pis.service.UserManagerInternal;

import model.Mistnost;
import model.Uzivatel;

@ManagedBean(name="userEdit")
@ViewScoped
public class UserEditController {

	@EJB
	private RoomManager roomManager;
	@EJB
	private UserManagerInternal userManager;
	
	private Integer id;
	
	
	private List<Mistnost> offices;

	private Mistnost defaultOffice;
	private Uzivatel thisUser;
	
		
	private Mistnost findMyRoom(){
		for(int i = 0; i < getOffices().size(); ++i){
			if(getOffices().get(i).getMistnostID() == getThisUser().getKancelar().getMistnostID())
				return getOffices().get(i);
		}
		return null;
	}
	
	@PostConstruct
    public void init() {
		
	    id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));       

    	setThisUser(userManager.findUserById(id));
    	
    	setOffices(roomManager.findAllOffices());
    	
    	setDefaultOffice(findMyRoom());
    }

	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getId() {
		return id;
	}


	
	public String actionEdit()
    {
		try {
			getThisUser().setKancelar(getDefaultOffice());
			String heslo = getThisUser().getHeslo();
			heslo = Password.getSaltedHash(heslo);
			getThisUser().setHeslo(heslo);
			userManager.save(getThisUser());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User edited successfully"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			
			return "list.xhtml?faces-redirect=true";
		}
		catch(Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed to edit user..." + e.getMessage()));
			e.printStackTrace();
		}
		
		return "add";
    }

	public Mistnost getDefaultOffice() {
		return defaultOffice;
	}

	public void setDefaultOffice(Mistnost defaultOffice) {
		this.defaultOffice = defaultOffice;
	}

	public Uzivatel getThisUser() {
		return thisUser;
	}

	public void setThisUser(Uzivatel thisUser) {
		this.thisUser = thisUser;
	}

	public List<Mistnost> getOffices() {
		return offices;
	}

	public void setOffices(List<Mistnost> offices) {
		this.offices = offices;
	}
}
