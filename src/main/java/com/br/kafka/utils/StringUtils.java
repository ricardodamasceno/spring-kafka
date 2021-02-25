package com.br.kafka.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtils {

    public static String parseObjectToJsonString(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(o);
        } catch (Exception e){
            System.out.println("Failed to parse object");
            throw e;
        }
    }

}
