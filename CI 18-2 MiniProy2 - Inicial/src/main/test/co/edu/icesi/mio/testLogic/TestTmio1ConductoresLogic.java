package co.edu.icesi.mio.testLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.logic.ITmioConductoresLogic;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestTmio1ConductoresLogic {

	@Autowired
	private ITmioConductoresLogic conductorLogic;
	

	@Test
	public void testCrearConductorCamposValidos() {
		assertNotNull(conductorLogic);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874310");
		tmioConductor.setNombre("Sandra");
		tmioConductor.setApellidos("Nino");
		Calendar d = new GregorianCalendar(2018,01,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1998,01,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor);
		assertNotNull(conductorLogic.findByCedula("1143874310"));
		
		Tmio1Conductore tmioConductor2 = new Tmio1Conductore();
		tmioConductor2.setCedula("1143874314");
		tmioConductor2.setNombre("Sandra");
		tmioConductor2.setApellidos("Nino Arbelaez");
		Calendar d2 = new GregorianCalendar(2018,01,20);
		tmioConductor2.setFechaContratacion(d2.getTime());
		Calendar d3 = new GregorianCalendar(1998,01,18);
		tmioConductor2.setFechaNacimiento(d3.getTime());
		tmioConductor2.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor2.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor2);
		assertNotNull(conductorLogic.findByCedula("1143874314"));
	}
	
	@Test
	public void testCrearConductorCamposInvalidos() {
		assertNotNull(conductorLogic);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("114387431A");
		tmioConductor.setNombre("Sa");
		tmioConductor.setApellidos("Ni");
		Calendar d = new GregorianCalendar(2018,11,12);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(2003,01,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor);
		assertNull(conductorLogic.findByCedula("114387431A"));
	}
	
	@Test
	public void testActualizarConductorCamposValidos() {
		assertNotNull(conductorLogic);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874310");
		tmioConductor.setNombre("Sandra Mishale");
		tmioConductor.setApellidos("Nino Arbelaez");
		Calendar d = new GregorianCalendar(2018,01,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1998,01,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.updateConductor(tmioConductor);
		assertEquals("Sandra Mishale", conductorLogic.findByCedula("1143874310").getNombre());
		assertEquals("Nino Arbelaez", conductorLogic.findByCedula("1143874310").getApellidos());
		//conductorLogic.deleteConductor(tmioConductor);
	}
	
	@Test
	public void testEncontrarPorNombre() {
		assertNotNull(conductorLogic);
		
		List<Tmio1Conductore> conductores = conductorLogic.findByName("Sandra Mishale");
		assertNotNull("No se encontro el conductor por ese nombre", conductores);
		assertEquals(1, conductores.size());
		assertEquals("1143874310", conductores.get(0).getCedula());
	}
	
	@Test
	public void testEncontrarPorApellido() {
		assertNotNull(conductorLogic);
		
		List<Tmio1Conductore> conductores = conductorLogic.findByLastname("Nino Arbelaez");
		assertNotNull("No se encontro el conductor por ese nombre", conductores);
		assertEquals(2, conductores.size());
		for(int i=0;i<conductores.size(); i++) {
			System.out.println(conductores.get(i).getCedula());
		}
		assertTrue(conductores.get(0).getCedula().equals("1143874310") || conductores.get(1).getCedula().equals("1143874310"));
		assertTrue(conductores.get(0).getCedula().equals("1143874314") || conductores.get(1).getCedula().equals("1143874314"));
	}	
}
