# 수정기능

- 상품정보를 수정하는 기능

- 수정기능이 어떻게 동작하는지 한글로 자세히 설명부터 하고 그대로 코드로 번역

  - 한글로 설명을 못하겠으면 다른 사이트 벤치마크

  - 특정 부분을 코드로 번역 하는 법을 모르면 찾아보기

  - 글 4개 수정기능 한 번에 다 만들려고 하지말고 글 하나만 수정하는 기능 생성

<br>

---

<br>

수정기능을 글로 설명
---
- 이것도 일종의 게시판

  - 글마다 수정버튼이 있는데 누르면 글수정 페이지로 이동

  - 그 페이지엔 폼이 있고 글의 내용이 이미 채워져있음

  - 전송누르면 그걸로 DB의 기존 글 수정

<br>

### 1. 버튼누르면 수정페이지로 이동
- 버튼누르면 특정 페이지로 이동시키고 싶으면 \<a href="/url"> 

<br>

> list.html
```html
<div class="card" th:each="i : ${items}">
    <img src="https://placehold.co/300">
    (생략)
    <a th:href="/???">✏️</a>
</div>
```
- 글목록 페이지에 a태그 하나 만들고 누르면 수정페이지로 이동

- 수정페이지 URL은 안만들었으니 서버에서 하나 생성

<br>

> ItemController.java
```java
@GetMapping("/edit")
String edit() {
    return "edit.html";
}
```
- 수정페이지 보내줄 API 생성

<br>

> edit.html
```html
<form action="/???" method="POST">
  <input name="title">
  <input name="price">
  <button type="submit">전송</button>
</form> 
```
- 수정페이지 html 파일 생성

- html 내용은 글작성페이지랑 똑같을거같아서 그대로 복사

<br>

#### Q. 글이 4개라 수정페이지도 4개 필요
- /edit/1로 접속하면 1번글 내용이 채워진 수정페이지

- /edit/2로 접속하면 2번글 내용이 채워진 수정페이지

- API 비슷한거 여러개 만드려면 URL 파라미터 문법 사용

<br>

> ItemController.java
```java
@GetMapping("/edit/{id}")
String edit() {
    return "edit.html";
}
```
- 누가 /edit/1 로 접속하든 /edit/2로 접속하든 수정페이지를 잘 보여줌

<br>

### 2. 그 페이지엔 폼이 있고 글 내용이 이미 채워져있음

- /edit/1로 접속하면 1번 글의 내용이 채워져있어야함

    - 접속해보면 지금은 빈칸

      - DB에서 1번 글 가져와서 넣어주면 됨

<br>

> ItemController.java
```java
@GetMapping("/edit/{id}")
String edit(Model model) {
    Optional<Item> result = itemRepository.findById(1L);
    if (result.isPresent()) {
        model.addAttribute("data", result.get());
        return "edit.html";
    } else {
        return "redirect:/list";
    }
}
```
- id가 1인 글을 DB에서 가져와서 html에 넣으라고 작성

<br>

> edit.html
```html
<form action="/???" method="POST">
  <input name="title" th:value="${data.title}">
  <input name="price" th:value="${data.price}">
  <button type="submit">전송</button>
</form>
```
- 그걸 html에 적용

    - value속성 만지면 이미 채워진 값을 맘대로 설정할 수 있음

<br>

### 3. 전송누르면 그 내용으로 DB의 기존 글 수정
- 수정페이지 폼에서 전송누르면 서버로 보내고 서버는 그 내용으로 DB를 수정

<br>

#### 힌트1. DB내용 수정은 .save() 쓰면 됨

- .save() 안에 object 하나 넣으면 그 내용으로 행을 하나 추가해줌

    - 단, 예외 존재

        - 추가하려는 id가 이미 테이블에 존재하면 추가가 아니라 기존 내용을 덮어쓰기

<br>

#### 힌트2. 서버에서 어떤 정보가 필요한데 정보가 없다면

- 유저에게 보내라고하거나 DB에서 뽑아보거나 택1

<br>

> edit.html
```html
<form action="/edit" method="POST">
  <input name="title" th:value="${data.title}">
  <input name="price" th:value="${data.price}">
  <button type="submit">전송</button>
</form> 
```
- 전송누르면 /edit으로 POST 요청 날리라고 작성
 
<br>

> ItemController.java
```java
@PostMapping("/edit")
String editItem(String title, Integer price) {
  유저가 보낸 내용으로 DB수정 하기~~
  return "redirect:/list";
}
```
- 서버에 API 생성

<br>

#### Q. 글 수정같은거 할 때는 PUT요청이 좋다던데?

- \<form>태그 쓰면 PUT DELETE 요청 사용 불가

    - ajax 기능 쓰면 가능한데 그건 삭제기능 만들 때 사용

<br>

---

<br>


왜 항상 1번상품 내용만 채워진 페이지가 나옴?
---
- 지금은 /edit/1, /edit/2 아무렇게나 접속해도 항상 1번 상품의 내용만 나옴

    - 항상 1번상품만 꺼내서 보여주라고 코드 작성해둠

<br>

> ItemController.java
```java
@GetMapping("/edit/{id}")
String edit(@PathVariable Long id, Model model) {
    Optional<Item> result = itemRepository.findById(id);
    if (result.isPresent()) {
        model.addAttribute("data", result.get());
        return "edit.html";
    } else {
        return "redirect:/list";
    }
}
```
- 유저가 URL 파라미터자리에 입력한 숫자를 .findById()에 넣으라고 코드 작성

  - /edit/2로 접속하면 2번 상품을 가져와서 html에 보여줌


<br>

---

<br>

JPA 사용해서 수정하려면
---
- 수정하고 싶으면

  - object 뽑고

  - object에 변수들 값을 집어넣고

  - 그 다음에 save 안에 object 넣으면 됨

- 행 하나 추가하는 문법이지만 id값이 같은 행이 이미 있으면 그 행을 덮어쓰기해줌

<br>

> ItemController.java
```java
@PostMapping("/edit")
String editItem(String title, Integer price) {
    Item item = new Item();
    item.setId(1L);
    item.setTitle(title);
    item.setPrice(price);
    itemRepository.save(item);
    return "redirect:/list";
}
```
- 유저가 폼으로 보낸 title, price, 1L을 object에 넣고 .save()

    - 1번 글 수정

<br>

---

<br>

다른 글도 수정하고 싶다면
---
- 지금은 1번글만 수정해주는데 2번글, 3번글 수정기능은?

- 비슷한 API 많이 만들기 싫으면 URL 파라미터 쓰거나

    - .setId(1L) 로 하드코딩 해놓는게 아니라 .setId(유저가 수정하고 싶은 글번호) 입력

- 유저가 수정하고 싶은 글번호(서버가 알 수 없는 정보) 알려면?

    - (1) 유저에게 보내라고 하거나

    - (2) DB에서 뽑아보거나

<br>

> edit.html
```html
<form action="/edit" method="POST">
  <input name="id" th:value="${data.id}">
  <input name="title" th:value="${data.title}">
  <input name="price" th:value="${data.price}">
  <button type="submit">전송</button>
</form> 
```
- 유저가 수정요청 날릴 때 수정원하는 글의 번호도 여기 집어넣어서 함께 보내라고 작성

- data.id 하면 글번호 나옴

- \<input> 안보이게 하고 싶으면 `style="display:none"` 추가

<br>

> ItemController.java
```java
@PostMapping("/edit")
String editItem(String title, Integer price, Long id) {
    Item item = new Item();
    item.setId(id);
    item.setTitle(title);
    item.setPrice(price);
    itemRepository.save(item);
    return "redirect:/list";
}
```
- 유저가 폼으로 보낸 title, price, id를 object에 넣고 .save() 

- 이제 어떤 수정페이지로 방문하든간에 id도 잘 전송됨

  - 서버로 id 전달이 잘 되서 수정도 잘 됨

<br>

---

<br>

결론
```
1. 테이블에 이미 존재하는 id를 기입해서 .save() 하면 덮어쓰기(수정) 됨

2. 코드짤 땐 한 번에 모든걸 구현하려고 하지 말고 쉬운거 하나부터 개발하고 확장하면 쉬움

    이런걸 초보때 알아둬야 나중에 알아서 잘함

3. 서버에서 모르는 정보는 유저에게 보내라고 하거나 DB에서 꺼내보거나 둘 중 하나 선택
```
 
<br>

---

<br>

응용
---
### 1. 상품마다 수정페이지로 이동하는 링크 만들기
> list.html
```html
<div class="card" th:each="i : ${items}">
  (생략)
  <a th:href="@{ '/edit/' + ${i.id} }">✏️</a> 
```

<br>

### 2. Controller 기능과 별 상관없어보이는 코드는 Service 클래스로 분리

> ItemService.java
```java
public void editItem(String title, Integer price, Long id) {
    Item item = new Item();
    item.setId(id);
    item.setTitle(title);
    item.setPrice(price);
    itemRepository.save(item);
}
```

> ItemController.java
```java
    @PostMapping("/edit")
    String editItem(String title, Integer price, Long id) {
        itemService.editItem(title, price, id);
        return "redirect:/list";
    }
```
- 수정하는 코드는 Service로 옮겨서 함수로 만들기

- 앞으로 itemService.editItem(어쩌구, 어쩌구, 어쩌구) 쓰면 수정이 잘 됨

- 기존에 있던 saveItem() 함수와 비슷해서 그거 재활용해도 ok

    - but, 강박적인 코드 재활용은 코드가 더 복잡해질 수 있음

<br>

### 3. 수정시 유저가 제목을 100자 이상으로 길게 쓰거나 가격란에 음수를 보냈을 때 대처하는 코드 작성

> ItemService.java
```java
    public void editItem(String title, Integer price, Long id) {
        if(title.length()>=100 || price<=0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "값을 다시 확인하세요");
        } else {
            Item item = new Item();
            item.setId(id);
            item.setTitle(title);
            item.setPrice(price);
            itemRepository.save(item);
        }
    }
```
- `title 파라미터가 100자 이상이면 에러를 내달라`고 코드 작성

<br>

