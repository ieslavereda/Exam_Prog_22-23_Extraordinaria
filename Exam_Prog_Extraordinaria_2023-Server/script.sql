DROP DATABASE IF EXISTS extraordinaria;

CREATE DATABASE extraordinaria;

USE extraordinaria;

CREATE TABLE `bolas` (
  `id` int(11) NOT NULL AUTO_INCREMENT, 
  `numero` int(11) NOT NULL,
  `color` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero` (`numero`)
);


DELIMITER @@

CREATE PROCEDURE clean()
BEGIN

    DELETE FROM bolas;

END@@

DELIMITER ;;

DROP USER IF EXISTS examen@'%';
CREATE USER examen@'%' identified by '1234';
GRANT ALL PRIVILEGES ON extraordinaria.* TO examen@'%';
GRANT ALL PRIVILEGES ON mysql.proc TO examen@'%'; 




