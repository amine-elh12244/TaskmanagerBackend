package amine.elh.crudsimplebackend.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    private String idUsers;
    private String nom;
    private int age;
    private String email;
    @DBRef(lazy = false)
    private FUser fUser;

}
