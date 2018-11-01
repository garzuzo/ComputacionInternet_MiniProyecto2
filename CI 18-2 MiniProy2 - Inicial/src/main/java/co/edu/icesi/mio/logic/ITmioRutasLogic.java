package co.edu.icesi.mio.logic;

import java.math.BigDecimal;

import co.edu.icesi.mio.model.Tmio1Ruta;

public interface ITmioRutasLogic {

	public void add(Tmio1Ruta bus);

	public void update(Tmio1Ruta bus);

	public void delete(Tmio1Ruta bus);

	public void findByRangoDias(BigDecimal di, BigDecimal df);

	public Tmio1Ruta findById(int id);
}
