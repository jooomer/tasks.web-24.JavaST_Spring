DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

LOCK TABLES `category` WRITE;

INSERT INTO `category` VALUES (1,'Cabinets'),(2,'Sofas'),(3,'Armchairs'),(4,'Tables');

UNLOCK TABLES;

