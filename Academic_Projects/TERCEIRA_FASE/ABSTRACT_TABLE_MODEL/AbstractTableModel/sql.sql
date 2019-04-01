CREATE DATABASE IF NOT EXISTS tablemodel;
USE tablemodel;

CREATE TABLE IF NOT EXISTS vendas(
id int not null primary key auto_increment,
descricao varchar(45) not null,
quantidade int(5) not null,
valor decimal(10,2) not null
);

select * from vendas