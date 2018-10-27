package co.edu.icesi.mio.logic;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.model.Tmio1Conductore;

public interface ITmioConductoresLogic {
	public void createConductor(Tmio1Conductore conductor, EntityManager em);
	public void updateConductor(Tmio1Conductore conductor, EntityManager em);
	public void deleteConductore(Tmio1Conductore conductor, EntityManager em);
	
	public Tmio1Conductore findByName(String name, EntityManager em);
	public Tmio1Conductore findByLastname(String lastname, EntityManager em);
	public Tmio1Conductore findByCedula(String cedula, EntityManager em);
}
