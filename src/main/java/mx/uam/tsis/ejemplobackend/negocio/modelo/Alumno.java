package mx.uam.tsis.ejemplobackend.negocio.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * La clase representa a los alumnos
 * @author urielcortes
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity // Indica que hay que persistir en BD
public class Alumno {
	@NotNull
	@ApiModelProperty(notes="Matricula del alumno", required=true)
	@Id // Indica que es la llave primaria
	private Integer matricula;
	
	@NotBlank
	@ApiModelProperty(notes="Nombre del alumno", required=true)
	private String nombre;

	@NotBlank
	@ApiModelProperty(notes="Carrera del alumno", required=true)
	private String carrera;
}
