package com.syedm.thymecrud.Repository;

import com.syedm.thymecrud.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
