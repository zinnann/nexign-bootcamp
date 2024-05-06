package ru.grak.brt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BrtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrtApplication.class, args);
    }

}
