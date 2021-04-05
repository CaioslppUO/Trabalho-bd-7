DROP DATABASE IF EXISTS Vendas;
CREATE DATABASE IF NOT EXISTS Vendas;
USE Vendas;
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
VALUES ("Consultor de vendas"),
  ("Auxiliar de limpeza"),
  ("Ajudante de Churrasqueiro"),
  ("Professor"),
  ("Promotor de vendas");
INSERT INTO `Cliente`(nome, idProfissao)
VALUES ("Lucas", 1),
  ("Caio", 2),
  ("Levi", 3),
  ("Jose", 4);
INSERT INTO `Cliente`(nome)
VALUES("Eduardo");
INSERT INTO `NotaCompra`(dataCompra, totalNota)
VALUES ("2020-02-05", 975.36),
  ("2020-02-10", 509.00),
  ("2020-06-03", 599.16),
  ("2020-07-12", 904.59),
  ("2020-07-17", 817.20),
  ("2020-07-18", 920.74),
  ("2020-08-06", 68.54),
  ("2020-08-09", 409.10),
  ("2020-09-01", 345.29),
  ("2020-09-08", 823.91),
  ("2020-09-29", 468.59),
  ("2021-03-05", 32.11);
INSERT INTO `NotaVenda`(dataVenda, totalVenda, codCliente)
VALUES ("2020-09-25", 681.42, 3),
  ("2020-12-25", 123.42, 1),
  ("2021-01-01", 227.45, 1),
  ("2021-01-12", 467.05, 2),
  ("2021-01-15", 77.00, 3),
  ("2021-01-18", 81.03, 4),
  ("2021-02-02", 379.99, 3),
  ("2021-02-03", 203.57, 1),
  ("2021-02-17", 318.12, 2),
  ("2021-02-28", 325.30, 3),
  ("2021-03-06", 474.03, 4),
  ("2021-03-09", 90.45, 4);
INSERT INTO `produto`(nomePro, precoVenda, precoCusto)
VALUES ("Arroz", 40, 20),
  ("Feijão", 9.36, 4),
  ("Macarrão", 2.05, 0.75),
  ("Molho de toma", 1.19, 0.45),
  ("Legumes", 6.50, 1.50),
  ("Frutas", 4.19, 1.25),
  ("Pão de forma", 6.59, 2.50),
  ("Leite", 2.11, 0.90),
  ("Manteiga", 7.40, 2.98),
  ("Carnes", 37.99, 20.00),
  ("Peixes", 35.00, 15.00),
  ("30 Ovos", 120.00, 40.00);
INSERT INTO `ItemCompra`(nroCompra, qtde, valor, codPro)
VALUES(1, 50, 1000, 1),
  (2, 50, 200, 2),
  (3, 50, 35.50, 3),
  (4, 50, 22.5, 4),
  (5, 50, 75, 5),
  (6, 50, 62.5, 6),
  (7, 50, 125, 7),
  (8, 50, 45, 8),
  (9, 50, 149, 9),
  (10, 50, 1000, 10),
  (11, 50, 750, 11),
  (12, 50, 2000, 12);
INSERT INTO `ItemVenda`(nroVenda, qtde, valor, codPro)
VALUES (1, 33, 1320, 1),
  (2, 6, 240, 2),
  (3, 32, 299.52, 3),
  (4, 50, 102.5, 4),
  (5, 35, 227.5, 5),
  (6, 50, 209.5, 6),
  (7, 47, 309.73, 7),
  (8, 15, 31.65, 8),
  (9, 24, 177.6, 9),
  (10, 19, 721.81, 10),
  (12, 26, 3120, 12);