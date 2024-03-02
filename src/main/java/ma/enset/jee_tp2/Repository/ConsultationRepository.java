package ma.enset.jee_tp2.Repository;

import ma.enset.jee_tp2.entites.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
