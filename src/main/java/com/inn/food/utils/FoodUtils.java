package com.inn.food.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FoodUtils {

    private FoodUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\" " + responseMessage + "\"}", httpStatus);

    }
}
