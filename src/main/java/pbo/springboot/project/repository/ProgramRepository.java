package pbo.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbo.springboot.project.model.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
}