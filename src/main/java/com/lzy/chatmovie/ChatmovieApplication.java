package com.lzy.chatmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ChatmovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatmovieApplication.class, args);
    }


    @Bean
    public BCryptPasswordEncoder encoder ()
    {
        return new BCryptPasswordEncoder();
    }

}
