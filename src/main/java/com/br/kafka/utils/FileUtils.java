package com.br.kafka.utils;

import com.br.kafka.vo.UserRequestVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<UserRequestVO> loadUsersFromFile(String path){
        Gson gson = new GsonBuilder().create();
        JsonParser parser = new JsonParser();

        try{
            List<UserRequestVO> users = new ArrayList<>();
            JsonArray usersJson = (JsonArray) parser.parse(new FileReader(path));

            usersJson.forEach(user -> {
                users.add(gson.fromJson(user, UserRequestVO.class));
            });
            return users;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
