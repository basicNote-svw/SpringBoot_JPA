# 상품목록 페이지 만들기 (Thymeleaf)
- 누가 /list로 접속하면 list.html 페이지 전송

    - 그 안엔 상품들 집어넣기

<br>

list.html 보내주는 기능
---
> ItemController.java
```java
package com.apple.shop.item;
import org.springframework.stereotype.Controller;

@Controller
public class ItemController {

    @GetMapping("/list")
    String list(){
        return "list.html";
    }

}
```
- API 생성

  - 상품과 관련된 API들을 한 곳에 모아놓으려고 새로 파일 생성

  - 아무 클래스에 @Controller 붙이면 API들 안에 만들 수 있음

  - 원래 파일 상단에 `package 파일경로;` 적어줘야 다른 파일에서도 여기 있던 코드를 사용가능

<br>

> list.html
```html
<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
</head>
<body>

    <div>
      <img>
      <h4>바지</h4>
      <p>7억</p>
    </div>
    <div>
      <img>
      <h4>셔츠</h4>
      <p>8억</p>
    </div>

</body>
</html> 
```
- list.html 파일 생성 후 상품들을 채워넣기

<br>

---

<br>

매번 다른 상품 보내주기
---
- 접속할 때 마다 항상 같은 내용의 html 페이지만 보내주는 문제 발생

- 쇼핑몰은 맨날 다른 상품명이랑 가격을 보내야함

    - 템플릿 엔진 사용

- 템플릿 엔진 : 서버의 데이터를 html에 집어넣어주는걸 도와주는 외부 라이브러리

    - Thymeleaf 템플릿 엔진 등

<br>

### Thymeleaf 설치

> build.gradle
```java
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
}
```
- build.gradle 파일에 차이점만 잘 붙여넣고

- load gradle changes 버튼누르면 외부 라이브러리 설치가 가능

- 라이브러리 설치했으면 서버 중지했다가 재시작해야 잘 적용됨

<br>

### Thymeleaf 작성
- html 파일을 templates 폴더 안에 만들어야 Thymeleaf 문법으로 html을 만들 수 있음

    - Thymeleaf 써도 기존 html 파일이랑 똑같이 작성, 사용 가능

    - html 곳곳에 Thymeleaf 문법으로 서버데이터를 집어넣을 수 있다는게 차이점

<br>

---

<br>

Thymeleaf로 서버데이터 html에 넣어 보내주기
---
- 서버에서 특정 html 파일에 서버의 데이터를 집어넣는 기능

  1. 서버 API 함수의 파라미터에 Model model 넣고

  2. API안에서 model.addAttribute("작명", 전송할데이터)

  3. html 태그에 th:text="${작명}"

<br>

> 코드
```java
@GetMapping("/list")
String list(Model model) {
    model.addAttribute("name", "홍길동");
    return "list";
}
```
1. 서버 API 함수의 파라미터에 Model model 넣고

2. model.addAttribute("작명", 전송할데이터)

    - list.html에서 name이라고 사용하면 `홍길동` 출력

<br>

> src/main/resources/templates/list.html
```html
<h4 th:text="${name}">바지</h4>
```
3. html 태그에 th:text="${작명}" 이라고 써야 서버에서 보낸 데이터 출력 가능

    - 위 html을 유저에게 전송할 땐 앞으로 <h4>태그에 "홍길동"이 박혀서 전송됨

    - Thymeleaf 문법을 사용하고 싶으면 templates 폴더로 html 파일을 옮겨야 잘 동작

<br>

 