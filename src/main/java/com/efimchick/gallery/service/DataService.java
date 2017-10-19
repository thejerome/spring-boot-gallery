package com.efimchick.gallery.service;

import com.efimchick.gallery.domain.LocalImage;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static com.efimchick.gallery.domain.Utils.unescapePath;


/**
 * Created by Evgenii_Efimchik on 17-Oct-17.
 */

@Service
public class DataService {

    public Optional<InputStreamResource> findImageDataById(String id) {

        Path path = unescapePath(id);
        System.out.println(path);
        return LocalImage.of(path)
                .map(LocalImage::getPath)
                .map(DataService::getFileInputStream)
                .map(InputStreamResource::new);

    }

    private static InputStream getFileInputStream(Path path) {
        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
