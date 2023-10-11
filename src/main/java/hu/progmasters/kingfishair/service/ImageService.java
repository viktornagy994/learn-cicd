package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.Image;
import hu.progmasters.kingfishair.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getPlaneImageUrl(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image not found."));
        return image;
    }
}




