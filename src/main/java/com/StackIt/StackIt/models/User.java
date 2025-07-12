package com.StackIt.StackIt.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    private String mail;
    private String name ;
    private String password ;

    @OneToMany
    private List<Question> questionList = new ArrayList<>();

    @OneToMany
    private List<Notification> notifications = new ArrayList<>();

}
