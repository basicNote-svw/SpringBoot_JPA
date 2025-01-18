# public private protected static (access modifiers)
- 코드관리를 도와주는 문법들

<br>

public 키워드
---
- 클래스에 변수랑 함수 만들어둘 때 public 부착

    - 이 변수와 함수는 모든 곳에서 가져다쓸 수 있다는 뜻

- 아무 클래스에서나 .title을 출력하고 수정 가능

<br>

> Item 클래스
```java
@Entity
public class Item {
    public String title;
    public Integer price;
}
```

> 다른 클래스
```java
Item item = new Item();
item.title = "모자"; //사용가능
```

<br>

---

<br>

package private
---
- 클래스에 변수랑 함수 만들어둘 때 아무것도 안붙이면 package private

    - 같은 폴더 안에 있는 클래스들만 이 변수, 함수 사용 가능

<br>

> Item 클래스
```java
@Entity
public class Item {
    String title;
    Integer price;
}
```

> 같은 폴더의 클래스
```java
Item item = new Item();
item.title = "모자"; //사용가능
```

<br>

---

<br>

private
---
- 클래스에 변수랑 함수 만들어둘 때 private

  - 다른 클래스에서 사용 금지

  - 이 클래스 안에서만 가져다쓸 수 있음

- 클래스 바깥에서 함부로 이 변수를 건드리지 않게 하고 싶으면 사용

<br>

> Item 클래스
```java
@Entity
public class Item {
    private String title;
    private Integer price;
}
```

> 다른 클래스
```java
Item item = new Item();
item.title = "모자"; //불가능
```

<br>

---

<br>

protected
---
- protected 붙이면 같은 폴더의 클래스에선 자유롭게 가져다가 사용가능

    - 다른 폴더로 가면 사용 불가능

    - 아무것도 안붙이던거랑 동작방식이 똑같음

<br>

> Item 클래스
```java
@Entity
public class Item {
    protected String title;
    protected Integer price;
}
```

> 같은 폴더의 클래스
```java
Item item = new Item();
item.title = "모자"; //사용가능
```

<br>

### 💡 참고
- **이 클래스를 상속한 클래스가 있다면** 어디있어도 사용 가능

- 원래 클래스를 복사해서 비슷하게 생긴 클래스를 하나 더 만들 수 있음

    - extends를 쓰면 복사 가능 (상속)

- 상속은 객체지향 개념이 처음 나왔을 땐 좋다고 많이 사용했었는데 요즘은 기피하는 경향 많음

    - protected 쓸 이유가 딱히 없어짐

<br>

---

<br>

결론
---
- 클래스에 변수랑 함수 만들 때 왼쪽을 비워두는 것 보다는

    - 변수를 다른데서 못쓰게 만들고 싶으면 private

    - 다른데서 맘대로 쓰게 만들고 싶으면 public

<br>

---

<br>

static
---
- new 키워드로 object 안뽑고 클래스명에다가 점찍어서 바로 꺼내서 쓸 수 있는 변수, 함수 생성 가능

- 클래스에서 변수함수를 복사해서 쓰는 것의 장점을 버리겠다는 뜻

    - 일반적인 상황에선 쓰지 않고 많은 곳에서 부담없이 쓸 수 있는 유틸리티용 함수 만들 때 가끔 사용

<br>

> Item 클래스
```java
@Entity
public class Item {
    public static String title;
    public static Integer price;
}
```

> 다른 클래스
```java
Item.title //이렇게 사용가능
```

<br>

---

<br>

private 붙은 변수 수정하기
---
- 사람들이 private 쓰면 안전하다고 좋아함

- 근데 private 붙여놓으면 출력도 불가

- private 붙여놓은 변수들을 나중에 변경하고 출력하려면?

    - 함수 미리 생성 후 가져다가 사용

      - private 변수 출력해주는 함수

      - private 변수 수정해주는 함수

<br>

### getter, setter
- private 변수의 출력과 수정을 대신 해주는 함수들x

<br>

> 변수 출력
```java
@Entity
public class Item {
    private String title;
    private Integer price;
    
    public String getTitle(){
        return title;
    }
}
```
- ex) private 붙은 title 변수를 나중에 출력하려면

    - 출력해주는 함수 생성

        - title 변수를 그 자리에 반환하라고 작성

<br>

> 변수 수정
```java
@Entity
public class Item {
    private String title;
    private Integer price;
    
    public void setTitle(String title) {
        this.title = title;
    }
}
```
- ex) private 붙은 title 변수를 나중에 수정하려면

    - 수정해주는 함수 생성

        - 앞으로 수정하고 싶으면 `.setTitle("수정할내용")`

<br>

> Lombok 문법
```java
@Entity
@Getter
@Setter
public class Item {
    private String title;
    private Integer price;
}
```
- @Getter @Setter를 변수나 클래스 위에 붙이면 getter, setter 함수 자동 생성

    - Lombok 덕분

<br>

---

<br>

사용 이유
---
- public 대신 private 붙여놓고 getter setter 만들어서 가져다쓰는 이유

    - .title을 자유롭게 가져다쓰는거보다는 private 붙여놓고 getter setter 만들어쓰는게 안전

<br>

> ex1
```java
Item item = new Item();
item.title = "매우긴문자~~~~~~~~~";
```
- 나중에 .title 변수에 뭔가를 저장할 때 실수를 하면?

    - ex) 255자 까지만 저장 가능한데 1000자가 넘는 문자를 넣어버림

<br>

> 해결
```java
@Entity
public class Item {
    private String title;
    private Integer price;
    
    public void setTitle(String title) {
        만약에 title변수에 이상한 긴 문자 넣으려고 하면 차단하기~~;
        this.title = title;
    }
}
```
- 방지하고 싶으면 private 붙여놓고 setter함수만 쓸 수 있게 만들어둠

    - setter 함수 안에 if 문으로 이상한 데이터를 거르는 로직 작성

<br>

> 예시2

- price 변수의 타입을 Integer에서 Long으로 바꾸고 싶은 경우

    - 그냥 바꿔버리면 price 변수 쓰는 곳을 전부 다 수정해야함

    - setter 함수를 만들어놓은 경우에는 수정할 필요 없이 그냥 setter 함수 안에서 타입만 변경하면 ok

<br>

- setter는 변수의 API를 만드는 것과 비슷

- API를 안전하게 잘 만들어두면 변수에 이상한 데이터 넣어려해도 API에서 잘 걸러줄 수 있음

    - 보다 안전하게 + 안정적으로 사용 가능

- 검사로직이 추가되면 안전하게 쓸 수 있는 것이라 setter만 사용시 번거로울 수 있음

<br>

---

<br>

정리
---
- 클래스 안에 변수, 함수 만들 때 private, public, static 중 골라 사용

```
1. 다른 클래스에서 쓸 수 있게 만들거면 public

2. 다른 클래스에서 못쓰게 만들거면 private

3. 유틸리티 함수같은거 만들 때는 static 
```


- private 넣어두고 getter, setter 만들어서 쓰면 장점이 있기 때문에 그렇게 쓰는 사람이 많음

<br>

---

<br>

응용
---
### 1. 이름과 나이를 저장할 수 있는 클래스 생성
- object를 하나 뽑아 이름과 나이를 저장

    - 클래스 안의 변수들을 private 변수로 만들어서 사용

<br>

> 답
```java
@Getter
@Setter
public class Test {
  private String name;
  private Integer age;
  
  public static void main(String[] args) {
      var a = new Test();
      a.setName("이용복");
      a.setAge(20);
  }
}
```

<br>

### 2. 나이를 +1 하는 함수와 나이를 바꿔주는 함수를 각각 만들어서 사용

> 동작예시
```java
var object = new 클래스();
object.한살더하기();
object.나이설정(12);
```
- 한살더하기() 사용시 object에 저장된 나이가 +1 

- 나이설정(12) 사용시 object에 저장된 나이가 12로 변경

<br>

> 답
```java
@Getter
@Setter
public class Test {
    private Integer age;
    private String name;
    public void 한살더하기(){
        this.age = this.age + 1;
    }
    public void 나이설정(Integer a){
        this.age = a;
    }
    public static void main(String[] args) {
      var a = new Test();
      a.setName("이용복");
      a.setAge(20);
      a.나이설정(25);
      a.한살더하기();
      System.out.println(a.getAge());
    }
}
```
- 26 출력

<br>

### 3. 위에서 만든 함수를 사용할 때
- 나이가 음수가 되거나 100살 이상이 되는 경우를 막아주도록 함수를 업그레이드

    - 나이설정(-10) 이라고 사용하면 나이 변돟 X

<br>

> 답
```java
public void 나이설정(Integer a){
    if (a > 0 && a < 100) {
        this.age = a;
    }
}
```

<BR>

### 4. Item class의 변수들도 전부 private로 만들어서 사용
- 변수들을 쓰는 곳들을 다 수정

<br> 