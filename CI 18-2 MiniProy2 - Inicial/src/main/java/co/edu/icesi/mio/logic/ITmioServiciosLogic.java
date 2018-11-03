package co.edu.icesi.mio.logic;

import java.util.Calendar;
import java.util.List;

import co.edu.icesi.mio.model.Tmio1Servicio;

public interface ITmioServiciosLogic {

	public void createServicio(Tmio1Servicio servicio);
	public void updateServicio(Tmio1Servicio servicio);
	public void deleteServicio(Tmio1Servicio servicio);
	public List<Tmio1Servicio> findByRangeOfDates(Calendar d1, Calendar d2);
}
