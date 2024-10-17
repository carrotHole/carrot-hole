-- MySQL dump 10.13  Distrib 5.7.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: carrot_hole
-- ------------------------------------------------------
-- Server version	5.7.32-log

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
-- Table structure for table `au_dept`
--

DROP TABLE IF EXISTS `au_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_dept` (
  `id` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `dept_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `dept_type` int(11) NOT NULL COMMENT '部门类型',
  `status` int(11) NOT NULL COMMENT '状态',
  `parent_id` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '父主键',
  `parent_ids` text COLLATE utf8mb4_bin COMMENT '上级部门主键集合',
  `created_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_dept`
--

LOCK TABLES `au_dept` WRITE;
/*!40000 ALTER TABLE `au_dept` DISABLE KEYS */;
INSERT INTO `au_dept` VALUES ('1','carrot',1,1,1,'0','[\"0\"]','1','2024-09-15 13:44:34','1'),('f4edd33a772f48e592fcfa542fffd642','4软',1,1,1,'0','[\"0\"]','superman','2024-10-17 07:47:42','1');
/*!40000 ALTER TABLE `au_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_dept_user`
--

DROP TABLE IF EXISTS `au_dept_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_dept_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `tenant_id` varchar(32) DEFAULT NULL COMMENT '租户主键',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `dept_id` varchar(255) DEFAULT NULL COMMENT '部门主键',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门用户关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_dept_user`
--

LOCK TABLES `au_dept_user` WRITE;
/*!40000 ALTER TABLE `au_dept_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_dept_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_menu`
--

DROP TABLE IF EXISTS `au_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_menu` (
  `id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `parent_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '父主键',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '菜单名称',
  `menu_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '菜单类型',
  `menu_url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '菜单地址',
  `component_path` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '组件地址',
  `permission_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限编码',
  `project_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '应用主键',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '图标',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `created_by` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_menu`
--

LOCK TABLES `au_menu` WRITE;
/*!40000 ALTER TABLE `au_menu` DISABLE KEYS */;
INSERT INTO `au_menu` VALUES ('94c09f07b4fa4f8480f39de755e7e3c7','c56e3d824b264a96b25d63ad6bca654c','用户管理','1','/user','/user/UserIndex','au:user:page','0f6e5861fb4e403fb69fbbc48c480807',0,2,'User',NULL,'superman','2024-09-29 07:14:49','1'),('c56e3d824b264a96b25d63ad6bca654c','0','基础管理','0',NULL,NULL,'','0f6e5861fb4e403fb69fbbc48c480807',0,1,'MoreFilled',NULL,'superman','2024-09-29 07:51:00','1'),('f3f7c209e4fe4e9fb6f31093e2be851b','c56e3d824b264a96b25d63ad6bca654c','租户管理','1','/tenant','/tenant/TenantIndex','au:tenant:list','0f6e5861fb4e403fb69fbbc48c480807',0,1,'Menu',NULL,'superman','2024-09-30 07:55:26','1'),('1f8c1cbb02a4434785977bc14b7f41c0','c56e3d824b264a96b25d63ad6bca654c','应用管理','1','/project','/project/ProjectIndex','au:project:','0f6e5861fb4e403fb69fbbc48c480807',0,5,'Project',NULL,'superman','2024-09-30 08:11:12','1'),('db508db8463c425a958b86462c617798','c56e3d824b264a96b25d63ad6bca654c','菜单管理','1','/menu','/menu/MenuIndex','au:menu:','0f6e5861fb4e403fb69fbbc48c480807',0,3,'Menu',NULL,'superman','2024-09-30 08:15:25','1'),('a0a5ac5d35ec4bb586d16078f7d08a63','c56e3d824b264a96b25d63ad6bca654c','角色管理','1','/role','/role/RoleIndex','au:role:','0f6e5861fb4e403fb69fbbc48c480807',0,5,'Role',NULL,'superman','2024-09-30 08:16:15','1');
/*!40000 ALTER TABLE `au_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_project`
--

DROP TABLE IF EXISTS `au_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_project` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `tenant_id` varchar(32) DEFAULT NULL COMMENT '租户主键',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `project_name` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `sort` varchar(255) DEFAULT NULL COMMENT '排序',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `au_project_sort` (`sort`),
  KEY `au_project_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_project`
--

LOCK TABLES `au_project` WRITE;
/*!40000 ALTER TABLE `au_project` DISABLE KEYS */;
INSERT INTO `au_project` VALUES ('0f6e5861fb4e403fb69fbbc48c480807','1','superman','2024-09-27 15:55:05','超级应用','1','0'),('2594f534e525448db4d90e13c7c79028','1','superman','2024-10-08 16:55:11','应用2','2','0');
/*!40000 ALTER TABLE `au_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_role`
--

DROP TABLE IF EXISTS `au_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_role` (
  `id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色名',
  `project_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '应用主键',
  `dept_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '部门主键',
  `status` int(11) NOT NULL COMMENT '状态',
  `remark` text CHARACTER SET utf8mb4 COMMENT '备注',
  `menu_range` int(11) NOT NULL COMMENT '菜单范围',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `created_by` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_role`
--

LOCK TABLES `au_role` WRITE;
/*!40000 ALTER TABLE `au_role` DISABLE KEYS */;
INSERT INTO `au_role` VALUES ('88190527569c484d9d4e60e5f16e0b21','4','4','1',0,'4',0,NULL,NULL,NULL,'1'),('3a3344dd9c834aa9b2374e5d2339c1d5','管理员','1','1',0,'1',0,NULL,NULL,NULL,'1');
/*!40000 ALTER TABLE `au_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_role_menu`
--

DROP TABLE IF EXISTS `au_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_role_menu` (
  `id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `role_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色主键',
  `menu_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '菜单主键',
  `au_range` int(11) NOT NULL COMMENT '权限范围',
  `created_by` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_role_menu`
--

LOCK TABLES `au_role_menu` WRITE;
/*!40000 ALTER TABLE `au_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_role_menu_permission`
--

DROP TABLE IF EXISTS `au_role_menu_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_role_menu_permission` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `tenant_id` varchar(32) DEFAULT NULL COMMENT '租户主键',
  `role_mend_id` varchar(255) DEFAULT NULL COMMENT '角色菜单主键',
  `dept_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单权限范围';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_role_menu_permission`
--

LOCK TABLES `au_role_menu_permission` WRITE;
/*!40000 ALTER TABLE `au_role_menu_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_role_menu_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_role_menu_range`
--

DROP TABLE IF EXISTS `au_role_menu_range`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_role_menu_range` (
  `id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `role_mend_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色菜单主键',
  `dept_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '部门主键',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色菜单范围关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_role_menu_range`
--

LOCK TABLES `au_role_menu_range` WRITE;
/*!40000 ALTER TABLE `au_role_menu_range` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_role_menu_range` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_tenant`
--

DROP TABLE IF EXISTS `au_tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_tenant` (
  `id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `tenant_mark` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '租户标志',
  `tenant_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '租户名',
  `link_user` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '联系人',
  `link_cellphone` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '联系电话',
  `created_by` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='租户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_tenant`
--

LOCK TABLES `au_tenant` WRITE;
/*!40000 ALTER TABLE `au_tenant` DISABLE KEYS */;
INSERT INTO `au_tenant` VALUES ('1','carrot','carrot','carrot',NULL,NULL,'2024-09-15 13:44:05',NULL);
/*!40000 ALTER TABLE `au_tenant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_user`
--

DROP TABLE IF EXISTS `au_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_user` (
  `id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `created_type` int(11) NOT NULL COMMENT '创建方式',
  `username` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(11) NOT NULL COMMENT '状态',
  `dept_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '部门主键',
  `real_user` int(11) NOT NULL COMMENT '是否为真实用户',
  `created_by` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_user`
--

LOCK TABLES `au_user` WRITE;
/*!40000 ALTER TABLE `au_user` DISABLE KEYS */;
INSERT INTO `au_user` VALUES ('1',1,'superman','$2a$10$Ux3vdv7KglWjBAbe7PYHZe237ST7cHujbe/xkBBmTg6chf6tdHQsC','超级管理员',1,1,'1',1,'1','2024-09-15 13:45:54','1'),('4',1,'3','$2a$10$Ux3vdv7KglWjBAbe7PYHZe237ST7cHujbe/xkBBmTg6chf6tdHQsC','超级管理员',1,1,'1',1,'1','2024-09-15 13:45:54','1'),('6',1,'5','$2a$10$Ux3vdv7KglWjBAbe7PYHZe237ST7cHujbe/xkBBmTg6chf6tdHQsC','超级管理员',1,0,'1',1,'1','2024-09-15 13:45:54','1'),('11',1,'32','$2a$10$Ux3vdv7KglWjBAbe7PYHZe237ST7cHujbe/xkBBmTg6chf6tdHQsC','超级管理员',1,1,'1',1,'1','2024-09-15 13:45:54','1'),('12',1,'33','$2a$10$Ux3vdv7KglWjBAbe7PYHZe237ST7cHujbe/xkBBmTg6chf6tdHQsC','超级管理员',1,1,'1',1,'1','2024-09-15 13:45:54','1'),('14',1,'25','$2a$10$Ux3vdv7KglWjBAbe7PYHZe237ST7cHujbe/xkBBmTg6chf6tdHQsC','超级管理员',1,1,'1',1,'1','2024-09-15 13:45:54','1'),('15',1,'26','$2a$10$Ux3vdv7KglWjBAbe7PYHZe237ST7cHujbe/xkBBmTg6chf6tdHQsC','超级管理员',1,1,'1',1,'1','2024-09-15 13:45:54','1'),('5dc1a437a8744024a24c632099984d67',1,'333','$2a$10$1XjzhVoKBoIG02yJe1VARua5dY/m2w21S10.0pTwNKhnQFwiFH9eK','333',0,0,'3333',1,'superman','2024-09-20 13:48:43','1'),('59c2931d966d437b81c180813643a667',1,'2221','$2a$10$K1tPiyFEtPwPr405D/onv.lRbFpvlE.ecnsbH9q2ytq5UaCe7mbwe','2222',3,1,'1',1,'superman','2024-09-20 15:43:49','1'),('986fb17afa284ce3b4f8863d04c655ea',1,'332','$2a$10$24lbW/bsMHA0FHIciADrguYwCOSgxq85t1YomhcklSLxwf11e49/q','3',0,1,'3',1,'superman','2024-09-20 15:47:09','1'),('3a7ab920bea0410dba12f9d40f610ec7',1,'33233','$2a$10$xohpm0rsCj6YfbKTz9tT7eJJzqPu87kYkaR4hiZxwgww8bUXfFZ7e','2',0,1,'1',1,'superman','2024-09-20 15:47:27','1');
/*!40000 ALTER TABLE `au_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_user_role`
--

DROP TABLE IF EXISTS `au_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_user_role` (
  `id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `role_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色主键',
  `user_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户主键',
  `project_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '项目主键',
  `created_by` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_user_role`
--

LOCK TABLES `au_user_role` WRITE;
/*!40000 ALTER TABLE `au_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict`
--

DROP TABLE IF EXISTS `dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `type` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '字典类型',
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '字典名',
  `remark` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户主键',
  `created_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `DictSys_tenant_id_index` (`tenant_id`),
  KEY `DictSys_type_index` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统字典类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict`
--

LOCK TABLES `dict` WRITE;
/*!40000 ALTER TABLE `dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_content`
--

DROP TABLE IF EXISTS `dict_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_content` (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `type` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '字典类型',
  `mark` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `remark` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '租户主键',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `dict_content_mark_index` (`mark`),
  KEY `dict_content_tenant_id_index` (`tenant_id`),
  KEY `dict_content_type_index` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统字典内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_content`
--

LOCK TABLES `dict_content` WRITE;
/*!40000 ALTER TABLE `dict_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `dict_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_content_sys`
--

DROP TABLE IF EXISTS `dict_content_sys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_content_sys` (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `type` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '字典类型',
  `mark` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `remark` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '租户主键',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `dict_content_sys_mark_index` (`mark`),
  KEY `dict_content_sys_tenant_id_index` (`tenant_id`),
  KEY `dict_content_sys_type_index` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统字典内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_content_sys`
--

LOCK TABLES `dict_content_sys` WRITE;
/*!40000 ALTER TABLE `dict_content_sys` DISABLE KEYS */;
INSERT INTO `dict_content_sys` VALUES ('04a23c58e62d4542ae3ca89a512265d8','MENU_TYPE','2','按钮',NULL,'1',3),('1','STATUS','0','禁用',NULL,'1',1),('1d5777524b9c4fbb85b4763d4d4664ac','MENU_TYPE','0','目录',NULL,'1',1),('2','STATUS','1','正常',NULL,'1',2),('3','SEX','0','女',NULL,'1',1),('4','SEX','1','男',NULL,'1',2),('5','SEX','2','未知',NULL,'1',3),('6','NATION','1','汉族',NULL,'1',1),('fe25ee4fee564284a1b05e84ca7d9a30','MENU_TYPE','1','菜单',NULL,'1',2);
/*!40000 ALTER TABLE `dict_content_sys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_sys`
--

DROP TABLE IF EXISTS `dict_sys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_sys` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `type` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '字典类型',
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '字典名',
  `remark` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户主键',
  `created_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `DictSys_tenant_id_index` (`tenant_id`),
  KEY `DictSys_type_index` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统字典类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_sys`
--

LOCK TABLES `dict_sys` WRITE;
/*!40000 ALTER TABLE `dict_sys` DISABLE KEYS */;
INSERT INTO `dict_sys` VALUES ('1','SEX','性别','性别','1',NULL,NULL),('2','NATION','民族','民族','1',NULL,NULL),('3','STATUS','状态','备注','1',NULL,NULL),('616cc572c520400e86ce46ad597bc972','MENU_TYPE','菜单类型',NULL,'1','superman','2024-09-29 07:39:12');
/*!40000 ALTER TABLE `dict_sys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_version`
--

DROP TABLE IF EXISTS `sys_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_version` (
  `id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `version` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '版本'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统版本表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_version`
--

LOCK TABLES `sys_version` WRITE;
/*!40000 ALTER TABLE `sys_version` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_version` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-17 16:13:53
