-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bacon
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `bacon`
--

DROP TABLE IF EXISTS `bacon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bacon` (
  `id` varchar(11) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `data` longtext,
  `response_run_id` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bacon_response_idx` (`response_run_id`),
  CONSTRAINT `bacon_response` FOREIGN KEY (`response_run_id`) REFERENCES `response` (`run_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bacon`
--

LOCK TABLES `bacon` WRITE;
/*!40000 ALTER TABLE `bacon` DISABLE KEYS */;
INSERT INTO `bacon` VALUES ('AQdObnNxAJJ',0,'2019-06-06 11:34:05','2019-06-06 11:34:06','[\"Picanha tenderloin meatball frankfurter biltong, alcatra short loin beef ribs ribeye prosciutto jowl.  Tongue turducken shankle ground round picanha fatback beef ribs spare ribs sausage hamburger corned beef capicola tenderloin pork chop ham.  Spare ribs jerky ham hock buffalo alcatra ribeye.  Ribeye meatloaf pastrami ham hock flank ground round fatback spare ribs burgdoggen boudin doner biltong.\"]',57,1),('bhRMrXAedzL',0,'2019-06-04 11:53:45','2019-06-04 11:53:45','[\"Pork loin chuck spare ribs, pig alcatra picanha pastrami.  Corned beef rump cupim boudin meatloaf picanha doner ball tip tri-tip frankfurter capicola pancetta.  Ham spare ribs capicola kielbasa, cupim chicken kevin swine frankfurter chuck ball tip drumstick rump.  Jowl turducken shoulder jerky short loin pastrami strip steak fatback buffalo biltong.  Fatback turducken cupim shoulder pancetta, cow boudin prosciutto ribeye.  Leberkas bresaola cow picanha short ribs, shank spare ribs flank meatball alcatra sirloin meatloaf.\"]',48,0),('dcRzrISqFPC',0,'2019-06-06 07:02:35','2019-06-06 07:02:36','[\"Bresaola capicola short loin strip steak fatback pork loin pork.  Short ribs strip steak ham turducken jowl spare ribs pork loin picanha jerky kielbasa ground round biltong buffalo kevin hamburger.  Beef rump porchetta pancetta venison.  Pork loin swine beef ribs shankle, t-bone flank alcatra kielbasa biltong kevin ball tip pastrami.\"]',52,1),('EdsGJmwWFnZ',0,'2019-06-06 11:20:30','2019-06-06 11:20:30','[\"Cupim capicola hamburger, jowl porchetta bacon filet mignon chuck pork loin shankle frankfurter meatball.  Capicola shankle cupim pig, tail bresaola sirloin landjaeger tri-tip biltong picanha tenderloin porchetta.  Ball tip strip steak doner tail landjaeger salami, corned beef porchetta shoulder beef ribs ground round sausage pastrami chicken turkey.  Short loin jerky cupim tri-tip.\"]',54,0),('GmJqAVgqnRG',0,'2019-06-04 11:53:41','2019-06-04 11:53:41','[\"Pork loin shoulder shank alcatra sausage.  Andouille frankfurter leberkas biltong boudin, burgdoggen kielbasa turkey.  Filet mignon beef boudin, capicola meatloaf sausage cupim chuck alcatra corned beef.  Sausage alcatra ham, kevin pig tongue ball tip meatloaf meatball bresaola spare ribs.  Strip steak beef salami filet mignon, chicken cupim ball tip cow pork rump prosciutto.  Porchetta pork picanha short ribs beef ribs turducken.\"]',48,0),('HjmSGfxtQMd',0,'2019-06-04 11:54:00','2019-06-04 11:54:00','[\"Biltong drumstick tongue ham hock ball tip frankfurter chicken, meatball andouille swine.  Swine cupim hamburger tail venison filet mignon picanha flank spare ribs sausage boudin meatloaf buffalo porchetta brisket.  Doner tenderloin pork belly buffalo filet mignon pork, salami cow ham pastrami.  Venison prosciutto salami tenderloin pork chop sausage tri-tip, capicola fatback pork loin flank.  Sausage ball tip t-bone, kevin spare ribs short loin doner alcatra filet mignon hamburger ground round turducken.  Alcatra pig pork loin tenderloin pastrami.  Sirloin bacon ground round boudin cupim bresaola fatback beef ribs chuck ball tip cow shoulder prosciutto rump.\"]',49,0),('IwXMmUYgjan',0,'2019-06-06 11:20:25','2019-06-06 11:20:26','[\"Tenderloin ham drumstick swine beef ribs hamburger cow capicola short ribs landjaeger tri-tip venison.  Burgdoggen corned beef turducken meatball, short ribs bacon salami pork loin.  Cupim venison rump doner shank.  Spare ribs pastrami fatback, alcatra burgdoggen meatball doner bresaola turducken pork belly.\"]',53,1),('JCCZiGjgYaX',0,'2019-06-04 11:53:50','2019-06-04 11:53:50','[\"Venison salami ball tip flank burgdoggen beef ribs andouille short loin short ribs hamburger sirloin ground round.  Venison leberkas jerky, ham pork loin pork chop pork belly chuck hamburger jowl pastrami shankle ground round.  Capicola bresaola short loin, cupim corned beef tenderloin spare ribs buffalo pig.  Drumstick salami chicken porchetta biltong capicola boudin tri-tip meatloaf swine beef ribs frankfurter.  Tongue landjaeger shoulder, beef ribs prosciutto shankle short loin ribeye brisket.\"]',48,0),('ORobuTcbsiv',0,'2019-06-06 11:20:40','2019-06-06 11:20:40','[\"Flank venison drumstick pork belly, pork chop ground round kielbasa prosciutto tenderloin turkey tri-tip.  Beef tenderloin boudin tongue burgdoggen chicken turkey bresaola landjaeger frankfurter t-bone.  Boudin meatloaf hamburger, ham hock shankle spare ribs bacon pork belly leberkas meatball tail jerky.  Strip steak chuck bresaola burgdoggen kevin kielbasa, beef swine buffalo rump bacon salami porchetta.  Bresaola leberkas venison pork fatback.\"]',56,0),('SVoMJCjYvic',0,'2019-06-06 06:43:56','2019-06-06 06:43:56','[\"Cupim turkey landjaeger, meatloaf alcatra shank ground round.  Doner bacon capicola pastrami pork belly alcatra hamburger cow tri-tip kevin pork chop picanha ground round tail boudin.  Tenderloin buffalo shank spare ribs tail.  Short loin ball tip tri-tip, tenderloin ham hock corned beef shank chuck pork drumstick.\"]',51,0),('SvOOISlguLe',0,'2019-06-06 11:20:35','2019-06-06 11:20:35','[\"Ham hock flank filet mignon ball tip t-bone picanha jowl.  Filet mignon rump salami landjaeger ribeye burgdoggen, drumstick pork loin jerky beef.  Shank meatball biltong hamburger, meatloaf tri-tip drumstick tenderloin rump chicken turducken cupim.  Pork chop tongue corned beef brisket, flank shankle tenderloin boudin.\"]',55,0),('VwopdlGTaUw',0,'2019-06-06 06:19:05','2019-06-06 06:19:05','[\"Chicken doner cupim cow ground round brisket.  Pastrami shankle frankfurter, ham porchetta jowl turducken prosciutto salami sirloin.  Burgdoggen chuck pork belly short loin swine shoulder ball tip, kevin shank tenderloin.  Corned beef fatback landjaeger tongue.  Short ribs pork chop hamburger salami ribeye boudin.\"]',50,0),('WzLYreWNTgx',0,'2019-06-04 11:53:55','2019-06-04 11:53:55','[\"Flank leberkas shankle meatloaf turkey.  Pancetta fatback drumstick, ham pork belly picanha jowl pastrami chuck pig buffalo ribeye venison tail.  Pancetta sirloin porchetta, short ribs pork belly ham flank doner biltong beef ribs cupim tenderloin.  Spare ribs flank pastrami corned beef, bresaola tongue rump beef cupim filet mignon pork belly pork prosciutto shank.  Biltong fatback boudin, cow ham hock frankfurter jerky ribeye tail.  Pork spare ribs cow tail.  Turducken t-bone flank ground round shoulder.\"]',49,0),('xCDUjHwSGyv',0,'2019-06-06 06:19:00','2019-06-06 06:19:03','[\"Ham bresaola chicken boudin ground round.  Turkey leberkas ham hock pastrami short loin jerky porchetta pork belly burgdoggen biltong meatloaf.  Meatball pork sirloin ham.  Jerky tenderloin pig pork loin brisket swine t-bone capicola, boudin strip steak venison andouille beef ribs.  Alcatra fatback pork loin, sausage bacon rump drumstick tail doner shoulder porchetta pork flank hamburger turkey.  Jerky biltong capicola shoulder pork loin bresaola tenderloin.\"]',50,3),('XEuZurYNNjl',0,'2019-06-06 07:02:40','2019-06-06 07:02:40','[\"Pastrami jerky short loin fatback pork belly.  Beef ribs swine strip steak turducken t-bone ham kevin drumstick.  Shank cupim filet mignon tri-tip alcatra turducken.  Ribeye short ribs meatloaf turducken biltong salami.  Strip steak brisket beef ribs turkey t-bone hamburger shoulder pig jowl pork chop pork belly.  Corned beef t-bone strip steak, shank ribeye picanha drumstick doner fatback jowl hamburger shoulder kevin leberkas boudin.\"]',52,0),('ZKKOcmQIoQI',0,'2019-06-06 11:34:10','2019-06-06 11:34:10','[\"Brisket beef ribs alcatra bresaola burgdoggen, cupim jowl corned beef.  Ham hock pork belly filet mignon short ribs chuck short loin biltong pig ball tip meatloaf shank.  Burgdoggen cow corned beef, ham brisket short loin jowl salami kielbasa turkey t-bone pig shankle porchetta andouille.  Prosciutto jerky bresaola pancetta shankle ball tip.  Fatback salami t-bone, landjaeger meatloaf pastrami leberkas cupim rump.  Cow porchetta chicken shoulder picanha rump.  Ham hock tongue ham drumstick.\"]',57,0);
/*!40000 ALTER TABLE `bacon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `histogram`
--

DROP TABLE IF EXISTS `histogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `histogram` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(245) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `histogram`
--

LOCK TABLES `histogram` WRITE;
/*!40000 ALTER TABLE `histogram` DISABLE KEYS */;
INSERT INTO `histogram` VALUES (81,'jerky',2,0),(82,'tri-tip',1,0),(83,'chicken',1,0),(84,'turducken',1,0),(85,'tongue',1,0),(86,'meatball',1,0),(87,'corned',1,0),(88,'beef',1,0),(89,'Shank',1,0),(90,'pork',1,0),(91,'shankle',1,0),(92,'biltong',1,0),(93,'ball',1,0),(94,'Filet',1,0),(95,'rump',2,0),(96,'flank',2,0),(97,'salami',1,0),(98,'cupim.',1,0),(99,'mignon',2,0),(100,'tip',1,0),(101,'loin',1,0),(102,'jowl.',1,0),(103,'beef.',1,0),(104,'hock',1,0),(105,'brisket,',1,0),(106,'meatloaf',1,0),(107,'boudin.\"]',1,0),(108,'t-bone',1,0),(109,'hamburger,',1,0),(110,'chop',1,0),(111,'drumstick',2,0),(112,'landjaeger',1,0),(113,'burgdoggen,',1,0),(114,'filet',1,0),(115,'tenderloin',2,0),(116,'picanha',1,0),(117,'ribeye',1,0),(118,'Pork',1,0),(119,'[\"Ham',1,0),(120,'porchetta.',1,0),(121,'meatball',1,0),(122,'beef',1,0),(123,'venison',2,0),(124,'kevin',1,0),(125,'Boudin',1,0),(126,'rump',1,0),(127,'ham',1,0),(128,'[\"Flank',1,0),(129,'hock',1,0),(130,'frankfurter',1,0),(131,'hamburger,',1,0),(132,'chop',1,0),(133,'Bresaola',1,0),(134,'jerky.',1,0),(135,'fatback.\"]',1,0),(136,'Strip',1,0),(137,'landjaeger',1,0),(138,'kielbasa,',1,0),(139,'bresaola',2,0),(140,'turkey',2,0),(141,'tri-tip.',1,0),(142,'ground',1,0),(143,'chuck',1,0),(144,'prosciutto',1,0),(145,'chicken',1,0),(146,'belly',1,0),(147,'steak',1,0),(148,'tongue',1,0),(149,'pork',4,0),(150,'boudin',1,0),(151,'shankle',1,0),(152,'kielbasa',1,0),(153,'salami',1,0),(154,'Beef',1,0),(155,'spare',1,0),(156,'burgdoggen',2,0),(157,'meatloaf',1,0),(158,'tail',1,0),(159,'t-bone.',1,0),(160,'buffalo',1,0),(161,'belly,',1,0),(162,'drumstick',1,0),(163,'swine',1,0),(164,'bacon',2,0),(165,'round',1,0),(166,'leberkas',2,0),(167,'tenderloin',2,0),(168,'ribs',1,0),(169,'jerky',1,0),(170,'Tongue',1,0),(171,'turducken',1,0),(172,'Ribeye',1,0),(173,'doner',1,0),(174,'meatball',1,0),(175,'corned',1,0),(176,'pastrami',1,0),(177,'beef',3,0),(178,'sausage',1,0),(179,'pork',1,0),(180,'boudin',1,0),(181,'shankle',1,0),(182,'hamburger',1,0),(183,'biltong,',1,0),(184,'alcatra',2,0),(185,'fatback',2,0),(186,'ham',2,0),(187,'flank',1,0),(188,'loin',1,0),(189,'jowl.',1,0),(190,'capicola',1,0),(191,'spare',2,0),(192,'hock',2,0),(193,'burgdoggen',1,0),(194,'frankfurter',1,0),(195,'meatloaf',1,0),(196,'buffalo',1,0),(197,'chop',1,0),(198,'ham.',1,0),(199,'ribeye.',1,0),(200,'Spare',1,0),(201,'[\"Picanha',1,0),(202,'round',2,0),(203,'tenderloin',2,0),(204,'picanha',1,0),(205,'ribeye',1,0),(206,'biltong.\"]',1,0),(207,'short',1,0),(208,'ground',2,0),(209,'ribs',5,0),(210,'prosciutto',1,0),(211,'drumstick.\"]',1,0),(212,'corned',2,0),(213,'brisket',1,0),(214,'pastrami',1,0),(215,'beef',1,0),(216,'cow',1,0),(217,'biltong',1,0),(218,'pig',2,0),(219,'alcatra',1,0),(220,'ham',2,0),(221,'tip',1,0),(222,'beef,',1,0),(223,'shoulder',1,0),(224,'beef.',1,0),(225,'hock',2,0),(226,'porchetta',2,0),(227,'Prosciutto',1,0),(228,'landjaeger',1,0),(229,'bresaola',2,0),(230,'burgdoggen,',1,0),(231,'rump.',2,0),(232,'filet',1,0),(233,'turkey',1,0),(234,'chuck',1,0),(235,'jerky',1,0),(236,'shank.',1,0),(237,'belly',1,0),(238,'chicken',1,0),(239,'tip.',1,0),(240,'tongue',1,0),(241,'cupim',2,0),(242,'pork',1,0),(243,'shankle',2,0),(244,'ball',2,0),(245,'andouille.',1,0),(246,'pancetta',1,0),(247,'kielbasa',1,0),(248,'salami',2,0),(249,'mignon',1,0),(250,'loin',2,0),(251,'meatloaf',2,0),(252,'[\"Brisket',1,0),(253,'t-bone',1,0),(254,'Fatback',1,0),(255,'Cow',1,0),(256,'jowl',2,0),(257,'Burgdoggen',1,0),(258,'leberkas',1,0),(259,'Ham',2,0),(260,'picanha',1,0),(261,'short',3,0),(262,'t-bone,',1,0),(263,'ribs',2,0);
/*!40000 ALTER TABLE `histogram` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER histogram
BEFORE UPDATE ON histogram
FOR EACH ROW 
BEGIN
  
        SET NEW.frequency = NEW.frequency + OLD.frequency;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `response` (
  `run_id` int(11) NOT NULL AUTO_INCREMENT,
  `client` varchar(240) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT '1',
  PRIMARY KEY (`run_id`),
  KEY `PK_run_id` (`run_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response`
--

LOCK TABLES `response` WRITE;
/*!40000 ALTER TABLE `response` DISABLE KEYS */;
INSERT INTO `response` VALUES (48,'0:0:0:0:0:0:0:1',0,'2019-06-04 11:54:00','2019-06-04 11:54:00',9),(49,'0:0:0:0:0:0:0:1',0,'2019-06-04 11:54:18','2019-06-04 11:54:18',0),(50,'0:0:0:0:0:0:0:1',0,'2019-06-06 06:20:15','2019-06-06 06:20:15',63),(51,'0:0:0:0:0:0:0:1',0,'2019-06-06 06:44:17','2019-06-06 06:44:17',5),(52,'0:0:0:0:0:0:0:1',0,'2019-06-06 07:03:22','2019-06-06 07:03:22',4),(53,'0:0:0:0:0:0:0:1',0,'2019-06-06 11:20:45','2019-06-06 11:20:46',537),(54,'0:0:0:0:0:0:0:1',0,'2019-06-06 11:22:27','2019-06-06 11:22:27',73),(55,'0:0:0:0:0:0:0:1',0,'2019-06-06 11:24:23','2019-06-06 11:24:23',61),(56,'0:0:0:0:0:0:0:1',0,'2019-06-06 11:27:05','2019-06-06 11:27:05',79),(57,'0:0:0:0:0:0:0:1',0,'2019-06-06 11:34:23','2019-06-06 11:34:23',178);
/*!40000 ALTER TABLE `response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bacon'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-06 11:36:24
