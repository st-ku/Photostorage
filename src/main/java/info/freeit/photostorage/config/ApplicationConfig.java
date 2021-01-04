package info.freeit.photostorage.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public Cloudinary cloudinaryConfig(@Value("${cloudinary.cloud.name}") String cloudName,
                                       @Value("${cloudinary.api.key}") String apiKey,
                                       @Value("${cloudinary.api.secret}") String apiSecret) {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }
}
