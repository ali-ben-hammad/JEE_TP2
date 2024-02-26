    package ma.enset.jee_tp2.entites;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.Collection;
    import java.util.Date;


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

       @OneToMany(mappedBy = "patient")
       private Collection<RendezVous> rendezVous;


    }
