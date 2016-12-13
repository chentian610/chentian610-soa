package com.chentian610.chentian610.getui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
