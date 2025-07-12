package com.StackIt.StackIt.repositories;

import com.StackIt.StackIt.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question , Integer> {
}
