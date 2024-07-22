package amine.elh.crudsimplebackend.repositories;


import amine.elh.crudsimplebackend.documents.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByUsername(String username);
}
