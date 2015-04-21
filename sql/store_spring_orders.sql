CREATE DATABASE  IF NOT EXISTS `store_spring` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `store_spring`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: store_spring
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
  `name` varchar(255) DEFAULT NULL,
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
INSERT INTO `orders` VALUES (1,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:04','Order A/1','DELIVERED',1),(2,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:05','Order B/1','DELIVERED',2),(3,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:05','Order C/1','CANCELED',1),(4,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:06','Order D/1','PAID',2),(5,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:06','Order A/2','DELIVERED',1),(6,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:06','Order B/2','DELIVERED',2),(7,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:06','Order C/2','CANCELED',1),(8,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:06','Order D/2','PAID',2),(9,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:06','Order A/3','DELIVERED',1),(10,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:06','Order B/3','DELIVERED',2),(11,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:06','Order C/3','CANCELED',1),(12,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:07','Order D/3','PAID',2),(13,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:07','Order A/4','DELIVERED',1),(14,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:07','Order B/4','DELIVERED',2),(15,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:07','Order C/4','CANCELED',1),(16,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:07','Order D/4','PAID',2),(17,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:07','Order A/5','DELIVERED',1),(18,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:08','Order B/5','DELIVERED',2),(19,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:08','Order C/5','CANCELED',1),(20,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:08','Order D/5','PAID',2),(21,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:08','Order A/6','DELIVERED',1),(22,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:08','Order B/6','DELIVERED',2),(23,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:09','Order C/6','CANCELED',1),(24,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:09','Order D/6','PAID',2),(25,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:09','Order A/7','DELIVERED',1),(26,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:09','Order B/7','DELIVERED',2),(27,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:09','Order C/7','CANCELED',1),(28,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:09','Order D/7','PAID',2),(29,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:09','Order A/8','DELIVERED',1),(30,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:09','Order B/8','DELIVERED',2),(31,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:10','Order C/8','CANCELED',1),(32,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:10','Order D/8','PAID',2),(33,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:10','Order A/9','DELIVERED',1),(34,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:10','Order B/9','DELIVERED',2),(35,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:10','Order C/9','CANCELED',1),(36,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:10','Order D/9','PAID',2),(37,3880,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:10','Order A/10','DELIVERED',1),(38,7990,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:11','Order B/10','DELIVERED',2),(39,5830,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:11','Order C/10','CANCELED',1),(40,11850,'Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ','2015-04-20 17:21:11','Order D/10','PAID',2);
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

-- Dump completed on 2015-04-20 17:40:49
