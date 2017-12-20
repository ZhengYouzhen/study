/*
Navicat MySQL Data Transfer

Source Server         : zyz
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : d_article

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-18 15:14:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `userId` bigint(20) DEFAULT NULL COMMENT '文章作者,用户id',
  `typeId` bigint(20) NOT NULL COMMENT '类别id',
  `first_img` varchar(255) DEFAULT NULL COMMENT '文章的第一张配图',
  `content` text NOT NULL COMMENT '文章内容',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改文章时间',
  `status` int(11) NOT NULL DEFAULT '0',
  `summary` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '第一个标题', '3', '1', 'static/images/face.jpg', '这是第一篇文章，内容非常有限！', '2017-12-13 19:40:44', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('2', '第二个标题', '3', '1', 'static/images/face.jpg', '这是第二篇测试文章，修改了userId的名字！', '2017-12-13 20:17:52', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('3', '第三篇文章', '3', '1', 'static/images/face.jpg', '<img src=\"static/uploads/01.jpg\"/><p>这是第三篇文章！</p>', '2017-12-14 16:41:34', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('4', '这是第四篇文章', '3', '2', null, '这是第四篇文章内容，希望成功！', '2017-12-14 17:08:51', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('5', '这是第五个标题', '3', '1', null, '<p>这是第五as积分垃圾</p><p>阿拉丁副科级啊ad看风景</p><p>阿隆索东风科技</p>', '2017-12-14 17:15:49', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('6', '第六篇文章', '3', '1', null, '<p>撒旦法</p><p>爱的色放</p>', '2017-12-14 17:18:32', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('7', '这是第七个标题', '3', '2', null, '<p><img src=\"/static/uploads/1.jpg\" alt=\"1.jpg\"></p><p>不想说，没有为什么！</p>', '2017-12-14 19:15:05', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('8', '第八篇文章', '3', '1', null, '<p><img src=\"/static/uploads/1.jpg\" alt=\"1.jpg\"></p><p>我是谁，&nbsp;我在哪里，我要干什么？</p>', '2017-12-15 15:09:28', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('9', '第九篇文章了', '3', '1', 'static/uploads/0.jpg', '<p><img src=\"/static/uploads/01.jpg\" alt=\"01.jpg\"></p><p>苍天啊，救救我啊！</p>', '2017-12-15 16:07:36', null, '0', '这是文章摘要，加加加...');
INSERT INTO `t_article` VALUES ('10', '第十篇文章', '3', '2', 'static/uploads/1.jpg', '', '2017-12-16 09:25:57', null, '0', '  啦啦啦阿拉附近这是第十篇文章，加上了文章摘要，加点样式吧');
INSERT INTO `t_article` VALUES ('11', '第十一个标题', '3', '2', '', '<p>上一篇给摘要赋值了，content却没有获取值，这让人很难受</p><pre><span>form</span>.on(<span>\'select(typeId)\'</span>, <span>function </span>(data) {<br>    data.value <span>//得到被选中的值<br></span>});</pre>', '2017-12-16 09:32:15', null, '0', '上一篇给摘要赋值了，content却没有获取值，这让人很难受。希望这次有值');

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '哪个用户评论了',
  `articleId` bigint(20) DEFAULT NULL COMMENT '评论的文章',
  `des` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `t_history`
-- ----------------------------
DROP TABLE IF EXISTS `t_history`;
CREATE TABLE `t_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  `articleId` bigint(20) NOT NULL COMMENT '文章id',
  `history_time` datetime NOT NULL COMMENT '浏览的历史时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_history
-- ----------------------------

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '公告标题',
  `des` text NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(200) NOT NULL,
  `des_zh` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'dongjie', '冻结与激活用户');
INSERT INTO `t_permission` VALUES ('2', 'fabu', '发布通知');
INSERT INTO `t_permission` VALUES ('3', 'shenhe', '审核文章');
INSERT INTO `t_permission` VALUES ('4', 'fabiao', '发表文章');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'root', '最高权限用户');
INSERT INTO `t_role` VALUES ('2', 'admin', '管理员');
INSERT INTO `t_role` VALUES ('3', 'general', '普通用户');

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1', '1');
INSERT INTO `t_role_permission` VALUES ('2', '1', '2');
INSERT INTO `t_role_permission` VALUES ('3', '1', '3');
INSERT INTO `t_role_permission` VALUES ('4', '1', '4');
INSERT INTO `t_role_permission` VALUES ('5', '2', '2');
INSERT INTO `t_role_permission` VALUES ('6', '2', '3');
INSERT INTO `t_role_permission` VALUES ('7', '2', '4');
INSERT INTO `t_role_permission` VALUES ('8', '3', '4');

-- ----------------------------
-- Table structure for `t_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL COMMENT '类别的名字',
  `des` varchar(255) DEFAULT NULL COMMENT '类别的简单描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', 'java', 'java相关的文章');
INSERT INTO `t_type` VALUES ('2', 'js', 'javascript相关的文章');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(100) NOT NULL,
  `head` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'root', '000000', '4QrcOUm6Wau+VuBX8g+IPg==', 'static/images/face.jpg');
INSERT INTO `t_user` VALUES ('2', 'admin', '123456', '4QrcOUm6Wau+VuBX8g+IPg==', 'static/images/face.jpg');
INSERT INTO `t_user` VALUES ('3', '用户1', '123456789', '4QrcOUm6Wau+VuBX8g+IPg==', 'static/images/face.jpg');
INSERT INTO `t_user` VALUES ('4', '用户2', '1234567890', '4QrcOUm6Wau+VuBX8g+IPg==', 'static/images/face.jpg');
INSERT INTO `t_user` VALUES ('5', '用户3', '987654321', '4QrcOUm6Wau+VuBX8g+IPg==', 'static/images/face.jpg');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2', '2');
INSERT INTO `t_user_role` VALUES ('3', '3', '3');
