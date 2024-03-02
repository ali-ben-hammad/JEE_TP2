package ma.enset.jee_tp2.sevice;

import jakarta.transaction.Transactional;
import ma.enset.jee_tp2.Repository.ConsultationRepository;
import ma.enset.jee_tp2.Repository.MedecinRepository;
import ma.enset.jee_tp2.Repository.PatientRepository;
import ma.enset.jee_tp2.Repository.RendezVousRepository;
import ma.enset.jee_tp2.entites.Consultation;
import ma.enset.jee_tp2.entites.Medecin;
import ma.enset.jee_tp2.entites.Patient;
import ma.enset.jee_tp2.entites.RendezVous;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    private MedecinRepository medecinRepository;
    private PatientRepository patientRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public HospitalServiceImpl(MedecinRepository medecinRepository, PatientRepository patientRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient p) {
        return patientRepository.save(p);
    }
    @Override
    public Patient findPatientById(Long id) {
        return patientRepository.findById(id).get();
    }
    @Override
    public Medecin findMedecinById(Long id) {
        return medecinRepository.findById(id).get();
    }

    @Override
    public Medecin saveMedecin(Medecin m) {
        return medecinRepository.save(m);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous r) {
        return rendezVousRepository.save(r);
    }

    @Override
    public Consultation saveConsultation(Consultation c) {
        return consultationRepository.save(c);
    }
}
