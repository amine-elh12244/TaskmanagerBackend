package amine.elh.crudsimplebackend.repositories;

import amine.elh.crudsimplebackend.documents.Tache;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TacheRepository extends MongoRepository<Tache, String> {
}
