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
	
	public void eliminarDatos(Tmio1Conductore tmioConductor) {
		conductorLogic.deleteConductor(tmioConductor);
	}
	
	@Test
	public void testCrearConductorCamposValidos() {
	
		assertNotNull(conductorLogic);
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874310");
		tmioConductor.setNombre("Sandra");
		tmioConductor.setApellidos("Nino");
		Calendar d = new GregorianCalendar(2018,1,20);
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
		Calendar d2 = new GregorianCalendar(2018,1,20);
		tmioConductor2.setFechaContratacion(d2.getTime());
		Calendar d3 = new GregorianCalendar(1998,1,18);
		tmioConductor2.setFechaNacimiento(d3.getTime());
		tmioConductor2.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor2.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor2);
		
		assertNotNull(conductorLogic.findByCedula(tmioConductor.getCedula()));
		assertNotNull(conductorLogic.findByCedula(tmioConductor2.getCedula()));
		
		eliminarDatos(tmioConductor);
		eliminarDatos(tmioConductor);
	}
	

	@Test
	public void testCrearConductorCamposInvalidos() {
		assertNotNull(conductorLogic);
	 	
		Tmio1Conductore tmioConductor3 = new Tmio1Conductore();
		tmioConductor3.setCedula("114387431A");
		tmioConductor3.setNombre("Sa");
		tmioConductor3.setApellidos("Ni");
		Calendar d = new GregorianCalendar(2018,11,12);
		tmioConductor3.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(2003,1,18);
		tmioConductor3.setFechaNacimiento(d1.getTime());
		tmioConductor3.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor3.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor3);
		assertNull(conductorLogic.findByCedula(tmioConductor3.getCedula()));
	}
	
	@Test
	public void testActualizarConductorCamposValidos() {
		assertNotNull(conductorLogic);
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143875310");
		tmioConductor.setNombre("Sandra");
		tmioConductor.setApellidos("Nino");
		Calendar d = new GregorianCalendar(2018,1,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1998,1,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor);

		Tmio1Conductore conductor = conductorLogic.findByCedula(tmioConductor.getCedula());
		conductor.setNombre("Sandra Mishale");
		conductor.setApellidos("Nino Arbelaez");		
		conductorLogic.updateConductor(conductor);
		
		assertEquals("Sandra Mishale", conductorLogic.findByCedula(tmioConductor.getCedula()).getNombre());
		assertEquals("Nino Arbelaez", conductorLogic.findByCedula(tmioConductor.getCedula()).getApellidos());
		
		eliminarDatos(tmioConductor);
	}
	
	
	@Test
	public void testEncontrarPorNombre() {
		assertNotNull(conductorLogic);
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874250");
		tmioConductor.setNombre("Juana Maria");
		tmioConductor.setApellidos("Nino");
		Calendar d = new GregorianCalendar(2018,1,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1998,1,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor);
		assertNotNull(conductorLogic.findByCedula("1143874250"));
	
		
		List<Tmio1Conductore> conductores = conductorLogic.findByName("Juana Maria");
		assertNotNull("No se encontro el conductor por ese nombre", conductores);
		assertEquals(1, conductores.size());
		assertEquals("1143874250", conductores.get(0).getCedula());
		eliminarDatos(tmioConductor);
	}
	
	@Test
	public void testEncontrarPorApellido() {
		assertNotNull(conductorLogic);
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874260");
		tmioConductor.setNombre("Sandra Milena");
		tmioConductor.setApellidos("Nino Arbelaez");
		Calendar d = new GregorianCalendar(2018,1,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1998,1,18);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor);
		assertNotNull(conductorLogic.findByCedula("1143874260"));
		
		Tmio1Conductore tmioConductor2 = new Tmio1Conductore();
		tmioConductor2.setCedula("1143874264");
		tmioConductor2.setNombre("Natalie");
		tmioConductor2.setApellidos("Nino Arbelaez");
		Calendar d2 = new GregorianCalendar(2018,1,20);
		tmioConductor2.setFechaContratacion(d2.getTime());
		Calendar d3 = new GregorianCalendar(1998,1,18);
		tmioConductor2.setFechaNacimiento(d3.getTime());
		tmioConductor2.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor2.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor2);
		
		List<Tmio1Conductore> conductores = conductorLogic.findByLastname("Nino Arbelaez");
		assertNotNull("No se encontro el conductor por ese apellido", conductores);
		assertEquals(2, conductores.size());
		for(int i=0;i<conductores.size(); i++) {
			System.out.println(conductores.get(i).getCedula());
		}
		assertTrue(conductores.get(0).getCedula().equals("1143874260") || conductores.get(1).getCedula().equals("1143874260"));
		assertTrue(conductores.get(0).getCedula().equals("1143874264") || conductores.get(1).getCedula().equals("1143874264"));
		
		eliminarDatos(tmioConductor);
		eliminarDatos(tmioConductor2);
	}	
	
	@Test
	public void testDelete() {
		assertNotNull(conductorLogic);
		
		Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("1143874318");
		tmioConductor.setNombre("Pedro");
		tmioConductor.setApellidos("Suarez");
		Calendar d = new GregorianCalendar(2018,3,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1967,3,25);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorLogic.createConductor(tmioConductor);
		conductorLogic.deleteConductor(tmioConductor);
	}
}
