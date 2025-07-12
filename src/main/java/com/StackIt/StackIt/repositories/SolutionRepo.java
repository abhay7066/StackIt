package com.StackIt.StackIt.repositories;

import com.StackIt.StackIt.models.Comment;
import com.StackIt.StackIt.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionRepo extends JpaRepository<Solution,Integer> {
    List<Solution> findByStatusFalse();
}
