package io.github.carrothole.carrot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "io.github.carrothole.carrot.mapper")
public class CarrotApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrotApplication.class, args);
    }


}
