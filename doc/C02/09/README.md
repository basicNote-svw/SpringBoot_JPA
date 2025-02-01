# 가입 기능

회원가입기능
---
- 설명

  - 누가 /register 접속하면 회원가입페이지 보여주고

  - 전송누르면 서버로 보내서 서버는 그걸 DB에 저장


<br>

### 1. /register 접속하면 회원가입페이지 보여주고

> register.html
```html
<form action="/member" method="POST">
    <input name="displayName" placeholder="이름">
    <input name="username" placeholder="아이디">
    <input name="password" type="password">
    <button type="submit">전송</button>
</form> 
```
- 누가 /register 페이지 접속하면 이 파일 보내주기

- API 만들려면 Controller 클래스에 만들기

  - 기존의 글작성기능들이랑 다른 주제

    - 새로 Controller 클래스를 만들어서 코드 작성

  - 같은 클래스안에는 같은 주제의 함수들을 담는게 좋음

<br>

> member/MemberController.java
```java
@Controller
public class MemberController {
    @GetMapping("/register")
    public String register() {
        return "register.html";
    }
}
```
- Controller 클래스 하나 생성

  - 누가 /register 페이지 방문시 register.html을 보여달라고 API 작성

<br>

### 2. 전송누르면 서버로 전달 후 서버는 그걸 DB에 저장
- 폼전송하면 /member로 POST요청을 날리도록 코드 작성

  - 서버에도 그걸 처리하는 API 작성

<br>

> member/MemberController.java
```java
@PostMapping("/member")
public String addMember() {
    유저가보낸 아이디비번이름 디비에 저장~~
    return "redirect:/list";
}
```
- DB 어디에 저장 위치

  - 테이블을 새로 하나 만들어서 보관

<br>

> member/Member.java
```java
@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @Column(unique = true)
    public String username;
    public String displayName;
    public String password;
}
```
- 새로 클래스 만들어서 테이블 생성

- 회원은 아주 많아질 것 가정하여 id는 Long 타입

- username에 아이디 저장 

  - 아이디는 항상 유니크해야하기 때문에 unique 제약 부여

- 테이블이 잘 생성되어있는지 DBeaver 확인

<br>

#### 💡 Member 테이블에 데이터를 JPA로 입출력하는 3-step
- interface 생성
> member/MemberRepository.java
```java
public interface MemberRepository extends JpaRepository<Member, Long> {

}
```

<br>

- 등록하고 사용
> member/MemberController.java
```java
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    @PostMapping("/member")
    public String addMember(
    String username,
    String password,
    String displayName
    ) {
        Member member = new Member();
        member.setUsername(username);
        var hash = new BCryptPasswordEncoder().encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
        return "redirect:/list";
    }
}
```
- new BCryptPasswordEncoder() 사용해서 비번부분은 해싱해서 저장

  - 해싱했으니 나중에 유저가 로그인할 때도 유저가 제출한 비번을 해싱해서 DB에 있던거랑 비교

<br>

---

<br>

Bean 으로 만들어쓰기
---
- 위에서 만든 API

  - API를 실행할 때 마다 매번 new BCryptPasswordEncoder() 실행

  - 그 안에 있던 함수하나 쓰려고 매번 object 뽑는게 좀 비효율적

    - 스프링한테 object 하나 뽑아두라고 한 다음에 dependency injection 사용

- dependency injection식으로 구현

  - 클래스 하나 만들어서 @Service나 @Component 붙이고 등록하고 가져와 사용

- BCryptPasswordEncoder 클래스는 이미 누가 만들어놓은 클래스

  - 보통은 라이브러리에 작성되어있는 코드를 우리가 직접 수정하진 않음

  - 잘못건드렸다가 안되거나 나중에 업데이트 되는 경우 코드가 꼬일 수도 있음

<br>

> 이미 누가 만들어놓은 클래스를 dependency injection 식으로 사용하는 방법
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  (생략)
}
```
- new 클래스()를 뱉는 함수 하나 생성

  - 함수담긴 곳에 @Configuration, 그 함수에 @Bean 부여

- 스프링이 이걸 가져가서 Bean으로 만들어줌

<br>

### 💡 Bean
- 스프링이 뽑은 object

- Bean으로 등록해두면 아무데서나 dependency injection 식으로 가져다가 쓸 수 있음

  - 한 번 뽑아놓은 object를 계속 재사용하니까 매번 new 안해도 되어서 효율적

<br>

> Bean 사용
```java
@Controller
@RequiredArgsConstructor
public class MemberController {
  private final PasswordEncoder passwordEncoder;
  (생략)
}
```
- Bean 쓰려면 평소에 하던대로 등록해서 쓰면 됨

  - passwordEncoder 변수 안에는 new BCryptPasswordEncoder()가 들어있음

    - 마음대로 가져다쓰면 됨

<br>

### 💡 API 안에서 매번 new Member() 이거 하는 것도 비효율 아님? Bean으로 만들까요?
- API 실행될 때 마다 매번 새로운 정보가 들어있는 new Member()를 만들어서 써야함

  - new Member()를 한 번 뽑아놓고 계속 재사용되게 만들면 이상해짐

<br>

---

<br>

응용
---
### 1. 너무 짧은 아이디나 비번을 전송하는 경우 가입을 막으려면?
- Service 레이어로 옮겨서 코드 작성

> MemberService.java
```java
public void saveMember(String username,
String password,
String displayName) throws Exception {
  if (username.length() < 8 || password.length() < 8){
    throw new Exception("너무짧음");
  }
  Member member = new Member();
  member.setUsername(username);
  var hash = passwordEncoder.encode(password);
  member.setPassword(hash);
  member.setDisplayName(displayName);
  memberRepository.save(member);
}
```
- Service용 함수를 하나 만들어서 DB에 저장하라고 코드 작성

  - 그 전에 아이디, 비번 길이가 8자 미만이면 에러 반환 코드 작성

<br>

### 2. DB에 이미 같은 아이디가 존재하는 경우 가입을 막으려면?
- 이미 username 저장할 컬럼에 unique=true 라는 제약을 걸어놨으면 알아서 에러를 뱉어줘서 예외처리만 잘하면 됨

> MemberService.java
```java
public void saveMember(String username,
String password,
String displayName) throws Exception {
  var result = memberRepository.findByUsername(username);
  if (result.isPresent()){
      throw new Exception("존재하는아이디");
  }
  if (username.length() < 8 || password.length() < 8){
      throw new Exception("너무짧음");
  }
  (생략)
}
```
- Service용 함수에 username이 이미 있으면 에러뱉으라고 코드 작성

- 함수 사용시 특정 에러가 나는 경우 유저에게 메세지를 보내거나 다른 페이지로 이동시키기 가능

  - try catch 사용


