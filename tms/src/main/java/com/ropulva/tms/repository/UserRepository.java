package com.ropulva.tms.repository;

import com.ropulva.tms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
