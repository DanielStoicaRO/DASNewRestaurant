package main.das.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello My New Restaurant!
 */

@SpringBootApplication
public class RestaurantApp {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApp.class, args);
        System.out.println("DAS Restaurant successfully start!");
    }
}
