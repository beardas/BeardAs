package com.RanReco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RanRecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RanRecoApplication.class, args);
		System.out.println("랜덤/추천 - 여행지/맛집 리스트 시작!");
	}

}
