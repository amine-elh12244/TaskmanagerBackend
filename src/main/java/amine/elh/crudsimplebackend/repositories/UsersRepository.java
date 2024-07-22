package amine.elh.crudsimplebackend.repositories;

import amine.elh.crudsimplebackend.documents.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {

}
