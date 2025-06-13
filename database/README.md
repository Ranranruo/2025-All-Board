# 📦 All Board Database

---

## 🔧 Skills

- **Database**: MariaDB

---

## 📁 Database Structure
```

```

---

## 🛠️ History

### 2025-06
- **2025-06-12**
  - `member, role, member_role_bridge, member_log 추가`

- **2025-06-13**
  - `member_log 테이블 처럼 1개의 테이블에 대항하는 로그 테이블이 있으면 테이블도 지저분하게 많아지고 관리하기 힘들것같아서 삭제 결정 DBMS 내장 로깅 기능을 이용하기로 결정 이거 결정하려고 spring batch 와 aop에 대해서도 알아봄`
  - `create_at, update_at 과거분사형으로 변경`
  - `flag 처럼 에매한 필드명보단 delete로 하는것이 좋겠다고 판단했다. falg -> is_deleted`
  - `어차피 is_deleted 컬럼은 0, 1 두가지 값으로 판단하기 때문에 null 때는 삭제 아닌걸로 날짜일때는 삭제 된걸로 하는게 삭제 시간도 저장할 수 있고 updated_at 보다 행동이 명확하고 어울린다고 판단했다. is_deleted -> deleted_at`
