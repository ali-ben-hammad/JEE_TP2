package ma.enset.jee_tp2.sevice;

import ma.enset.jee_tp2.entites.Consultation;
import ma.enset.jee_tp2.entites.Medecin;
import ma.enset.jee_tp2.entites.Patient;
import ma.enset.jee_tp2.entites.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient p);
    Medecin saveMedecin(Medecin m);
    RendezVous saveRendezVous(RendezVous r);
    Consultation saveConsultation(Consultation c);
    Patient findPatientById(Long id);

    Medecin findMedecinById(Long id);



}
