package co.edu.icesi.mio.logic;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;

public class TmioBusesLogic implements ITmioBusesLogic {

	@Autowired
	private ITmio1_Buses_DAO buses;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void add(Tmio1Bus bus) {

		if (bus != null && buses.findById(em, bus.getId()) == null && placaMESix(bus) && marcaMEThree(bus)
				&& modeloNumbersEFour(bus.getModelo()) && tipoPAT(bus.getTipo()) && capacidadMZero(bus.getCapacidad()))
			buses.save(em, bus);
	}

	@Override
	public void update(Tmio1Bus bus) {
		if (bus != null && buses.findById(em, bus.getId()) != null && placaMESix(bus) && marcaMEThree(bus)
				&& modeloNumbersEFour(bus.getModelo()) && tipoPAT(bus.getTipo()) && capacidadMZero(bus.getCapacidad()))
			buses.update(em, bus);
	}

	@Override
	public void delete(Tmio1Bus bus) {

		if (bus != null && buses.findById(em, bus.getId()) != null)
			buses.delete(em, bus);
	}

	@Override
	public void findByModelo(BigDecimal m) {

		if (modeloNumbersEFour(m))
			buses.findByModel(em, m);
	}

	@Override
	public void findByTipo(String t) {
		if (tipoPAT(t))
			buses.findByType(em, t);
	}

	@Override
	public void findByCapacidad(BigDecimal c) {
		if (capacidadMZero(c))
			buses.findByCapacity(em, c);
	}

	/**
	 * VALIDACIONES
	 */

	// la placa esté definida y tenga seis caracteres
	public boolean placaMESix(Tmio1Bus bus) {
		return bus.getPlaca().length() == 6;
	}

	// la marca esté definida y tenga al menos tres caracteres
	public boolean marcaMEThree(Tmio1Bus bus) {
		return bus.getMarca().length() >= 3;
	}

	// el modelo sea numérico de cuatro dígitos
	public boolean modeloNumbersEFour(BigDecimal bus) {
		return bus.compareTo(new BigDecimal("10000")) < 0;
	}

	// el tipo sea P, A, o T
	public boolean tipoPAT(String bus) {
		return bus.matches("P|A|T");
	}

	// la capacidad sea numérica mayor a cero
	public boolean capacidadMZero(BigDecimal bus) {
		return bus.compareTo(BigDecimal.ZERO) > 0;
	}

}
