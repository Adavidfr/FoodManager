package com.inn.food.serviceImpl;

import com.inn.food.POJO.User;
import com.inn.food.constents.FoodConstants;
import com.inn.food.dao.UserDao;
import com.inn.food.service.UserService;
import com.inn.food.utils.FoodUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {

                User user = userDao.findEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return FoodUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    FoodUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return FoodUtils.getResponseEntity(FoodConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }

            return FoodUtils.getResponseEntity(FoodConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        private boolean validateSignUpMap (Map < String, String > requestMap){
            if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                    && requestMap.containsKey("email") && requestMap.containsKey("password")) {
                return true;
            }
            return false;
        }

        private User getUserFromMap (Map < String, String > requestMap){
            User user = new User();
            user.setName(requestMap.get("name"));
            user.setContactNumber(requestMap.get("contactNumber"));
            user.setEmail(requestMap.get("email"));
            user.setPassword(requestMap.get("password"));
            user.setStatus("false");
            user.setRole("user");
            return user;

        }

    }
