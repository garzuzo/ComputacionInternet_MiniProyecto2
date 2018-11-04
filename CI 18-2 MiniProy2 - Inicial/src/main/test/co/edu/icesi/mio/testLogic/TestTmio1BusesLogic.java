package co.edu.icesi.mio.testLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.logic.ITmioBusesLogic;
import co.edu.icesi.mio.model.Tmio1Bus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestTmio1BusesLogic {

	@Autowired
	private ITmioBusesLogic buses;

	private int b1;
	private int b2;
	private int b11;
	private int b12;
	private int b13;

	// private Tmio1Bus b;

	public void setupEscenarioCamposValidos() {
		// assertNotNull(buses);
		Tmio1Bus b = new Tmio1Bus();

		// b.setId(1);
		b.setCapacidad(new BigDecimal(10));
		b.setMarca("1234");
		b.setModelo(new BigDecimal(4943));
		b.setPlaca("123456");
		b.setTipo("P");
		buses.add(b);

		b1 = b.getId();
	}

	public boolean setupEscenarioCamposInvalidos() {
		// assertNotNull(buses);
		Tmio1Bus b = new Tmio1Bus();

		b.setCapacidad(new BigDecimal(0));
		b.setMarca("123");
		b.setModelo(new BigDecimal(494));
		b.setPlaca("12345");
		b.setTipo("D");

		return buses.add(b);

	}

	public int[] setupEscenarioFindByCapacidad() {
		// assertNotNull(buses);
		Tmio1Bus b1 = new Tmio1Bus();

		b1.setCapacidad(new BigDecimal(10));
		b1.setMarca("1234");
		b1.setModelo(new BigDecimal(1234));
		b1.setPlaca("123456");
		b1.setTipo("P");
		buses.add(b1);

		Tmio1Bus b2 = new Tmio1Bus();

		b2.setCapacidad(new BigDecimal(2));
		b2.setMarca("4444");
		b2.setModelo(new BigDecimal(4943));
		b2.setPlaca("123456");
		b2.setTipo("A");
		buses.add(b2);

		Tmio1Bus b3 = new Tmio1Bus();

		b3.setCapacidad(new BigDecimal(10));
		b3.setMarca("1234");
		b3.setModelo(new BigDecimal(1234));
		b3.setPlaca("123456");
		b3.setTipo("P");
		buses.add(b3);

		int[] arr = { b1.getId(), b2.getId(), b3.getId() };
		return arr;
	}

	public int[] setupEscenarioFindByTipo() {

		Tmio1Bus b1 = new Tmio1Bus();

		// b1.setId(1);
		b1.setCapacidad(new BigDecimal(10));
		b1.setMarca("1234");
		b1.setModelo(new BigDecimal(1234));
		b1.setPlaca("123456");
		b1.setTipo("P");
		buses.add(b1);

		Tmio1Bus b2 = new Tmio1Bus();

		b2.setCapacidad(new BigDecimal(2));
		b2.setMarca("4444");
		b2.setModelo(new BigDecimal(4943));
		b2.setPlaca("123456");
		b2.setTipo("A");
		buses.add(b2);

		Tmio1Bus b3 = new Tmio1Bus();

		b3.setCapacidad(new BigDecimal(10));
		b3.setMarca("1234");
		b3.setModelo(new BigDecimal(1234));
		b3.setPlaca("123456");
		b3.setTipo("P");
		buses.add(b3);

		int[] arr = { b1.getId(), b2.getId(), b3.getId() };
		return arr;
	}

	public int[] setupEscenarioFindByModelo() {

		Tmio1Bus b1 = new Tmio1Bus();

		b1.setCapacidad(new BigDecimal(10));
		b1.setMarca("1234");
		b1.setModelo(new BigDecimal(1234));
		b1.setPlaca("123456");
		b1.setTipo("P");
		buses.add(b1);

		Tmio1Bus b2 = new Tmio1Bus();

		b2.setCapacidad(new BigDecimal(2));
		b2.setMarca("4444");
		b2.setModelo(new BigDecimal(4943));
		b2.setPlaca("123456");
		b2.setTipo("A");
		buses.add(b2);

		Tmio1Bus b3 = new Tmio1Bus();

		b3.setCapacidad(new BigDecimal(10));
		b3.setMarca("1234");
		b3.setModelo(new BigDecimal(1234));
		b3.setPlaca("123456");
		b3.setTipo("P");
		buses.add(b3);

		int[] arr = { b1.getId(), b2.getId(), b3.getId() };
		return arr;
	}

	public void setupEscenarioEliminarDatos() {

		Tmio1Bus bus1 = buses.findById(b1);

		buses.delete(bus1);

	}

	public void setupEscenarioEliminarTipoCapacidadModelo(int b1, int b2, int b3) {
		Tmio1Bus bus3 = buses.findById(b1);
		Tmio1Bus bus4 = buses.findById(b2);
		Tmio1Bus bus5 = buses.findById(b3);
		buses.delete(bus3);
		buses.delete(bus4);
		buses.delete(bus5);
	}

	@Test
	public void addCamposValidosTest() {
		assertNotNull(buses);

		setupEscenarioCamposValidos();

		assertNotNull(buses.findById(b1));

		setupEscenarioEliminarDatos();
	}

	@Test
	public void addCamposInvalidosTest() {
		assertNotNull(buses);
		assertFalse(setupEscenarioCamposInvalidos());

	}

	@Test
	public void updateCamposValidosTest() {
		assertNotNull(buses);
		setupEscenarioCamposValidos();
		Tmio1Bus b = buses.findById(b1);
		b.setModelo(new BigDecimal(4444));
		b.setTipo("A");
		b.setCapacidad(new BigDecimal(3));
		buses.update(b);
		assertEquals(buses.findById(b1).getModelo(), new BigDecimal(4444));
		assertEquals(buses.findById(b1).getTipo(), "A");
		assertEquals(buses.findById(b1).getCapacidad(), new BigDecimal(3));

		setupEscenarioEliminarDatos();
	}

	@Test
	public void findByModelo() {
		assertNotNull(buses);

		int arr[] = setupEscenarioFindByModelo();

		List<Tmio1Bus> lista = buses.findByModelo(new BigDecimal(1234));
		assertEquals(lista.size(), 2);

		setupEscenarioEliminarTipoCapacidadModelo(arr[0], arr[1], arr[2]);
	}

	@Test
	public void findByTipo() {
		assertNotNull(buses);

		int arr[] = setupEscenarioFindByTipo();
		List<Tmio1Bus> lista = buses.findByTipo("P");
		assertEquals(lista.size(), 2);

		setupEscenarioEliminarTipoCapacidadModelo(arr[0], arr[1], arr[2]);
	}

	@Test
	public void findByCapacidad() {
		assertNotNull(buses);

		int arr[] = setupEscenarioFindByCapacidad();
		List<Tmio1Bus> lista = buses.findByCapacidad(new BigDecimal(2));

		setupEscenarioEliminarTipoCapacidadModelo(arr[0], arr[1], arr[2]);
	}
}
