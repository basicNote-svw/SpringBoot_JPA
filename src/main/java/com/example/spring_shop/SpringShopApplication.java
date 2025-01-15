package com.example.spring_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShopApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringShopApplication.class, args);

		String name = "이용복";
		System.out.println(name);

		final int fingerCount = 10;
//		fingerCount = 20;	에러 발생

		if(fingerCount == 10){
			System.out.println("정상");
		}

		class Test {
			String name = "이용복";
			void hello(){
				System.out.println("안녕");
			}
		}

		var test = new Test();
		System.out.println(test.name);

		class Friend1 {
			String name = "황현진";
			int age = 20;
		}

		class Friend2 {
			String name = "한지성";
			int age = 20;
		}

		class Friend {
			String name = "이용복";
			int age = 20;

			Friend(String 이름) {
				this.name = 이름;
			}
		}

		var friend1 = new Friend("황현진");
		var friend2 = new Friend("한지성");
		var friend3 = new Friend("이민호");

		System.out.println(friend1.name + "\n" + friend2.name);
		System.out.println(friend3.name);
	}

}
