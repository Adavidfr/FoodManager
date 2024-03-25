package com.inn.food.dao;

import com.inn.food.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer> {

    User findEmailId(@Param("email") String email);

}
