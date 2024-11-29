package es.iessoterohernandez.daw.dwes.GestionNominas.laboral.service;

import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model.Empleado;
import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

	private final EmpleadoRepository empleadoRepository;

	public EmpleadoService(EmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}

	// Obtener todos los empleados
	public List<Empleado> obtenerEmpleados() {
		return empleadoRepository.findAll();
	}

	// Obtener un empleado por su DNI
	public Empleado obtenerEmpleadoPorDni(String dni) {
		return empleadoRepository.findByDni(dni);
	}

	// Filtrar empleados por un campo y valor específicos
	public List<Empleado> filtrarEmpleados(String campo, Object valor) {
		switch (campo) {
		case "nombre":
			return empleadoRepository.filtrarPorNombre((String) valor); // Filtro por nombre, se espera String
		case "sexo":
			return empleadoRepository.filtrarPorSexo(((String) valor).charAt(0)); // Suponiendo que es "M" o "F"
		case "categoria":
			return empleadoRepository.filtrarPorCategoria((Integer) valor); // Filtro por categoría, se espera Integer
		case "anyos":
			return empleadoRepository.filtrarPorAnyos((Integer) valor); // Filtro por años, se espera Integer
		default:
			throw new IllegalArgumentException("Campo no soportado: " + campo); // Lanza excepción si el campo no es
																				// válido
		}
	}

	public void eliminarEmpleado(String dni) {
		Empleado empleado = empleadoRepository.findByDni(dni);
		if (empleado != null) {
			empleadoRepository.delete(empleado);
		}
	}

}
