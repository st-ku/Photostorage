package info.freeit.photostorage.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CloudStorageServiceImpl implements CloudStorageService {

    private final Cloudinary cloudinary;
    private final Tika tika = new Tika();
    private final List<String> supportedImageFormatList = Arrays.asList("image/png","image/bmp","image/jpeg","image/webp");

    @Autowired
    public CloudStorageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    @Transactional
    @SneakyThrows
    public Map save(byte[] file) {
        String detectedType = tika.detect(file);
        if (supportedImageFormatList.contains(detectedType)) {
            Map<String, String> response = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return response;
        }
        else throw new UnsupportedOperationException("Unsupported file format");
    }
}
