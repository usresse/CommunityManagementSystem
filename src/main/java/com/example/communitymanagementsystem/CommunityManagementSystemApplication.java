package com.example.communitymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CommunityManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityManagementSystemApplication.class, args);
    }

}
