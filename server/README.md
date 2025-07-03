# 📦 All Board Server

Spring Boot 기반의 웹 애플리케이션입니다.

---

## 🔧 Skills

- **Jdk**: 24
- **Framework**: Spring Boot 3.5.0
- **Build Tool**: Gradle
- **Database**: MariaDB
- **ORM**: Spring Data JPA (Hibernate)
- **Docs**: RESTDocs
- **Socket**: WebSocketAPI
- **Test**: Junit
- **Others**: Lombok 

---

## 📁 Folder Structure
```

 main/
├── java/
│   └── sms/
│       └── allBoard/
│           ├── AllBoardApplication.java
│           ├── Auth/
│           │   ├── DTO/
│           │   ├── Exception/
│           │   ├── Service/
│           │   ├── AuthController.java
│           │   ├── AuthExceptionHandler.java
│           │   └── AuthValidator.java
│           └── Common/
│               ├── Config/
│               ├── Domain/
│               │   ├── Member/
│               │   ├── MemberRoleBridge/
│               │   └── Role/
│               ├── Enum/
│               ├── Interface/
│               ├── Security/
│               ├── Service/
│               │   └── Auth/
│               │       └── Verification/
│               └── Util/
└── resources/
       └── application.properties
```

---

## 🛠️ History

### 2025-06
- **2025-06-12**
  - `create project, db connection`

- **2025-06-13**
  - `Auth 기능에 필요한 Member, Role, MemberRoleBridge 기본적인 Domain 구현`
  - `예전 프로젝트 참고해서 기본적인 로그인 및 회원가입 기능 조금 구현`
  - `하야할것: 이메일 인증 기능, refresh token rotation, redis auto delete, flag filter 등등...`
  - `폴더구조가 엄청나게 복잡해졌다.`
  
- **2025-06-16**
  - `이메일 인증 기능 추가 완료`
  
- **2025-06-18**
  - `이메일 인증 기능 solid에 맞게 아키텍처 구상 및 설계`
  
- **2025-06-19**
  - `회원가입 로직 리펙토링 시작(미완성)`
  
- **2025-06-20**
  - `회원가입 로직 리펙토링 완료 (정상 동작)`
  - `구현체가 잘 변경되지 않을것 같고 특정 서비스에 종속되지 않은 Service들 Util로 이름 변경 및 Util 파일로 옮기기`
  - `Jwt 관련 로그인 인증 및 인가 공통 디렉토리 안에 Security로 정리`
  - `redis expire time 지정`
  - `access token 재발급 및 refresh token rotaion api 구현 완료`
  - `soft delete 구현`
  - `flag filtering 구현`
  - `soft delte 와 flag filtering은 아쉬운 부분이 있음 나중에 바꿔야 할것같다.`
  - `어느정도 필요한곳에 주석 추가`
  - `readme에 folder structure 수정`

- **2025-06-23**
  - `Domain폴더의 구조 변경 각 역할마다 폴더 추가 ex) Service, Repository, Model...`
  - `모든 Entity 폴더 네이밍 Model로 변경 `

- **2025-06-30**
  - `Config server init`
  - `Discovery server init`
  - `Gateway init`
  - `Member service init`
  - `msa 기본 환경 설정 완료`

- **2025-07-01**
  - `msa 나누고 공통 코드 jitPack으로 외부 라이브러리화`

- **2025-07-03**
  - `패키지명 lowercase로 변경 다른 기본 패키지는 다 lower case이라 통일성있는것 같음`

## ✨ Features

- ~~`JavaMailSender를 활용한 이메일 인증 sign-up 기능`~~
- ~~`jwt와 redis를 활용한 sign-in 기능`~~
- ~~`socket config`~~
- `msa로 전환`
- `socket으로 방 생성 및 참가 기능`