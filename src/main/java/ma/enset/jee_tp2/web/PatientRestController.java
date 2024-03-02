package ma.enset.jee_tp2.web;


import ma.enset.jee_tp2.Repository.PatientRepository;
import ma.enset.jee_tp2.entites.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("patients")
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }
}
