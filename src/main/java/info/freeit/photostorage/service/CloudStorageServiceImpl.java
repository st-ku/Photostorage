package info.freeit.photostorage.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudStorageServiceImpl implements CloudStorageService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudStorageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    @Transactional
    public Map save(String url) {
        String validUrl = Objects.requireNonNull(url, "CloudStorageServiceImpl got null url.");

        if (Strings.isBlank(url)) {
            throw new RuntimeException("CloudStorageServiceImpl got empty url.");
        }
        // TODO: 08.01.2021 при необходимости заполнить параметрами https://cloudinary.com/documentation/upload_images
        Map params = ObjectUtils.asMap();
        Map response = new HashMap();

        try {
            response = cloudinary.uploader().upload(validUrl, params);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
