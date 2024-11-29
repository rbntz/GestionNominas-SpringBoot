package es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generado por la base de datos
	private Long id; // ID único para cada empleado

	@Column(name = "dni")
	private String dni;
	
	private String nombre;
	private char sexo;
	private int categoria;
	private int anyos;

	// Constructor vacío (requerido por JPA)
	public Empleado() {
	}

	// Constructor con parámetros
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
		
		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;

		if (categoria < 1 || categoria > 10) {
			throw new DatosNoCorrectosException("Datos no correctos");
		} else {
			this.categoria = categoria;
		}

		if (anyos < 0) {
			throw new DatosNoCorrectosException("Datos no correctos");
		} else {
			this.anyos = anyos;
		}
	}

}
