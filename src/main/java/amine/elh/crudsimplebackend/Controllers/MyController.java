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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
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
    public FUser createFUser(@RequestBody FUser fUser) {
        return fUserService.createFUser(fUser);
    }

    @GetMapping("/fuser")
    public List<FUser> getAllFUsers() {
        return fUserService.getAllFUsers();
    }

    @GetMapping("/fuser/{id}")
    public Optional<FUser> getFUserById(@PathVariable String id) {
        return fUserService.getFUserById(id);
    }

    @PutMapping("/fuser")
    public FUser updateFUser(@RequestBody FUser fUser) {
        return fUserService.updateFUser(fUser);
    }

    @DeleteMapping("/fuser/{id}")
    public void deleteFUser(@PathVariable String id) {
        fUserService.deleteFUser(id);
    }

    // Users endpoints

    @PostMapping("/users")
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user);
    }



    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<Users> getUserById(@PathVariable String id) {
        return usersService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody Users user) {
        // Optional: You can add logic here to check if the user exists before updating.
        user.setIdUsers(id); // Ensure the user's ID is set to the ID from the path.
        Users updatedUser = usersService.updateUser(user);
        return ResponseEntity.ok(updatedUser); // Return the updated user.
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        usersService.deleteUser(id);
    }

    // HEntTache endpoints
    @PostMapping("/henttache")
    public HEntTache createHEntTache(@RequestBody HEntTache hEntTache) {
        return hEntTacheService.createHEntTache(hEntTache);
    }

    @GetMapping("/henttache")
    public List<HEntTache> getAllHEntTaches() {
        return hEntTacheService.getAllHEntTaches();
    }

    @GetMapping("/henttache/{id}")
    public Optional<HEntTache> getHEntTacheById(@PathVariable String id) {
        return hEntTacheService.getHEntTacheById(id);
    }

    @PutMapping("/henttache/{id}")
    public ResponseEntity<HEntTache> updateHEntTache(@PathVariable String id, @RequestBody HEntTache hEntTache) {
        hEntTache.setIdHEntTache(id); // Adjust the method to set ID if necessary.
        HEntTache updatedHEntTache = hEntTacheService.updateHEntTache(hEntTache);
        return ResponseEntity.ok(updatedHEntTache); // Return the updated HEntTache.
    }

    @DeleteMapping("/henttache/{id}")
    public ResponseEntity<?> deleteHEntTacheAndRelatedHDetTaches(@PathVariable String id) {
        System.out.println("Received request to delete HEntTache and related HDetTaches for id: {}"+
                id);
        hEntTacheService.deleteHEntTacheAndRelatedHDetTaches(id);
        return ResponseEntity.ok().build();
    }

    // HDetTache endpoints
    @PostMapping("/hdettache")
    public ResponseEntity<HDetTache> createHDetTache(@RequestBody HDetTache hDetTache) {
        try {
            HDetTache createdHDetTache = hDetTacheService.createHDetTache(hDetTache);
            return new ResponseEntity<>(createdHDetTache, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/hdettache")
    public List<HDetTache> getAllHDetTaches() {
        return hDetTacheService.getAllHDetTaches();
    }

    @GetMapping("/hdettache/{id}")
    public Optional<HDetTache> getHDetTacheById(@PathVariable String id) {
        return hDetTacheService.getHDetTacheById(id);
    }

    @PutMapping("/hdettache/{id}")
    public ResponseEntity<HDetTache> updateHDetTache(@PathVariable String id, @RequestBody HDetTache hDetTache) {
        hDetTache.setIdHDetTache(id); // Assuming the method to set ID is setIdHEntTache. Adjust if necessary.
        HDetTache updatedHDetTache = hDetTacheService.updateHDetTache(hDetTache);
        return ResponseEntity.ok(updatedHDetTache); // Return the updated HDetTache.
    }

    @DeleteMapping("/hdettache/{id}")
    public void deleteHDetTache(@PathVariable String id) {
        hDetTacheService.deleteHDetTache(id);
    }


    @GetMapping("/hdettache/byHEntTacheId/{idHEntTache}")
    public List<HDetTache> findByHEntTacheId(@PathVariable String idHEntTache) {
        return hDetTacheService.findByHEntTacheId(idHEntTache);
    }

    // Tache endpoints
    @PostMapping("/tache")
    public Tache createTache(@RequestBody Tache tache) {
        return tacheService.createTache(tache);
    }

    @GetMapping("/tache")
    public List<Tache> getAllTaches() {
        return tacheService.getAllTaches();
    }

    @GetMapping("/tache/{id}")
    public Optional<Tache> getTacheById(@PathVariable String id) {
        return tacheService.getTacheById(id);
    }

    @PutMapping("/tache")
    public Tache updateTache(@RequestBody Tache tache) {
        return tacheService.updateTache(tache);
    }

    @DeleteMapping("/tache/{id}")
    public void deleteTache(@PathVariable String id) {
        tacheService.deleteTache(id);
    }

    // Image endpoints

    @PostMapping("/image/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String imageId = imageService.store(file);
            return ResponseEntity.ok(imageId); // Return just the image ID as plain text
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }


    @GetMapping("/image/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
        Image image = imageService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust the media type as needed
                .body(new ByteArrayResource(image.getFile().getData()));
    }
}
