package org.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TypTechniky;


@Stateless
public class TechniqueTypeManager {
	
	@PersistenceContext
    private EntityManager em;
	
	public List<TypTechniky> findAll() {
		return em.createQuery("SELECT tt FROM TypTechniky tt", TypTechniky.class).getResultList();
	}

}
