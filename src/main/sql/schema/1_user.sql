DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `comments` longtext,
  `email` varchar(100) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `in_black_list` bit(1) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `userType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;

INSERT INTO `user` VALUES (1,'Kensington, London, United Kingdom','Interactively procrastinate high-payoff ...','freddie@mercury.com','','Freddie','\0','Mercury','admin','$2a$10$BtWFA5qTwZBw3nBO/KyG8OvGeQoDFkOXme.1NWcz4e/PpTrYwM5e6','(103)501-23-92','ROLE_ADMIN'),(2,'Holmby Hills, Los Angeles, California, United ','Interactively procrastinate high-payoff ...','michael@jackson.com','','Michael','','Jackson','user','$2a$10$QtDFy1eMxa3FC.Zg0SDvbeC5Kc5oEbKQLOVoktZ64jKlgWeTI3ewa','(103)694-03-85','ROLE_USER'),(3,'Walton, Liverpool, United Kingdom','Interactively procrastinate high-payoff ...','paul@mccartney.com','','Paul','','McCartney','manager','$2a$10$7gHAUJgig4dDQ9ix7SvCSuGY6FywkKyibnwSXCCDmPL80o9IuhSDa','(103)694-03-85','ROLE_MANAGER');

UNLOCK TABLES;

