package ma.enset.jee_tp2;

import ma.enset.jee_tp2.Repository.PatientRepository;
import ma.enset.jee_tp2.entites.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JeeTp2Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JeeTp2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // ajouter des patients
/*
        patientRepository.save(new Patient(null, "Hassan", new Date(), 205, false));
        patientRepository.save(new Patient(null, "Khalid", new Date(), 987, false));
        patientRepository.save(new Patient(null, "Omar", new Date(), 30, true));
        patientRepository.save(new Patient(null, "ali", new Date(), 103, true));
*/

        // consulter les patients
        System.out.println();
        System.out.println("********** Liste des patients **********");
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });

        // chercher un patient
        System.out.println();
        System.out.println("********** Chercher un patient **********");
        Patient patient = patientRepository.findById(1L).get();
        System.out.println("Patient trouvé avec id 1L: ");
        System.out.println(patient.toString());

        System.out.println();
        List<Patient> patients = patientRepository.findByNomContains("ali");
        System.out.println("Patients trouvé avec nom containing ali : ");
        patients.forEach(p -> {
            System.out.println(p.toString());
        });

        // mettre à jour un patient
        System.out.println();
        System.out.println("********** Mettre à jour un patient **********");
        patient.setNom("yazid");
        patientRepository.save(patient);
        System.out.println("Patient mis à jour avec id 1L: ");

        // supprimer un patient
        System.out.println();
        System.out.println("********** Supprimer un patient **********");
        patientRepository.deleteById(1L);
        System.out.println("Patient supprimé avec id 1L: ");
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });


    }
}
