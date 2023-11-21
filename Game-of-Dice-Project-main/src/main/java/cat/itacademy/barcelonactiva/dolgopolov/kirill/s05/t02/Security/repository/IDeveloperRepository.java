package cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.repository;

import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByUsername(String username);
}


