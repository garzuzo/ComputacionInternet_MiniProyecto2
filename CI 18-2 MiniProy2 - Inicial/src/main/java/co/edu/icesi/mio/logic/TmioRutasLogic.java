package co.edu.icesi.mio.logic;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.model.Tmio1Ruta;

public class TmioRutasLogic implements ITmioRutasLogic{

	
	@Autowired
	private ITmio1_Rutas_DAO rutas;
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public void add(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		rutas.save(em, ruta);
	}

	@Override
	public void update(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		rutas.update(em, ruta);
	}

	@Override
	public void delete(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		rutas.delete(em, ruta);
	}

	@Override
	public void findByRangoDias(BigDecimal di,BigDecimal df) {
		// TODO Auto-generated method stub
		rutas.findByRangeOfDays(em, di, df);
	}

}
