DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` int(11) NOT NULL,
  PRIMARY KEY (`users_id`,`roles_id`),
  KEY `FK_m9m48prwc72ufnnhmy7m4bl7c` (`roles_id`),
  CONSTRAINT `FK_9frxso8e6wtgsfi4honspywoc` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_m9m48prwc72ufnnhmy7m4bl7c` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_role` WRITE;

INSERT INTO `user_role` VALUES (1,1),(2,1),(3,1),(1,2),(3,3);

UNLOCK TABLES;

