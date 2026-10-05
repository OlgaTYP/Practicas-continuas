package com.example.todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApiApplication {

  public static void main(String[] args) {
    System.out.println("Starting Todo API Application!!!");
    SpringApplication.run(TodoApiApplication.class, args);
  }
}
