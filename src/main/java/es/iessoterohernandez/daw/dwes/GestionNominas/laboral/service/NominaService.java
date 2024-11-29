package es.iessoterohernandez.daw.dwes.GestionNominas.laboral.service;

import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model.Empleado;
import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model.Nomina;
import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.repository.EmpleadoRepository;
import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.repository.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NominaService {

    @Autowired
    private NominaRepository nominaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Método para obtener la nómina por el DNI del empleado
    public Nomina obtenerNominaPorDni(String dni) {
        System.out.println("Buscando nómina para el DNI: " + dni); // Agrega un log aquí
        Nomina nomina = nominaRepository.findByEmpleado_Dni(dni);
        if (nomina == null) {
            System.out.println("No se encontró la nómina para el DNI: " + dni); // Si es null, logueamos el fallo
        }
        return nomina;
    }
}