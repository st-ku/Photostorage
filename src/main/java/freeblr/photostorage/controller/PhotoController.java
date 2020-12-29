package freeblr.photostorage.controller;

import freeblr.photostorage.model.Picture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        //some path = path + picture.getName(), for example

        /*File imgPath = new File("some path");
        byte[] image = Files.readAllBytes(imgPath.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);*/

        return null;
    }

    /*
    Need to add for correct work (in future) - if we will return files instead of links
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    ByteArrayHttpMessageConverter byteConverter = new ByteArrayHttpMessageConverter();
    converters.add(byteConverter);
    super.configureMessageConverters(converters);
}
     */
}
