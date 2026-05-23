package pbo.springboot.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pbo.springboot.project.model.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}