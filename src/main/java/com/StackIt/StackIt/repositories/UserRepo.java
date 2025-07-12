package com.StackIt.StackIt.repositories;

import com.StackIt.StackIt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String > {


}
