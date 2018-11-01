package co.edu.icesi.mio.testLogic;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

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

	private void setupEscenarioCamposValidos() {
		Tmio1Bus b = new Tmio1Bus();

		b.setId(2);
		b.setCapacidad(new BigDecimal(10));
		b.setMarca("1234");
		b.setModelo(new BigDecimal(4943));
		b.setPlaca("123456");
		b.setTipo("P");
		buses.add(b);

	}

	private void setupEscenarioCamposInvalidos() {

		Tmio1Bus b = new Tmio1Bus();

		b.setId(3);
		b.setCapacidad(new BigDecimal(0));
		b.setMarca("123");
		b.setModelo(new BigDecimal(494));
		b.setPlaca("12345");
		b.setTipo("D");

		buses.add(b);

	}

	private void setupEscenarioFindByModeloTipoCapacidad() {

		Tmio1Bus b1 = new Tmio1Bus();

		b1.setId(2);
		b1.setCapacidad(new BigDecimal(10));
		b1.setMarca("1234");
		b1.setModelo(new BigDecimal(4943));
		b1.setPlaca("123456");
		b1.setTipo("P");
		buses.add(b1);

		Tmio1Bus b2 = new Tmio1Bus();

		b2.setId(2);
		b2.setCapacidad(new BigDecimal(2));
		b2.setMarca("4444");
		b2.setModelo(new BigDecimal(4943));
		b2.setPlaca("123456");
		b2.setTipo("A");
		buses.add(b2);

		Tmio1Bus b3 = new Tmio1Bus();

		b3.setId(3);
		b3.setCapacidad(new BigDecimal(10));
		b3.setMarca("1234");
		b3.setModelo(new BigDecimal(4943));
		b3.setPlaca("123456");
		b3.setTipo("P");
		buses.add(b3);
	}

	private void setupEscenarioEliminarDatos() {
		//in process
		Tmio1Bus b1=buses.findById(1);
		Tmio1Bus b2=buses.findById(2);
		Tmio1Bus b3=buses.findById(3);
		buses.delete(b1);
		buses.delete(b2);
		buses.delete(b3);
	}
	@Test
	public void addCamposValidosTest() {
		assertNotNull(buses);

		setupEscenarioCamposValidos();

	}

	@Test
	public void addCamposInvalidosTest() {
		assertNotNull(buses);
		setupEscenarioCamposInvalidos();

	}

	@Test
	public void updateCamposValidosTest() {
		assertNotNull(buses);
		setupEscenarioCamposValidos();
	}

	@Test
	public void findByModelo() {
		assertNotNull(buses);

		setupEscenarioFindByModeloTipoCapacidad();
		// me debe retornar una lista, hay que cambiar varios retornos
		buses.findByModelo(new BigDecimal(1234));

	}

	@Test
	public void findByTipo() {
		assertNotNull(buses);
		// me debe retornar una lista
		setupEscenarioFindByModeloTipoCapacidad();
		buses.findByTipo("P");
	}

	@Test
	public void findByCapacidad() {
		assertNotNull(buses);
		// me debe retornar una lista
		setupEscenarioFindByModeloTipoCapacidad();
		buses.findByCapacidad(new BigDecimal(2));
	}
}
