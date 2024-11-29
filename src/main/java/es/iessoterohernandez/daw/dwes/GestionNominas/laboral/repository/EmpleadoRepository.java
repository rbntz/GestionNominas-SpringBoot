package es.iessoterohernandez.daw.dwes.GestionNominas.laboral.repository;

import es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Obtener un empleado por su DNI
    Empleado findByDni(String dni);

    // Filtrar empleados por cualquier campo excepto DNI (nombre, categoría, etc.)
    @Query("SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    List<Empleado> filtrarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT e FROM Empleado e WHERE e.sexo = :sexo")
    List<Empleado> filtrarPorSexo(@Param("sexo") Character sexo);

    @Query("SELECT e FROM Empleado e WHERE e.categoria = :categoria")
    List<Empleado> filtrarPorCategoria(@Param("categoria") Integer categoria);

    @Query("SELECT e FROM Empleado e WHERE e.anyos = :anyos")
    List<Empleado> filtrarPorAnyos(@Param("anyos") Integer anyos);
}
