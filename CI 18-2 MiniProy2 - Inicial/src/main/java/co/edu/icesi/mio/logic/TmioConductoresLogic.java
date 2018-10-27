package co.edu.icesi.mio.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

@Service
public class TmioConductoresLogic implements ITmioConductoresLogic{

	@Autowired
	private ITmio1_Conductores_DAO conductorDAO;
	
	
	@Transactional
	public void createConductor(Tmio1Conductore conductor, EntityManager em) {
		//hacer validaciones sobre conductor
		//NO ESTOY SEGURA DE PASAR EL ENTITYMANAGER COMO PARAMETRO O DEBE CREARSE.
		conductorDAO.save(em,conductor);
	}

	@Override
	public void updateConductor(Tmio1Conductore conductor, EntityManager em) {
		conductorDAO.update(em, conductor);
	}

	@Transactional
	public void deleteConductore(Tmio1Conductore conductor, EntityManager em) {
		conductorDAO.delete(em, conductor);
	}

	@Transactional
	public Tmio1Conductore findByName(String name, EntityManager em) {

		return null;
	}

	@Transactional
	public void findByLastname(String lastname, EntityManager em) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void findByCedula(String cedula, EntityManager em) {
		// TODO Auto-generated method stub
		
	}

	
}
