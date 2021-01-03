package info.freeit.photostorage.service;

import info.freeit.photostorage.model.Picture;

import java.io.File;
import java.util.List;

public interface PictureService {

    List<Picture> getAllPictures();

    Picture getPictureById();

    String getMetadataById();

    List<String> getAllMetaData();

    void savePicture(Picture picture, File file);

}
