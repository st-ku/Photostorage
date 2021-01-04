package info.freeit.photostorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PhotoStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhotoStorageApplication.class, args);
    }
}
