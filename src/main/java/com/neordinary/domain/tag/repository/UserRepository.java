package com.neordinary.domain.tag.repository;

import com.neordinary.domain.tag.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByTag_TagId(Long tagId);
}
