package cristinamastellaro.BE_U2_S2_G2.repositories;

import cristinamastellaro.BE_U2_S2_G2.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, UUID> {
    boolean existsByEmail(String email);
}
