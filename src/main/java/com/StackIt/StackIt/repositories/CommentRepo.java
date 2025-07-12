package com.StackIt.StackIt.repositories;

import com.StackIt.StackIt.models.Comment;
import com.StackIt.StackIt.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByStatusFalse();
}
