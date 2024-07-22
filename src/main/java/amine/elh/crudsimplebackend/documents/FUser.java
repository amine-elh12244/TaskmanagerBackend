package amine.elh.crudsimplebackend.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "f_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FUser {
    @Id
    private String idFUser;
    private String libelleFamille;
    private int coefficient;
    private String remarques;

}
