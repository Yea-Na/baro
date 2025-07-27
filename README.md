### 이 프로젝트는 JWT 인증/인가를 기반으로 하는 사용자 관리 RESTful API 서버로, 로그인, 회원가입 기능을 제공합니다. 


**데이터베이스 설정**
   Docker Compose를 사용하여 PostgreSQL 컨테이너를 실행
   * **데이터베이스 접속 정보**:
    * 호스트: localhost
    * 포트: 5432
    * 사용자명: test
    * 비밀번호: 1234
    * 데이터베이스 이름: users

---

### 💡 API 문서 및 테스트 (API Documentation & Testing)

* **Swagger**: `http://localhost:8080/swagger-ui.html`
* **test 디렉토리 내부에 있는 Auth.http 파일로도 요청 등 테스트 가능**

---
    
---

###  주요 API 명세

* `POST /v1/api/auth/signup`: 회원가입
* `POST /v1/api/auth/signin`: 로그인 (JWT 토큰 발급)

---

 ### 기술 스택 (Tech Stack)

* **Backend**:
    * Java 17
    * Spring Boot 3.3.4
    * Spring Data JPA
    * Spring Security (JWT)
    * Lombok
    * Gradle
* **Database**:
    * PostgreSQL
* **API Documentation**:
    * SpringDoc OpenAPI (Swagger UI)
