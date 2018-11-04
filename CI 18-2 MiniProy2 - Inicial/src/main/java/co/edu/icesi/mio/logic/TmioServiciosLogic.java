package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.dao.ITmio1_Servicios_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

@Service
public class TmioServiciosLogic implements ITmioServiciosLogic {

	@Autowired
	private ITmio1_Servicios_DAO servicioDAO;

	@Autowired
	private ITmio1_Conductores_DAO conductorDAO;

	@Autowired
	private ITmio1_Buses_DAO busDAO;

	@Autowired
	private ITmio1_Rutas_DAO rutaDAO;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void createServicio(Tmio1Servicio servicio) {
		if (servicio != null && getServicio(servicio.getId()) == null
				&& validacionLlavesForaneas(servicio.getTmio1Bus(), servicio.getTmio1Conductore(),
						servicio.getTmio1Ruta())
				&& validacionBusesYConductoresDisponibles(servicio.getTmio1Bus(), servicio.getTmio1Conductore())
				&& validacionFechaInicioFinal(servicio.getId().getFechaInicio(), servicio.getId().getFechaFin())) {
			servicioDAO.save(em, servicio);
		} else {
			// LANZAR UNA EXCEPCION
		}
	}

	@Transactional
	public void updateServicio(Tmio1Servicio servicio) {
		if (servicio != null && getServicio(servicio.getId()) != null
				&& validacionLlavesForaneas(servicio.getTmio1Bus(), servicio.getTmio1Conductore(),
						servicio.getTmio1Ruta())
				&& validacionBusesYConductoresDisponibles(servicio.getTmio1Bus(), servicio.getTmio1Conductore())
				&& validacionFechaInicioFinal(servicio.getId().getFechaInicio(), servicio.getId().getFechaFin())) {
			servicioDAO.update(em, servicio);
		} else {
			// LANZAR UNA EXCEPCION
		}
	}

	@Transactional
	public void deleteServicio(Tmio1Servicio servicio) {
		if (servicio != null && getServicio(servicio.getId()) != null)
			servicioDAO.delete(em, getServicio(servicio.getId()));
	}
	
	@Transactional
	public List<Tmio1Servicio> findByRangeOfDates(Calendar d1, Calendar d2) {
		List<Tmio1Servicio> servicios=null;
		if(d1!=null && d2!=null)
			servicios= servicioDAO.findByRangeOfDates(em,d1, d2);
		return servicios;
	}
	
	@Transactional
	public Tmio1Servicio getServicio(Tmio1ServicioPK id) {
		return servicioDAO.findById(em, id);
	}

	/**
	 * VALIDACIONES : las llaves for�neas existan; la fecha inicio est� definida y
	 * sea menor o igual que la fecha final. �La soluci�n debe validar que al
	 * momento de crear un nuevo servicio, tanto el bus como el conductor se
	 * encuentren disponibles, es decir que no se encuentren asignados a otro
	 * servicio que coincida con las fechas, d�as y horarios establecidos
	 */
	public boolean validacionLlavesForaneas(Tmio1Bus bus, Tmio1Conductore conductor, Tmio1Ruta ruta) {
		boolean validacion = false;

		if (bus != null && conductor != null && ruta != null && findById(bus.getId()) != null
				&& findByCedula(conductor.getCedula()) != null
				&& findByIdRuta(ruta.getId()) != null)
			validacion = true;

		return validacion;
	}

	public boolean validacionFechaInicioFinal(Date fechaInicio, Date fechaFin) {

		return fechaInicio.compareTo(fechaFin) <= 0 ? true : false;
	}

	public boolean validacionBusesYConductoresDisponibles(Tmio1Bus b, Tmio1Conductore c) {
		boolean validacion = false;
		if (busDAO.busesThatAreFree(em).contains(b) && conductorDAO.driversThatAreFree(em).contains(c)) {
			validacion = true;
		}
		return validacion;
	}

	@Transactional
	public Tmio1Bus findById(int id) {
		return busDAO.findById(em, id);
	}
	
	public boolean validacionCedula(String cedula) {
		return cedula.matches("[0-9]+");
	}
	
	@Transactional
	public Tmio1Conductore findByCedula(String cedula) {
		Tmio1Conductore conductor= null;
		if(validacionCedula(cedula))
			conductor= conductorDAO.findByCedula(em, cedula);
		return conductor; 
	}
	
	@Transactional
	public Tmio1Ruta findByIdRuta(int id) {
		return rutaDAO.findById(em, id);		
	}
}
