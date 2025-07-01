SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET character_set_connection = utf8mb4;
SET character_set_client = utf8mb4;
SET character_set_results = utf8mb4;

DROP DATABASE IF EXISTS member;

CREATE DATABASE member;
USE member;

DROP TABLE IF EXISTS member;

-- CREATE
CREATE TABLE member (
  id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '고유번호',
  username     VARCHAR(20)     NOT NULL UNIQUE COMMENT '이름',
  email        VARCHAR(50)     NOT NULL COMMENT '이메일',
  display_name VARCHAR(12)     NOT NULL DEFAULT '유저' COMMENT '닉네임',
  password     VARCHAR(100)    NOT NULL COMMENT '비밀번호',
  created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
  updated_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
  deleted_at   DATETIME        COMMENT '삭제 시간',
  PRIMARY KEY (id)
) COMMENT = '회원';