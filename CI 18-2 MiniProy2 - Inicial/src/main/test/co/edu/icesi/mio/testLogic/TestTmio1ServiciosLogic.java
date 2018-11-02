package co.edu.icesi.mio.testLogic;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.dao.Tmio1_Buses_DAO;
import co.edu.icesi.mio.dao.Tmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.Tmio1_Rutas_DAO;
import co.edu.icesi.mio.logic.ITmioBusesLogic;
import co.edu.icesi.mio.logic.ITmioConductoresLogic;
import co.edu.icesi.mio.logic.ITmioRutasLogic;
import co.edu.icesi.mio.logic.ITmioServiciosLogic;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestTmio1ServiciosLogic {

	@Autowired
	public ITmioServiciosLogic serviciosLogic;
	
	@Autowired
	public ITmioConductoresLogic conductoresLogic;
	
	@Autowired
	public ITmioBusesLogic busesLogic;
	
	@Autowired
	public ITmioRutasLogic rutasLogic;
	
	@Test
	public void testCrearServicioCamposValidos() {
		assertNotNull(serviciosLogic);
		assertNotNull(conductoresLogic);
		assertNotNull(busesLogic);
		assertNotNull(rutasLogic);
		
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor("1143874310");
		s1PK.setIdBus(-23);
		s1PK.setIdRuta(-46);
		Calendar d2 = new GregorianCalendar(2018,1,20);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,11,25);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(-23));
		s1.setTmio1Conductore(conductoresLogic.findByCedula("1143874310"));
		s1.setTmio1Ruta(rutasLogic.findById(-46));
	
		serviciosLogic.createServicio(s1);
	}
	
	@Test
	public void testCrearServicioCamposInvalidos() {
		assertNotNull(serviciosLogic);
		assertNotNull(conductoresLogic);
		assertNotNull(busesLogic);
		assertNotNull(rutasLogic);
		
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor("1143874310");
		s1PK.setIdBus(-23);
		s1PK.setIdRuta(-46);
		Calendar d2 = new GregorianCalendar(2018,11,25);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,01,20);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(-23));
		s1.setTmio1Conductore(conductoresLogic.findByCedula("1143874310"));
		s1.setTmio1Ruta(rutasLogic.findById(-46));
	
		serviciosLogic.createServicio(s1);
	}
	
	@Test
	public void testCrearServicioCamposInvalidosParte2() {
		assertNotNull(serviciosLogic);
		assertNotNull(conductoresLogic);
		assertNotNull(busesLogic);
		assertNotNull(rutasLogic);
		
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor("1143874310");
		s1PK.setIdBus(-23);
		s1PK.setIdRuta(-46);
		Calendar d2 = new GregorianCalendar(2018,11,25);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,01,20);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(-23));
		s1.setTmio1Conductore(conductoresLogic.findByCedula("1143874310"));
		s1.setTmio1Ruta(rutasLogic.findById(-46));
	
		serviciosLogic.createServicio(s1);
	}
	
}
