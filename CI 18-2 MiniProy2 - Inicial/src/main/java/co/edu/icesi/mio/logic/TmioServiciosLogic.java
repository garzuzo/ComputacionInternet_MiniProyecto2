package co.edu.icesi.mio.logic;

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
public class TmioServiciosLogic implements ITmioServiciosLogic{

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
		//hacer validaciones sobre conductor
		//NO ESTOY SEGURA DE PASAR EL ENTITYMANAGER COMO PARAMETRO O DEBE CREARSE.
		
		if(servicio != null && getServicio(servicio.getId())==null && 
				validacionLlavesForaneas(servicio.getTmio1Bus(), servicio.getTmio1Conductore(), servicio.getTmio1Ruta()) &&
					validacionBusesYConductoresDisponibles(servicio.getTmio1Bus(), servicio.getTmio1Conductore()) &&
						validacionFechaInicioFinal()) {
				servicioDAO.save(em,servicio);
		}else {
			//LANZAR UNA EXCEPCION
		}	
	}
	
	@Override
	public void updateServicio(Tmio1Servicio servicio) {
		if(servicio != null && getServicio(servicio.getId())!=null &&
			validacionLlavesForaneas(servicio.getTmio1Bus(), servicio.getTmio1Conductore(), servicio.getTmio1Ruta()) &&
				validacionBusesYConductoresDisponibles(servicio.getTmio1Bus(), servicio.getTmio1Conductore()) &&
					validacionFechaInicioFinal()) {
				servicioDAO.update(em, servicio);
		}else {
			//LANZAR UNA EXCEPCION
		}
	}

	@Transactional
	public void deleteServicio(Tmio1Servicio servicio) {
		if(servicio!=null && getServicio(servicio.getId())!=null)
			servicioDAO.delete(em, servicio);
	}
	
	/**
	 * VALIDACIONES
	 * : las llaves foráneas existan; la fecha inicio esté definida y sea menor o
igual que la fecha final. “La solución debe validar que al momento de crear un nuevo servicio,
tanto el bus como el conductor se encuentren disponibles, es decir que no se encuentren
asignados a otro servicio que coincida con las fechas, días y horarios establecidos
	 */
	public boolean validacionLlavesForaneas(Tmio1Bus bus, Tmio1Conductore conductor, Tmio1Ruta ruta) {
		boolean validacion=false;
	
		if(bus!=null && conductor!= null && ruta!= null &&
			busDAO.findById(em, bus.getId())!= null &&
				conductorDAO.findByCedula(em, conductor.getCedula()) != null &&
					rutaDAO.findById(em, ruta.getId()) != null)
			validacion=true;
	
		return validacion;
	}
	
	public boolean validacionFechaInicioFinal(){
		return false;
	}
	
	public boolean validacionBusesYConductoresDisponibles(Tmio1Bus b, Tmio1Conductore c) {
		boolean validacion=false;
		if(!busDAO.busesThatAreFree(em).contains(b) && !conductorDAO.driversThatAreFree(em).contains(c)) {
			validacion=true;
		}
		return validacion;
	}
	
	public Tmio1Servicio getServicio(Tmio1ServicioPK id) {
		return servicioDAO.findById(em, id);
	}
}
