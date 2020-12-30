package freeblr.photostorage.controller;

import freeblr.photostorage.model.Picture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @see PhotoController#getAllPictures()
 * @see PhotoController#getPicture(Long)
 * Worth discussing - will we return files or links<br>
 *     (so that the front-team itself goes to the cloud server using the links)
 */

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @GetMapping("/metadata")
    public List<Picture> getAllMetadata() {
        return null;
    }

    @GetMapping("/metadata/{id}")
    public Picture getMetadata(@PathVariable Long id) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<byte[]>> getAllPictures() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPicture(@PathVariable Long id) throws IOException {
        return null;
    }

    @PostMapping("/photos")
    public void savePicture() throws IOException {

    }

}
