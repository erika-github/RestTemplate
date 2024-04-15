package com.example.restTemplate.practice.models;

import com.example.restTemplate.practice.dto.DtoPost;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.IntStream;

@UtilityClass
public class UserMapper {


    public Map<String, Object> buildUserDto(List<Post> lista){


        List<DtoPost> dtoPost= new ArrayList<>();

        Map<String, Object> m = new HashMap<String, Object>();


        IntStream.range(0, lista.size()).forEach(i -> {


                    dtoPost.add(new DtoPost(lista.get(i).getId(), lista.get(i).getTitle(), lista.get(i).getBody()));

                    //dtoPost.sort(Comparator.comparing(DtoPost::getId));
                });


        m.put("Post", dtoPost);


        return m;
    }

}




