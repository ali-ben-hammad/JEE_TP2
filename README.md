# Activité Pratique N°2 : ORM, Jpa, Hibernate Spring Data
## Entity Patient
```java
 @Entity
    @Data @NoArgsConstructor @AllArgsConstructor
    public class Patient {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nom;
        @Temporal(TemporalType.DATE)
        private Date dateNaissance;
        private int score;
        private boolean malade;
    }
```
## Fichier application.properties
```properties
server.port=8085
spring.datasource.url=jdbc:h2:mem:JEE_TP2
spring.h2.console.enabled=true
```
## Interface JPA
```java 
public interface PatientRepository extends JpaRepository<Patient, Long>{

        List<Patient> findByNomContains(String nom);
}
```
## Test de quelques opérations de gestion de patients
```java
        patientRepository.save(new Patient(null, "Hassan", new Date(), 205, false));
        patientRepository.save(new Patient(null, "Khalid", new Date(), 987, false));
        patientRepository.save(new Patient(null, "Omar", new Date(), 30, true));
        patientRepository.save(new Patient(null, "ali", new Date(), 103, true));


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
```
![alt text](https://drive.google.com/file/d/1Avl2DwTWmIca5VN96gEK-htXNCZKbXDV/view?usp=drive_link)

## Entity Consultation
```java
@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateConsultation;
    private String rapport;

    @OneToOne
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     private  RendezVous rendezVous;
}
```
## Entity Medecin
```java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medecin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String specialite;
    @OneToMany(mappedBy = "medecin", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<RendezVous> rendezVous;
}
```
## Entity RendezVous
```java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private boolean annule;
    @ManyToOne
    private Medecin medecin;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient patient;
    private StatusRDV status;
    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;
}
```
## Couche Service
### IHospitalService
```java
public interface IHospitalService {
    Patient savePatient(Patient p);
    Medecin saveMedecin(Medecin m);
    RendezVous saveRendezVous(RendezVous r);
    Consultation saveConsultation(Consultation c);
    Patient findPatientById(Long id);
    Medecin findMedecinById(Long id);
}
```
### HospitalServiceImpl
```java
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
```
## Test couche WEB
### PatientRestController
```java
@RestController
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("patients")
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }
}
```
## Migrer de H2 vers MySQL
### applictaion.properties
```properties
server.port=8085
# MySQL database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hospitalJEE?CreateDatabaseIfNotExist=true
# dialect for hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.show-sql=true

spring.jpa.hibernate.ddl-auto=create

#spring.h2.console.enabled=true
```
