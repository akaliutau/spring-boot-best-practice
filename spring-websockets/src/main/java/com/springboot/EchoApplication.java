package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@EnableWebSocket
@EnableAutoConfiguration
public class EchoApplication implements WebSocketConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @Bean
    public EchoHandler echoHandler() {
        return new EchoHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoHandler(), "/echo").setAllowedOrigins("*");// change origins for production
    }

}
