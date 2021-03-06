CREATE DATABASE  IF NOT EXISTS `epam_store_spring` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `epam_store_spring`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: epam_store_spring
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `productsQuantity` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9gap2fmw66v092ntb58rtohwh` (`order_id`),
  KEY `FK_3fea23hxar30bx7m7h8ed25n9` (`product_id`),
  CONSTRAINT `FK_3fea23hxar30bx7m7h8ed25n9` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK_9gap2fmw66v092ntb58rtohwh` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,1620,810,2,1,1),(2,600,600,1,1,3),(3,2640,880,3,1,4),(4,750,150,5,1,6),(5,90,90,1,2,2),(6,3600,900,4,2,5),(7,4260,710,6,2,10),(8,1200,600,2,3,3),(9,750,150,5,3,6),(10,1740,870,2,3,11),(11,690,230,3,3,12),(12,4000,400,10,3,15),(13,1350,90,15,4,2),(14,1620,810,2,5,1),(15,600,600,1,5,3),(16,2640,880,3,5,4),(17,750,150,5,5,6),(18,90,90,1,6,2),(19,3600,900,4,6,5),(20,4260,710,6,6,10),(21,1200,600,2,7,3),(22,750,150,5,7,6),(23,1740,870,2,7,11),(24,690,230,3,7,12),(25,4000,400,10,7,15),(26,1350,90,15,8,2),(27,1620,810,2,9,1),(28,600,600,1,9,3),(29,2640,880,3,9,4),(30,750,150,5,9,6),(31,90,90,1,10,2),(32,3600,900,4,10,5),(33,4260,710,6,10,10),(34,1200,600,2,11,3),(35,750,150,5,11,6),(36,1740,870,2,11,11),(37,690,230,3,11,12),(38,4000,400,10,11,15),(39,1350,90,15,12,2),(40,1620,810,2,13,1),(41,600,600,1,13,3),(42,2640,880,3,13,4),(43,750,150,5,13,6),(44,90,90,1,14,2),(45,3600,900,4,14,5),(46,4260,710,6,14,10),(47,1200,600,2,15,3),(48,750,150,5,15,6),(49,1740,870,2,15,11),(50,690,230,3,15,12),(51,4000,400,10,15,15),(52,1350,90,15,16,2),(53,1620,810,2,17,1),(54,600,600,1,17,3),(55,2640,880,3,17,4),(56,750,150,5,17,6),(57,90,90,1,18,2),(58,3600,900,4,18,5),(59,4260,710,6,18,10),(60,1200,600,2,19,3),(61,750,150,5,19,6),(62,1740,870,2,19,11),(63,690,230,3,19,12),(64,4000,400,10,19,15),(65,1350,90,15,20,2),(66,1620,810,2,21,1),(67,600,600,1,21,3),(68,2640,880,3,21,4),(69,750,150,5,21,6),(70,90,90,1,22,2),(71,3600,900,4,22,5),(72,4260,710,6,22,10),(73,1200,600,2,23,3),(74,750,150,5,23,6),(75,1740,870,2,23,11),(76,690,230,3,23,12),(77,4000,400,10,23,15),(78,1350,90,15,24,2),(79,1620,810,2,25,1),(80,600,600,1,25,3),(81,2640,880,3,25,4),(82,750,150,5,25,6),(83,90,90,1,26,2),(84,3600,900,4,26,5),(85,4260,710,6,26,10),(86,1200,600,2,27,3),(87,750,150,5,27,6),(88,1740,870,2,27,11),(89,690,230,3,27,12),(90,4000,400,10,27,15),(91,1350,90,15,28,2),(92,1620,810,2,29,1),(93,600,600,1,29,3),(94,2640,880,3,29,4),(95,750,150,5,29,6),(96,90,90,1,30,2),(97,3600,900,4,30,5),(98,4260,710,6,30,10),(99,1200,600,2,31,3),(100,750,150,5,31,6),(101,1740,870,2,31,11),(102,690,230,3,31,12),(103,4000,400,10,31,15),(104,1350,90,15,32,2),(105,1620,810,2,33,1),(106,600,600,1,33,3),(107,2640,880,3,33,4),(108,750,150,5,33,6),(109,90,90,1,34,2),(110,3600,900,4,34,5),(111,4260,710,6,34,10),(112,1200,600,2,35,3),(113,750,150,5,35,6),(114,1740,870,2,35,11),(115,690,230,3,35,12),(116,4000,400,10,35,15),(117,1350,90,15,36,2),(118,1620,810,2,37,1),(119,600,600,1,37,3),(120,2640,880,3,37,4),(121,750,150,5,37,6),(122,90,90,1,38,2),(123,3600,900,4,38,5),(124,4260,710,6,38,10),(125,1200,600,2,39,3),(126,750,150,5,39,6),(127,1740,870,2,39,11),(128,690,230,3,39,12),(129,4000,400,10,39,15),(130,1350,90,15,40,2);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-30 21:58:40
