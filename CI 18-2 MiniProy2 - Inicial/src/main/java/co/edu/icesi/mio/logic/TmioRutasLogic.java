package co.edu.icesi.mio.logic;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.model.Tmio1Ruta;

public class TmioRutasLogic implements ITmioRutasLogic {

	@Autowired
	private ITmio1_Rutas_DAO rutas;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void add(Tmio1Ruta ruta) {
	
		if (validacionNumeroRuta(ruta) && validacionDiaInicio(ruta) && validacionDiaFin(ruta)
				&& validacionDiaInicioMenorFin(ruta) && validacionHoraFin(ruta) && validacionHoraInicio(ruta)
				&& validacionHoraInicioMenorFin(ruta) && validacionActiva(ruta))
			rutas.save(em, ruta);
	}

	@Override
	public void update(Tmio1Ruta ruta) {
		if (validacionNumeroRuta(ruta) && validacionDiaInicio(ruta) && validacionDiaFin(ruta)
				&& validacionDiaInicioMenorFin(ruta) && validacionHoraFin(ruta) && validacionHoraInicio(ruta)
				&& validacionHoraInicioMenorFin(ruta) && validacionActiva(ruta))
		rutas.update(em, ruta);
	}

	@Override
	public void delete(Tmio1Ruta ruta) {

		rutas.delete(em, ruta);
	}

	@Override
	public void findByRangoDias(BigDecimal di, BigDecimal df) {
		// TODO Auto-generated method stub
		rutas.findByRangeOfDays(em, di, df);
	}

	/**
	 * VALIDACIONES
	 */

	public boolean validacionNumeroRuta(Tmio1Ruta ruta) {
		return ruta.getNumero().length() == 3;
	}

	public boolean validacionDiaInicio(Tmio1Ruta ruta) {
		return ruta.getDiaInicio().compareTo(BigDecimal.ONE) >= 0
				&& ruta.getDiaInicio().compareTo(new BigDecimal("7")) <= 0;
	}

	public boolean validacionDiaFin(Tmio1Ruta ruta) {
		return ruta.getDiaFin().compareTo(BigDecimal.ONE) >= 0 && ruta.getDiaFin().compareTo(new BigDecimal("7")) <= 0;
	}

	public boolean validacionDiaInicioMenorFin(Tmio1Ruta ruta) {
		return ruta.getDiaInicio().compareTo(ruta.getDiaFin()) <= 0;
	}

	public boolean validacionHoraFin(Tmio1Ruta ruta) {
		return ruta.getHoraFin().compareTo(BigDecimal.ONE) >= 1
				&& ruta.getHoraFin().compareTo(new BigDecimal("1440")) <= 0;
	}

	public boolean validacionHoraInicio(Tmio1Ruta ruta) {
		return ruta.getHoraInicio().compareTo(BigDecimal.ONE) >= 1
				&& ruta.getHoraInicio().compareTo(new BigDecimal("1440")) <= 0;
	}

	public boolean validacionHoraInicioMenorFin(Tmio1Ruta ruta) {
		return ruta.getHoraInicio().compareTo(ruta.getHoraFin()) <= 0;
	}

	public boolean validacionActiva(Tmio1Ruta ruta) {
		return ruta.getActiva().matches("S|N");
	}

}
