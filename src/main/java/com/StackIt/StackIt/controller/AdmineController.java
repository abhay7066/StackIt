package com.StackIt.StackIt.controller;

import com.StackIt.StackIt.models.Question;
import com.StackIt.StackIt.repositories.QuestionRepo;
import com.StackIt.StackIt.services.AdminSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("Admin")
public class AdmineController{

    @Autowired
    AdminSerivce adminSerivce;

    @GetMapping("/home")
    Map<String, Object> home(Principal principal){
        if(principal.getName()=="admin123@gmail.com") {
            return adminSerivce.getAdminHome();
        }else{
            return null;
        }
    }

}
