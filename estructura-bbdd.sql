CREATE DATABASE gestionnominas;

USE gestionnominas;

-- Estructura tablas Empleado y Nomina.

CREATE TABLE Empleado (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(20) NOT NULL,
    DNI VARCHAR(9) NOT NULL,
    sexo CHAR(1) NOT NULL,
    categoria INT DEFAULT 1,
    anyos INT DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT categoria_chk CHECK (categoria >= 1 AND categoria <= 10),
    CONSTRAINT anyos_chk CHECK (anyos >= 0),
    CONSTRAINT dni_unique UNIQUE (DNI)
);

CREATE TABLE Nomina (
    id BIGINT NOT NULL AUTO_INCREMENT,
    dni_Empleado VARCHAR(9) NOT NULL,   -- Referencia al DNI del empleado directamente
    sueldo_calculado DECIMAL(10,2),
    PRIMARY KEY (id),
    CONSTRAINT dniEmpleado_fk FOREIGN KEY (dni_Empleado) REFERENCES Empleado(DNI) ON DELETE CASCADE
);

-- Inserción de los empleados.

INSERT INTO Empleado (nombre, DNI, sexo, categoria, anyos) VALUES 
('Carlos', '12345678A', 'M', 5, 3),
('Ana', '87654321B', 'F', 4, 5),
('Pedro', '11223344C', 'M', 7, 10),
('Laura', '44332211D', 'F', 3, 2),
('Luis', '99887766E', 'M', 2, 1),
('Carlos', '22334455F', 'M', 5, 3),
('Sofía', '66778899G', 'F', 8, 7),
('Elena', '55667788H', 'F', 1, 0),
('Pedro', '33445566I', 'M', 7, 10),
('Lucía', '77889900J', 'F', 10, 6);

-- Inserción de las nóminas basadas en el DNI de los empleados.

INSERT INTO Nomina (dni_Empleado, sueldo_calculado) VALUES 
('12345678A', 32000.00),
('87654321B', 28000.00),
('11223344C', 45000.00),
('44332211D', 26000.00),
('99887766E', 24000.00),
('22334455F', 32000.00),
('66778899G', 40000.00),
('55667788H', 22000.00),
('33445566I', 45000.00),
('77889900J', 50000.00);
