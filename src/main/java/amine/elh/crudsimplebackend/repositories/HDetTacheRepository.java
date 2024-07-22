package amine.elh.crudsimplebackend.repositories;

import amine.elh.crudsimplebackend.documents.HDetTache;
import amine.elh.crudsimplebackend.documents.HEntTache;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface HDetTacheRepository extends MongoRepository<HDetTache, String> {

    @Query("{'hEntTache.idHEntTache': ?0}")
    List<HDetTache> findByHEntTacheId(String idHEntTache);


    @Query(value = "{ 'hEntTache.idHEntTache': ?0 }", delete = true)
    void deleteByHEntTacheId(String idHEntTache);



}
