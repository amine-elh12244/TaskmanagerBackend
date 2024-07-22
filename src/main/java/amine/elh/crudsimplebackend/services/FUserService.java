package amine.elh.crudsimplebackend.services;

import amine.elh.crudsimplebackend.documents.FUser;
import amine.elh.crudsimplebackend.repositories.FUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FUserService {

    @Autowired
    private FUserRepository fUserRepository;

    // Create
    public FUser createFUser(FUser fUser) {
        return fUserRepository.save(fUser);
    }

    // Read
    public List<FUser> getAllFUsers() {
        return fUserRepository.findAll();
    }


    public Optional<FUser> getFUserById(String id) {
        return fUserRepository.findById(id);
    }


    // Update
    public FUser updateFUser(FUser fUser) {
        return fUserRepository.save(fUser);
    }

    // Delete
    public void deleteFUser(String id) {
        fUserRepository.deleteById(id);
    }
}
