package jwt.springjwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface User extends JpaRepository<jwt.springjwt.model.User, Integer> {
    Optional<jwt.springjwt.model.User> findByEmail(String email);
}