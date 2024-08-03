package amine.elh.crudsimplebackend.repositories;


import amine.elh.crudsimplebackend.documents.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByEmail(String email);

    Boolean existsByUsername(String username);
}
