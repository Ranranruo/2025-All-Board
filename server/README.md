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
src/
├── main/
│   ├── java/
│   │   └── sms/
│   │       └── allBoard/
│   │           ├── AllBoardApplication.java
│   │           ├── Auth/
│   │           │   ├── Details/
│   │           │   ├── DTO/
│   │           │   ├── Exception/
│   │           │   ├── Filter/
│   │           │   ├── Impl/
│   │           │   ├── JWT/
│   │           │   ├── AuthController.java
│   │           │   ├── AuthExceptionHandler.java
│   │           │   ├── AuthService.java
│   │           │   └── AuthValidator.java
│   │           └── Common/
│   │               ├── Config/
│   │               ├── Domain/
│   │               │   ├── Member/
│   │               │   ├── MemberRoleBridge/
│   │               │   └── Role/
│   │               ├── Enum/
│   │               ├── Interface/
│   │               └── Util/
│   └── resources/
│       └── application.properties
└── test/

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