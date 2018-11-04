package co.edu.icesi.mio.testLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

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

	private int ruta1;
	private int ruta2;
	private int ruta3;

	private void setupEscenarioCamposValidos() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("via jamundi");
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setDiaInicio(new BigDecimal(7));
		ruta.setHoraFin(new BigDecimal(1440));
		ruta.setHoraInicio(new BigDecimal(1));

		ruta.setNumero("a12");
		rutas.add(ruta);

		ruta1 = ruta.getId();
	}

	private void setupEscenarioCamposValidosUpdateValido() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("via jamundi");
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setDiaInicio(new BigDecimal(7));
		ruta.setHoraFin(new BigDecimal(1440));
		ruta.setHoraInicio(new BigDecimal(1));

		ruta.setNumero("a12");
		rutas.add(ruta);

		ruta2 = ruta.getId();
	}

	private void setupEscenarioCamposValidosUpdateInvalido() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("via jamundi");
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setDiaInicio(new BigDecimal(7));
		ruta.setHoraFin(new BigDecimal(1440));
		ruta.setHoraInicio(new BigDecimal(1));

		ruta.setNumero("a12");
		rutas.add(ruta);

		ruta3 = ruta.getId();
	}

	private boolean setupEscenarioCamposInvalidos() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("via jamundi");
		ruta.setDiaFin(new BigDecimal(8));
		ruta.setDiaInicio(new BigDecimal(9));
		ruta.setHoraFin(new BigDecimal(1441));
		ruta.setHoraInicio(new BigDecimal(0));

		ruta.setNumero("a123");
		return rutas.add(ruta);
	}

	private int[] setupEscenarioFindByRangoDias() {
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setActiva("S");
		ruta1.setDescripcion("via jamundi");
		ruta1.setDiaFin(new BigDecimal(6));
		ruta1.setDiaInicio(new BigDecimal(5));
		ruta1.setHoraFin(new BigDecimal(1440));
		ruta1.setHoraInicio(new BigDecimal(1));

		ruta1.setNumero("a12");
		rutas.add(ruta1);

		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setActiva("S");
		ruta2.setDescripcion("via jamundi");
		ruta2.setDiaFin(new BigDecimal(7));
		ruta2.setDiaInicio(new BigDecimal(7));
		ruta2.setHoraFin(new BigDecimal(1440));
		ruta2.setHoraInicio(new BigDecimal(1));

		ruta2.setNumero("a12");
		rutas.add(ruta2);

		Tmio1Ruta ruta3 = new Tmio1Ruta();
		ruta3.setActiva("S");
		ruta3.setDescripcion("via jamundi");
		ruta3.setDiaFin(new BigDecimal(5));
		ruta3.setDiaInicio(new BigDecimal(3));
		ruta3.setHoraFin(new BigDecimal(1440));
		ruta3.setHoraInicio(new BigDecimal(1));

		ruta3.setNumero("a12");
		rutas.add(ruta3);

		Tmio1Ruta ruta4 = new Tmio1Ruta();
		ruta4.setActiva("S");
		ruta4.setDescripcion("via jamundi");
		ruta4.setDiaFin(new BigDecimal(6));
		ruta4.setDiaInicio(new BigDecimal(2));
		ruta4.setHoraFin(new BigDecimal(1440));
		ruta4.setHoraInicio(new BigDecimal(1));

		ruta4.setNumero("a12");
		rutas.add(ruta4);
		int[] arr = { ruta1.getId(), ruta2.getId(),ruta3.getId(), ruta4.getId() };
		return arr;
	}

	private void setupEscenarioEliminarDatos(int[] arr) {

		Tmio1Ruta ruta1 = rutas.findById(arr[0]);
		Tmio1Ruta ruta2 = rutas.findById(arr[1]);
		Tmio1Ruta ruta3 = rutas.findById(arr[2]);
		Tmio1Ruta ruta4 = rutas.findById(arr[3]);
		rutas.delete(ruta1);
		rutas.delete(ruta2);
		rutas.delete(ruta3);
		rutas.delete(ruta4);
	}

	@Test
	public void addCamposValidosTest() {
		assertNotNull(rutas);
		setupEscenarioCamposValidos();

		assertNotNull(rutas.findById(ruta1));
		rutas.delete(rutas.findById(ruta1));
	}

	@Test
	public void addCamposInvalidosTest() {
		assertNotNull(rutas);
		assertFalse(setupEscenarioCamposInvalidos());

	}

	@Test
	public void updateCamposValidosTest() {
		assertNotNull(rutas);
		setupEscenarioCamposValidosUpdateValido();
		Tmio1Ruta ruta = rutas.findById(ruta2);
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setDiaInicio(new BigDecimal(3));
		rutas.update(ruta);

		assertEquals(rutas.findById(ruta2).getDiaFin(), new BigDecimal(5));
		assertEquals(rutas.findById(ruta2).getDiaInicio(), new BigDecimal(3));
		rutas.delete(rutas.findById(ruta2));
	}

	@Test
	public void updateCamposInvalidosTest() {
		assertNotNull(rutas);
		setupEscenarioCamposValidosUpdateInvalido();
		Tmio1Ruta ruta = rutas.findById(ruta3);

		ruta.setDiaFin(new BigDecimal(8));
		ruta.setHoraFin(new BigDecimal(1445));
		ruta.setNumero("a1234");
		rutas.update(ruta);
		// tienen que seguir con los valores anteriores, ya que los ingresados
		// no son validos
		assertNotEquals(rutas.findById(ruta3).getDiaFin(), new BigDecimal(8));
		assertNotEquals(rutas.findById(ruta3).getHoraFin(), new BigDecimal(1445));
		assertNotEquals(rutas.findById(ruta3).getNumero(), "a1234");

		rutas.delete(rutas.findById(ruta3));
	}

	@Test
	public void findByRangoDias() {
		assertNotNull(rutas);
		int[] arr = setupEscenarioFindByRangoDias();

		List<Tmio1Ruta> list = rutas.findByRangoDias(new BigDecimal(3), new BigDecimal(6));

		assertEquals(list.size(), 2);

		setupEscenarioEliminarDatos(arr);

	}
}
