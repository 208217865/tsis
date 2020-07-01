package mx.uam.tsis.ejemplobackend.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.annotations.ApiOperation;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;


@Service
public class AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	/**
	 * 
	 * @param nuevoAlumno
	 * @return el alumno que se acaba de crear si la creacion es exitosa, null de lo contrario
	 */
	
	@ApiOperation(
			value="Create",
			notes="verifica si existe el objeto alumno para evitar duplicidad y si no existe conflicto pide al repositorio guardar al nuevo alumno, recibe de parametro al objeto nuevo alumno"
			)
	public Alumno create(Alumno nuevoAlumno) {
		
		// Regla de negocio: No se puede crear más de un alumno con la misma matricula
		Optional <Alumno> alumnoOpt = alumnoRepository.findById(nuevoAlumno.getMatricula());
		
		if(alumnoOpt.isPresent()) {
			
			return null;
			
		} else {
			
			return alumnoRepository.save(nuevoAlumno);
			
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	@ApiOperation(
			value="Despliega alumnos",
			notes="Solicita la lista de alumnos al repositorio, no requiere parametros"
			)
	public Iterable <Alumno> retrieveAll () {
		return alumnoRepository.findAll();
	}
	@ApiOperation(
			value="Busca alumno",
			notes="Solicita el alumno al repositorio, requiere la matricula del alumno por matricula"
			)
	
	public Optional<Alumno> findMatricula(Integer matricula) {
		Optional <Alumno> alumno = alumnoRepository.findById(matricula);
		if(alumno.isPresent()) {
			
			return alumno;
			
		} else {
			
			return null;
			
		}
	}
	
	@ApiOperation(
			value="Actualiza Alumno",
			notes="verifica la existencia de la matricula del alumno a modificar y si no existe conflicto solicita al repositorio actualizar los datos del objeto alumno, requiere un objeto de tipo alumno como parametro"
			)
	public Alumno updateAlumno(Alumno newalumno) {
		Optional<Alumno> alumnohelper= findMatricula(newalumno.getMatricula());
		if(alumnohelper== null) {
			return null;
		}else {
		return alumnoRepository.save(newalumno);
		}
	}
	@ApiOperation(
			value="Borra alumno",
			notes="Verifica la existencia de la matricula del alumno en el repositorio, requiere la matricula del alumno a elminar como parametro"
			)
	public Optional<Alumno> eliminaAlumno(Integer matricula) {
		Optional<Alumno> alumnohelper=findMatricula(matricula);
		if(alumnohelper== null) {
			return null;
		}else {
		alumnoRepository.deleteById(matricula);
		return null;
		}
	}
	
	
}
