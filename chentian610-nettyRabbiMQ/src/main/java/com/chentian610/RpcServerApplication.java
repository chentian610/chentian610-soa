package com.chentian610;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by patterncat on 2016-04-08.
 */
@SpringBootApplication
public class RpcServerApplication {

    public static void main(String[] args){
        SpringApplication app = new SpringApplication(RpcServerApplication.class);
        app.setWebEnvironment(false);
        app.run(args);
    }
}
