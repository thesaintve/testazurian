-- 1) Crear la base de datos dbprueba
-- 2) Crear la tabla vehiculo
-- 3) Configurar la cadena de conexion, usuario y clave del persistence.xml
-- 4) Abrir el proyecto en esta ruta http://localhost:8080/mavenSpringRest/faces/index.xhtml
-- 5) El endpint Rest http://localhost:8080/mavenSpringRest/vehiculo

CREATE TABLE vehiculo
(
  matricula VARCHAR(6) NOT NULL,
  modelo VARCHAR(20),
  kilometraje INTEGER,
  kilometraje_ini INTEGER,
  alta VARCHAR(1) NOT NULL,
  CONSTRAINT vehikpy PRIMARY KEY (matricula)
);


