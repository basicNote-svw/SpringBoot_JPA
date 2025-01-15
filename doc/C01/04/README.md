# 웹서버와 웹페이지 만들기 (Controller)

서버
---

|서버|
|-|
|![이미지](./img/01.png)|

- 유저가 데이터 요청하면 그 데이터 보내주는 프로그램

    - 이거 해줘 그러면 진짜 그거 해주는 프로그램

- 유튜브 서버

  - 누가 동영상달라고 요청하면 동영상 보내주는 프로그램

- 네이버 웹툰 서버

  - 누가 웹툰달라고 요청하면 웹툰보내주는 프로그램

- 웹서버

  - 그냥 누가 웹페이지 달라고 하면 웹페이지 보내주는 서버

- 서버개발

    - 누가 메인페이지달라고 하면 메인페이지 보내주고

    - 로그인페이지 달라고 하면 로그인페이지 보내주고

<br>

---

<br>

서버기능 만들려면 Controller
---
- .java 파일 생성

> BasicController.java
```java
package com.apple.shop;

@Controller
public class BasicController {

}
```
- 원래 .java 파일안엔 보통 파일명과 똑같은 클래스부터 넣고 시작

    - 클래스는 에디터에서 알아서 생성해줌

- 서버기능 만들기

    - 아무 클래스에 @Controller 써놓고 시작

<br>

> BasicController.java
```java
@Controller
public class BasicController {
    @GetMapping("/경로")
    @ResponseBody
    String hello(){
        return "유저에게 보내줄데이터";
    }
}
```
- @GetMapping() 안에 페이지 경로 기재

- @ResponseBody 쓰고

    - return 오른쪽에 유저에게 보내줄 데이터 작성

- /경로로 방문했을 때 그 데이터를 보내줌

- @ 붙은걸 작성할 땐 엔터키 잘 쳐서 상단에서 import 해와야 동작

  - Spring boot 프레임워크 사용법

    - @ 잘 넣으면 알아서 서버기능이 되도록 만들어놓은 프레임워크

    - @ 잘 붙이면 main 함수에 안넣어도 알아서 제때 잘 실행

<br>

> BasicController.java
```java
@Controller
public class BasicController {
    @GetMapping("/")
    @ResponseBody
    String hello(){
        return "Hello";
    }
}
```
- 누가 / 메인페이지로 접속했을 때 "Hello"라고 유저에게 메세지를 보내는 기능

    - 프로젝트 실행 후 브라우저켜서 `localhost:8080` 접속

<br>

---

<br>

다른 페이지 생성
---
- URL을 다르게 입력하면 다른 페이지를 각각 보여줌

    - ex) 누가 /about으로 접속하면 사이트 소개글 보여주기

<br>

> BasicController.java
```java
@Controller
public class BasicController {
    @GetMapping("/")
    @ResponseBody
    String hello() {
        return "Hello";
    }

    @GetMapping("/about")
    @ResponseBody
    String hello2() {
        return "피싱사이트에요";
    }
}
```
- 새로운 서버 기능이 하나 필요할 때 마다 @GetMapping() 덩어리 복붙해서 사용

<br>

- 누가 /mypage 접속하면 "마이페이지입니다" 메시지 보내주고 싶다면

<br>

> BasicController.java
```java
@Controller
public class BasicController {
    @GetMapping("/")
    @ResponseBody
    String hello() {
        return "Hello";
    }

    @GetMapping("/about")
    @ResponseBody
    String hello2() {
        return "피싱사이트에요";
    }

    @GetMapping("/mypage")
    @ResponseBody
    String hello3() {
        return "마이페이지입니다";
    }
}
```

<br>

---

<br>

html 보내기
---
> BasicController.java
```java
@Controller
public class BasicController {

    @GetMapping("/")
    @ResponseBody
    String hello(){
      return "<h4>안녕!</h4>";
    }

}
```
- 간단한 문자 말고 html을 보내고 싶으면 return 오른쪽에 html 기재

    - 보통 보내고 싶은 html이 매우 긴 경우엔 별도의 파일 만들어서 html 집어넣어두고 그 파일을 전송

<br>

> resources/static/index.html
```html
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h4>메인페이지</h4>
</body>
</html>
```
- html css js 파일들은 resources 폴더안의 static 폴더에 보관

- index.html 파일 생성

  - ! 느낌표 입력하고 탭키 누르면 기본 양식이 자동완성

- .html 파일 : html 담는 파일

- html : 웹페이지 만들 때 쓰는 언어

> BasicController.java
```java
@Controller
public class BasicController {

    @GetMapping("/")
    String hello(){
      return "index.html";
    }
}
```
- 유저에게 html 파일을 보내주고 싶으면 

  - @ResponseBody 빼고 return 오른쪽에 "html파일경로" 작성

- @ResponseBody

  - 쓰면 return 오른쪽에 있는걸 유저에게 보내라는 뜻

  - 안쓰면 return 오른쪽에 있는 경로의 파일을 전송하라는 뜻

    - 파일경로는 static 폴더가 기본

<br>

---

<br>

결론
---
- 웹페이지 보내주는 기능 만들기

    - @Controller 클래스 안에 함수 생성 후 코드 작성

 <br>

---

<br>

응용
---
- 누가 /date 로 접속하면 현재 날짜와 시간을 보내주는 기능

    - http://localhost:8080/date로 접속시 날짜와 시간이 보이면 성공

    - HTML 말고 날짜 데이터만 보내줘도 OK

<br>

> BasicController.java
```java
@GetMapping("/date")
@ResponseBody
String date() {
    return ZonedDateTime.now().toString();
}
```
- `LocalDateTime.now().toString()` 또는 `ZonedDateTime.now().toString()`

  - 현재 시간을 문자형태로 출력 가능

<br>