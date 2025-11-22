package com.neordinary.domain.tag.repository;

import com.neordinary.domain.tag.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
