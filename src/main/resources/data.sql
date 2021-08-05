-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: imob_jafc
-- ------------------------------------------------------
-- Server version	8.0.23

SET FOREIGN_KEY_CHECKS=0;

-- Inserindo dados na Tabela Negócios
INSERT INTO `tb_business` VALUES 
  (1,'Aluguel'),
  (2,'Compra');

-- Inserindo dados na Tabela Categoria
INSERT INTO `tb_category` VALUES 
  (1,'Casa'),
  (2,'Apartamento'),
  (3,'Chácara'),
  (4,'Terreno');

-- Inserindo dados na Tabela Municípío
INSERT INTO `tb_county` VALUES 
  (1,'Sorocaba',1),
  (2,'Pindamonhangaba',1),
  (3,'Piedade',1),
  (4,'Duque de Caxias',2),
  (5,'Cabo Frio',2),
  (6,'São Gonçalo',2),
  (7,'Belo Horizonte',3),
  (8,'Uberlândia',3),
  (9,'Juiz de Fora',3);

-- Inserindo dados na Tabela Bairro
INSERT INTO `tb_neighborhood` VALUES 
  (1,'Santa Rosália',1),
  (2,'Campolim',1),
  (3,'São Benedito',2),
  (4,'Parque das Nações',2),
  (5,'Boa Vista',3),
  (6,'Moreiras',3),
  (7,'Campos Elíseos',4),
  (8,'Imbariê',4),
  (9,'Vila Nova',5),
  (10,'Jardim Machado',5),
  (11,'Monjolos',6),
  (12,'Neves',6),
  (13,'Savassi',7),
  (14,'Pampulha',7),
  (15,'São José',8),
  (16,'Santa Rosa',8),
  (17,'Alto dos Passos',9),
  (18,'Estrela Sul',9);

-- Inserindo dados na Tabela Imóvel
INSERT INTO `tb_property` VALUES 
  (1,'Vicente Funes Marins, 152',2,1,1,3),
  (2,'São Joaquim, 187',1,2,2,3),
  (3,'Rodrigues Bismare, 284',2,3,4,5),
  (4,'Doutor Benedito Vergílio, 987',2,4,4,1),
  (5,'José de Anchieta, 9713',2,2,10,3),
  (6,'Eteivina Campos, 174',1,2,11,3);

-- Inserindo dados na Tabela Quarto
INSERT INTO `tb_room` VALUES 
  (1,0),
  (2,1),
  (3,2),
  (4,3),
  (5,4),
  (6,5);

-- Inserindo dados na Tabela Estado
INSERT INTO `tb_state` VALUES 
  (1,'São Paulo','SP'),
  (2,'Rio de Janeiro','SP'),
  (3,'Minas Gerais','MG');

SET FOREIGN_KEY_CHECKS=1