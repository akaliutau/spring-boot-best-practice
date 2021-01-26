package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * A simple application which prints a list of bean names in the context
 * @author akaliutau
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DemoApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(DemoApplication.class, args);

        System.out.println("# Beans: " + ctx.getBeanDefinitionCount());

        var names = ctx.getBeanDefinitionNames();
        Arrays.sort(names);
        Arrays.asList(names).forEach(System.out::println);
    }
}
