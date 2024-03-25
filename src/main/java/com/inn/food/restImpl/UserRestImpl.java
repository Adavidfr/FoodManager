package com.inn.food.restImpl;

import com.inn.food.constents.FoodConstants;
import com.inn.food.rest.UserRest;
import com.inn.food.service.UserService;
import com.inn.food.utils.FoodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return FoodUtils.getResponseEntity(FoodConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
