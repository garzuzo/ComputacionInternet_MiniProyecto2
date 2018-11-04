package co.edu.icesi.mio.testLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;
import co.edu.icesi.mio.model.Tmio1SitiosRuta;

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

		Tmio1Bus b = new Tmio1Bus();
		b.setCapacidad(new BigDecimal(110));
		b.setMarca("Volvo");
		b.setModelo(new BigDecimal(2010));
		b.setPlaca("EVN876");
		b.setTipo("T");
		b.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		b.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		busesLogic.add(b);

		Tmio1Conductore tmioConductor = new Tmio1Conductore();
		tmioConductor.setCedula("1143874318");
		tmioConductor.setNombre("Johnatan");
		tmioConductor.setApellidos("Garzon");
		Calendar d = new GregorianCalendar(2018, 1, 20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1988, 1, 18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		conductoresLogic.createConductor(tmioConductor);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("Ruta Canasgordas");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setHoraInicio(new BigDecimal(6));
		ruta.setHoraFin(new BigDecimal(17));
		ruta.setNumero("A11");
		ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		rutasLogic.add(ruta);

		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor(tmioConductor.getCedula());
		s1PK.setIdBus(b.getId());
		s1PK.setIdRuta(ruta.getId());
		Calendar d2 = new GregorianCalendar(2018, 1, 20);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018, 11, 25);
		s1PK.setFechaFin(d3.getTime());

		Tmio1Servicio s1 = new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(b.getId()));
		s1.setTmio1Conductore(conductoresLogic.findByCedula(tmioConductor.getCedula()));
		s1.setTmio1Ruta(rutasLogic.findById(ruta.getId()));

		serviciosLogic.createServicio(s1);

		assertNotNull(serviciosLogic.getServicio(s1PK));
		eliminarDatos1(b, tmioConductor, s1, ruta);
	}

	public void eliminarDatos1(Tmio1Bus b, Tmio1Conductore c, Tmio1Servicio s, Tmio1Ruta r) {
		serviciosLogic.deleteServicio(s);
		busesLogic.delete(b);
		conductoresLogic.deleteConductor(c);

		rutasLogic.delete(r);
	}
	public void eliminarDatos2(Tmio1Bus b, Tmio1Conductore c, Tmio1Servicio s, Tmio1Ruta r) {
		serviciosLogic.deleteServicio(s);
		busesLogic.delete(b);
		conductoresLogic.deleteConductor(c);

		rutasLogic.delete(r);
	}
	public void eliminarDatos3(Tmio1Bus b, Tmio1Conductore c, Tmio1Servicio s, Tmio1Ruta r) {
		serviciosLogic.deleteServicio(s);
		busesLogic.delete(b);
		conductoresLogic.deleteConductor(c);

		rutasLogic.delete(r);
	}
	public void eliminarDatos4(Tmio1Bus b, Tmio1Conductore c, Tmio1Servicio s, Tmio1Ruta r) {
		serviciosLogic.deleteServicio(s);
		busesLogic.delete(b);
		conductoresLogic.deleteConductor(c);

		rutasLogic.delete(r);
	}
	@Test
	public void testCrearServicioCamposInvalidos() {
		assertNotNull(serviciosLogic);
		assertNotNull(conductoresLogic);
		assertNotNull(busesLogic);
		assertNotNull(rutasLogic);
	
		Tmio1Bus b = new Tmio1Bus();
		b.setCapacidad(new BigDecimal(110));
		b.setMarca("Volvo");
		b.setModelo(new BigDecimal(2010));
		b.setPlaca("EVN878");
		b.setTipo("T");
		busesLogic.add(b);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874326");
		tmioConductor.setNombre("Luis");
		tmioConductor.setApellidos("Sanchez");
		Calendar d = new GregorianCalendar(2018,5,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1998,1,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());		
		conductoresLogic.createConductor(tmioConductor);
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("Ruta Canasgordas");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setHoraInicio(new BigDecimal(6));
		ruta.setHoraFin(new BigDecimal(17));
		ruta.setNumero("A11");
    	ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
    	ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
    	ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
    	rutasLogic.add(ruta);
    	
    	
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor(tmioConductor.getCedula());
		s1PK.setIdBus(b.getId());
		s1PK.setIdRuta(ruta.getId());
		Calendar d2 = new GregorianCalendar(2018,11,25);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,1,20);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(b.getId()));
		s1.setTmio1Conductore(conductoresLogic.findByCedula(tmioConductor.getCedula()));
		s1.setTmio1Ruta(rutasLogic.findById(ruta.getId()));
	
		serviciosLogic.createServicio(s1);
		assertNull(serviciosLogic.getServicio(s1PK));
	}
	
	@Test
	public void testCrearServicioCamposInvalidosParte2() {
		assertNotNull(serviciosLogic);
		assertNotNull(conductoresLogic);
		assertNotNull(busesLogic);
		assertNotNull(rutasLogic);
		
		Tmio1Bus b = new Tmio1Bus();
		b.setCapacidad(new BigDecimal(110));
		b.setMarca("Volvo");
		b.setModelo(new BigDecimal(2010));
		b.setPlaca("EVN879");
		b.setTipo("T");
		busesLogic.add(b);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874319");
		tmioConductor.setNombre("Johnatan");
		tmioConductor.setApellidos("Garzon");
		Calendar d = new GregorianCalendar(2018,1,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1988,1,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());		
		conductoresLogic.createConductor(tmioConductor);
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("Ruta Canasgordas");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(6));
		ruta.setHoraFin(new BigDecimal(17));
		ruta.setNumero("A11");
    	ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
    	ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
    	ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
    	rutasLogic.add(ruta);
    	 
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor(tmioConductor.getCedula());
		s1PK.setIdBus(b.getId());
		s1PK.setIdRuta(ruta.getId());
		Calendar d2 = new GregorianCalendar(2018,1,20);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,10,25);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(b.getId()));
		s1.setTmio1Conductore(conductoresLogic.findByCedula(tmioConductor.getCedula()));
		s1.setTmio1Ruta(rutasLogic.findById(ruta.getId()));
	
		serviciosLogic.createServicio(s1);
		assertNotNull(serviciosLogic.getServicio(s1PK));
		
		
		Tmio1Ruta ruta2= new Tmio1Ruta();
		ruta2.setActiva("S");
		ruta2.setDescripcion("Ruta Canasgordas");
		ruta2.setDiaInicio(new BigDecimal(3));
		ruta2.setDiaFin(new BigDecimal(4));
		ruta2.setHoraInicio(new BigDecimal(7));
		ruta2.setHoraFin(new BigDecimal(14));
		ruta2.setNumero("A11");
		ruta2.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		ruta2.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		ruta2.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
    	rutasLogic.add(ruta2);
    	
		Tmio1ServicioPK s2PK = new Tmio1ServicioPK();
		s2PK.setCedulaConductor(tmioConductor.getCedula());
		s2PK.setIdBus(b.getId());
		s2PK.setIdRuta(ruta2.getId());
		Calendar d4 = new GregorianCalendar(2018,2,20);
		s2PK.setFechaInicio(d4.getTime());
		Calendar d5 = new GregorianCalendar(2018,11,25);
		s2PK.setFechaFin(d5.getTime());
		
		Tmio1Servicio s2= new Tmio1Servicio();
		s2.setId(s2PK);
		s2.setTmio1Bus(busesLogic.findById(b.getId()));
		s2.setTmio1Conductore(conductoresLogic.findByCedula(tmioConductor.getCedula()));
		s2.setTmio1Ruta(rutasLogic.findById(ruta2.getId()));
	
		serviciosLogic.createServicio(s2);
		assertNull(serviciosLogic.getServicio(s2PK));
	}
	
	@Test
	public void testUpdateCamposValidos() {
		assertNotNull(serviciosLogic);
		assertNotNull(conductoresLogic);
		assertNotNull(busesLogic);
		assertNotNull(rutasLogic);
		
		Tmio1Bus b = new Tmio1Bus();
		b.setCapacidad(new BigDecimal(110));
		b.setMarca("Volvo");
		b.setModelo(new BigDecimal(2010));
		b.setPlaca("EVN877");
		b.setTipo("T");
		busesLogic.add(b);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874288");
		tmioConductor.setNombre("Luis");
		tmioConductor.setApellidos("Perez");
		Calendar d = new GregorianCalendar(2018,1,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1988,1,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());		
		conductoresLogic.createConductor(tmioConductor);
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("Ruta Canasgordas");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(6));
		ruta.setHoraFin(new BigDecimal(22));
		ruta.setNumero("A14");
    	ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
    	ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
    	ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
    	rutasLogic.add(ruta);
    	 
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor(tmioConductor.getCedula());
		s1PK.setIdBus(b.getId());
		s1PK.setIdRuta(ruta.getId());
		Calendar d2 = new GregorianCalendar(2018,3,20);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,11,25);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(b.getId()));
		s1.setTmio1Conductore(conductoresLogic.findByCedula(tmioConductor.getCedula()));
		s1.setTmio1Ruta(rutasLogic.findById(ruta.getId()));
	
		serviciosLogic.createServicio(s1);
		assertNotNull(serviciosLogic.getServicio(s1PK));
		
		s1PK.setFechaInicio(new GregorianCalendar(2018,3,22).getTime());
		

		serviciosLogic.updateServicio(s1);
		Calendar nuevo= new GregorianCalendar();
		nuevo.setTime(s1PK.getFechaInicio());
		assertEquals(22, nuevo.get(Calendar.DAY_OF_MONTH));
		
		eliminarDatos2(b, tmioConductor, s1, ruta);
	}
	
	@Test
	public void testEncontrarPorRangoDeFechas() {
		assertNotNull(serviciosLogic);
		assertNotNull(conductoresLogic);
		assertNotNull(busesLogic);
		assertNotNull(rutasLogic);
		
		Tmio1Bus b = new Tmio1Bus();
		b.setCapacidad(new BigDecimal(110));
		b.setMarca("Volvo");
		b.setModelo(new BigDecimal(2010));
		b.setPlaca("EVN887");
		b.setTipo("T");
		busesLogic.add(b);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874290");
		tmioConductor.setNombre("Javier");
		tmioConductor.setApellidos("Perez");
		Calendar d = new GregorianCalendar(2018,1,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1988,1,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());		
		conductoresLogic.createConductor(tmioConductor);
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("Ruta Canasgordas");
		ruta.setDiaInicio(new BigDecimal(5));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(6));
		ruta.setHoraFin(new BigDecimal(22));
		ruta.setNumero("A14");
    	ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
    	ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
    	ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
    	rutasLogic.add(ruta);
    	 
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor(tmioConductor.getCedula());
		s1PK.setIdBus(b.getId());
		s1PK.setIdRuta(ruta.getId());
		Calendar d2 = new GregorianCalendar(2018,5,25);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,8,25);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(b.getId()));
		s1.setTmio1Conductore(conductoresLogic.findByCedula(tmioConductor.getCedula()));
		s1.setTmio1Ruta(rutasLogic.findById(ruta.getId()));
	
		serviciosLogic.createServicio(s1);
		assertNotNull(serviciosLogic.getServicio(s1PK));
		
		Calendar d4 = new GregorianCalendar(2018,5,1);
		Calendar d5 = new GregorianCalendar(2018,9,28);
		List<Tmio1Servicio> servicios = serviciosLogic.findByRangeOfDates(d4, d5);
		assertNotNull("No existen servicios en este rango de fechas", servicios);
		assertEquals(1, servicios.size());
		assertEquals("1143874290", servicios.get(0).getTmio1Conductore().getCedula());
		eliminarDatos3(b, tmioConductor, s1, ruta);
	}
	
	@Test
	public void testDelete() {
		assertNotNull(serviciosLogic);
		assertNotNull(conductoresLogic);
		assertNotNull(busesLogic);
		assertNotNull(rutasLogic);
		
		Tmio1Bus b = new Tmio1Bus();
		b.setCapacidad(new BigDecimal(110));
		b.setMarca("Volvo");
		b.setModelo(new BigDecimal(2010));
		b.setPlaca("EVN897");
		b.setTipo("T");
		busesLogic.add(b);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874320");
		tmioConductor.setNombre("Jaime");
		tmioConductor.setApellidos("Perez");
		Calendar d = new GregorianCalendar(2018,4,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1988,1,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());		
		conductoresLogic.createConductor(tmioConductor);
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("Ruta Canasgordas");
		ruta.setDiaInicio(new BigDecimal(5));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(6));
		ruta.setHoraFin(new BigDecimal(22));
		ruta.setNumero("A14");
    	ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
    	ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
    	ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
    	rutasLogic.add(ruta);
    	 
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor(tmioConductor.getCedula());
		s1PK.setIdBus(b.getId());
		s1PK.setIdRuta(ruta.getId());
		Calendar d2 = new GregorianCalendar(2018,4,25);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,10,25);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		s1.setTmio1Bus(busesLogic.findById(b.getId()));
		s1.setTmio1Conductore(conductoresLogic.findByCedula(tmioConductor.getCedula()));
		s1.setTmio1Ruta(rutasLogic.findById(ruta.getId()));
	
		serviciosLogic.createServicio(s1);
		assertNotNull(serviciosLogic.getServicio(s1PK));
		serviciosLogic.deleteServicio(s1);
	}
}
