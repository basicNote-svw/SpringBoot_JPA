# 로그인 기능

- Spring security 라이브러리 쓰면 셋팅하는 코드만 복붙하면 로그인기능 구현 끝

  - 로그인용 html 페이지 만들고

  - 폼으로 로그인하겠다고 설정해놓고

  - DB에 있던 유저정보를 꺼내는 코드 작성

- session 방식의 로그인기능 작동

<br>

### 1. 로그인용 html 페이지
> loginController
```java
@GetMapping("/login")
public String login() {
    return "login.html";
}
```
- API 생성

<br>

> login.html
```html
<form action="/login" method="POST">
  <input name="username">
  <input name="password" type="password">
  <button type="submit">전송</button>
</form> 
```
- 방문시 보여줄 html 페이지 생성

- 아이디는 username, 패스워드는 password 이름으로 전송해야 잘됨

- 전송버튼 누르면 /login으로 POST 요청 날려야 잘됨

  - Spring Security쓸 때는 기본으로 이렇게 해줘야 잘되기 때문에 이렇게 설정

<br>

### 2. 폼으로 로그인하겠다고 설정
> SecurityConfig.java
```java
http.formLogin((formLogin) -> formLogin.loginPage("/login")
    .defaultSuccessUrl("/")
    .failureUrl("/fail")
);
```
- 설정하는 SecurityConfig 클래스에 코드 추가

  - 앞으로 폼으로 로그인하겠다는 뜻

- 성공이나 실패시 이동할 페이지 url도 적어줄 수 있음

- 성공시, 실패시 이동할 페이지도 설정가능

<br>

> login.html
```html
<div th:if="${param.error}">
  <h4>아이디나 비번 틀림</h4>
</div> 
```
- 실패시 이동할 페이지는 안적으면 기본적으로 /login?error 페이지로 이동

  - 쿼리스트링식으로 에러가 있었다는걸 전달해주는 것

- 실패시 안내메세지 보여주고 싶으면 login 페이지에 위와 같은 코드를 적어두면 됨

  - `만약에 쿼리스트링 중 error 이름을 가진 쿼리스트링이 있으면 특정 html 보여달라`는 뜻

<br>

#### Q. 로그인 실패시 아이디/비번 중 뭐가 틀렸는지 어떻게 알려줌?
- 아이디나 비번 중에 뭐가 틀렸는지 정확하게 알려주는건 보안상 좋지 않기 때문에 이 정도로 만족하기

<br>

### 3. DB에 있던 유저정보 꺼내는 코드를 작성

> MyUserDetailsService.java
```java
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DB에서 username을 가진 유저를 찾아와서
        return new User(유저아이디, 비번, 권한) 해주세요
    }
}
```
- MyUserDetailsService.java 파일 생성 후 코드 작성시 로그인 기능 알아서 동작

<br>

---

<br>

spring security 동작흐름
---

| spring security 동작흐름 |
|----------------------|
| ![이미지](./img/01.png) |


- 유저가 폼으로 username, password 라는 이름의 데이터들을 제출

- 사진에 있는 클래스들을 타고 이동해 UserDetailsService 클래스에 도착

  - 방금 만든 클래스

- 여기서 유저정보를 DB에서 꺼내주기만 하면

  - 컴퓨터가 알아서 유저가 제출한 비번이랑 DB에서 꺼낸 비번이랑 비교

    - DaoAuthenticationProvider 안에서 진행

- PasswordEncoder라는 Bean이 있으면 해싱도 자동으로 해줌

- 이상하면 에러 발생시켜줌

- 이상한게 없으면 입장권용 쿠키를 하나 생성해서 유저에게 보내주고

  - 누가 무슨 이름으로 언제 로그인했는지 세션데이터 생성해서 저장해줌

- 앞으로 유저가 제출한 입장권 검사해보는 것도 알아서 다 자동으로 됨

<br>

### MyUserDetailsService.java
- 유저가 폼으로 아이디/비번 제출시 비번 맞는지 자동으로 검사

  - 비번 검사하려면 DB에 있던 비번도 필요

    - 라이브러리는 비번이 DB어디에 있는지 모름

- DB에 있던 비번 찾아오는건 직접 코드로 작성 

  - 코드 작성하는 곳이 방금 만든 MyUserDetailsService 클래스

- loadUserByUsername() 함수만들고 거기서 아이디/비번/권한 담아서 return 

  - 비번검사 등이 알아서 됨

<br>

### 💡
- 라이브러리 문법 쓸 때

  - 외우고 이해하고 그럴 필요 X

  - 대충 흐름 이해만 한 다음에 복붙식으로 쓰는게 좋은 사용법

<br>

---

<br>

interface
---
- 어떤 클래스만들 때 특정 함수 이름들을 꼭 만들게 강제하고 싶을 때 interface 문법 사용

> 사용법
```java
interface 가이드 {
    public void doThis();
}

class Test implements 가이드 {
    public void doThis() {
        System.out.println("dd");
    }
}
```
- interface 하나 만들어두고 강제할 함수 만들기

  - 함수 이름과 타입만 집어넣을 수 있고 변수도 넣을 수 있음

- 클래스 하나 만들 때 `implements interface이름` 작성

  - interface에 있던 함수들을 만들도록 강제할 수 있음

  - interface에 있던 함수들을 똑같이 안만들면 에러

- interface : 쉽게 비유하자면 일종의 클래스 가이드라인

    - 특정 규격에 맞게 코드짜라고 강제하고 싶을 때 사용하면 유용함

<BR>

> ex
```java
interface 가이드 {
    public void doThis();
}

class A implements 가이드 {
    public void doThis(){ 어쩌구~ }
}
class B implements 가이드 {
    public void doThis(){ 저쩌구~ }
} 

가이드 a = new A(); //new A() 지우고 new B()로 교체가 쉬워서 나중에 관리가 편리해짐
a.doThis();
```
- 비슷한 클래스를 많이 만들어서 사용해야하는 경우

  - interface로 가이드부터 만들어두고 코드짜놓으면 나중에 클래스를 변경해야할 때 편리

- 변수의 타입을 interface로 해두면 같은 interface를 implement한 클래스끼리 자유롭게 변경 가능

- ex) ArrayList, LinkedList 대신 List라는 interface 타입을 집어넣는 것

<br>

---

<br>

loadUserByUsername 함수 완성하기
---
> MyUsetDetailsService.java
```java
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    DB에서 username을 가진 유저를 찾아와서
    return new User(유저아이디, 비번, 권한) 해주세요
}
```
- loadUserByUsername 함수에다가 파라미터 추가

  - 유저가 로그인시 제출한 username 들어옴

- DB에서 이거랑 일치하는 회원을 찾아와

  - 그 회원정보들을 new User()에 담아서 return 해주라고 코드 작성시 회원기능 구현 끝

<br>

---

<br>

테이블에서 특정 컬럼에 들어있는 값을 찾아오기
---

| 테이블              |
|----------------------|
| ![이미지](./img/02.png) |

- 테이블에서 행 하나 찾고 싶으면?

  - JPA문법으로 `.findById(1L)` 등 사용시 행 하나 찾아올 수 있음

    - id 컬럼이 1과 일치하는 행 찾아오는 문법

- 지금은 username 컬럼을 바탕으로 찾아와야함

  - `.findByUsername()` 있으면 좋을거같으나 없음

    - 여기다가 "kim" 입력하면 username 컬럼이 kim인 행을 찾아오게하면 좋을듯

- repository interface : 소원비는 곳

<br>

> MemberRepository.java
```java
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
```
- Repository interface 안에다가 함수 기재 

  - JPA가 이걸 보고 이 함수를 진짜로 만들어줌

- `findBy컬럼명(파라미터);` 이런 식으로 만들면

  - 그 컬럼명에 파라미터가 들어있는 행을 찾아오는 함수를 만들어줌

- 결과를 무슨 타입에 담아줄지도 맘대로 정하면 됨 

  - 행을 못찾을 수도 있으니 Optional

<br>

### 참고1 
- findBy 말고 findAllBy 라고 붙이면 파라미터가 들어있는 모든 행을 다 찾아와줌

  - 여러개 찾아오니까 List같은거에 담는게 좋음

<br>

### 참고2
- 이런걸 Derived Query Methods 라고 부름

- 여러가지 작업을 하는 DB 입출력 문법을 생성 가능
  
  - and, or 조건주기

  - 특정문자 포함되었는지 like연산자 검색하기

  - 특정 숫자 이상/이하인거 검색하기

  - 정렬하기

<br>

---

<br>




 
 
 

