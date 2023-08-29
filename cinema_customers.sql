-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `Eposta` varchar(255) DEFAULT NULL,
  `registiration_year` varchar(45) DEFAULT NULL,
  `gold_customer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('01231','Mert','Gizem','012345678','deneme@deneme1.com','1990','Yes'),('01234','Ali','Mert','012345671','deneme@deneme.com','2000','Yes'),('12312','Kaan','Serin','012345672','deneme@deneme2.com','2015','No'),('12313','Merve','Avşar','012345673','deneme@deneme3.com','1998','Yes'),('12314','Emine','Ay','012345674','deneme@deneme4.com','2020','No'),('12315','Melis','Sinan','012345675','deneme@deneme5.com','2022','No'),('12316','Gizem','Yıldız','012345676','deneme@deneme6.com','1998','Yes'),('12317','Kübra','Gül','012345677','deneme@deneme7.com','1996','Yes'),('12318','Büşra','Can','012345678','deneme@deneme8.com','2005','Yes'),('12319','Başak','Bal','012345679','deneme@deneme9.com','2008','Yes'),('12321','Melike','Kaya','012345680','deneme@deneme10.com','2022','No'),('12322','Emel','Taş','012345681','deneme@deneme11.com','2004','Yes'),('12323','Ömer','Dağ','012345682','deneme@deneme12.com','2003','Yes'),('12324','Eren','Yılmaz','012345683','deneme@deneme13.com','2013','Yes'),('12325','Fazlı','Cansu','012345684','deneme@deneme14.com','2011','Yes'),('12326','Utku','Canan','012345685','deneme@deneme15.com','2022','No'),('12327','Furkan','Yer','012345686','deneme@deneme16.com','2000','Yes'),('12328','Sefa','Çelik','012345687','deneme@deneme17.com','1996','Yes'),('12329','Nevzat','Demir','012345688','deneme@deneme18.com','2023','No'),('12330','Hakan','Baykuş','012345689','deneme@deneme19.com','2020','No'),('12331','Mehmet','Şahin','012345691','deneme@deneme20.com','2013','Yes'),('12332','Sena','Dinçer','012345692','deneme@deneme21.com','2012','Yes'),('12333','Rana','Melek','012345693','deneme@deneme22.com','2011','Yes'),('12334','Gökhan','Balcı','012345694','deneme@deneme23.com','2004','Yes'),('12335','Sinem','Koçak','012345695','deneme@deneme24.com','2005','Yes'),('12336','Ceren','Akalın','012345690','deneme@deneme25.com','2007','Yes'),('12337','Selen','Açıkgöz','012345696','deneme@deneme26.com','2008','Yes'),('12338','Rabia','Akçıl','012345697','deneme@deneme27.com','2009','Yes'),('12339','Ozan','Yener','012345698','deneme@deneme28.com','2007','Yes'),('12340','Tuğba','Keklik','012345699','deneme@deneme29.com','2005','Yes'),('12341','Elif','Yavuz','012345667','deneme@deneme30.com','2005','Yes'),('12342','Ezgi','Kartal','012345668','deneme@deneme31.com','2004','Yes'),('12343','Çağrı','Derin','012345666','deneme@deneme32.com','2003','Yes');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-12 20:49:05
