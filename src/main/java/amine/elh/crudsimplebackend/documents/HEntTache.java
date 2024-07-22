package amine.elh.crudsimplebackend.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Document(collection = "h_ent_tache")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HEntTache {
    @Id
    private String idHEntTache;
    private String libelleJournee;
    @DBRef
    private Users users;
    private Date dateOperation;
    private String remarques;

    // Getters and setters
}
