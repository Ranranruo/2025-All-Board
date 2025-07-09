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

### 2025-07

- **2025-07-01**
  - `msa 나누고 공통 코드 jitPack으로 외부 라이브러리화`

- **2025-07-03**
  - `패키지명 lowercase로 변경 다른 기본 패키지는 다 lower case이라 통일성있는것 같음`
  - `email, phone 인증관련 코드를 하나의 interface로 추상화 하기 위해 전략패턴 도입 시도 -> (실패) 전략패턴을 하기엔 폴더구조가 맞지 않는다. Context파일을 두는곳이 에매함 보통 Common에 파일을 Service레이어에서 사용하애하는데 반대가 되버림`
  - `identifier 삭제 -> 비지니스 로직이라고 할것도 없고 구조만 더 복잡해 지는것 같다.`
  - `이메일 인증 서버 개발 완료`
  - `Info 디렉토리 model -> vo 폴더명 변경 model은 포함되는 범위가 너무 넓고 Info가 인증에 대한 값을 나타내는 class이기 때문에 vo가 적절한것 같다.`
  - `member-service exists member 확인 기능 개발 완료`

- **2025-07-04**
  - `msa 서비스에 계층을 둬서 ui->service ms->data ms 로 나눌지 기본대로 할지 고민`
  - ```회원가입을 구현할때 보통 member-service, role-service를 auth-service에 묶어서 auth-service에서 비지니스 로직을 처리하고 member-service, role-service는 데이터 관련 명령만 진행하는데 게시판 서비스 같은거는 바로 post-service로 접근해서 post-service->comment-service 이런식으로 감 뭔가 통일성이 없는것 같다. 이럴꺼면 게시판 서비스를 포함한 모든 서비스도 auth-service처럼 비지니스 로직을 수행하는 서비스와 데이터를 관리하는 서비스로 나누어서 개발하는게 더 통일성있지 않을까?```
  - ```우아한테크의 마이크로서비스 여행기 영상을 보니까 이건 msa 구조의 문제가 아니라 도메인을 어떻게 나눌것인가에 대한 문제 인것 같다. 무작정 member-service, role-service 이렇게 나누는것이 아니라 회원이라는 도메인 관점에서 봐서 member,role,verification 이렇게 하나의 auth-service로 묶는게 맞는것 같다. 내가 너무 나눌 생각만 한것 같다.```
  - `member-service, role-service, verification-service 모두 '회원/인증'이라는 도메인 관점에서 봤을때 묶는게 좋을것 같아 auth-service로 통합`

- **2025-07-09**
  - `이메일 인증 코드 전송 로직 rabbitMq로 비동기화 전환`
  - `회원가입 로직 msa 전환 완료 (+RabbitMQ)`

## ✨ Features

- ~~`JavaMailSender를 활용한 이메일 인증 sign-up 기능`~~
- ~~`jwt와 redis를 활용한 sign-in 기능`~~
- ~~`socket config`~~
- `msa로 전환`
- `socket으로 방 생성 및 참가 기능`