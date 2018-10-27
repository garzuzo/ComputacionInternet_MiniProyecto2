package co.edu.icesi.demo.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITAlumnoDao;
import co.edu.icesi.demo.dao.ITCarreraDao;
import co.edu.icesi.demo.dao.ITFacultadDao;
import co.edu.icesi.demo.dao.ITProgAlumnoDao;
import co.edu.icesi.demo.dao.ITProgramaDao;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TMatxaprobar;
import co.edu.icesi.demo.model.TMatxprograma;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

@Service
public class DummyLogic implements IDummyLogic {
	

	@Autowired
	private ITCarreraDao carreraDAO;
	
	@Autowired
	private ITProgramaDao programaDAO;
	
	@Autowired
	private ITAlumnoDao alumnoDAO;
	
	@Autowired
	private ITFacultadDao facultadDAO;
	
	@Autowired
	private ITProgAlumnoDao progAlumnoDAO;
	
	@Transactional
	public void createAlumno() {

		TFacultad tfacultad= new TFacultad();
		tfacultad.setCodigo("20");
		tfacultad.setDescripcion("Ingenieria");
		ArrayList<TCarrera> carreras= new ArrayList<TCarrera>(); 
		tfacultad.setTCarreras(carreras);
		
		TCarrera tcarrera = new TCarrera();
		tcarrera.setCodigo("18");
		tcarrera.setDescripcion("Ingenieria Sistemas");
		tcarrera.setTProgramas(new ArrayList<TPrograma>());
		tcarrera.setTFacultad(tfacultad);
		carreras.add(tcarrera);
		facultadDAO.save(tfacultad);
		carreraDAO.save(tcarrera);
		
		TPrograma tprograma = new TPrograma();
		tprograma.setCodigo("18");
		tprograma.setAlias("SIS");
		tprograma.setDescripcion("Ingenieria Sistemas");
		tprograma.setTProgAlumnos(new ArrayList<TProgAlumno>());
		tprograma.setTMatxaprobars(new ArrayList<TMatxaprobar>());
		tprograma.setTMatxprogramas(new ArrayList<TMatxprograma>());
		tprograma.setTCarrera(tcarrera);
		
		programaDAO.save(tprograma);
		
		TAlumno talumno = new TAlumno();
		talumno.setNombre("Jack");
		talumno.setApellidos("Bauer");
		talumno.setCodigo("a174815");
		talumno.setSexo("M");
		talumno.setTipo("E");		
		talumno.setTProgAlumnos(new ArrayList<TProgAlumno>());

		alumnoDAO.save(talumno);
		TProgAlumnoPK tprogalumnopk = new TProgAlumnoPK ();
		tprogalumnopk.setAlumnoCodigo(talumno.getCodigo());
		tprogalumnopk.setPeriodoAcad("182");
		tprogalumnopk.setPrincipal("S");
		tprogalumnopk.setProgramaCodigo(tprograma.getCodigo());
		
		TProgAlumno tprogalumno = new TProgAlumno();
		tprogalumno.setSemestre("1");
		tprogalumno.setCohorte("182");
		tprogalumno.setId(tprogalumnopk);	
		tprogalumno.setTAlumno(talumno);
		tprogalumno.setTPrograma(tprograma);

		progAlumnoDAO.save(tprogalumno);
	}
}
