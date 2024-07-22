package amine.elh.crudsimplebackend.services;

import amine.elh.crudsimplebackend.documents.Tache;
import amine.elh.crudsimplebackend.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    // Create
    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    // Read
    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    public Optional<Tache> getTacheById(String id) {
        return tacheRepository.findById(id);
    }

    // Update
    public Tache updateTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    // Delete
    public void deleteTache(String id) {
        tacheRepository.deleteById(id);
    }
}

