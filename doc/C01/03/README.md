# 자바 기본 문법 2 (class, constructor)

class
---
- 변수와 함수 보관하는 통

1. 클래스 쓰면 변수랑 함수들을 보관해둘 수 있음

2. 보관해둔 변수나 함수 쓰려면 new 클래스(); 로 클래스 복사본을 하나 만들어서 점찍어서 꺼내써야함

3. new 키워드로 뽑은 클래스 복사본을 object라고 부름

<br>

> 사용법
```java
class Test {
    String name = "kim";
    void hello(){ System.out.println("안녕");  }
}
```
- class 만들려면 `class 작명 { }`

  - 중괄호 안에 변수나 함수 여러개 보관 가능

- 보관했던 변수와 함수를 자유롭게 가져다쓸 수 있음

<br>

---

<br>

****class에 있던 변수 함수 사용
---
- class에 보관해뒀던 변수랑 함수를 쓰고 싶으면 class의 복사본부터 만들기

> 복사
```java
var test = new Test();
```
- new 클래스() : `클래스에 있던 변수랑 함수 복사해달라`는 뜻

- object : 변수랑 함수 복사본 담고있는 자료

<br>

> 데이터 꺼내기
```java
var test = new Test();
System.out.println(test.name);
```
- object 안에 저장된 변수랑 함수를 가져다쓰고 싶으면

    - 점찍어서 변수나 함수 꺼내서 쓰기

- `.name` : Test 클래스에 보관해놨던 kim이라는 변수 남음

- test.hello() 처럼 그 안에 있던 함수 사용 가능

<br>

> 복사
```java
Test test = new Test();
```
- 클래스를 복사해올 때 타입을 정확히 넣고 싶으면

    - 타입자리에 클래스명 넣으면 됨

- 클래스도 자료형 역할 가능





<br>

---

<br>

class 쓰는 이유
---
1. 자바를 선택한 이상 자바에선 항상 코딩을 클래스부터 써놓고 시작하기 때문에 어쩔 수 없이 강제로 써야함



2. 관련있는 변수, 함수를 한 곳에 보관할 수 있으니까 코드 정리해두기 좋음



3. 중요한 변수, 함수 원본을 안전하게 보관할 수 있음

   - 중요한 서류 수정할 때 원본을 직접 수정하지 않음

        - 원본을 직접 수정하면 위험할 수 있어서 복사한 다음에 그걸 가져다가 쓰는게 안전
    
   - class도 마찬가지
   
     - 변수, 함수 원본을 보관해두고 new 키워드로 복사해서 쓰면 원본 데이터 안전

<br>

### 💡 참고1
- 클래스안에는 복사안되는 변수, 복사안해도 쓸 수 있는 변수 생성 가능

  - static private 같은 여러 키워드 사용

<br>

### 💡 참고2
- object = 인스턴스

- 클래스 안에 있는 변수 = field / attribute

- 클래스 안에 있는 함수 = method

<br>

---

<br>

constructor 문법
---
- Q) 친구 한 명의 이름과 나이를 변수로 저장해놓고 싶다면?

    - 친구의 이름, 나이 저장할 변수 2개 생성

    - 클래스 생성 후 안에 작성

<br>

> 코드
```java
class Friend {
    String name = "kim";
    int age = 20;
}
```
- Friend 클래스 하나 만들어서 이름, 나이를 보관

- 앞으로 이 친구의 나이가 필요하면

    - new Friend()로 object 뽑아서 사용

<br>

- Q) 친구가 한 명 더 생겼는데, 나이는 똑같은데 이름이 "park"

  - 그 친구도 클래스 하나 만들어서 이름, 나이 저장하려면?

<br>

> 코드
```java
class Friend1 {
    String name = "park";
    int age = 20;
}
```
- 클래스 하나 더 만들어서 내용 작성

<br>

- Q) 또 다른 이름의 친구가 또 생기면?

  - 비슷한 클래스는 굳이 여러개 만들 필요 X

  - 클래스에서 쓸 수 있는 constructor와 파라미터문법 사용

    - object 뽑을 때 마다 매번 다른 변수값을 가지도록 클래스를 만들 수 있음

<br>

> 코드
```java
class Friend {
    String name = "kim";
    int age = 20;
    
    Friend(){
        this.name = "새친구이름";
    }
}
```
- class 안에다가 클래스랑 똑같은 이름의 함수( ){ } 생성

    - 이 함수는 object를 하나 만들 때 자동으로 실행

    - 함수안에는 `this` 키워드 사용 가능

        - this : 새로 생성될 object 뜻함

          - `this.name = “새친구이름”`

          - name 변수의 초깃값을 맘대로 설정 가능

- 새로 생성될 object의 name 변수에 "새친구이름" 넣으라는 뜻

<br>

- 스페셜한 함수랑 this문법을 잘 쓰면 object를 뽑을 때 마다 매번 다른 값을 변수에 집어넣어서 뽑을 수 있음

    - 뽑을 때 마다 name변수에 "kim"도 넣고 "park"도 자유롭게 넣어서 뽑을 수 있음

<br>

> 사용법
```java
class Friend {
    String name = "kim";
    int age = 20;

    Friend(String 이름){
        this.name = 이름;
    }
}
```
- 매번 가변적으로 설정할 부분에다가 파라미터 추가

    - 이름이라는 파라미터 추가

    - 파라미터도 일종의 변수라서 왼쪽에 타입도 집어넣어야함

<br>

> 코드
```java
var friend1 = new Friend("kim");
var friend2 = new Friend("park");
var friend3 = new Friend("이민호");

System.out.println(friend1.name + "\n" + friend2.name);
System.out.println(friend3.name);
```
- new Friend() 로 object 뽑을 때

    - 소괄호 안에 원하는 값을 집어넣어서 뽑을 수 있음

<br>

### 💡 참고1
- this는 생략 가능

  - 없어도 this 생략되었겠거니 하면서 컴퓨터가 알아서 채워줌

<br>

### 💡 참고2
- 파라미터는 원하는 만큼 많이 만들 수 있음

<br>

### 💡 참고3
- 위에서 만든 함수가 constructor

<br>

---

<br>

객체지향 프로그래밍
---
- class와 object를 많이 활용

- 객체지향 문법 : 길고 복잡한 코드 정리도구, 관리도구

    - class 만들고 object 뽑고 constructor 쓸 줄 알면 초보때는 충분

- 스프링 부트는 코드 창조할 일 보다 코드 배치하는 일이 더 많음

<br>

---

<br>

정리
---
- 클래스는 변수 함수 보관함

- 클래스에 있던 변수함수를 사용하고 싶으면 new 클래스()로 object 뽑기

    - 클래스 안에 들어있던 변수 함수가 object로 복사됨

- object 뽑을 때마다 각각 다른 변수값을 부여하고 싶으면 constructor 생성

    - `클래스이름( ){ }` 으로 함수만들면 constructor

    - 그 안에 있는 코드는 object 뽑을 때 자동실행
  
      - this 써서 변수의 초깃값 맘대로 설정 가능 
  
      - 함수 파라미터 문법도 사용가능

- field, attribute, method, instance

<br>


 

 