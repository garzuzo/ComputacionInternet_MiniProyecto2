package co.icesi.edu.demo.testlogic;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITAlumnoDao;
import co.edu.icesi.demo.dao.ITProgramaDao;
import co.edu.icesi.demo.exceptions.LogicException;
import co.edu.icesi.demo.logic.IEstudianteLogic;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestEstudianteLogic {

	@Autowired
	private IEstudianteLogic alumnoLogic;
	
	@Autowired
	private ITProgramaDao programaDAO;
	
	@Test
	public void testCrearAlumno() throws LogicException {

		assertNotNull(alumnoLogic);
		TAlumno talumno = new TAlumno();
		talumno.setNombre("Pedro");
		talumno.setApellidos("Nino");
		talumno.setCodigo("A0002262");
		talumno.setSexo("M");
		talumno.setTipo("E");	
		talumno.setTProgAlumnos(new ArrayList<TProgAlumno>());	
		
		TProgAlumnoPK tprogalumnopk = new TProgAlumnoPK ();
		tprogalumnopk.setAlumnoCodigo(talumno.getCodigo());
		tprogalumnopk.setPeriodoAcad("182");
		tprogalumnopk.setPrincipal("S");
		tprogalumnopk.setProgramaCodigo(programaDAO.findById("18").getCodigo());
		
		TProgAlumno tprogalumno = new TProgAlumno();
		tprogalumno.setSemestre("2");
		tprogalumno.setCohorte("181");
		tprogalumno.setId(tprogalumnopk);	
		tprogalumno.setTAlumno(talumno);
		tprogalumno.setTPrograma(programaDAO.findById("18"));
		
		alumnoLogic.createAlumno(talumno, tprogalumno);
	}
	
}
