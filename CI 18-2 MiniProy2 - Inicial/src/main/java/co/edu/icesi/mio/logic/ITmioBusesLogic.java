package co.edu.icesi.mio.logic;

import java.math.BigDecimal;

import co.edu.icesi.mio.model.Tmio1Bus;

public interface ITmioBusesLogic {

		public void add(Tmio1Bus bus);
	
		public void update(Tmio1Bus bus);
	
		public void delete(Tmio1Bus bus);
	
		public void findByModelo(BigDecimal m);
	
		public void findByTipo(String t);
	
		public void findByCapacidad(BigDecimal c);

}
