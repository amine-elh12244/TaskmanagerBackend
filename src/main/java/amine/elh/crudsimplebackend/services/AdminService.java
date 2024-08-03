package amine.elh.crudsimplebackend.services;

import amine.elh.crudsimplebackend.documents.Admin;
import amine.elh.crudsimplebackend.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        // Password is saved as it is, without encoding
        return adminRepository.save(admin);
    }

    public Admin authenticate(String username, String password) {
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (password.equals(admin.getPassword())) {
                return admin;
            }
        }
        return null;
    }

    // Method to get all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Method to get an admin by username
    public Optional<Admin> getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    // Method to get an admin by ID
    public Optional<Admin> getAdminById(String id) {
        return adminRepository.findById(id);
    }
}
