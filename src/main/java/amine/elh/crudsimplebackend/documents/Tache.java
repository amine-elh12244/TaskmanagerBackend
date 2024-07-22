package amine.elh.crudsimplebackend.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tache")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tache {
    @Id
    private String idTache;
    private String libelleTache;
    private int coefficient;
    private String remarques;


}
