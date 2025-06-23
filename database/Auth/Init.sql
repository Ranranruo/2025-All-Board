DROP TABLE IF EXISTS Member_Role_Bridge;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS Role;

-- CREATE
CREATE TABLE Member (
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

CREATE TABLE Role (
  id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '고유번호',
  name        VARCHAR(30)     NOT NULL                COMMENT '이름',
  description TEXT            COMMENT '설명',
  created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
  updated_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
  deleted_at   DATETIME        COMMENT '삭제 시간',
  PRIMARY KEY (id)
) COMMENT = '권한';

CREATE TABLE Member_Role_Bridge (
  id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '고유번호',
  member_id   BIGINT UNSIGNED NOT NULL COMMENT '회원 고유번호',
  role_id     BIGINT UNSIGNED NOT NULL COMMENT '권한 고유번호',
  created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
  updated_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
  deleted_at   DATETIME        COMMENT '삭제 시간',
  PRIMARY KEY (id),
  FOREIGN KEY (member_id) REFERENCES Member(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES Role(id) ON DELETE CASCADE
) COMMENT = '회원 권한 브릿지';

INSERT INTO Role (name, description) VALUES ('ADMIN', '관리자');
INSERT INTO Role (name, description) VALUES ('MEMBER', '회원');

