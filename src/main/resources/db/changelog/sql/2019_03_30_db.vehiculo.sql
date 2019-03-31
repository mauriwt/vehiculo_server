create table tipo_vehiculos(
id serial not null,
tipo varchar(25),
CONSTRAINT pk_id_tipo_vehiculos PRIMARY KEY (id)
);
CREATE TABLE vehiculos
(
  id serial NOT NULL,
  marca character varying(50) NOT NULL,
  modelo character varying(50),
  anio_fabricacion int, 
  capacidad_tanque numeric, 
  nro_pasajeros numeric, 
  capacidad_carga numeric, 
  tipo_vehiculo int,
  estado boolean,
  CONSTRAINT pk_id_vehiculos PRIMARY KEY (id),
  CONSTRAINT fk_vehiculos_tipo_vehiculos FOREIGN KEY (tipo_vehiculo)
      REFERENCES tipo_vehiculos (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);