package amine.elh.crudsimplebackend.repositories;

import amine.elh.crudsimplebackend.documents.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Roles, String> {

 Optional<Roles> findByName(String name);

}