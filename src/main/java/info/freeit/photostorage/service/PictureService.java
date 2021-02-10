package info.freeit.photostorage.service;

import info.freeit.photostorage.model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PictureService {

    List<Picture> getAllPictures();

    List<Picture> getAllPictures(Sort sort);

    Page<Picture> getAllPictures(Pageable pageable);

    Picture getPictureById(Long id);

    Picture savePicture(Picture picture, byte[] file);

    Picture updatePicture(Picture picture);

    List<String> getAllPictureUrls();

}
