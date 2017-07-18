package org.pis.backend;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Mistnost;
import model.Uzivatel;

import org.pis.service.RoomManager;
import org.pis.service.UserManagerException;
import org.pis.service.UserManagerInternal;


@ManagedBean
@ViewScoped
public class UserBean
{
	@EJB
	private UserManagerInternal userMgr;
    private Uzivatel user;
    @EJB
    private RoomManager roomMgr;
    private List<Mistnost> rooms;
    
    public UserBean()
    {
    	
        user = new Uzivatel();
    }
    
    @PostConstruct
    public void init() {
    	rooms = roomMgr.findAllOffices();
    }
    
    public List<Mistnost> getRooms()
    {
    	return rooms;
    }
    
    /**
     * @return the person
     */
    public Uzivatel getUser()
    {
        return user;
    }

    /**
     * @param person the person to set
     */
    public void setUser(Uzivatel person)
    {
        this.user = person;
    }
    
    public List<Uzivatel> getPeople()
    {
		return userMgr.findAll();
    }
    
   

   //====================================================
    
	public String actionNew()
	{
		user = new Uzivatel();
		return "new";
	}
	
	public String actionInsertNew()
    {
		try {
			userMgr.addUserEncodePassword(user);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User added successfully"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			user = new Uzivatel();
	        return "list.xhtml?faces-redirect=true";
		} catch (UserManagerException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User cannot be created: " + e.getMessage()));
		}
		catch(Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed to add user..."));
			e.printStackTrace();
		}
		
		return "failed";
    }
    
    public String actionUpdate()
    {
    	userMgr.save(user);
        return "update";
    }
    
    public String actionEdit(Uzivatel user)
    {
    	setUser(user);
    	return "edit";
    }
    
    public String actionDelete(Uzivatel user)
    {
    	userMgr.remove(user);
    	return "delete";
    }

}
