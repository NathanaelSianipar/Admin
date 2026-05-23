package pbo.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbo.springboot.project.model.Lulusan;

public interface LulusanRepository extends JpaRepository<Lulusan, Long> {
}