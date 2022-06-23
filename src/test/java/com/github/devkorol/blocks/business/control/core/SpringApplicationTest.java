package com.github.devkorol.blocks.business.control.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = "com.github.devkorol.blocks.business.control.core"
)
public class SpringApplicationTest {

  public static void main(String[] args) {
    SpringApplication.run(SpringApplicationTest.class, args);
  }
}