package com.chentian610.redpackage;

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
