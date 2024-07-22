package amine.elh.crudsimplebackend.services;

import amine.elh.crudsimplebackend.documents.HEntTache;
import amine.elh.crudsimplebackend.repositories.HDetTacheRepository;
import amine.elh.crudsimplebackend.repositories.HEntTacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HEntTacheService {

    @Autowired
    private HEntTacheRepository hEntTacheRepository;
    @Autowired
    private HDetTacheRepository hDetTacheRepository;

    // Create
    public HEntTache createHEntTache(HEntTache hEntTache) {
        return hEntTacheRepository.save(hEntTache);
    }

    // Read
    public List<HEntTache> getAllHEntTaches() {
        return hEntTacheRepository.findAll();
    }

    public Optional<HEntTache> getHEntTacheById(String id) {
        return hEntTacheRepository.findById(id);
    }

    // Update
    public HEntTache updateHEntTache(HEntTache hEntTache) {
        return hEntTacheRepository.save(hEntTache);
    }

    // Delete
    public void deleteHEntTache(String id) {
        hEntTacheRepository.deleteById(id);
    }



    @Transactional
    public void deleteHEntTacheAndRelatedHDetTaches(String idHEntTache) {
        System.out.println("Attempting to delete HEntTache and related HDetTaches for id: {}"+ idHEntTache);
        HEntTache hEntTache = hEntTacheRepository.findById(idHEntTache).orElse(null);
        if (hEntTache != null) {
            System.out.println("Found HEntTache: {}"+ hEntTache);
            // Delete all HDetTache documents associated with this HEntTache
            hDetTacheRepository.deleteByHEntTacheId(idHEntTache);
            // Now delete the HEntTache itself
            hEntTacheRepository.delete(hEntTache);
            System.out.println("Deleted HEntTache and related HDetTaches for id: {}"+ idHEntTache);
        } else {
            System.out.println("HEntTache not found for id: {}"+ idHEntTache);
        }
    }
}
