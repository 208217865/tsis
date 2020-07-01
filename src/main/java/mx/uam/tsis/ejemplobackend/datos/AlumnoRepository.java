package mx.uam.tsis.ejemplobackend.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.servicios.AlumnoController;

/**
 * Se encarga de almacenar y recuperar alumnos
 * 
 * @author uriel cortes
 *
 */
@Component

public interface AlumnoRepository extends CrudRepository<Alumno, Integer>{
 
}
