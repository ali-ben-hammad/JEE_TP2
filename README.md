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
