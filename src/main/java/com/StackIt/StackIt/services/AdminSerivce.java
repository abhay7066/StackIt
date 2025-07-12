package com.StackIt.StackIt.services;

import com.StackIt.StackIt.models.Comment;
import com.StackIt.StackIt.models.Question;
import com.StackIt.StackIt.models.Solution;
import com.StackIt.StackIt.repositories.CommentRepo;
import com.StackIt.StackIt.repositories.QuestionRepo;
import com.StackIt.StackIt.repositories.SolutionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminSerivce {
    @Autowired
    AiService aiService;

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    SolutionRepo solutionRepo;
    @Autowired
    QuestionRepo questionRepo;

    public Map<String, Object> getAdminHome(){
        Map<String, Object> response = new HashMap<>();

        Map<Integer, String> quetions = questionRepo.findByStatusFalse().stream()
                .filter(q -> !q.isStatus())
                .collect(Collectors.toMap(
                        Question::getId,
                        q -> "title: " + q.getTitle() + "\ndescription: " + q.getDescription()
                ));
        Map<Integer, String> comments = commentRepo.findByStatusFalse().stream()
                .filter(q -> !q.isStatus())
                .collect(Collectors.toMap(
                        Comment::getId,
                        q -> q.getContent()
                ));


        Map<Integer, String> answers = solutionRepo.findByStatusFalse().stream()
                .filter(q -> !q.isStatus())
                .collect(Collectors.toMap(
                            Solution::getId,
                        q -> q.getContent()
                ));

        return Map.of("quetions",quetions,"comments",comments,"answers",answers);

    }
}
