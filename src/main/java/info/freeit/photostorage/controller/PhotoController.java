package info.freeit.photostorage.controller;

import info.freeit.photostorage.model.Picture;
import info.freeit.photostorage.service.PictureService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see PhotoController#getAllPictures()
 * @see PhotoController#getPicture(Long)
 * Worth discussing - will we return files or links<br>
 * (so that the front-team itself goes to the cloud server using the links)
 */

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final PictureService pictureService;

    public PhotoController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/metadata")
    public ResponseEntity<List<Picture>> getAllMetadata() {
      return new ResponseEntity<>(pictureService.getAllPictures(), HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    public ResponseEntity<Picture> getMetadata(@PathVariable Long id) {
        return new ResponseEntity<>(pictureService.getPictureById(id), HttpStatus.OK);
    }

    /**
     * Get pictures's URLS from DB
     * @return List only URLS of pictures from BB
     */
    @GetMapping("/photos")
    public ResponseEntity<List<String>> getAllPictures() {
        return new ResponseEntity<>(pictureService.getAllPictureUrls(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPicture(@PathVariable Long id) {
        return new ResponseEntity<>(pictureService.getPictureById(id).getUrl(), HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Picture>> getAllLatest(@RequestParam(value = "page", required = true) int page, @RequestParam(value = "size", required = true) int size ) {
        Pageable sortedByUpdatedAsc =
                PageRequest.of(page, size, Sort.by("updated").descending());
        return new ResponseEntity<>(pictureService.getAllPictures(sortedByUpdatedAsc).getContent(), HttpStatus.OK);
    }

    @PostMapping(path =  "/photos", consumes = "multipart/form-data")
    public ResponseEntity<Picture> savePicture(@RequestParam("imageFile") MultipartFile fileUpload) throws IOException {
        Picture picture = new Picture();
        picture.setName(fileUpload.getOriginalFilename());
        return new ResponseEntity<>(pictureService.savePicture(picture, fileUpload.getBytes()), HttpStatus.OK);
    }

}
