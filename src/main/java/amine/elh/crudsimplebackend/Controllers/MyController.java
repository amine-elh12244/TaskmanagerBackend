package amine.elh.crudsimplebackend.Controllers;

import amine.elh.crudsimplebackend.documents.*;
import amine.elh.crudsimplebackend.repositories.FUserRepository;
import amine.elh.crudsimplebackend.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private FUserService fUserService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private HEntTacheService hEntTacheService;

    @Autowired
    private HDetTacheService hDetTacheService;

    @Autowired
    private TacheService tacheService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private FUserRepository fUserRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // FUser endpoints
    @PostMapping("/fuser")
    @PreAuthorize("hasRole('ADMIN')")
    public FUser createFUser(@RequestBody FUser fUser) {
        return fUserService.createFUser(fUser);
    }

    @GetMapping("/fuser")
    @PreAuthorize("hasRole('ADMIN')")
    public List<FUser> getAllFUsers() {
        return fUserService.getAllFUsers();
    }

    @GetMapping("/fuser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<FUser> getFUserById(@PathVariable String id) {
        return fUserService.getFUserById(id);
    }

    @PutMapping("/fuser")
    @PreAuthorize("hasRole('ADMIN')")
    public FUser updateFUser(@RequestBody FUser fUser) {
        return fUserService.updateFUser(fUser);
    }

    @DeleteMapping("/fuser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFUser(@PathVariable String id) {
        fUserService.deleteFUser(id);
    }

    // Users endpoints
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Users> getUserById(@PathVariable String id) {
        return usersService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody Users user) {
        user.setIdUsers(id);
        Users updatedUser = usersService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable String id) {
        usersService.deleteUser(id);
    }

    // HEntTache endpoints
    @PostMapping("/henttache")
    @PreAuthorize("hasRole('ADMIN')")
    public HEntTache createHEntTache(@RequestBody HEntTache hEntTache) {
        return hEntTacheService.createHEntTache(hEntTache);
    }

    @GetMapping("/henttache")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HEntTache> getAllHEntTaches() {
        return hEntTacheService.getAllHEntTaches();
    }

    @GetMapping("/henttache/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<HEntTache> getHEntTacheById(@PathVariable String id) {
        return hEntTacheService.getHEntTacheById(id);
    }

    @PutMapping("/henttache/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HEntTache> updateHEntTache(@PathVariable String id, @RequestBody HEntTache hEntTache) {
        hEntTache.setIdHEntTache(id);
        HEntTache updatedHEntTache = hEntTacheService.updateHEntTache(hEntTache);
        return ResponseEntity.ok(updatedHEntTache);
    }

    @DeleteMapping("/henttache/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHEntTacheAndRelatedHDetTaches(@PathVariable String id) {
        hEntTacheService.deleteHEntTacheAndRelatedHDetTaches(id);
        return ResponseEntity.ok().build();
    }

    // HDetTache endpoints
    @PostMapping("/hdettache")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HDetTache> createHDetTache(@RequestBody HDetTache hDetTache) {
        try {
            HDetTache createdHDetTache = hDetTacheService.createHDetTache(hDetTache);
            return new ResponseEntity<>(createdHDetTache, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/hdettache")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HDetTache> getAllHDetTaches() {
        return hDetTacheService.getAllHDetTaches();
    }

    @GetMapping("/hdettache/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<HDetTache> getHDetTacheById(@PathVariable String id) {
        return hDetTacheService.getHDetTacheById(id);
    }

    @PutMapping("/hdettache/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HDetTache> updateHDetTache(@PathVariable String id, @RequestBody HDetTache hDetTache) {
        hDetTache.setIdHDetTache(id);
        HDetTache updatedHDetTache = hDetTacheService.updateHDetTache(hDetTache);
        return ResponseEntity.ok(updatedHDetTache);
    }

    @DeleteMapping("/hdettache/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHDetTache(@PathVariable String id) {
        hDetTacheService.deleteHDetTache(id);
    }

    @GetMapping("/hdettache/byHEntTacheId/{idHEntTache}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HDetTache> findByHEntTacheId(@PathVariable String idHEntTache) {
        return hDetTacheService.findByHEntTacheId(idHEntTache);
    }

    // Tache endpoints
    @PostMapping("/tache")
    @PreAuthorize("hasRole('ADMIN')")
    public Tache createTache(@RequestBody Tache tache) {
        return tacheService.createTache(tache);
    }

    @GetMapping("/tache")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Tache> getAllTaches() {
        return tacheService.getAllTaches();
    }

    @GetMapping("/tache/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Tache> getTacheById(@PathVariable String id) {
        return tacheService.getTacheById(id);
    }

    @PutMapping("/tache")
    @PreAuthorize("hasRole('ADMIN')")
    public Tache updateTache(@RequestBody Tache tache) {
        return tacheService.updateTache(tache);
    }

    @DeleteMapping("/tache/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTache(@PathVariable String id) {
        tacheService.deleteTache(id);
    }

    // Image endpoints
    @PostMapping("/image/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String imageId = imageService.store(file);
            return ResponseEntity.ok(imageId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }

    @GetMapping("/image/download/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
        Image image = imageService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(image.getFile().getData()));
    }
}
