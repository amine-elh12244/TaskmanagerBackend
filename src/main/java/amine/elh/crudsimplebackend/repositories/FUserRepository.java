package amine.elh.crudsimplebackend.repositories;

import amine.elh.crudsimplebackend.documents.FUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FUserRepository extends MongoRepository<FUser, String> {
}
