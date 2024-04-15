package com.example.restTemplate.practice.configRestTemplate;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigRestTemplate {

    /*@Bean
    public RestTemplate resTemplate(RestTemplateBuilder builder){

        return builder.build();

    }*/

    @Bean
    public RestTemplate resTemplate(){

        return new RestTemplate();

    }
}
