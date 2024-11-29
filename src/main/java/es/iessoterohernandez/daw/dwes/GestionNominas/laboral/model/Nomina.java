package es.iessoterohernandez.daw.dwes.GestionNominas.laboral.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Nomina {

    private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000 };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;
    
    @Column(name = "sueldo_calculado")
    private double sueldoCalculado;

    @ManyToOne
    @JoinColumn(name = "dni_empleado", referencedColumnName = "dni")
    private Empleado empleado;

    public Nomina() {}

    public Nomina(Empleado empleado) {
        this.empleado = empleado;
        this.sueldoCalculado = sueldo(empleado); // Calculamos el sueldo cuando se crea la nómina
    }

    // Método para calcular el sueldo basado en el empleado
    public double sueldo(Empleado e) {
        int categoriaSueldo = e.getCategoria();
        this.sueldoCalculado = SUELDO_BASE[categoriaSueldo - 1] + 5000 * e.getAnyos();
        return this.sueldoCalculado;
    }
    
}

