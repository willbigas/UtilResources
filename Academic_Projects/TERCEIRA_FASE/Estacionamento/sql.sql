create database  if not exists estacionamento;
use estacionamento;

CREATE TABLE carro (
  id int(10) NOT NULL auto_increment,
  placa varchar(50) NULL,
  cor varchar(50) NULL,
  modelo varchar(50) null,
  marca varchar(50) null,
  PRIMARY KEY(id)
);

CREATE TABLE condutor (
  id int(10) NOT NULL auto_increment,
  nome varchar(50) NULL,
  PRIMARY KEY(id)
);

CREATE TABLE tipoCliente (
  id int(10) NOT NULL auto_increment,
  idTipo Integer(1) NULL,
  PRIMARY KEY(id)
);


CREATE TABLE entrada (
  id int(10) NOT NULL auto_increment,
  dataEntrada date,
  dataSaida date,
  valorTotal decimal(10,2),
  fk_carro int,
  fk_condutor int,
  fk_tipoCliente int,
  PRIMARY KEY(id),
  FOREIGN KEY (fk_carro) REFERENCES carro(id),
  FOREIGN KEY (fk_condutor) REFERENCES condutor(id),
  FOREIGN KEY (fk_tipoCliente) REFERENCES tipoCliente(id)
);

select * from carro;

select * from condutor;

select * from tipoCliente;

select * from Entrada;
