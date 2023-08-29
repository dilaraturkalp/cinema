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
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `customer_ıd` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `price` int NOT NULL,
  `seatid` int NOT NULL,
  `hall_id` int NOT NULL,
  `session_id` int NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `seatid` (`seatid`) USING BTREE,
  KEY `hall_id` (`hall_id`) USING BTREE,
  KEY `customer_ıd` (`customer_ıd`) USING BTREE,
  CONSTRAINT `hall_id` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`hall_id`),
  CONSTRAINT `seatid` FOREIGN KEY (`seatid`) REFERENCES `seat` (`seatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES ('111C1','01231',10,1,1,1),('111C2','12312',10,2,1,1),('111C3','12313',10,3,2,2),('111C4','12314',10,4,6,1),('111C5','12315',10,5,1,2),('111C6','12316',10,1,1,1),('111C7','12317',10,2,5,3),('111C8','12318',10,10,1,4),('111C9','12319',10,11,3,16),('111D1','12320',10,12,1,11),('111D2','12321',10,13,2,1),('111D3','12322',10,14,1,1),('111D4','12323',10,15,1,10),('111D5','12324',10,16,4,5),('111D6','12325',10,17,1,7),('111D7','12326',10,18,3,1),('111D8','12327',10,1,1,8),('111D9','12328',10,1,5,3),('111E1','12330',10,9,1,6),('111E2','12329',10,7,2,8),('111E3','12331',10,8,1,9);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
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
