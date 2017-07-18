package org.pis.backend.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.pis.service.TechniqueManager;
import org.pis.service.TechniqueTypeManager;
import org.pis.service.UserManagerException;
import org.pis.service.UserManagerInternal;

import model.EvidenceTechniky;
import model.Mistnost;
import model.TypTechniky;
import model.Uzivatel;

@Stateless
@ManagedBean(name="technique")
@ViewScoped
public class TechniqueController {
	
	@EJB
	private TechniqueManager techniqueManager;
	
	@EJB
	private TechniqueTypeManager techniqueTypeManager;
	
	@EJB
	private UserManagerInternal userManager;
	
	private List<TypTechniky> types;
	private List<Uzivatel> users;
	
	
	private EvidenceTechniky technique;
	
	private Integer id;
	
	
    /**
     * Default constructor. 
     */
    public TechniqueController() {
		technique = new EvidenceTechniky();
	}
    
    
    @PostConstruct
    public void init() {
    	types = techniqueTypeManager.findAll();
    	users = userManager.findAll();
    }
    
    
    public EvidenceTechniky getTechnique() {
    	return technique;
    }
    
    public List<EvidenceTechniky> getAllAvailableTechnique() {
    	return techniqueManager.findAll();
    }

    public List<TypTechniky> getTechniqueTypes() {
    	return types;
    }
    
    public List<Uzivatel> getUsers() {
    	return users;
    }
    
	public String actionInsertNew()
    {
		try {
			techniqueManager.add(technique);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Equipment added successfully"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			technique = new EvidenceTechniky();
			return "list.xhtml?faces-redirect=true";
		}
		catch(Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed to add equipment..." + e.getMessage()));
			e.printStackTrace();
		}
		
		return "add";
    }
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	
	public EvidenceTechniky getTechniqueDetail() {
		return techniqueManager.findById(id);
	}

}
