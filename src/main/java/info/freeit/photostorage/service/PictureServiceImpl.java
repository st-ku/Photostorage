package info.freeit.photostorage.service;

import info.freeit.photostorage.model.Picture;
import info.freeit.photostorage.repository.PictureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final CloudStorageService cloudStorageService;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudStorageService cloudStorageService) {
        this.pictureRepository = pictureRepository;
        this.cloudStorageService = cloudStorageService;
    }

    @Override
    public List<String> getAllPictureUrls() {
        return pictureRepository.findAllPictureUrls();
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public List<Picture> getAllPictures(Sort sort) {
        return pictureRepository.findAll(sort);
    }

    @Override
    public Page<Picture> getAllPictures(Pageable pageable) {
        return pictureRepository.findAll(pageable);
    }

    @Override
    public Picture getPictureById(Long id) {
        return pictureRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Picture savePicture(Picture picture, byte[] file) {
        createEntity(picture, cloudStorageService.save(file));
        pictureRepository.save(picture);
        return picture;
    }
    @Override
    public Picture updatePicture(Picture picture) {
        pictureRepository.save(picture);
        return picture;
    }

    private void createEntity(Picture entity, Map cloudResponse) {
        entity.setHeight((Integer) cloudResponse.get("height"));
        entity.setWidth((Integer) cloudResponse.get("width"));
        entity.setUrl((String) cloudResponse.get("url"));
        entity.setType((String) cloudResponse.get("type"));
    }

}
