-- MySQL dump 10.13  Distrib 5.7.35, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tiny_to_oss
-- ------------------------------------------------------
-- Server version	5.7.35

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
-- Table structure for table `history_image_user`
--

DROP TABLE IF EXISTS `history_image_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_image_user` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `user_id` int(12) NOT NULL,
  `image_name` varchar(255) NOT NULL COMMENT '图片名字',
  `image_link` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `history_image_user_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户上传的历史记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_image_user`
--

LOCK TABLES `history_image_user` WRITE;
/*!40000 ALTER TABLE `history_image_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `history_image_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `account_name` varchar(255) NOT NULL COMMENT '用户名称',
  `account_password` varchar(255) NOT NULL COMMENT '用户密码',
  `access_id` varchar(255) DEFAULT NULL COMMENT '传输oss的参数accessId',
  `access_key` varchar(255) DEFAULT NULL COMMENT '传输oss的参数accessKey',
  `endpoint` varchar(255) DEFAULT NULL COMMENT '传输oss的参数endpoint',
  `bucket` varchar(255) DEFAULT NULL COMMENT '传输oss的参数bucket',
  `dir` varchar(255) DEFAULT NULL COMMENT '传输oss的参数dir，图片的保存路径',
  `create_time` datetime NOT NULL COMMENT '用户创建的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_account_name_uindex` (`account_name`),
  UNIQUE KEY `t_user_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-02 15:36:22
