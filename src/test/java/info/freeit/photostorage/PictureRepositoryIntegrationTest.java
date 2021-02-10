package info.freeit.photostorage;

import info.freeit.photostorage.model.Picture;
import info.freeit.photostorage.repository.PictureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PictureRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PictureRepository pictureRepository;

    @Test
    public void whenFindAll_thenReturnPicture() {
        Picture picture = new Picture();
        picture.setName("test");
        picture.setWidth(1);
        picture.setHeight(1);
        picture.setType("test");
        entityManager.persist(picture);
        entityManager.flush();
        List<Picture> found = pictureRepository.findAll();
        assertThat(found.listIterator().next().getId())
                .isEqualTo(picture.getId());
    }

}