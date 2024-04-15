package com.example.restTemplate.practice.services;

import com.example.restTemplate.practice.models.Post;
import com.example.restTemplate.practice.models.User;
import com.example.restTemplate.practice.models.UserMapper;
import org.apache.tomcat.util.codec.binary.BaseNCodec;
//import org.springframework.http.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.apache.tomcat.util.codec.binary.Base64;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;


import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.apache.tomcat.util.codec.binary.Base64.*;

@Service
public class RestTemplateImpl {

    private final RestTemplate resTemplate;

    public RestTemplateImpl(RestTemplate resTemplate) {
        this.resTemplate = resTemplate;
    }


    private String hash() {
        String credentials = "admin:1234";
        return Base64.encodeBase64String(credentials.getBytes());


    }


    public Map<String, Object> getUserList() {


        Map<String, Object> map = new HashMap<String, Object>();


        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            //headers.setBasicAuth("admin","12345");
            headers.add("Authorization", "Basic " + hash());
            HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
            System.out.println("HEADERS::::" + headers);
        /*ResponseEntity<Post[]> response = (resTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Post[].class

        ));*/


            ResponseEntity<List<Post>> response = resTemplate.exchange(
                    "https://jsonplaceholder.typicode.com/posts",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<List<Post>>() {
                    }
            );

            return UserMapper.buildUserDto(Objects.requireNonNull(response.getBody()));


        } catch (ResourceAccessException ex) {

            map.put("Error Message", "Verifique que el servicio que está siendo consumido, se encuentre en funcionamiento: " + ex.getMessage() + "::::" + ex);

            return map;

        } catch (HttpClientErrorException.NotFound ex) {

            List<String> ls = new ArrayList<>();

            map.put("MessageResponse", "No se han encontrado registros");
            map.put("BodyResponse", ls);
            map.put("CodeResponse", "404");

            return map;

        }


    }
}