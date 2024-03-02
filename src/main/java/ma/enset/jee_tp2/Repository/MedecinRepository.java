package ma.enset.jee_tp2.Repository;
import ma.enset.jee_tp2.entites.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
        List<Medecin> findByNomContains(String nom);


}
