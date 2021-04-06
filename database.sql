DROP DATABASE IF EXISTS Vendas2;
CREATE DATABASE IF NOT EXISTS Vendas2;
USE Vendas2;

CREATE TABLE IF NOT EXISTS Profissao(
  idProfissao int unsigned auto_increment primary key not null,
  nomeProfissao varchar(255)
);

CREATE TABLE IF NOT EXISTS Cliente(
  codCliente int unsigned auto_increment primary key not null,
  nome varchar(255),
  idProfissao int unsigned,
  foreign key (idProfissao) references Profissao(idProfissao)
);

CREATE TABLE IF NOT EXISTS NotaCompra(
  nroCompra int unsigned auto_increment primary key not null,
  dataCompra date,
  totalNota float
);

CREATE TABLE IF NOT EXISTS NotaVenda(
  nroVenda int unsigned auto_increment primary key not null,
  dataVenda date,
  totalVenda float,
  codCliente int unsigned,
  foreign key (codCliente) references Cliente(codCliente)
);

CREATE TABLE IF NOT EXISTS produto(
  codPro int unsigned auto_increment primary key not null,
  nomePro varchar(255),
  precoVenda int,
  precoCusto int
);

CREATE TABLE IF NOT EXISTS ItemCompra(
  nroCompra int unsigned not null,
  qtde int,
  valor float,
  codPro int unsigned,
  foreign key (codPro) references produto(codPro),
  foreign key (nroCompra) references NotaCompra(nroCompra)
);

CREATE TABLE IF NOT EXISTS ItemVenda(
  nroVenda int unsigned not null,
  qtde int,
  valor float,
  codPro int unsigned,
  foreign key (codPro) references produto(codPro),
  foreign key (nroVenda) references NotaVenda(nroVenda)
);

INSERT INTO `Profissao`(nomeProfissao)
VALUES 
  ("Consultor de vendas"),
  ("Auxiliar de limpeza"),
  ("Ajudante de Churrasqueiro"),
  ("Professor"),
  ("Promotor de vendas"),
  ("Contador"),
  ("Engenheiro"),
  ("Motorista"),
  ("Jerdineiro"),
  ("Eletricista"),
  ("Chef"),
  ("Acessor de marketing");

INSERT INTO `Cliente`(nome, idProfissao)
VALUES 
  ("Lucas", 7),
  ("Claudia", 11),
  ("Luis", 8),
  ("Rute", 5),
  ("Pietro", 9),
  ("Flavia", 4);
  
INSERT INTO `Cliente`(nome)
VALUES
("Eduardo"),
("Carlos"),
("Eduardo"),
("Beatriz"),
("Sophia");

INSERT INTO `NotaCompra`(dataCompra, totalNota)
VALUES 
  ("2018-02-05", 975.36),
  ("2017-02-10", 509.00),
  ("2015-06-03", 599.16),
  ("2016-07-12", 904.59),
  ("2019-07-17", 817.20),
  ("2021-07-18", 920.74),
  ("2021-08-06", 68.54),
  ("2019-08-09", 409.10),
  ("2018-09-01", 345.29),
  ("2017-09-08", 823.91),
  ("2020-09-29", 468.59),
  ("2021-03-05", 32.11),
  ("2018-03-06", 975.36),
  ("2017-05-10", 509.00),
  ("2015-07-03", 599.16),
  ("2016-01-12", 904.59),
  ("2019-03-17", 817.20),
  ("2021-06-18", 920.74),
  ("2021-09-06", 68.54),
  ("2019-10-09", 409.10),
  ("2018-08-01", 345.29),
  ("2017-04-08", 823.91),
  ("2020-04-29", 468.59),
  ("2021-05-05", 32.11);

INSERT INTO `NotaVenda`(dataVenda, totalVenda, codCliente)
VALUES 
  ("2019-09-25", 681.42, 6),
  ("2019-12-25", 123.42, 9),
  ("2018-01-01", 227.45, 1),
  ("2020-01-12", 467.05, 6),
  ("2021-01-15", 77.00, 9),
  ("2017-01-18", 81.03, 4),
  ("2021-02-02", 379.99, 11),
  ("2017-02-03", 203.57, 1),
  ("2020-02-17", 318.12, 2),
  ("2020-02-28", 325.30, 7),
  ("2021-03-06", 474.03, 7),
  ("2021-03-09", 90.45, 4);

INSERT INTO `produto`(nomePro, precoVenda, precoCusto)
VALUES 
  ("Arroz", 40, 20),
  ("Feijão", 9.36, 4),
  ("Macarrão", 2.05, 0.75),
  ("Molho de tomate", 1.19, 0.45),
  ("Tomate", 6.50, 1.50),
  ("Banana", 4.19, 1.25),
  ("Pão de forma", 6.59, 2.50),
  ("Leite", 2.11, 0.90),
  ("Manteiga", 7.40, 2.98),
  ("Kit Picanha", 197.99, 150.00),
  ("Tilapia", 35.00, 15.00),
  ("Caixa 70 Ovos", 120.00, 40.00),
  ("Leite", 2.5, 1.5),
  ("Farinha", 3.5, 2.5),
  ("Abacaxi", 2.5, 1.2),
  ("Cenoura", 1.5, 0.7),
  ("Laranja", 0.5, 0.3),
  ("Melancia", 6.5, 4.5),
  ("Pera", 2.5, 2.0),
  ("Whiskey Jack Daniel's", 210, 190);

INSERT INTO `ItemCompra`(nroCompra, qtde, valor, codPro)
VALUES
  (1, 50, 20, 19),
  (2, 50, 200, 18),
  (3, 50, 35.50, 15),
  (4, 50, 22.5, 9),
  (6, 50, 62.5, 9),
  (7, 50, 125, 7),
  (8, 50, 45, 16),
  (10, 50, 1000, 10),
  (11, 50, 750, 15),
  (12, 50, 2000, 17),
  (13, 50, 450, 19),
  (14, 50, 46, 14),
  (15, 50, 146, 20),
  (16, 50, 468, 7),
  (17, 50, 861, 6),
  (18, 50, 714, 6),
  (19, 50, 70, 7),
  (20, 50, 40, 3),
  (21, 50, 70, 3),
  (22, 50, 95, 1),
  (23, 50, 784, 6),
  (24, 50, 48, 8);
INSERT INTO `ItemVenda`(nroVenda, qtde, valor, codPro)
VALUES (1, 33, 1320, 1),
  (2, 6, 240, 2),
  (4, 50, 102.5, 4),
  (5, 35, 227.5, 19),
  (6, 50, 209.5, 6),
  (8, 15, 31.65, 14),
  (9, 24, 177.6, 9),
  (10, 19, 721.81, 10),
  (12, 26, 3120, 2),
  (3, 21, 3120, 4),
  (3, 95, 3120, 6),
  (4, 12, 3120, 7),
  (7, 3, 3120, 9),
  (9, 6, 3120, 11),
  (9, 2, 3120, 16),
  (11, 8, 3120, 14),
  (11, 9, 3120, 20),
  (12, 12, 3120, 12);