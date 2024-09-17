-- MySQL dump 10.13  Distrib 5.7.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: carrot_hole
-- ------------------------------------------------------
-- Server version	5.7.44-log

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
  `id` varchar(255) NOT NULL COMMENT '主键',
  `dept_name` varchar(255) NOT NULL COMMENT '部门名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `dept_type` int(11) NOT NULL COMMENT '部门类型',
  `status` int(11) NOT NULL COMMENT '状态',
  `parent_id` varchar(255) NOT NULL COMMENT '父主键',
  `parent_ids` text COMMENT '上级部门主键集合',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_dept`
--

LOCK TABLES `au_dept` WRITE;
/*!40000 ALTER TABLE `au_dept` DISABLE KEYS */;
INSERT INTO `au_dept` VALUES ('1','carrot',1,1,1,'0','[\"0\"]','1','2024-09-15 13:44:34','1');
/*!40000 ALTER TABLE `au_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_menu`
--

DROP TABLE IF EXISTS `au_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_menu` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '父主键',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` varchar(255) DEFAULT NULL COMMENT '菜单类型',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单地址',
  `component_path` varchar(255) DEFAULT NULL COMMENT '组件地址',
  `permission_code` varchar(255) DEFAULT NULL COMMENT '权限编码',
  `project_id` varchar(255) DEFAULT NULL COMMENT '应用主键',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_menu`
--

LOCK TABLES `au_menu` WRITE;
/*!40000 ALTER TABLE `au_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_role`
--

DROP TABLE IF EXISTS `au_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_role` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `role_name` varchar(255) NOT NULL COMMENT '角色名',
  `role_type` varchar(255) NOT NULL COMMENT '角色类型',
  `project_id` varchar(255) NOT NULL COMMENT '应用主键',
  `dept_id` varchar(255) NOT NULL COMMENT '部门主键',
  `level` int(11) NOT NULL COMMENT '级别',
  `status` int(11) NOT NULL COMMENT '状态',
  `remark` text COMMENT '备注',
  `menu_range` int(11) NOT NULL COMMENT '菜单范围',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_role`
--

LOCK TABLES `au_role` WRITE;
/*!40000 ALTER TABLE `au_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_role_menu`
--

DROP TABLE IF EXISTS `au_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_role_menu` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `role_id` varchar(255) NOT NULL COMMENT '角色主键',
  `menu_id` varchar(255) NOT NULL COMMENT '菜单主键',
  `au_range` int(11) NOT NULL COMMENT '权限范围',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_role_menu`
--

LOCK TABLES `au_role_menu` WRITE;
/*!40000 ALTER TABLE `au_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_role_menu_range`
--

DROP TABLE IF EXISTS `au_role_menu_range`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_role_menu_range` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `role_mend_id` varchar(255) NOT NULL COMMENT '角色菜单主键',
  `dept_id` varchar(255) NOT NULL COMMENT '部门主键',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单范围关联表';
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
  `id` varchar(255) NOT NULL COMMENT '主键',
  `tenant_mark` varchar(255) NOT NULL COMMENT '租户标志',
  `tenant_name` varchar(255) NOT NULL COMMENT '租户名',
  `link_user` varchar(255) DEFAULT NULL COMMENT '联系人',
  `link_cellphone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='租户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_tenant`
--

LOCK TABLES `au_tenant` WRITE;
/*!40000 ALTER TABLE `au_tenant` DISABLE KEYS */;
INSERT INTO `au_tenant` VALUES ('1','carrot','carrot','carrot',NULL,NULL,'2024-09-15 13:44:05');
/*!40000 ALTER TABLE `au_tenant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_user`
--

DROP TABLE IF EXISTS `au_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_user` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `created_type` int(11) NOT NULL COMMENT '创建方式',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(11) NOT NULL COMMENT '状态',
  `dept_id` varchar(255) NOT NULL COMMENT '部门主键',
  `real_user` int(11) NOT NULL COMMENT '是否为真实用户',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_user`
--

LOCK TABLES `au_user` WRITE;
/*!40000 ALTER TABLE `au_user` DISABLE KEYS */;
INSERT INTO `au_user` VALUES ('1',1,'superman','$2a$10$Ux3vdv7KglWjBAbe7PYHZe237ST7cHujbe/xkBBmTg6chf6tdHQsC','超级管理员',1,1,'1',1,'1','2024-09-15 13:45:54','1');
/*!40000 ALTER TABLE `au_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_user_role`
--

DROP TABLE IF EXISTS `au_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `au_user_role` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `role_id` varchar(255) NOT NULL COMMENT '角色主键',
  `user_id` varchar(255) NOT NULL COMMENT '用户主键',
  `project_id` varchar(255) NOT NULL COMMENT '项目主键',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_user_role`
--

LOCK TABLES `au_user_role` WRITE;
/*!40000 ALTER TABLE `au_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `au_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_version`
--

DROP TABLE IF EXISTS `sys_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_version` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `version` varchar(255) NOT NULL COMMENT '版本'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统版本表';
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

-- Dump completed on 2024-09-17 20:03:30
