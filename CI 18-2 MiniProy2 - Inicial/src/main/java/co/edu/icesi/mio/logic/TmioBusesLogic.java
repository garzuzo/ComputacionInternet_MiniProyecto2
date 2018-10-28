package co.edu.icesi.mio.logic;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;

public class TmioBusesLogic implements ITmioBusesLogic{

	@Autowired
	private ITmio1_Buses_DAO buses;
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public void add(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		buses.save(em, bus);
	}

	@Override
	public void update(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		buses.update(em, bus);
	}

	@Override
	public void delete(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		buses.delete(em, bus);
	}

	@Override
	public void findByModelo(BigDecimal m) {
		// TODO Auto-generated method stub
		buses.findByModel(em, m);
	}

	@Override
	public void findByTipo(String t) {
		// TODO Auto-generated method stub
		buses.findByType(em, t);
	}

	@Override
	public void findByCapacidad(BigDecimal c) {
		// TODO Auto-generated method stub
		buses.findByCapacity(em, c);
	}

}
