/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.239.100-Oracle
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 127.0.0.1:1521
 Source Schema         : ORDINARY_FRAMEWORK_ADMIN

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 22/08/2022 17:12:16
*/


-- ----------------------------
-- Table structure for ADMIN
-- ----------------------------
DROP TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN";
CREATE TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" (
  "ADMIN_ID" NUMBER(18,0) NOT NULL,
  "USERNAME" VARCHAR2(255 BYTE) NOT NULL,
  "NICKNAME" VARCHAR2(255 BYTE) NOT NULL,
  "PASSWORD" VARCHAR2(255 BYTE) NOT NULL,
  "ROLE_ID" NUMBER(18,0),
  "STATUS" NUMBER(5,0) DEFAULT 1 NOT NULL,
  "TOKEN" VARCHAR2(255 BYTE),
  "CREATE_TIME" DATE NOT NULL,
  "UPDATE_TIME" DATE NOT NULL
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."ADMIN_ID" IS '管理员ID';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."USERNAME" IS '登录账号';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."NICKNAME" IS '昵称';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."PASSWORD" IS '密码';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."ROLE_ID" IS '角色ID，外联ROLE ROLE_ID';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."STATUS" IS '状态 (0.禁用 1.启用)';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."TOKEN" IS '令牌';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ADMIN"."UPDATE_TIME" IS '更新时间';
COMMENT ON TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" IS '管理员表';

-- ----------------------------
-- Records of ADMIN
-- ----------------------------
INSERT INTO "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" VALUES ('1', 'admin', 'admin', '14e1b600b1fd579f47433b88e8d85291', NULL, '1', '20189511-53ce-4f75-8675-22e58fd5f067', TO_DATE('2022-08-22 11:15:11', 'SYYYY-MM-DD HH24:MI:SS'), TO_DATE('2022-08-22 15:56:27', 'SYYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for OPERATE_LOG
-- ----------------------------
DROP TABLE "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG";
CREATE TABLE "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG" (
  "OPERATE_LOG_ID" NUMBER(18,0) NOT NULL,
  "ADMIN_ID" NUMBER(18,0),
  "MODULE" VARCHAR2(255 BYTE),
  "NAME" VARCHAR2(255 BYTE),
  "TYPE" VARCHAR2(255 BYTE),
  "REQUEST_METHOD" VARCHAR2(255 BYTE),
  "REQUEST_URL" VARCHAR2(255 BYTE),
  "USER_IP" VARCHAR2(255 BYTE),
  "USER_AGENT" VARCHAR2(255 BYTE),
  "JAVA_METHOD" VARCHAR2(255 BYTE),
  "JAVA_METHOD_ARGS" VARCHAR2(255 BYTE),
  "START_TIME" DATE,
  "DURATION" NUMBER(5,0),
  "RESULT_CODE" NUMBER(5,0),
  "RESULT_MSG" NCLOB,
  "RESULT_DATA" NCLOB
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."OPERATE_LOG_ID" IS '操作日志ID';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."ADMIN_ID" IS '管理员ID，外联ADMIN ADMIN_ID';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."MODULE" IS '操作模块';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."NAME" IS '操作名';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."TYPE" IS '操作分类';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."REQUEST_METHOD" IS '请求方法名';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."REQUEST_URL" IS '请求地址';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."USER_IP" IS '用户IP';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."USER_AGENT" IS '浏览器 UserAgent';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."JAVA_METHOD" IS 'Java 方法名';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."JAVA_METHOD_ARGS" IS 'Java 方法的参数';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."START_TIME" IS '开始时间';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."DURATION" IS '执行时长，单位：毫秒';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."RESULT_CODE" IS '结果码';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."RESULT_MSG" IS '结果提示';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG"."RESULT_DATA" IS '结果数据';
COMMENT ON TABLE "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG" IS '操作日志表';

-- ----------------------------
-- Records of OPERATE_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for PERMISSION
-- ----------------------------
DROP TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION";
CREATE TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" (
  "PERMISSION_ID" NUMBER(18,0) NOT NULL,
  "PARENT_ID" NUMBER(18,0) NOT NULL,
  "TYPE" NUMBER(5,0) NOT NULL,
  "NAME" VARCHAR2(255 BYTE) NOT NULL,
  "VALUE" VARCHAR2(500 BYTE) NOT NULL,
  "NUM" NUMBER(9,0) NOT NULL,
  "CREATE_TIME" DATE NOT NULL,
  "UPDATE_TIME" DATE NOT NULL
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION"."PERMISSION_ID" IS '权限ID';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION"."PARENT_ID" IS '父级权限ID (顶级权限的PARENT_ID=0)';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION"."TYPE" IS '类型 (1.菜单 2.接口)';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION"."NAME" IS '权限名称';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION"."VALUE" IS '权限标识 (菜单权限是前端路由，接口权限是uri)';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION"."NUM" IS '序号 (按降序排列)';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION"."UPDATE_TIME" IS '更新时间';
COMMENT ON TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" IS '权限表';

-- ----------------------------
-- Records of PERMISSION
-- ----------------------------

-- ----------------------------
-- Table structure for ROLE
-- ----------------------------
DROP TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE";
CREATE TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" (
  "ROLE_ID" NUMBER(18,0) NOT NULL,
  "NAME" VARCHAR2(255 BYTE) NOT NULL,
  "PERMISSION_IDS" NCLOB NOT NULL,
  "STATUS" NUMBER(5,0) DEFAULT 1 NOT NULL,
  "CREATE_TIME" DATE NOT NULL,
  "UPDATE_TIME" DATE NOT NULL
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ROLE"."ROLE_ID" IS '角色ID';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ROLE"."NAME" IS '角色名称';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ROLE"."PERMISSION_IDS" IS '权限ID集合，外联permission permission_id';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ROLE"."STATUS" IS '状态 (0.禁用 1.启用)';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ROLE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "ORDINARY_FRAMEWORK_ADMIN"."ROLE"."UPDATE_TIME" IS '更新时间';
COMMENT ON TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" IS '角色表';

-- ----------------------------
-- Records of ROLE
-- ----------------------------

-- ----------------------------
-- Sequence structure for SEQ_ADMIN
-- ----------------------------
DROP SEQUENCE "ORDINARY_FRAMEWORK_ADMIN"."SEQ_ADMIN";
CREATE SEQUENCE "ORDINARY_FRAMEWORK_ADMIN"."SEQ_ADMIN" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_OPERATE_LOG
-- ----------------------------
DROP SEQUENCE "ORDINARY_FRAMEWORK_ADMIN"."SEQ_OPERATE_LOG";
CREATE SEQUENCE "ORDINARY_FRAMEWORK_ADMIN"."SEQ_OPERATE_LOG" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_PERMISSION
-- ----------------------------
DROP SEQUENCE "ORDINARY_FRAMEWORK_ADMIN"."SEQ_PERMISSION";
CREATE SEQUENCE "ORDINARY_FRAMEWORK_ADMIN"."SEQ_PERMISSION" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_ROLE
-- ----------------------------
DROP SEQUENCE "ORDINARY_FRAMEWORK_ADMIN"."SEQ_ROLE";
CREATE SEQUENCE "ORDINARY_FRAMEWORK_ADMIN"."SEQ_ROLE" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Primary Key structure for table ADMIN
-- ----------------------------
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" ADD CONSTRAINT "SYS_C0012081" PRIMARY KEY ("ADMIN_ID");

-- ----------------------------
-- Checks structure for table ADMIN
-- ----------------------------
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" ADD CONSTRAINT "SYS_C0012074" CHECK ("ADMIN_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" ADD CONSTRAINT "SYS_C0012075" CHECK ("USERNAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" ADD CONSTRAINT "SYS_C0012076" CHECK ("NICKNAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" ADD CONSTRAINT "SYS_C0012077" CHECK ("PASSWORD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" ADD CONSTRAINT "SYS_C0012078" CHECK ("STATUS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" ADD CONSTRAINT "SYS_C0012085" CHECK ("CREATE_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ADMIN" ADD CONSTRAINT "SYS_C0012086" CHECK ("UPDATE_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table OPERATE_LOG
-- ----------------------------
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG" ADD CONSTRAINT "SYS_C0012073" PRIMARY KEY ("OPERATE_LOG_ID");

-- ----------------------------
-- Checks structure for table OPERATE_LOG
-- ----------------------------
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."OPERATE_LOG" ADD CONSTRAINT "SYS_C0012048" CHECK ("OPERATE_LOG_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table PERMISSION
-- ----------------------------
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012070" PRIMARY KEY ("PERMISSION_ID");

-- ----------------------------
-- Checks structure for table PERMISSION
-- ----------------------------
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012049" CHECK ("PERMISSION_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012050" CHECK ("PARENT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012051" CHECK ("TYPE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012052" CHECK ("NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012053" CHECK ("VALUE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012054" CHECK ("NUM" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012055" CHECK ("CREATE_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."PERMISSION" ADD CONSTRAINT "SYS_C0012056" CHECK ("UPDATE_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table ROLE
-- ----------------------------
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" ADD CONSTRAINT "SYS_C0012071" PRIMARY KEY ("ROLE_ID");

-- ----------------------------
-- Checks structure for table ROLE
-- ----------------------------
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" ADD CONSTRAINT "SYS_C0012057" CHECK ("ROLE_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" ADD CONSTRAINT "SYS_C0012058" CHECK ("NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" ADD CONSTRAINT "SYS_C0012059" CHECK ("PERMISSION_IDS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" ADD CONSTRAINT "SYS_C0012060" CHECK ("STATUS" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" ADD CONSTRAINT "SYS_C0012061" CHECK ("CREATE_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "ORDINARY_FRAMEWORK_ADMIN"."ROLE" ADD CONSTRAINT "SYS_C0012062" CHECK ("UPDATE_TIME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
