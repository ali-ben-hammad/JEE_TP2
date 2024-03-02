package ma.enset.jee_tp2.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Patient patient;
    @Enumerated(EnumType.STRING)
    private StatusRDV status;
    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;
}
