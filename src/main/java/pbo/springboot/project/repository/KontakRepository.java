package pbo.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbo.springboot.project.model.Kontak;

@Repository
public interface KontakRepository extends JpaRepository<Kontak, Long> {

}

