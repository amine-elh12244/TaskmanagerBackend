package amine.elh.crudsimplebackend.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalTime;
import java.util.Date;

@Document(collection = "h_det_tache")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HDetTache {
    @Id
    private String idHDetTache;
    @DBRef
    @Indexed
    private HEntTache hEntTache;
    @DBRef
    private Tache tache;
    private LocalTime hDebut;
    private LocalTime hFin;
    private long tempsDiff;
    private int coefficient;
    private double prixCalc;
    private String remarques;


}
