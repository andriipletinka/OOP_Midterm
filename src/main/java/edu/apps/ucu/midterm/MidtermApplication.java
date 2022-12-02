package edu.apps.ucu.midterm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class MidtermApplication {
    @Autowired
    public String data;

    public static void main(String[] args) {
        SpringApplication.run(MidtermApplication.class, args);
    }

    @GetMapping
    public String printData() {
        return data;
    }
}
