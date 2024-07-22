package amine.elh.crudsimplebackend.repositories;


import amine.elh.crudsimplebackend.documents.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
