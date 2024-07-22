package amine.elh.crudsimplebackend.services;


import amine.elh.crudsimplebackend.documents.Image;
import amine.elh.crudsimplebackend.repositories.ImageRepository;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String store(MultipartFile file) {
        try {
            Binary imageFile = new Binary(file.getBytes());
            Image image = new Image();
            image.setFile(imageFile);
            image = imageRepository.save(image);
            return image.getId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    public Image getFile(String id) {
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }
}
