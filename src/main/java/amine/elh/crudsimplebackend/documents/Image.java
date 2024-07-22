package amine.elh.crudsimplebackend.documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.Binary;

@Document(collection = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    private String id;
    private Binary file;
}
