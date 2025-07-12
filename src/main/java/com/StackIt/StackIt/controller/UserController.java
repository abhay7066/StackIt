package com.StackIt.StackIt.controller;

import com.StackIt.StackIt.DTO.CommonMessageDTo;
import com.StackIt.StackIt.DTO.HomeResponseDTO;
import com.StackIt.StackIt.DTO.QuestionAdDTO;
import com.StackIt.StackIt.DTO.SoltuionRequestDTo;
import com.StackIt.StackIt.models.Question;
import com.StackIt.StackIt.models.Solution;
import com.StackIt.StackIt.models.User;
import com.StackIt.StackIt.repositories.QuestionRepo;
import com.StackIt.StackIt.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuestionRepo questionRepo ;

    @GetMapping("/home")
    public ResponseEntity<HomeResponseDTO> gerhome (Principal principal) throws UserPrincipalNotFoundException {
        String id = "abhaysc7778@gmail.com";
        User user = userRepo.findById(id)
                .orElseThrow(()-> new UserPrincipalNotFoundException("user nt found"));

        List<Question> questionList =questionRepo.findAll();
        System.out.println(questionList);

        HomeResponseDTO dto = new HomeResponseDTO();

//        dto.setQuestion(questionList);
        dto.setUsername(user.getName());
        dto.setUserId(user.getMail());

        System.out.println(questionList.get(0).getSolutions());
        Map<Question , List<Solution>> map = new HashMap<>();

        questionList.forEach(
                question ->  map.put(question , questionList.get(0).getSolutions())
        );

        dto.setMap(map);

        return ResponseEntity.ok(dto);

    }

    @PostMapping("/user/question")
    public ResponseEntity<CommonMessageDTo> createQuestion(@RequestBody QuestionAdDTO questionAdDTO) throws Exception {
        String id = "abhaysc7778@gmail.com";

        User user = userRepo.findById(id)
                .orElseThrow(()-> new Exception("user not found "));

        List<Question> questionList = user.getQuestionList();

        Question question = new Question();

        question.setCreateAt(LocalDateTime.now());
        question.setTitle(questionAdDTO.getTitle());
        question.setDescription(question.getDescription());
        question.setStatus(false);

        questionList.add(question);

        userRepo.save(user);

        return ResponseEntity.ok(new CommonMessageDTo("quetion created"));

    }

    @PutMapping("/user/answer")
    public ResponseEntity<CommonMessageDTo> createSolution(@RequestBody SoltuionRequestDTo soltuionRequestDTo) throws Exception {
        String id = "abhaysc7778@gmail.com";

        User user = userRepo.findById(id)
                .orElseThrow(() -> new Exception("user not found "));

        Solution solution = new Solution();

        Question question = questionRepo.findById(soltuionRequestDTo.getQuestionId())
                .orElseThrow(() -> new UserPrincipalNotFoundException("question not found"));


        solution.setCreatedAt(LocalDateTime.now());
        solution.setAnswerProvider(user);
        solution.setContent(soltuionRequestDTo.getContent());

        List<Solution> solutionList = question.getSolutions();
        solutionList.add(solution);

        questionRepo.save(question);

        return ResponseEntity.ok(new CommonMessageDTo("solution submite"));
    }

}
