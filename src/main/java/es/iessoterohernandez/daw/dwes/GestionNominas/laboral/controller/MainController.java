package es.iessoterohernandez.daw.dwes.GestionNominas.laboral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model.Empleado;
import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model.Nomina;
import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.service.EmpleadoService;
import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.service.NominaService;

import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private NominaService nominaService;

	// Redirigir al índice al acceder a la raíz
	@GetMapping("/")
	public String inicio() {
		return "index";
	}

	// Listar todos los empleados
	@GetMapping("/listar")
	public String listarEmpleados(Model model) {
		List<Empleado> lista = empleadoService.obtenerEmpleados();
		model.addAttribute("lista", lista);
		return "listar"; // Vista listar.html dentro de templates
	}

	@GetMapping("/eliminar")
	public String eliminarEmpleado(@RequestParam("dni") String dniEmpleado, Model model) {
		try {
			empleadoService.eliminarEmpleado(dniEmpleado);
			System.out.println("Empleado con DNI " + dniEmpleado + " eliminado satisfactoriamente.");

			return "redirect:/";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "Error al eliminar el empleado.");
			return "error";
		}
	}

	// Mostrar formulario para filtrar empleados
	@GetMapping("/filtrarEmpleados")
	public String mostrarFormularioFiltrar() {
		return "filtrarEmpleados"; // Vista filtrarEmpleados.html
	}

	// Filtrar empleados
	@PostMapping("/listarEmpleadosFiltrados")
	public String listarEmpleadosFiltrados(@RequestParam("campo") String campo, @RequestParam("valor") String valor,
			Model model) {
		Object valorConvertido = valor; // Por defecto es un String

		if ("categoria".equals(campo) || "anyos".equals(campo)) {
			try {
				valorConvertido = Integer.parseInt(valor); // Convertir a Integer
			} catch (NumberFormatException e) {
				model.addAttribute("error", "Valor no válido para " + campo);
				return "listarEmpleadosFiltrados"; // Devolver la vista con el error
			}
		}

		List<Empleado> lista = empleadoService.filtrarEmpleados(campo, valorConvertido);
		if (lista == null || lista.isEmpty()) {
			model.addAttribute("error", "No se encontraron empleados con ese filtro.");
		} else {
			model.addAttribute("lista", lista);
		}
		return "listarEmpleadosFiltrados"; // Vista listarEmpleadosFiltrados.html
	}

	// Calcular nómina
	@GetMapping("/buscarEmpleado")
	public String mostrarFormularioCalcularNomina(Model model) {
	    try {
	        // Lógica antes de retornar la vista, si es necesario
	        return "buscarEmpleado"; // Vista buscarEmpleado.html
	    } catch (Exception e) {
	        model.addAttribute("error", "Hubo un error al cargar la vista: " + e.getMessage());
	        return "error"; // Vista de error (asegúrate de tener esta vista)
	    }
	}

	// Procesar cálculo de nómina
	@PostMapping("/calcularNomina")
	public String calcularNomina(@RequestParam("dni") String dniEmpleado, Model model) {
	    System.out.println("DNI recibido: " + dniEmpleado); // Agregar esta línea para depurar
	    Nomina nomina = nominaService.obtenerNominaPorDni(dniEmpleado);

	    if (nomina == null) {
	        model.addAttribute("error", "El DNI ingresado no existe o no tiene nómina asociada.");
	    } else if (nomina.getSueldoCalculado() <= 0) {
	        model.addAttribute("error", "No se pudo calcular el sueldo para el empleado con DNI " + dniEmpleado);
	    } else {
	        model.addAttribute("nomina", nomina);
	    }
	    return "calcularNomina";  // Vista calcularNomina.html
	}

}
