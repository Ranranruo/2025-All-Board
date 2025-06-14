-- CREATE
CREATE TABLE Member_Role_Bridge (
  id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '고유번호',
  member_id   BIGINT UNSIGNED NOT NULL COMMENT '회원 고유번호',
  role_id     BIGINT UNSIGNED NOT NULL COMMENT '권한 고유번호',
  create_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
  update_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
  flag        BOOLEAN         NOT NULL DEFAULT FALSE COMMENT '플래그',
  PRIMARY KEY (id),
  FOREIGN KEY (member_id) REFERENCES Member(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES Role(id) ON DELETE CASCADE
) COMMENT = '회원 권한 브릿지';

-- DROP
DROP TABLE IF EXISTS Member_Role_Bridge;