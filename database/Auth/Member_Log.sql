-- 폐쇠

# -- CREATE
# CREATE TABLE Member_Log (
#     id           BIGINT UNSIGNED AUTO_INCREMENT COMMENT '고유번호',
#     member_id    BIGINT UNSIGNED NOT NULL COMMENT '회원 고유번호',
#     actor_id     BIGINT UNSIGNED NOT NULL COMMENT '행위자 고유번호',
#     action       ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL COMMENT '수행된 동작',
#     create_at    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
#     before_data  JSON COMMENT '변경 전 데이터',
#     after_data   JSON COMMENT '변경 후 데이터',
#     reason       TEXT COMMENT '변경 사유 또는 비고',
#     PRIMARY KEY (id, create_at),
#     INDEX (member_id, create_at) USING BTREE
# ) COMMENT = '회원 기록'
# PARTITION BY RANGE COLUMNS (create_at) (
#     PARTITION p202506 VALUES LESS THAN ('2025-07-01'),
#     PARTITION p202507 VALUES LESS THAN ('2025-08-01'),
#     PARTITION p202508 VALUES LESS THAN ('2025-09-01'),
#     PARTITION p202509 VALUES LESS THAN ('2025-10-01'),
#     PARTITION p202510 VALUES LESS THAN ('2025-11-01'),
#     PARTITION p202511 VALUES LESS THAN ('2025-12-01'),
#     PARTITION p202512 VALUES LESS THAN ('2026-01-01'),
#     PARTITION pmax    VALUES LESS THAN (MAXVALUE)
# );
#
# -- DROP
# DROP TABLE IF EXISTS Member_Log;