package mx.uam.tsis.ejemplobackend.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Alumno create(Alumno nuevoAlumno) {
		
		// Regla de negocio: No se puede crear más de un alumno con la misma matricula
		Alumno alumno = alumnoRepository.findByMatricula(nuevoAlumno.getMatricula());
		
		if(alumno == null) {
			
			return alumnoRepository.save(nuevoAlumno);
			
		} else {
			
			return null;
			
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List <Alumno> retrieveAll () {
		return alumnoRepository.find();
	}
	
	public Alumno findMatricula(Integer matricula) {
		Alumno alumno = alumnoRepository.findByMatricula(matricula);
		if(alumno == null) {
			
			return null;
			
		} else {
			
			return alumno;
			
		}
	}
	
	
	public Alumno updateAlumno(Alumno newalumno) {
		Alumno alumnohelper=findMatricula(newalumno.getMatricula());
		if(alumnohelper== null) {
			return null;
		}else {
		return alumnoRepository.update(newalumno);
		}
	}
	
	public Alumno eliminaAlumno(Integer matricula) {
		Alumno alumnohelper=findMatricula(matricula);
		if(alumnohelper== null) {
			return null;
		}else {
		return alumnoRepository.delete(matricula);
		}
	}
	
	
}
