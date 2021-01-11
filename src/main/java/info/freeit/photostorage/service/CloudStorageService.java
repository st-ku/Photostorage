package info.freeit.photostorage.service;

import java.util.Map;

public interface CloudStorageService {
    Map<String, String> save(byte[] file);
}
