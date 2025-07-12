package com.StackIt.StackIt.DTO;

import com.StackIt.StackIt.models.Question;
import com.StackIt.StackIt.models.Solution;

import java.util.List;
import java.util.Map;


public class HomeResponseDTO {

    private String username ;
    private String userId ;
    private Map<Question , List<Solution>> map ;

    public Map<Question, List<Solution>> getMap() {
        return map;
    }

    public void setMap(Map<Question, List<Solution>> map) {
        this.map = map;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
}


}