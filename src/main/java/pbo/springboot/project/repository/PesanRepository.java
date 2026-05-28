package pbo.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbo.springboot.project.model.Pesan;

public interface PesanRepository extends JpaRepository<Pesan, Long> {
}
