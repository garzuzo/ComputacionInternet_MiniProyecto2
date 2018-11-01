package co.edu.icesi.mio.testLogic;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.logic.ITmioRutasLogic;
import co.edu.icesi.mio.model.Tmio1Ruta;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestTmio1RutasLogic {

	@Autowired
	private ITmioRutasLogic rutas;

	private void setupEscenarioCamposValidos() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("via jamundi");
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setDiaInicio(new BigDecimal(7));
		ruta.setHoraFin(new BigDecimal(1440));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setId(1);
		ruta.setNumero("a12");
		rutas.add(ruta);
	}

	private void setupEscenarioCamposInvalidos() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("via jamundi");
		ruta.setDiaFin(new BigDecimal(8));
		ruta.setDiaInicio(new BigDecimal(9));
		ruta.setHoraFin(new BigDecimal(1441));
		ruta.setHoraInicio(new BigDecimal(0));
		ruta.setId(2);
		ruta.setNumero("a123");
		rutas.add(ruta);
	}

	private void setupEscenarioFindByRangoDias() {
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setActiva("S");
		ruta1.setDescripcion("via jamundi");
		ruta1.setDiaFin(new BigDecimal(6));
		ruta1.setDiaInicio(new BigDecimal(5));
		ruta1.setHoraFin(new BigDecimal(1440));
		ruta1.setHoraInicio(new BigDecimal(1));
		ruta1.setId(1);
		ruta1.setNumero("a12");
		rutas.add(ruta1);
		
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setActiva("S");
		ruta2.setDescripcion("via jamundi");
		ruta2.setDiaFin(new BigDecimal(7));
		ruta2.setDiaInicio(new BigDecimal(7));
		ruta2.setHoraFin(new BigDecimal(1440));
		ruta2.setHoraInicio(new BigDecimal(1));
		ruta2.setId(2);
		ruta2.setNumero("a12");
		rutas.add(ruta2);
		
		Tmio1Ruta ruta3 = new Tmio1Ruta();
		ruta3.setActiva("S");
		ruta3.setDescripcion("via jamundi");
		ruta3.setDiaFin(new BigDecimal(5));
		ruta3.setDiaInicio(new BigDecimal(3));
		ruta3.setHoraFin(new BigDecimal(1440));
		ruta3.setHoraInicio(new BigDecimal(1));
		ruta3.setId(3);
		ruta3.setNumero("a12");
		rutas.add(ruta3);
		
		Tmio1Ruta ruta4 = new Tmio1Ruta();
		ruta4.setActiva("S");
		ruta4.setDescripcion("via jamundi");
		ruta4.setDiaFin(new BigDecimal(6));
		ruta4.setDiaInicio(new BigDecimal(2));
		ruta4.setHoraFin(new BigDecimal(1440));
		ruta4.setHoraInicio(new BigDecimal(1));
		ruta4.setId(4);
		ruta4.setNumero("a12");
		rutas.add(ruta4);
		
	}
	
	private void setupEscenarioEliminarDatos() {
		//in process
		Tmio1Ruta ruta1=rutas.findById(1);
		Tmio1Ruta ruta2=rutas.findById(2);
		Tmio1Ruta ruta3=rutas.findById(3);
		Tmio1Ruta ruta4=rutas.findById(4);
		rutas.delete(ruta1);
		rutas.delete(ruta2);
		rutas.delete(ruta3);
		rutas.delete(ruta4);
	}

	@Test
	public void addCamposValidosTest() {
		assertNotNull(rutas);
		setupEscenarioCamposValidos();

	}

	@Test
	public void addCamposInvalidosTest() {
		assertNotNull(rutas);
		setupEscenarioCamposInvalidos();
	}

	@Test
	public void updateCamposValidosTest() {
		assertNotNull(rutas);
		setupEscenarioCamposValidos();
	}

	@Test
	public void updateCamposInvalidosTest() {
		assertNotNull(rutas);
		setupEscenarioCamposInvalidos();
	}

	@Test
	public void findByRangoDias() {
		assertNotNull(rutas);
		setupEscenarioFindByRangoDias();
		rutas.findByRangoDias(new BigDecimal(3),new BigDecimal(6));
	}
}
