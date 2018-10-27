package co.edu.icesi.demo.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITAlumnoDao;
import co.edu.icesi.demo.dao.ITCarreraDao;
import co.edu.icesi.demo.dao.ITFacultadDao;
import co.edu.icesi.demo.dao.ITProgAlumnoDao;
import co.edu.icesi.demo.dao.ITProgramaDao;
import co.edu.icesi.demo.exceptions.LogicException;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

@Service
public class EstudiantesLogic implements IEstudianteLogic {

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
	public TFacultad getFacultad (String codigo) throws LogicException
	{
		return facultadDAO.findById(codigo);
	}

	@Transactional
	public TCarrera getCarrera (String codigo) throws LogicException
	{
		return carreraDAO.findById(codigo);
	}
	
	@Transactional
	public TPrograma getPrograma (String codigo) throws LogicException
	{
		return programaDAO.findById(codigo);
	}
	
	@Transactional
	public void createAlumno(TAlumno talumno, TProgAlumno tprogalumno) throws LogicException {
		
		if(talumno != null && tprogalumno != null) {
	
			if(talumno.getCodigo()!= null && alumnoDAO.findById(talumno.getCodigo()) == null) {
				if(talumno.getNombre() != null
						&& talumno.getApellidos() != null 
						&& talumno.getSexo() != null 
						&& talumno.getTipo() != null) 
					alumnoDAO.save(talumno);
				else
					throw new LogicException();
			}
			else {
				throw new LogicException();
			}
			
			if(tprogalumno.getId() != null) {
				if(tprogalumno.getSemestre()!= null &&
						tprogalumno.getCohorte() != null &&
						getPrograma(tprogalumno.getTPrograma().getCodigo()) != null) {
					progAlumnoDAO.save(tprogalumno);
				}
				else {
					throw new LogicException();
				}
			}
			else {
				throw new LogicException();
			}
			
		}
		else {
			throw new LogicException();
		}
	}

}
