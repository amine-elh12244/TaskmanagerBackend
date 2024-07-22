package amine.elh.crudsimplebackend.services;

import amine.elh.crudsimplebackend.documents.FUser;
import amine.elh.crudsimplebackend.documents.Image;
import amine.elh.crudsimplebackend.documents.Users;
import amine.elh.crudsimplebackend.repositories.FUserRepository;
import amine.elh.crudsimplebackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ImageService imageService;

    @Autowired
    private FUserRepository fUserRepository;

    // Create


    // Create
    public Users createUser(Users user) {
        return usersRepository.save(user);
    }


    // Read
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(String id) {
        return usersRepository.findById(id);
    }

    // Update
    public Users updateUser(Users user) {
        return usersRepository.save(user);
    }

    // Delete
    public void deleteUser(String id) {
        usersRepository.deleteById(id);
    }
}
