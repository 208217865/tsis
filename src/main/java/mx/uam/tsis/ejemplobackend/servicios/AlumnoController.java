package mx.uam.tsis.ejemplobackend.servicios;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.AlumnoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

/**
 * Controlador para el API rest
 * 
 * @author humbertocervantes
 *
 */
@RestController
@Slf4j
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@ApiOperation(
			value="Crear alumno",
			notes="Permite crear un nuevo alumno, la matricula debe ser unica"
			)
	
	
	@PostMapping(path = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Alumno nuevoAlumno) {
				
		log.info("Recibí llamada a create con "+nuevoAlumno);
		
		Alumno alumno = alumnoService.create(nuevoAlumno);
		
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumno);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear alumno");
		}
	

	}
	
	@ApiOperation(
			value="Desplegar Alumnos",
			notes="Permite visualizar la lista existente de alumnos"
			)
	
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Alumno> result = alumnoService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}

	@ApiOperation(
			value="Buscar alumno por matricula",
			notes="Permite visualizar a un alumno, utilizando la matricula como parametro"
			)
	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("matricula") Integer matricula) {
		log.info("Buscando al alumno con matricula "+matricula);
		Optional<Alumno> alumnoEncontrado = alumnoService.findMatricula(matricula);
		if(alumnoEncontrado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumnoEncontrado);			
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no existe alumno con esa matricula");
		}
		
		
	}
	@ApiOperation(
			value="Actualizar alumno",
			notes="Permite actualizar los datos de un alumno, la matricula no se actualiza solo el nombre y la carrera"
			)
	@PutMapping(path= "/alumnos/{matricula}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> update(@PathVariable("matricula") Integer matricula, @RequestBody Alumno alumnonew) {
		log.info("Update the student with this : " + alumnonew);
		Alumno alumnoactualizado = alumnoService.updateAlumno(alumnonew);
		if(alumnonew !=null) {
			return ResponseEntity.status(HttpStatus.OK).body("alumno actualizado");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno con matricula"+matricula+"no encontrado");
		}
	}

	
	@ApiOperation(
			value="Borrar alumno",
			notes="Permite eliminar a un alumno existente"
			)
	@DeleteMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> delete(@PathVariable("matricula") Integer matricula) {
		log.info("Delete the student with the registration number: " + matricula);
		Optional<Alumno> eliminado = alumnoService.eliminaAlumno(matricula);
		if( eliminado ==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("alumno eliminado"+eliminado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
