-- CREATE
CREATE TABLE role (
  id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '고유번호',
  name        VARCHAR(30)     NOT NULL                COMMENT '이름',
  description TEXT            COMMENT '설명',
  created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
  updated_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
  deleted_at   DATETIME        COMMENT '삭제 시간',
  PRIMARY KEY (id)
) COMMENT = '권한';

-- DROP
DROP TABLE IF EXISTS role;