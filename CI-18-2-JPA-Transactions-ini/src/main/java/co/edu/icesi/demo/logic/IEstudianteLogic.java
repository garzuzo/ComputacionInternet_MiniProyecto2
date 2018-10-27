package co.edu.icesi.demo.logic;

import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.exceptions.LogicException;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TPrograma;

public interface IEstudianteLogic {

	public TFacultad getFacultad(String codigo) throws LogicException;
	public TCarrera getCarrera (String codigo) throws LogicException;
	public TPrograma getPrograma (String codigo) throws LogicException;
	public void createAlumno(TAlumno talumno, TProgAlumno tprogalumno) throws LogicException;
}
