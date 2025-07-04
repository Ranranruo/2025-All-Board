FROM mysql:8.4

# 호스트 Auth 폴더를 컨테이너 초기화 폴더로 복사
COPY ./Init.sql /docker-entrypoint-initdb.d/
COPY ./Auth/Init01.sql /docker-entrypoint-initdb.d/