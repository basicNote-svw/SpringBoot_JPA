package com.example.spring_shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {
    private String name;
    private Integer age;

    public void 한살더하기() {
        this.age = this.age + 1;
    }

    public void 나이설정(Integer a) {
        if(a > 0 && a <= 100)
            this.age = a;
    }

    public static void main(String[] args) {
        var a = new Test();
        a.setName("이용복");
        a.setAge(25);
        a.나이설정(-30);
        a.한살더하기();
        System.out.println(a.getAge());
    }

}

