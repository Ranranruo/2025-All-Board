# 1. Node 베이스 이미지
FROM node:20-alpine

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 의존성 설치
COPY package.json pnpm-lock.yaml ./
RUN npm install -g pnpm && pnpm install

# 4. 앱 코드 복사
COPY . .

# 5. 포트 개방
EXPOSE 3000

# 6. 개발 서버 실행 (기본: Webpack)
CMD ["pnpm", "next", "dev"]
