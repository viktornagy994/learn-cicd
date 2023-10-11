-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: localhost    Database: kingfishairdb
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `airport_url` text NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `iata` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (1,'https://avinor.no/en/airport/oslo-airport/','Oslo','Norway','OSL','Oslo Gardermoen'),(2,'https://www.swedavia.com/arlanda/','Stockholm','Sweden','ARN','Arlanda Airport'),(3,'https://www.isavia.is/en/keflavik-airport','Reykjavík','Iceland','KEF','Keflavík International'),(4,'https://www.finavia.fi/en/airports/helsinki-airport','Helsinki','Finnland','HEL','Helsinki-Vantaa');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extra_addon`
--

DROP TABLE IF EXISTS `extra_addon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extra_addon` (
  `seat_id` bigint NOT NULL,
  `extra_addon` varchar(255) DEFAULT NULL,
  KEY `FK16yrju68hnwh79mr8jfsi39qo` (`seat_id`),
  CONSTRAINT `FK16yrju68hnwh79mr8jfsi39qo` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_addon`
--

LOCK TABLES `extra_addon` WRITE;
/*!40000 ALTER TABLE `extra_addon` DISABLE KEYS */;
INSERT INTO `extra_addon` VALUES (1,'PET'),(2,'BAGGAGE'),(3,'BAGGAGE'),(4,'BAGGAGE'),(5,'PET'),(6,'CATERING'),(7,'PET'),(7,'CATERING'),(8,'PET'),(8,'CATERING'),(9,'PET'),(9,'CATERING'),(10,'PET'),(10,'CATERING'),(11,'BAGGAGE'),(11,'PET'),(11,'CATERING'),(12,'BAGGAGE'),(12,'PET'),(12,'CATERING'),(13,'BAGGAGE'),(13,'CATERING'),(14,'BAGGAGE'),(14,'CATERING'),(15,'BAGGAGE'),(15,'CATERING'),(16,'PET'),(19,'CATERING'),(20,'CATERING'),(22,'BAGGAGE'),(22,'PET'),(22,'CATERING'),(23,'PET'),(24,'BAGGAGE'),(24,'CATERING'),(25,'BAGGAGE'),(26,'PET'),(26,'CATERING'),(27,'BAGGAGE'),(27,'CATERING'),(28,'PET'),(29,'BAGGAGE'),(30,'BAGGAGE'),(31,'BAGGAGE'),(32,'PET'),(34,'BAGGAGE'),(34,'PET'),(35,'PET'),(36,'CATERING'),(37,'PET'),(38,'CATERING'),(39,'PET'),(39,'CATERING'),(40,'BAGGAGE'),(40,'PET'),(40,'CATERING'),(41,'BAGGAGE'),(41,'PET'),(42,'BAGGAGE'),(44,'PET'),(45,'BAGGAGE'),(45,'PET'),(45,'CATERING'),(46,'PET'),(47,'BAGGAGE'),(48,'BAGGAGE'),(50,'BAGGAGE'),(51,'BAGGAGE'),(52,'BAGGAGE'),(53,'BAGGAGE'),(54,'BAGGAGE'),(55,'PET'),(56,'CATERING'),(57,'BAGGAGE'),(57,'PET'),(57,'CATERING'),(58,'PET'),(59,'CATERING'),(60,'BAGGAGE'),(61,'BAGGAGE'),(62,'BAGGAGE'),(63,'CATERING'),(64,'PET'),(65,'BAGGAGE'),(65,'PET'),(65,'CATERING'),(67,'BAGGAGE'),(67,'PET'),(68,'PET'),(69,'PET'),(71,'BAGGAGE'),(72,'PET'),(73,'PET'),(74,'PET'),(75,'BAGGAGE'),(76,'PET'),(76,'CATERING'),(77,'BAGGAGE'),(77,'CATERING'),(78,'BAGGAGE'),(79,'BAGGAGE'),(80,'BAGGAGE'),(80,'PET'),(80,'CATERING'),(81,'BAGGAGE'),(81,'PET'),(83,'BAGGAGE'),(83,'PET'),(84,'PET'),(84,'CATERING'),(85,'CATERING'),(86,'CATERING'),(87,'PET'),(88,'PET'),(89,'PET'),(90,'PET'),(91,'PET'),(92,'BAGGAGE'),(92,'PET'),(92,'CATERING'),(93,'BAGGAGE'),(93,'PET'),(94,'BAGGAGE'),(94,'PET');
/*!40000 ALTER TABLE `extra_addon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `flight_number` varchar(255) NOT NULL,
  `st_arrival` datetime(6) NOT NULL,
  `st_departure` datetime(6) NOT NULL,
  `plane_id` bigint NOT NULL,
  `route_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7p9fvp6d7uh9cgn47uet8a8nb` (`plane_id`),
  KEY `FK13s4qgdsiygr6a578jbp0yjgb` (`route_id`),
  CONSTRAINT `FK13s4qgdsiygr6a578jbp0yjgb` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`),
  CONSTRAINT `FK7p9fvp6d7uh9cgn47uet8a8nb` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (4,'KF6284','2023-10-23 01:30:00.000000','2023-10-22 22:00:00.000000',3,7),(5,'KF6284','2023-10-28 02:30:00.000000','2023-10-27 23:00:00.000000',2,7),(6,'KF4584','2023-10-24 15:05:00.000000','2023-10-24 12:35:00.000000',2,7),(7,'KF4584','2023-10-21 15:05:00.000000','2023-10-21 12:35:00.000000',3,7),(8,'KF4585','2023-10-22 16:05:00.000000','2023-10-22 13:35:00.000000',1,8),(9,'KF4585','2023-10-25 16:05:00.000000','2023-10-25 13:35:00.000000',3,8),(10,'KF4585','2023-10-31 16:05:00.000000','2023-10-31 13:35:00.000000',2,8),(11,'KF5478','2023-10-13 17:00:00.000000','2023-10-13 16:00:00.000000',1,2),(12,'KF5478','2023-10-14 17:00:00.000000','2023-10-14 16:00:00.000000',3,2),(13,'KF5478','2023-10-15 17:00:00.000000','2023-10-15 16:00:00.000000',1,2),(14,'KF5478','2023-08-16 17:00:00.000000','2023-08-16 16:00:00.000000',2,1),(15,'KF5478','2023-10-17 17:00:00.000000','2023-10-17 16:00:00.000000',1,1),(16,'KF5478','2023-08-18 17:00:00.000000','2023-08-18 16:00:00.000000',1,1),(17,'KF6284','2023-08-22 01:30:00.000000','2023-08-21 22:00:00.000000',3,7),(19,'KF4856','2023-10-28 11:30:00.000000','2023-10-28 10:30:00.000000',2,6),(20,'KF1213','2023-11-28 11:30:00.000000','2023-11-28 10:30:00.000000',1,6),(22,'KF4562','2023-10-31 13:32:00.000000','2023-10-31 12:32:00.000000',1,1),(23,'KF1972','2023-12-25 19:50:00.000000','2023-12-25 18:10:00.000000',2,8);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` text NOT NULL,
  `airport_id` bigint DEFAULT NULL,
  `plane_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr3v11sq0fbqjqhb5xenrcob3c` (`airport_id`),
  KEY `FK9xgmr3s8c2xawq45l845hhb4d` (`plane_id`),
  CONSTRAINT `FK9xgmr3s8c2xawq45l845hhb4d` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`id`),
  CONSTRAINT `FKr3v11sq0fbqjqhb5xenrcob3c` FOREIGN KEY (`airport_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'https://europeandesign.s3.eu-central-1.amazonaws.com/uploads/2018/04/10190848/Train_Station_OSL.jpg',1,NULL),(2,'https://www.goodnewsfinland.com/dam/jcr:a1c6f408-d8bb-4e45-a007-0672b4ed3438/helsinki-airport-aci-rus-2000-2-4081694.jpeg',4,NULL),(3,'https://guidetoiceland.imgix.net/738325/x/0/keflavik.jpg',3,NULL),(4,'https://radisson-blu-skycity-hotel-arlanda.booked.net/data/Photos/OriginalPhoto/12752/1275234/1275234751/Radisson-Blu-Airport-Terminal-Hotel-Arlanda-Exterior.JPEG',2,NULL),(5,'https://www.airlineratings.com/wp-content/uploads/uploads/Airspace-Cabin-Vision-2035.jpg',NULL,1),(6,'https://www.airlineratings.com/wp-content/uploads/uploads/Airspace-Cabin-Vision-2035.jpg',NULL,2),(7,'https://www.airlineratings.com/wp-content/uploads/uploads/Airspace-Cabin-Vision-2035.jpg',NULL,3),(8,'https://cimg0.ibsrv.net/cimg/www.dornob.com/900x449_85/716/H2-powered-engine-609716.jpg',NULL,1),(9,'https://cimg0.ibsrv.net/cimg/www.dornob.com/900x449_85/716/H2-powered-engine-609716.jpg',NULL,2),(10,'https://cimg0.ibsrv.net/cimg/www.dornob.com/900x449_85/716/H2-powered-engine-609716.jpg',NULL,3),(11,'https://lp-cms-production.imgix.net/2023-03/shutterstock_1879262575.jpg',1,NULL),(12,'https://stadshuset.stockholm/optimized/c2x1_medium/globalassets/stadshuset/startsida/trygg_sthlm_sep_night_19_033-2_mindre_ny.jpg',2,NULL),(13,'https://www.neverendingfootsteps.com/wp-content/uploads/2021/08/the-northern-lights-at-the-sun-voyager-statue-in-reykjavik.jpg',3,NULL),(14,'https://larnefc.com/wp-content/uploads/2023/07/128641704_gettyimages-1216740151.jpg',4,NULL);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message_category` varchar(255) NOT NULL,
  `client_email` varchar(255) NOT NULL,
  `client_name` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `message_status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq4suf2rgy0sy4j1obyaqsdbq6` (`customer_id`),
  CONSTRAINT `FKq4suf2rgy0sy4j1obyaqsdbq6` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'PAYMENTCOMPLAINT','varga.dani8181@gmail.com','Dániel István Varga','Hallo Hallo',13,'HANDLED'),(2,'PAYMENTCOMPLAINT','lacibiro0719@gmail.com','Teszt Laci','I dont wanna pay!',27,'HANDLED'),(3,'COMPENSATION','varga.dani8181@gmail.com','Dániel István Varga','I need my money back!',13,'HANDLED'),(4,'COMPENSATION','valami@valami.hu','Varga Dániel','Just hello',NULL,'HANDLED'),(5,'BAGCOMPLAINT','varga.dani8181@gmail.com','Dániel István Varga','Where is my baggage?',13,'HANDLED'),(6,'RESERVATIONMODIFY','zitakondor@gmail.com','Zita Kondor','I made a mistake in the reservation by booking seat 1 and 4 for us. Would it be possible to change seats so we could sit next to each other? Thank you.',5,'HANDLED'),(7,'GENERALINFORMATION','zitakondor@gmail.com','Zita Kondor','What are the menu options on the flights?',NULL,'UNHANDLED'),(8,'GENERALINFORMATION','zitakondor@gmail.com','Zita Kondor','Do you provide a carrier for pets?',NULL,'HANDLED');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `is_used` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5lwtbncug84d4ero33v3cfxvl` (`user_id`),
  CONSTRAINT `FK5lwtbncug84d4ero33v3cfxvl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
INSERT INTO `password_reset_token` VALUES (1,'2023-10-08 08:24:47.888000','cd511588-e9db-4c04-a976-6138012e7de5',20,_binary '\0'),(2,'2023-10-08 11:10:06.586000','47fb339b-ae42-4704-ad14-4edfabf3ba9c',22,_binary '\0'),(3,'2023-10-08 11:30:08.239000','cea53171-7e5c-43f0-8a06-422b3518ad2f',23,_binary '\0'),(4,'2023-10-08 11:48:43.260000','45e26ff7-78c5-401c-bec6-d50133bac86b',24,_binary '\0'),(5,'2023-10-08 13:21:43.794000','047dba78-32bc-49e0-9a6e-e9e433cc2e31',25,_binary '\0'),(6,'2023-10-09 13:20:55.397000','df68f6d9-5ec0-44b8-8af2-86f3aaf95af5',13,_binary '\0'),(7,'2023-10-10 09:07:18.033000','13c3c7c9-939e-4661-a352-4f75972a99ab',26,_binary '\0'),(8,'2023-10-11 14:17:50.936000','81fdec11-5064-42fd-8e9c-cc78a0a83a2f',25,_binary '\0'),(9,'2023-10-12 08:07:33.292000','44258601-796b-430e-aa1e-2f430aad5f1b',26,_binary '\0'),(10,'2023-10-14 08:02:48.462000','f5dda8a3-879e-4cbb-98d6-847b21e696bb',27,_binary '\0'),(11,'2023-10-14 08:43:38.579000','9a30181f-dadc-47ef-addd-ec9b9fd2fe5e',26,_binary '\0'),(12,'2023-10-14 08:57:31.811000','65bdf549-41f2-4f30-a3f1-787cd43da122',27,_binary '\0'),(13,'2023-10-15 06:44:44.021000','2c164e96-7870-4911-9304-d780e279e414',27,_binary '\0'),(14,'2023-10-15 08:46:57.681000','2db84be1-c332-49fb-9d72-d481a5077c5a',32,_binary '\0'),(15,'2023-10-15 11:15:43.273000','bd7d0195-7e1b-4fd9-a2dc-f85591f69961',39,_binary '\0'),(16,'2023-10-15 11:40:17.390000','61feae17-52fc-4b67-9a46-1219f5100538',40,_binary '\0'),(17,'2023-10-15 11:57:09.665000','f9a266bc-1c82-40f0-8566-7f210a3dd502',41,_binary '\0'),(18,'2023-10-15 12:45:57.815000','542fd8fe-ab24-4738-bb6e-fc51b3f34da2',43,_binary '\0'),(19,'2023-10-15 14:21:00.859000','e84e3056-096a-4078-90d1-52fa33245c46',44,_binary '\0'),(20,'2023-10-21 08:16:04.508000','649a81ce-7e18-4ad5-a4f0-071f783c1d8a',46,_binary '\0'),(21,'2023-10-21 09:45:48.600000','e9fcdc2f-4972-4c4c-86dc-2cc263c9dedc',31,_binary '\0'),(22,'2023-10-21 11:42:09.427000','c0b668da-c3b1-4f8f-b790-8ec67288c908',47,_binary ''),(23,'2023-10-21 12:21:51.061000','6b3d8c08-66d6-454c-8d5d-a4891dc369f0',48,_binary ''),(24,'2023-10-21 12:38:43.596000','dfd6dd86-66f5-42e1-8918-f905cb1af091',49,_binary ''),(25,'2023-10-21 14:29:12.964000','89299a87-cc55-43d5-b86b-bb24f36831bb',50,_binary ''),(26,'2023-10-22 07:09:45.263000','43a8b35c-6c59-4d5a-9d81-63c6f87e6725',51,_binary ''),(27,'2023-10-22 07:26:52.768000','ed0554ea-ce34-4a7d-82c8-97633bf8d156',52,_binary ''),(28,'2023-10-22 09:25:48.831000','aca215c4-88bf-467a-9057-612a5d1efc8d',53,_binary '');
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane`
--

DROP TABLE IF EXISTS `plane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `number_of_seats` bigint NOT NULL,
  `registration_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane`
--

LOCK TABLES `plane` WRITE;
/*!40000 ALTER TABLE `plane` DISABLE KEYS */;
INSERT INTO `plane` VALUES (1,'Odin',12,'KF-BXK'),(2,'Thor',12,'KF-BHA'),(3,'Loki',12,'KF-BKL');
/*!40000 ALTER TABLE `plane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `total_fare` bigint NOT NULL,
  `customer_id` bigint NOT NULL,
  `flight_id` bigint NOT NULL,
  `is_paid` bit(1) DEFAULT NULL,
  `reservation_holding_deadline` datetime(6) DEFAULT NULL,
  `checked_in` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsifntqdjsocveiy2ufnba7171` (`customer_id`),
  KEY `FKasu7mudg91n0ypk81rx93ucp5` (`flight_id`),
  CONSTRAINT `FKasu7mudg91n0ypk81rx93ucp5` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
  CONSTRAINT `FKsifntqdjsocveiy2ufnba7171` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (2,389,13,17,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(3,589,4,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(4,389,18,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(5,389,13,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(6,539,4,15,_binary '','2023-09-13 18:06:41.000000',_binary ''),(7,539,13,16,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(8,539,13,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(9,539,13,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(10,539,13,15,_binary '','2023-09-13 18:06:41.000000',_binary ''),(11,639,13,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(12,639,13,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(13,439,13,16,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(14,439,4,15,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(15,639,28,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(16,289,27,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(17,289,26,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(18,339,26,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(19,339,26,14,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(20,2307,4,4,_binary '','2023-09-13 18:06:41.000000',_binary '\0'),(21,389,13,15,_binary '','2023-09-14 16:21:31.287000',_binary ''),(22,689,13,16,_binary '','2023-09-14 17:30:31.691000',_binary '\0'),(23,589,13,16,_binary '','2023-09-15 08:29:54.438000',_binary '\0'),(24,673,13,19,_binary '\0','2023-09-15 09:00:35.777000',_binary '\0'),(25,589,27,11,_binary '\0','2023-09-15 09:55:58.190000',_binary '\0'),(26,573,4,19,_binary '','2023-09-15 11:01:14.065000',_binary '\0'),(27,873,4,19,_binary '','2023-09-15 11:10:53.337000',_binary '\0'),(28,773,13,19,_binary '\0','2023-09-15 11:29:19.741000',_binary '\0'),(29,623,13,20,_binary '\0','2023-09-15 11:53:46.299000',_binary '\0'),(30,773,13,20,_binary '\0','2023-09-15 12:10:32.715000',_binary '\0'),(31,623,13,19,_binary '\0','2023-09-15 12:59:26.587000',_binary '\0'),(32,823,13,19,_binary '\0','2023-09-15 14:34:36.662000',_binary '\0'),(33,923,42,19,_binary '\0','2023-09-15 14:35:18.716000',_binary '\0'),(34,689,4,12,_binary '\0','2023-09-18 19:57:49.237000',_binary '\0'),(35,289,27,11,_binary '\0','2023-09-20 07:08:25.778000',_binary '\0'),(36,773,13,19,_binary '\0','2023-09-20 15:29:25.723000',_binary '\0'),(37,923,45,19,_binary '\0','2023-09-20 20:09:08.050000',_binary '\0'),(38,773,13,20,_binary '\0','2023-09-21 08:29:28.672000',_binary '\0'),(39,673,45,20,_binary '\0','2023-09-21 08:33:08.248000',_binary '\0'),(40,673,46,20,_binary '\0','2023-09-21 08:38:30.405000',_binary '\0'),(41,289,4,15,_binary '\0','2023-09-21 10:13:50.258000',_binary '\0'),(42,673,13,19,_binary '\0','2023-09-21 10:50:38.874000',_binary '\0'),(43,673,13,20,_binary '\0','2023-09-21 11:29:42.808000',_binary '\0'),(44,1707,5,8,_binary '\0','2023-09-21 11:35:08.624000',_binary '\0'),(45,673,13,20,_binary '\0','2023-09-21 11:55:17.137000',_binary '\0'),(46,673,13,20,_binary '\0','2023-09-21 12:35:10.341000',_binary '\0'),(47,773,13,20,_binary '\0','2023-09-21 12:52:09.221000',_binary '\0'),(48,623,13,20,_binary '\0','2023-09-21 14:42:18.676000',_binary '\0'),(49,639,4,15,_binary '\0','2023-09-21 15:11:23.443000',_binary '\0'),(50,773,13,20,_binary '\0','2023-09-21 15:42:01.641000',_binary '\0'),(51,623,13,20,_binary '\0','2023-09-21 16:04:53.596000',_binary '\0'),(52,389,27,15,_binary '\0','2023-09-21 20:16:39.687000',_binary '\0'),(53,673,13,20,_binary '\0','2023-09-22 05:10:29.408000',_binary '\0'),(54,673,13,20,_binary '\0','2023-09-22 05:15:12.327000',_binary '\0'),(55,623,13,19,_binary '\0','2023-09-22 07:23:28.354000',_binary '\0'),(56,773,13,19,_binary '\0','2023-09-22 07:40:05.377000',_binary '\0'),(57,923,13,19,_binary '\0','2023-09-22 09:39:15.804000',_binary '\0'),(58,289,4,15,_binary '\0','2023-09-22 11:19:12.772000',_binary '\0'),(59,589,4,15,_binary '\0','2023-09-23 17:41:22.855000',_binary '\0'),(60,773,13,20,_binary '\0','2023-09-24 06:13:27.028000',_binary '\0'),(61,489,5,15,_binary '\0','2023-09-25 17:52:48.553000',_binary '\0'),(62,573,5,20,_binary '\0','2023-09-25 17:55:58.837000',_binary '\0'),(63,389,5,15,_binary '\0','2023-09-25 17:59:04.316000',_binary '\0'),(64,773,13,20,_binary '\0','2023-09-25 18:17:54.896000',_binary '\0'),(65,773,13,20,_binary '\0','2023-09-25 18:18:52.545000',_binary '\0'),(66,489,54,15,_binary '\0','2023-09-27 09:48:20.751000',_binary '\0'),(67,673,13,20,_binary '\0','2023-09-27 15:44:38.653000',_binary '\0'),(68,2007,5,22,_binary '\0','2023-09-27 16:20:28.794000',_binary '\0'),(69,1807,5,23,_binary '\0','2023-09-27 16:22:26.529000',_binary '\0'),(70,2257,4,23,_binary '\0','2023-09-27 16:27:20.197000',_binary '\0'),(71,2157,13,23,_binary '\0','2023-09-27 16:27:40.071000',_binary '\0'),(72,1707,45,23,_binary '\0','2023-09-27 16:28:20.307000',_binary '\0'),(73,773,13,20,_binary '\0','2023-09-27 16:41:13.904000',_binary '\0'),(74,773,13,19,_binary '\0','2023-09-28 10:00:09.215000',_binary '\0'),(75,773,13,19,_binary '\0','2023-09-28 11:52:13.057000',_binary '\0'),(76,773,13,20,_binary '\0','2023-09-28 12:09:40.766000',_binary '\0'),(77,773,13,19,_binary '','2023-09-28 12:33:47.490000',_binary ''),(78,923,13,20,_binary '','2023-09-28 14:14:10.278000',_binary ''),(79,873,13,19,_binary '\0','2023-09-29 11:11:21.917000',_binary '\0'),(80,589,4,22,_binary '','2023-10-02 11:28:41.345000',_binary '\0');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `distance` bigint NOT NULL,
  `flight_time` bigint NOT NULL,
  `ticket_fare` bigint NOT NULL,
  `arrival_airport_id` bigint NOT NULL,
  `departure_airport_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfgy0gl8x94s6jf9pw5irxhjax` (`arrival_airport_id`),
  KEY `FKrhi4sk6rhj303om7ur6wp1g4q` (`departure_airport_id`),
  CONSTRAINT `FKfgy0gl8x94s6jf9pw5irxhjax` FOREIGN KEY (`arrival_airport_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKrhi4sk6rhj303om7ur6wp1g4q` FOREIGN KEY (`departure_airport_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,385,60,289,2,1),(2,385,60,289,1,2),(3,2445,240,1834,3,4),(4,2445,240,1834,4,3),(5,764,90,573,1,4),(6,764,90,573,4,1),(7,2143,210,1607,2,3),(8,2143,210,1607,3,2),(9,350,60,560,2,4),(10,1000,120,300,1,3);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gender` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `reservation_id` bigint DEFAULT NULL,
  `identification_number` varchar(255) DEFAULT NULL,
  `seat_number` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6voxk3ppixqgl102dbf4ccuuh` (`reservation_id`),
  CONSTRAINT `FK6voxk3ppixqgl102dbf4ccuuh` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,'MALE','Bende',3,'01',1),(2,'FEMALE','valaki',3,'11',2),(3,'FEMALE','dsadas',4,'cyxcyx',3),(4,'FEMALE','Kiss Mária',5,'KH123567',4),(5,'FEMALE','Helga B',6,'56985RE',5),(6,'MALE','Bende S',6,'76543DA',6),(7,'MALE','Daniel Varga',7,'KF123456',7),(8,'MALE','Valami Test',8,'54656',8),(9,'MALE','Dániel István Varga',9,'124567',9),(10,'MALE','Dániel István Varga',10,'0625645855',10),(11,'MALE','Varga Dániel',11,'23451fg',11),(12,'MALE','Valami Test',12,'213123',12),(13,'MALE','Fr',13,'Fff',1),(14,'FEMALE','vince',14,'0110',2),(15,'MALE','Varga István',15,'123456HE',3),(16,'MALE','Varga Dániel',15,'121235RT',4),(17,'MALE','1 1',16,'ik654321',5),(18,'MALE','TesztLaci',17,'KA123456',6),(19,'MALE','Teszt Laci',18,'AB123456',7),(20,'MALE','Teszt Laci',19,'AB123456',8),(21,'FEMALE','Teszt László',19,'AB654321',9),(22,'MALE','Bende',20,'42343242',10),(23,'FEMALE','Cica',20,'98798',11),(24,'MALE','Kutya',20,'234234',12),(25,'MALE','Dániel István Varga',21,'asd',3),(26,'FEMALE','Varga-Kokas Zsófi',22,'sdasdsadsad',6),(27,'MALE','Varga Dániel',22,'asdasdasdas',4),(28,'MALE','Dániel István Varga',23,'eewrwer',8),(29,'FEMALE','Varga-Kokas Zsófi',23,'wer',12),(30,'MALE','Dániel István Varga',24,'234213',1),(31,'MALE','Teszt Sokadik',25,'ER123456',2),(32,'FEMALE','Teszt Sokadik felesége',25,'she121321',1),(33,'FEMALE','fcrfr',26,'01',3),(34,'FEMALE','dede',27,'01',5),(35,'MALE','Dániel István Varga',28,'54656',6),(36,'MALE','Dániel István Varga',29,'54656',4),(37,'MALE','Dániel István Varga',30,'54656',1),(38,'MALE','Dániel István Varga',31,'54656',8),(39,'MALE','Dániel István Varga',32,'54656',10),(40,'FEMALE','Fricz Edina',33,'123456AE',4),(41,'MALE','A',34,'011',1),(42,'FEMALE','B',34,'022',6),(43,'MALE','Teszt Laci',35,'AB123456',3),(44,'MALE','Dániel István Varga',36,'1234567EG',2),(45,'FEMALE','Söröző medve',37,'uuuu999',4),(46,'MALE','Dániel István Varga',38,'4572757',7),(47,'FEMALE','Lilla Szeremi',39,'dddddddd',8),(48,'MALE','vargaa Dani',40,'asdasd',5),(49,'FEMALE','dededed',41,'011111',7),(50,'FEMALE','Dániel István Varga',42,'asdsa',7),(51,'MALE','Dániel István Varga',43,'145678',5),(52,'FEMALE','Kondor Zita',44,'123456',6),(53,'MALE','Dániel István Varga',45,'45626626',5),(54,'MALE','Dániel István Varga',46,'4562356',8),(55,'MALE','Dániel István Varga',47,'4562156',9),(56,'MALE','Dániel István Varga',48,'4562315',10),(57,'MALE','bende sari',49,'000111',1),(58,'MALE','Dániel István Varga',50,'36306463902',5),(59,'MALE','Dániel István Varga',51,'0659562321',6),(60,'MALE','Teszt Utas',52,'ID123456',8),(61,'MALE','Varga daniel',53,'1234568',7),(62,'MALE','Dániel István Varga',54,'65656',6),(63,'MALE','Dániel István Varga',55,'4568952HE',7),(64,'MALE','Dániel István Varga',56,'545454HE',7),(65,'MALE','Dániel István Varga',57,'545252HE',8),(66,'FEMALE','ffffffff',58,'011111',4),(67,'MALE','béla kiss',59,'121212R',8),(68,'MALE','Dániel István Varga',60,'556565656',9),(69,'FEMALE','Kondor Zita',61,'123456SS',7),(70,'FEMALE','khfcdbak aksj',62,'123456',1),(71,'FEMALE','sidfgi dsofus',63,'123456',8),(72,'FEMALE','Dániel István Varga',64,'asdasd',7),(73,'MALE','Dániel István Varga',65,'werwerewr',0),(74,'MALE','weecanbKK',66,'129834AA',1),(75,'FEMALE','Varga Dániel',67,'56565657',8),(76,'FEMALE','Kondor Zita',69,'123456AA',1),(77,'MALE','Barna Bence',69,'452976BB',4),(78,'MALE','Kovács Bálint',69,'326134CC',7),(79,'FEMALE','Kovács Mariann',69,'469214DD',8),(80,'MALE','Bende Sári',70,'0123456E',10),(81,'FEMALE','Balázs Helga',70,'0123456FE',12),(82,'MALE','Példa Ödön',70,'0123456EE',11),(83,'MALE','Varga Dániel',71,'5654855HE',9),(84,'FEMALE','Varga-Kokas Zsófi',71,'5654555HI',11),(85,'FEMALE','Szerémi Lilla',72,'RTHTZ77',5),(86,'MALE','Lénárt Levente',72,'HHZUK88',3),(87,'MALE','Dániel István Varga',73,'5454545ht',6),(88,'MALE','Varga Dániel',74,'5455hE',9),(89,'MALE','Dániel István Varga',75,'4545454GT',4),(90,'MALE','Dániel István Varga',76,'4544545HE',5),(91,'MALE','Dániel István Varga',77,'456789HE',7),(92,'MALE','Dániel István Varga',78,'456555HE',7),(93,'MALE','Daykj ikjxkx',79,'Kxkdkdj44',8),(94,'FEMALE','valaki',80,'qww111',1);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `password_reset_enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'xxx','12@gmail.com',_binary '\0','bende','$2a$12$tF0OjIo0CipDhyW3iOaGY.nIWBmgBXyD5S9BncQwPxpFYf6XvrH36','0654337337','ROLE_USER',_binary '\0'),(2,'cdcdcd','3@valami.com',_binary '\0','Sári Bende','$2a$12$0vxTUaXNDCLieyjVORDc4.677r6Bn/zRntz.syabUO9CBa531DKqq','2134124321412','ROLE_USER',_binary '\0'),(3,'Eszterke lakása','eszter.fodor96@gmail.com',_binary '\0','Eszterke','$2a$12$2sNu.s66IUcbf7B0XA7b9OKzOQrQJU.15u5gRhlfTgSfxFHcuUt3O','Eszterke telefonja ','ROLE_USER',_binary '\0'),(4,'6725, Szeged','saribende@gmail.com',_binary '\0','Bende Sari','$2a$12$TqO3Ru1/UeIeM7qZVLR3ruVoljaKsQ/pvAmqm0BOijJDDq0K4CQDy','+36300123456','ROLE_USER',_binary '\0'),(5,'1051, Budapest, Kossuth Lajos utca 1','zitakondor@gmail.com',_binary '\0','Zita Kondor','$2a$12$E32j9xtFkQT2xqNi/jW6oeHigIag7SX3CLU938L3NxZ6cGZ/B5Wli','0612345678','ROLE_USER',_binary '\0'),(6,'asdasdasdsa','valami@valami.hu',_binary '\0','asdasdsa','$2a$12$zjPx/0aqI.ovZ7nlSBlYUu1eV2zufqLOl5kZToeIE2YOooBurxj9a','25645855','ROLE_USER',_binary '\0'),(7,'valahol','varga@gmail.com',_binary '','Dani','$2a$12$MzY9pxji4L1CDFYHGp3vp.cNepXon/cwvnz3aw4bHlOA7/hPleixm','03544154545','ROLE_USER',_binary '\0'),(8,'','varga.daniasdasd8181@gmail.com',_binary '\0','Daniel Varga','','','ROLE_USER',_binary '\0'),(9,'Szegeden','varga.sdasdsa@gmail.com',_binary '\0','Daniel Varga','','6456465','ROLE_USER',_binary '\0'),(10,'','toth.vac@gmail.com',_binary '','Zoltán Tóth','','','ROLE_ADMIN',_binary '\0'),(11,'lánchíd alatt/b','lilla.szeri@gmail.com',_binary '\0','lili','$2a$12$jsINf4JWQrgIjyAIAFge4O6jy231z3Pix6RVe05isUI0/uYkcRsRK','123456789','ROLE_USER',_binary '\0'),(12,'lánchíd alatt/b','lilla.szerejhbghmi@gmail.com',_binary '','lili','$2a$12$26tL09vXaVBH9SfwUNXaZ.2wObUI58DoxgQO4cgFrY7v3voDCk38a','123456789','ROLE_USER',_binary '\0'),(13,'6722 Szeged, Egyetem utca 1','varga.dani8181@gmail.com',_binary '\0','Daniel Varga','$2a$12$i6TLjQ9mIGX2CNwvQR9SWO71.cqx7APpARyVpiIGddUMpQSRT1uvK','+36306463902','ROLE_USER',_binary ''),(14,'6722, Szeged, Egyetem utca 1','testadmin@test.hu',_binary '','Test Admin','$2a$12$HodYJXCnm0NI89SUMYjD4.3G1w/XP7JpDnTNGWwnQNfYd50mZ.G.G','+36306463902','ROLE_ADMIN',_binary '\0'),(15,'6722, Szeged, Egyetem utca 1','ksdhfk@gmail.com',_binary '','Varga Dániel','$2a$12$kHDja3mBrZiu/Bh.lDyAvuwUf/RaffjyaNzbVoPOb7vPNVNgIki8y','+36306463902','ROLE_USER',_binary '\0'),(16,'6722, Szeged, Egyetem utca 1','danivarga121432332@gmail.com',_binary '','Varga Dániel','$2a$12$82Qvf02V5k/brWeXcVK0K.2YHuhv2caDqFlAvhl80VrGa0kfyysU.','+36306463902','ROLE_USER',_binary '\0'),(17,'','danivarga12321321321312@gmail.com',_binary '','','$2a$12$bKPHQRfIRaXFzgG3PlCboOGl9zoEVxgrsvULzEZQc1RmPCWgasE4K','','ROLE_USER',_binary '\0'),(18,'6722, Szeged, Egyetem utca 1','danivarga124323412@gmail.com',_binary '','Varga Dániel','$2a$12$97P.DmOVdLMwjgjTq1Uhb.Bgfgtftk/wUgJYETqk3VTsQZGtZnbmC','+36306463902','ROLE_USER',_binary '\0'),(20,'6722, Szeged, Egyetem utca 1','danivarga12222112@gmail.com',_binary '','Varga Dániel','$2a$12$l4tKQe5mAd8r6j0YWpN/ZeKgij12itBZ/FbRd2Bn4WCOAZqAz2Cje','36306463902','ROLE_USER',_binary ''),(21,'6722, Szeged, Egyetem utca 1','danivarga1fdsf212@gmail.com',_binary '','Varga Dániel','$2a$12$Cw51/afpchyu1gZRGcyUyevZvBIyZ2q9PxW8njv/bzT5H0m2y9Cl6','36306463902','ROLE_USER',_binary '\0'),(22,'6722, Szeged, Egyetem utca 1','danivarga123212@gmail.com',_binary '','Varga Dániel','$2a$12$AKkvdNjsIeeKd/rFcVqqbeTE2vb.JEXqrBiN8rtlXOcUq66afFCPy','36306463902','ROLE_USER',_binary ''),(23,'Test home','danivarga1233312@gmail.com',_binary '','Valami Test','$2a$12$djAq7LhKdSe071qob46V.ekfALp6e8cudanEM6eh4djopKCHwRrFC','0659562321','ROLE_USER',_binary ''),(24,'6722, Szeged, Egyetem utca 1','danivarga1243412@gmail.com',_binary '','Varga Dániel','$2a$12$ZJVXVvMnHdL0AlmDqTdajOUb6VHNQ3Gkogi2f/IpZresmCC.O1gIi','36306463902','ROLE_USER',_binary ''),(25,'Test home','danivarga121432@gmail.com',_binary '','Valami Test','$2a$12$.Kz9XslBVEQqtSnQb6rRiOCgzE2PVQnIN/Yj.hjAv.qXyYFByQkMC','0659562321','ROLE_USER',_binary ''),(26,'Budapest','teszt.birolaszlo@gmail.com',_binary '','Teszt Laci','$2a$12$Rm1IvRWGasQzNquBlaY98u3Bl/B0o5dJ1Bd8S8zgq0qCwiNa13fkG','1','ROLE_ADMIN',_binary ''),(27,'1','lacibiro0719@gmail.com',_binary '','Teszt László','$2a$12$ssaRYvz7lW03Yw6W1AgFYOYGAUtu3yGuSPT5His5i7L65Bxgb.r/a','1','ROLE_USER',_binary ''),(28,'6726 Szeged','ivarga02@hotmail.com',_binary '','Varga István','$2a$12$c7d3WH.uxPEypkFUmIUwy.VLUups0StnavztMrqjHgyr900H5FV5q','0036209539021','ROLE_USER',_binary '\0'),(29,'1','uh@uh.uh',_binary '\0','1 1','$2a$12$6I/pDMAvEVios0a9Q5FdIOH4OBMGQqIYWXUqT6grYOoK2RdQIHpKS','1','ROLE_USER',_binary '\0'),(30,'1','hu@huhuhu.hu',_binary '\0','1 1','$2a$12$xKaIAqqo/zS.lSeNNJ.yC.wmAxZmal7UfbUQ9gZV6u8kjzzGV3xLe','1','ROLE_USER',_binary '\0'),(31,'1','birolaszlo993@gmail.com',_binary '\0','1 1','$2a$12$vkVtxY6/oNPy7JwaLNsrkeZf6BC8LWT79b6VkHqNSK/Lr/BD5s6lG','1','ROLE_USER',_binary ''),(32,'6722, Szeged, Egyetem utca 1','danivarga12312@gmail.com',_binary '','Varga Dániel','$2a$12$qzHYPirr.4XMG1Et.g90ku2NqGvy.grkH.YvEpj6iEEELWT711sIy','36306463902','ROLE_USER',_binary ''),(33,'6722, Szeged, Egyetem utca 1','danivarga1243412@gmail.com',_binary '\0','Varga Dániel','$2a$12$sMthG.HQsipVjdr.TKe2VenAu.sqTS8NX6NKuIjTdZhcAvBWORArG','36306463902','ROLE_USER',_binary '\0'),(34,'6722, Szeged, Egyetem utca 1','danivarga121444442@gmail.com',_binary '\0','Varga Dániel','$2a$12$fZPlnNRnO/gp8x/V95cLNOwIisckdLMWmX2ZqdPwG/rMoHI6suxiO','36306463902','ROLE_USER',_binary '\0'),(35,'dede','a@b.hu',_binary '\0','bende f','$2a$12$x35t9IT7eAK8CAv90IEFpea8UqHm1wmSgcc8QUwKudFdZ1dVIdBTi','22333','ROLE_USER',_binary '\0'),(36,'aakfbaeofuabo','zitakondor1234@gmail.com',_binary '\0','Test Géza','$2a$12$tkLklRBVX2B6.crERkptiuBDOQ/9.LAMw0Uw23jlZVJVVnUwrBAJS','1312414','ROLE_USER',_binary '\0'),(37,'deded','deded@gmail.hu',_binary '\0','bende bende','$2a$12$l2mtLY5x5NC7.Yp4crufyecMYYr1x8gPbgPJJZYvDnPnfDkNwxuRG','3630333','ROLE_USER',_binary '\0'),(38,'adasd','asdasdadas@valami.hu',_binary '\0','Varga Dániel','$2a$12$.x1ZG3yBqd5Sq7kxTyazbuf1W4K2QWHQrvCo6aj3dDKMR5FjLxcau','+36514552252','ROLE_USER',_binary '\0'),(39,'6722, Szeged, Egyetem utca 1','danivarga1243412@gmail.com',_binary '','Varga Dániel','$2a$12$YfjBVJbEKRdKpXFdlbpMMu4YuvRr1Srwpr9PNjC9RwjBE5zX/vrLm','+36306463902','ROLE_USER',_binary ''),(40,'6722, Szeged, Egyetem utca 1','danivarga1243412@gmail.com',_binary '','Varga Dániel','$2a$12$1osPq9EudYwkeZP0zlm/vOlzlgJ4qOEuDo63ErAk67U7y7.pd3qgi','+36306463902','ROLE_USER',_binary ''),(41,'6722, Szeged, Egyetem utca 1','danivarga12gsdfsdf12@gmail.com',_binary '','Varga Dániel','$2a$12$qC9FfutCXk0B9Eg./wVkTOHD00DnJiGDhua.VM6JM7er/kto6iNb.','+36306463902','ROLE_USER',_binary ''),(42,'Progmasters utca 100','regsedina@gmail.com',_binary '','Fricz Edina','$2a$12$MxYU6kYzP/H7EF5Y8al0IuTMIZNdJQB1QNV.6jhiKe90TS4NVrclC','+36301234567','ROLE_USER',_binary '\0'),(43,'6722, Szeged, Egyetem utca 1','danivarga1214342@gmail.com',_binary '','Varga Dániel','$2a$12$6RgiyC7U5lzO7KAlg2aEOuYCNzrY33bl.ygcWv5B/IiZsrYTwqPe2','+36306463902','ROLE_USER',_binary ''),(44,'6722, Szeged, Egyetem utca 1','danivarga1223423423432412@gmail.com',_binary '','Varga Dániel','$2a$12$Uw7reXI17cMs25Cs57eZHetoC627MkWVMIIKDLc3zfeWBA1NpJxri','+36306463902','ROLE_USER',_binary ''),(45,'984 01 Losonc, Zeleznicna 166','szamcsy@gmail.com',_binary '','Lilla Szerémi','','+36901234567','ROLE_USER',_binary '\0'),(46,'6722, Szeged, Egyetem utca 1','danivarga12333213123212@gmail.com',_binary '','Varga Dániel','$2a$12$pYxyrqMYtt7.lOOGUNmfy..L4OqRyKE.1D31C/tbLChW.eZ4eCrTy','+36306463902','ROLE_USER',_binary ''),(47,'6722, Szeged, Egyetem utca 1','danivarga12567897543312@gmail.com',_binary '','Varga Dániel','$2a$12$mbW2mMKxEFxYB/55fskbb.Rs2Y8NeIsWgzN9.2neT9Znx2dPVKZi6','+36306463902','ROLE_USER',_binary ''),(48,'6722, Szeged, Egyetem utca 1','danivarga12162365333333332@gmail.com',_binary '','Varga Dániel','$2a$12$DMuTO8Q/I0TwMm685Lcfz.LKpmNbqVq64N6I34MRRO2kyQ9JpPJX6','+36306463902','ROLE_USER',_binary ''),(49,'6722, Szeged, Egyetem utca 1','danivarga12562323434312@gmail.com',_binary '','Varga Dániel','$2a$12$zoERPv6Vxj.SbuhRaFU3SORg0fuuPVcpyQzOaqMrdHGfq/IG.L8Bm','+36306463902','ROLE_USER',_binary ''),(50,'6722, Szeged, Egyetem utca 1','danivarga1234324234232222212@gmail.com',_binary '','Varga Dániel','$2a$12$UctZxFTXjxieJgFok1drm.pfEWmSOtm8ovQfnws/MHEUwyff.Z0ky','+36306463902','ROLE_USER',_binary ''),(51,'6722, Szeged, Egyetem utca 1','danivarga124323432412@gmail.com',_binary '','Varga Dániel','$2a$12$gvJGMOJ5/tycB2UefmlBQeaIMoFROAMLFvc1jWmgvZ4fy/p.mmTl.','+36306463902','ROLE_USER',_binary ''),(52,'6722, Szeged, Egyetem utca 1','danivarga12fdsfsdd12@gmail.com',_binary '','Varga Dániel','$2a$12$wbzTaK69Rv5fdoaSvAA1ZuzbkQzhjXIlQKpUgOoNlDXkI1NQpppRS','+36306463902','ROLE_USER',_binary ''),(53,'6722, Szeged, Egyetem utca 1','danivarga125656565612@gmail.com',_binary '','Varga Dániel','$2a$12$7ztfJnpNEskgmvojS.CwFuPkxAEgSX3qCirEMOr6qUxbfYsM.86di','+36306463902','ROLE_USER',_binary ''),(54,'+369098712356','weecanbkk@gmail.com',_binary '','weecanb KK','$2a$12$rm4t5wQrdQ8GtxD8DjGz3uDd2lhhpGX9N9h1QlOSSErB58f3Zjo2m','+369098712356','ROLE_USER',_binary '\0'),(55,'6722, Szeged, Egyetem utca 1','danivarga1215555555555552@gmail.com',_binary '','Varga Dániel','$2a$12$XKShsPG8jXUuy5GW/a/JWuy.E1zg0Oz80A9CKCp03JHFxJzd1O6R2','+36306463902','ROLE_USER',_binary '\0'),(56,'6722, Szeged, Egyetem utca 1','danivarga1256565612@gmail.com',_binary '','Varga Dániel','$2a$12$qe3aDGUQydjTMuwvzM62Putfh7pRuMJ.L9mCYIp9.I8Q9WMloMgQ6','+36306463902','ROLE_USER',_binary '\0'),(57,'asdas','varga.dani1212@gmail.com',_binary '\0','Dániel István Varga','$2a$12$eXeHC6VL4NgrZ93oYlvwdefON5/dZ7KXeGcFJHu7rDnBsftleR6Hm','+36306463902','ROLE_USER',_binary '\0'),(58,'6722, Szeged, Egyetem utca 1','danivarga1244566223412@gmail.com',_binary '','Varga Dániel','$2a$12$jvGx7BJgTWTzxNtQkBaCQugcK.6Hwtux/3d.LPyvqqrW.APlIUYPy','+36306463902','ROLE_USER',_binary '\0'),(59,'Test home','danivarga12456789912@gmail.com',_binary '','Valami Test','$2a$12$Vdm6RAhHodx.x.cqPllMl.8y.RGurrmv0OmAhazUtibS8gnY9oh..','0659562321','ROLE_USER',_binary '\0'),(60,'6722, Szeged, Egyetem utca 1','danivarga1256554554212@gmail.com',_binary '','Varga Dániel','$2a$12$F4msmXvx4wZ/RcRird//Veg5EIQtS./Bv0pSW9ppYppGsZFFR2nHC','+36306463902','ROLE_USER',_binary '\0'),(61,'6722, Szeged, Egyetem utca 1','danivarga1212@gmail.com',_binary '','Varga Dániel','$2a$12$wIX31BI7NhDLKU2RliovY.Q.ME/vZHGRH0uX8f9ouf7oi2mor9bg2','+36306463902','ROLE_USER',_binary '\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrdn0mss276m9jdobfhhn2qogw` (`user_id`),
  CONSTRAINT `FKrdn0mss276m9jdobfhhn2qogw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
INSERT INTO `verification_token` VALUES (1,'2023-09-27 14:13:35.310000','170e1bb4-4b3a-498a-a112-bdd7c19011ef',1),(2,'2023-09-27 14:19:58.014000','05b9c312-997e-44b1-b586-1b739f5baebc',2),(3,'2023-09-29 13:52:01.538000','f49bb758-a7a0-4636-af3b-35b968b36094',3),(4,'2023-09-30 16:22:12.272000','d1a6ab30-c935-4a86-9dfe-d30c141fea0e',4),(5,'2023-09-30 16:28:55.121000','19bd5a05-48ca-47a8-a1ab-26acea2923e2',5),(6,'2023-09-30 16:47:54.190000','ec1ba705-068b-40ad-9457-ec32f9c969d8',6),(7,'2023-09-30 17:16:42.828000','a099c501-4688-439d-b608-d4e1836628ef',7),(8,'2023-09-30 17:43:14.635000','33e498f9-1f69-412b-8c1f-ad888aa25283',11),(9,'2023-09-30 17:43:16.316000','4b6d31b7-8e3f-4405-981a-9e456df6b4e8',12),(10,'2023-10-01 07:16:32.024000','196feb34-64f5-4c57-8a83-9e4412ae3e27',14),(11,'2023-10-01 12:07:38.931000','a2aab89b-0622-4c5f-a5d6-2797b3610054',15),(12,'2023-10-01 12:10:15.295000','4a9081e0-17f4-4a6b-897e-1aa3d3ce919d',16),(13,'2023-10-01 12:42:02.763000','b2cc8116-644c-47b8-a8b4-659f2aed62ec',17),(14,'2023-10-01 14:23:48.026000','58e06bde-f393-4e70-9d24-c6acfeed4a7e',18),(15,'2023-10-08 08:23:32.404000','8486c9f3-ec8e-4f92-8ade-fc31f0b2398a',20),(16,'2023-10-08 09:01:29.600000','fc2f25c6-3a30-469d-8c1a-d0360c61505c',21),(17,'2023-10-08 11:09:36.596000','93b1c9ba-2d75-42a5-b346-91cba698f989',22),(18,'2023-10-08 11:29:29.087000','e3f909a0-814c-4809-873c-15093214cc44',23),(19,'2023-10-08 11:48:15.172000','681707ac-19f2-4f3d-8a25-900eb7ee08ad',24),(20,'2023-10-08 13:21:11.770000','9cdfa9c1-30d6-4674-88e7-57d7fd70bca4',25),(21,'2023-10-10 09:02:52.241000','fd9b62e5-76d1-4ccb-bd8f-e2dfcc10f4c4',26),(22,'2023-10-10 09:20:53.428000','9b8f4b7e-8767-4aa6-9fa7-fc0215c27bfd',27),(23,'2023-10-11 06:47:15.328000','5f64523e-e3ee-430c-9ab3-ad9abadd8fce',28),(24,'2023-10-14 08:26:08.319000','83024adb-213d-4db7-abd2-4d875da0397b',29),(25,'2023-10-14 08:26:25.004000','fa4e6ddf-cb4b-4ea1-a93b-729cd37fde1e',30),(26,'2023-10-14 08:26:38.727000','3217bde4-e541-42de-b3a8-d98a92fd4e29',31),(27,'2023-10-15 08:46:16.052000','eb0939bb-4ec6-4fd6-a424-7c99588b6e36',32),(28,'2023-10-15 08:55:21.019000','e44763c1-f197-4597-8910-6851642213ef',33),(29,'2023-10-15 08:57:13.971000','6e52ba7f-fd43-45bd-b89b-693329f8c152',34),(30,'2023-10-15 09:01:54.252000','adae6e5e-4eff-44b3-9ee6-c4ec30881479',35),(31,'2023-10-15 09:01:59.475000','2bf770af-588f-42be-aeb1-6a9f35e0ca90',36),(32,'2023-10-15 09:03:44.352000','0fe2bed3-ea15-4ab9-90c0-d09f02da2391',37),(33,'2023-10-15 10:01:23.321000','2632bbf6-d36a-482d-935d-13b1539276e7',38),(34,'2023-10-15 11:15:03.818000','4af2bf77-e835-4121-ae46-a4cdcdde23f6',39),(35,'2023-10-15 11:39:42.790000','232a0916-994a-4789-ac02-6577bb7489fb',40),(36,'2023-10-15 11:56:39.655000','2bf6e942-9111-4cfa-ae87-bf425693b848',41),(37,'2023-10-15 12:13:52.746000','c9bc4427-4a77-49ce-8574-c1caec3720dc',42),(38,'2023-10-15 12:45:24.954000','3782495e-8d3c-4fad-be82-217961792c8a',43),(39,'2023-10-15 14:20:27.839000','f8d975ff-a427-4d46-b414-4d2ecac45e92',44),(40,'2023-10-21 08:15:39.046000','8ace8da0-366f-4e33-981f-60a00dcbe7b3',46),(41,'2023-10-21 11:41:40.042000','746825a7-74c0-4f85-a94d-368c698c04bc',47),(42,'2023-10-21 12:21:08.416000','d86a9187-1e8d-4108-97cf-866a93856ad7',48),(43,'2023-10-21 12:38:15.293000','ba089fcb-4f5c-4bd3-a7c6-e68290a1d727',49),(44,'2023-10-21 14:28:36.915000','af77be1d-021d-46c2-a1f2-70573bcc1496',50),(45,'2023-10-22 07:09:07.326000','d51886de-d321-4613-9dc7-eab4849b7efb',51),(46,'2023-10-22 07:26:19.375000','d8f39143-340b-49e1-a49c-103cb9ff66b9',52),(47,'2023-10-22 09:25:15.475000','9b3ef695-655f-411b-9fda-efe0ea61681a',53),(48,'2023-10-27 09:34:48.569000','1b9d7c67-5503-4dc9-bc4b-44401af5c2a9',54),(49,'2023-10-27 10:35:29.722000','62dfbf72-32bb-4cca-86c1-dc33f18b605e',55),(50,'2023-10-27 16:28:34.769000','fea84943-c3b9-418a-ad5d-8d7d42e2098f',56),(51,'2023-10-28 09:47:13.059000','d75b3ed9-7d19-4d09-8809-b05c99a27cbe',57),(52,'2023-10-28 11:39:39.280000','3396e787-37a7-402a-a533-65c3f6a94492',58),(53,'2023-10-28 11:57:07.369000','5a2d9b8e-7bc6-421c-97af-f0333af5d370',59),(54,'2023-10-28 12:20:51.836000','12af8ff1-d8af-463d-a238-7f9f370d3dca',60),(55,'2023-10-28 14:01:37.306000','4c2e9027-c362-488e-a0bb-0306a3bd1907',61);
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-04  8:53:53
