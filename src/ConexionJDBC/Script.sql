/*Nombre de la base de datos ----> sistema_ventas*/
create DATABASE sistema_ventas;
/*Creación de las  tablas*/
create table usuario(
id_usuario int primary key not null auto_increment,
nombre varchar(50) not null,
apellido varchar(50) not null,
dni varchar(8) not null,  /*varchar porque no hará operaciones matemáticas.*/
usuario varchar(50) not null,
contraseña varchar(150) not null,
id_cargo int not null
);

create table cargo(
id_cargo int primary key not null auto_increment,
cargo varchar(20) not null
);

create table cliente(
id_cliente int not null PRIMARY KEY AUTO_INCREMENT,
nombre varchar(50) not null,
apellido varchar(50) not null,
dni varchar(8) not null
);

create table categoria(
id_categoria int not null PRIMARY KEY AUTO_INCREMENT,
categoria varchar(50) not null
);

create table producto (
id_producto int primary key not null auto_increment,
nombre_producto varchar (30) not null,
precio_compra decimal(10,2) not null,
precio_venta decimal(10,2) not null,
cantidad int not null,
id_categoria int not null
);

/*************************************************************************************************************************************/

ALTER TABLE usuario ADD  FOREIGN KEY(id_cargo) REFERENCES cargo (id_cargo) ON DELETE CASCADE ON UPDATE CASCADE;

/*******insertamos datos en la tabla cargo******/
insert into cargo(cargo) values("Administrador"),("Vendedor")
