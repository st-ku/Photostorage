package info.freeit.photostorage.repository;

import info.freeit.photostorage.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long>, PictureRepositoryCustom {

    @Query("SELECT p FROM Picture p WHERE ((p.width < :width) AND (p.height < :height))")
    List<Picture> findAllLessThenDimension(@Param("width") int width,
                                           @Param("height") int height);
}
interface PictureRepositoryCustom {
    List<Picture> findAllByName(String name);
}