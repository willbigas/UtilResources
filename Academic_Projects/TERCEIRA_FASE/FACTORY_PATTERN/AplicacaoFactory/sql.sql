create database factory;

use factory;


CREATE TABLE endereco(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE pessoaFisica(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE pessoaDependente(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE pessoaJuridica(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE pessoaParente(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
PRIMARY KEY (id));

select * from endereco;

select * from pessoaDependente;

select * from pessoaFisica;

select * from pessoaJuridica;

select * from pessoaParente;


