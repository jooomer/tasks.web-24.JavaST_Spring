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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `orderStatus` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k8kupdtcdpqd57b6j4yq9uvdj` (`user_id`),
  CONSTRAINT `FK_k8kupdtcdpqd57b6j4yq9uvdj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:35','#2015-1','DELIVERED',1),(2,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:35','#2015-2','WAITING_FOR_PAIMENT',2),(3,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:35','#2015-3','WAITING_FOR_PAIMENT',1),(4,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:35','#2015-4','PAID',2),(5,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:35','#2015-5','DELIVERED',1),(6,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:35','#2015-6','WAITING_FOR_PAIMENT',2),(7,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-7','WAITING_FOR_PAIMENT',1),(8,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-8','PAID',2),(9,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-9','DELIVERED',1),(10,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-10','WAITING_FOR_PAIMENT',2),(11,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-11','WAITING_FOR_PAIMENT',1),(12,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-12','PAID',2),(13,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-13','DELIVERED',1),(14,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-14','WAITING_FOR_PAIMENT',2),(15,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-15','WAITING_FOR_PAIMENT',1),(16,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-16','PAID',2),(17,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:36','#2015-17','DELIVERED',1),(18,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-18','WAITING_FOR_PAIMENT',2),(19,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-19','WAITING_FOR_PAIMENT',1),(20,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-20','PAID',2),(21,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-21','DELIVERED',1),(22,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-22','WAITING_FOR_PAIMENT',2),(23,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-23','WAITING_FOR_PAIMENT',1),(24,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-24','PAID',2),(25,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-25','DELIVERED',1),(26,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-26','WAITING_FOR_PAIMENT',2),(27,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-27','WAITING_FOR_PAIMENT',1),(28,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-28','PAID',2),(29,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-29','DELIVERED',1),(30,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-30','WAITING_FOR_PAIMENT',2),(31,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:37','#2015-31','WAITING_FOR_PAIMENT',1),(32,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-32','PAID',2),(33,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-33','DELIVERED',1),(34,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-34','WAITING_FOR_PAIMENT',2),(35,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-35','WAITING_FOR_PAIMENT',1),(36,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-36','PAID',2),(37,5490,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-37','DELIVERED',1),(38,6380,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-38','WAITING_FOR_PAIMENT',2),(39,11060,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-39','WAITING_FOR_PAIMENT',1),(40,12300,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-29 14:02:38','#2015-40','PAID',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-29 14:07:38
