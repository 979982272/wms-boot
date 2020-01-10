package com.tudou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.tudou.**.mapper")
public class WmsBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsBootApplication.class, args);
    }
}
