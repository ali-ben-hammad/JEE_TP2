package ma.enset.jee_tp2;

import ma.enset.jee_tp2.Repository.ConsultationRepository;
import ma.enset.jee_tp2.Repository.MedecinRepository;
import ma.enset.jee_tp2.Repository.PatientRepository;
import ma.enset.jee_tp2.Repository.RendezVousRepository;
import ma.enset.jee_tp2.entites.*;
import ma.enset.jee_tp2.sevice.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class JeeTp2Application{

    public static void main(String[] args) {
        SpringApplication.run(JeeTp2Application.class, args);
    }


    @Bean
    CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            MedecinRepository medecinRepository
    ){
        return args -> {
            Stream.of("Ali", "Omar", "Khalid", "Hassan").forEach(nom -> {
                Patient patient = new Patient();
                patient.setNom(nom);
                patient.setDateNaissance(new Date());
                patient.setScore(100 + (int) (Math.random() * 1000));
                patient.setMalade(Math.random() > 0.5);
                hospitalService.savePatient(patient);
            });
            Stream.of("Berhili", "Benali", "barry", "belehbib").forEach(nom -> {
                Medecin medecin = new Medecin();
                medecin.setNom(nom);
                medecin.setEmail(nom + "@gmail.com");
                medecin.setSpecialite(Math.random()>0.5?"Cardiologue":"Dentiste");
                hospitalService.saveMedecin(medecin);
            });

            Patient patient = hospitalService.findPatientById(1L);
            Patient patient1 = patientRepository.findByNom("Ali");

            Medecin medecin = medecinRepository.findByNom("Berhili");
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            rendezVous.setStatus(StatusRDV.PENDING);
            hospitalService.saveRendezVous(rendezVous);

            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous);
            consultation.setRapport("Raapport de consultation");
            hospitalService.saveConsultation(consultation);

        };
    }
}
