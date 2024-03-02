package ma.enset.jee_tp2.Repository;

import ma.enset.jee_tp2.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.ByteBuffer;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long>{
        List<Patient> findByNomContains(String nom);
        Patient findByNom(String nom);
}
