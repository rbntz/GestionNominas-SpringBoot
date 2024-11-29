package es.iessoterohernandez.daw.dwes.GestionNominas.laboral.repository;

import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model.Nomina;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, Long> {

    // Método para obtener todas las nóminas
    List<Nomina> findAll();

    // Método para obtener una nómina por el DNI del empleado
    // Primero, buscamos al empleado por su DNI en el EmpleadoRepository
    Nomina findByEmpleado_Dni(String dniEmpleado);
}
