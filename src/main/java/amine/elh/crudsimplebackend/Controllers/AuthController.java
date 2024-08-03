package amine.elh.crudsimplebackend.Controllers;

import amine.elh.crudsimplebackend.Security.JWTGenerator;
import amine.elh.crudsimplebackend.documents.Admin;
import amine.elh.crudsimplebackend.documents.Roles;
import amine.elh.crudsimplebackend.dto.AuthResponseDTO;
import amine.elh.crudsimplebackend.dto.LoginDTO;
import amine.elh.crudsimplebackend.repositories.AdminRepository;
import amine.elh.crudsimplebackend.repositories.RoleRepository;
import amine.elh.crudsimplebackend.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          AdminRepository adminRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (adminRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
        }

        Admin admin = new Admin();
        admin.setUsername(registerDto.getUsername());
        admin.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        admin.setEmail(registerDto.getEmail());
        admin.setNom(registerDto.getNom());
        admin.setPrenom(registerDto.getPrenom());

        // Assuming you have a default role for new users
        Optional<Roles> roleOptional = roleRepository.findByName("ADMIN");
        if (roleOptional.isPresent()) {
            admin.setRoles(Collections.singletonList(roleOptional.get()));
        } else {
            // Handle the case where the role is not found
            return new ResponseEntity<>("Role not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        adminRepository.save(admin);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            if (e instanceof BadCredentialsException) {
                return new ResponseEntity<>(new AuthResponseDTO("Invalid email or password"), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(new AuthResponseDTO("User not found"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Admin> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Admin) {
            Admin authenticatedAdmin = (Admin) authentication.getPrincipal();
            return new ResponseEntity<>(authenticatedAdmin, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
