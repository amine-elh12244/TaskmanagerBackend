package amine.elh.crudsimplebackend.services;

import amine.elh.crudsimplebackend.documents.HDetTache;
import amine.elh.crudsimplebackend.documents.HEntTache;
import amine.elh.crudsimplebackend.repositories.HDetTacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HDetTacheService {

    @Autowired
    private HDetTacheRepository hDetTacheRepository;

    // Create
    public HDetTache createHDetTache(HDetTache hDetTache) {
        return hDetTacheRepository.save(hDetTache);
    }

    // Read
    public List<HDetTache> getAllHDetTaches() {
        return hDetTacheRepository.findAll();
    }

    public Optional<HDetTache> getHDetTacheById(String id) {
        return hDetTacheRepository.findById(id);
    }

    // Update
    public HDetTache updateHDetTache(HDetTache hDetTache) {
        return hDetTacheRepository.save(hDetTache);
    }

    // Delete
    public void deleteHDetTache(String id) {
        hDetTacheRepository.deleteById(id);
    }

    // Find by HEntTache
    public List<HDetTache> findByHEntTacheId(String idHEntTache) {
        return hDetTacheRepository.findByHEntTacheId(idHEntTache);
    }


}
