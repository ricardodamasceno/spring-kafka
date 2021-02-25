package com.br.kafka.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtils {

    public static String parseObjectToJsonString(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(o);
        } catch (Exception e){
            log.error("Failed to parse object");
            throw e;
        }
    }

}
