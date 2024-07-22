package amine.elh.crudsimplebackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import amine.elh.crudsimplebackend.documents.FUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private String idUsers;
    private String nom;
    private int age;
    private String email;
    private FUser fUser;
}
