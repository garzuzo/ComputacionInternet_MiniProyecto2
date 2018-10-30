package co.edu.icesi.mio.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;

@Repository
@Scope("singleton")
public class Tmio1_Buses_DAO implements ITmio1_Buses_DAO{

	//adicionales
	@Override
	public List<Tmio1Bus> findByModel(EntityManager em, BigDecimal model) {
		String jpql = "Select b from Tmio1Bus b where b.modelo="+ model;
		return 	em.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByType(EntityManager em, String type) {
		String jpql = "Select b from Tmio1Bus b where b.tipo= '"+  type + "'";
		return 	em.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByCapacity(EntityManager em, BigDecimal capacity) {
		String jpql = "Select b from Tmio1Bus b where b.capacidad="+ capacity;
		return 	em.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Tmio1Bus> busesThatAreFree(EntityManager em) {
		Calendar gc= new GregorianCalendar().getInstance();
		String actual= ("'" +gc.get(GregorianCalendar.YEAR)+ "-" + gc.get(GregorianCalendar.MONTH)+ "-" + 
				gc.get(GregorianCalendar.DAY_OF_MONTH)+ "'");
		String jpql= " Select c from Tmio1Bus c, Tmio1Servicio s"
				     + " WHERE c.id= s.tmio1Bus.id AND s.id.fechaFin>=" + actual;
		List<Tmio1Bus> busessWithServices= em.createQuery(jpql).getResultList();
		
		List<Tmio1Bus> buses = findAll(em);
		if(busessWithServices.size() >0)
		buses.removeAll(busessWithServices);
		return 	buses;
	}


	//normales
	@Override
	public void save(EntityManager em, Tmio1Bus bus) {
		em.persist(bus);
	}

	@Override
	public void update(EntityManager em, Tmio1Bus bus) {
		em.merge(bus);
	}

	@Override
	public void delete(EntityManager em, Tmio1Bus bus) {
		em.remove(bus);
	}

	@Override
	public List<Tmio1Bus> findAll(EntityManager em) {
		String jpql = "Select b from Tmio1Bus b";
		return 	em.createQuery(jpql).getResultList();
	}

	@Override
	public Tmio1Bus findById(EntityManager em, Integer id) {
		return em.find(Tmio1Bus.class, id);
	}

}
