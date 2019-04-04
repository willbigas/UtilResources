CREATE DATABASE IF NOT EXISTS exemplotablemodel;
USE exemplotablemodel;

CREATE TABLE IF NOT EXISTS categorias(
id int not null primary key auto_increment,
nome varchar(45) not null,
ativo boolean not null
);

select * from categorias