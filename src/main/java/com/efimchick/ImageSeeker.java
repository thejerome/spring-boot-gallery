package com.efimchick;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.efimchick.SpringBootGalleryApplication.ResourceNotFoundException;

/**
 * Created by Jerome on 15.05.2016.
 */

public class ImageSeeker {

    private Path path;

    @Autowired
    private ImageFilter imageFilter;

    public ImageSeeker(Path path) {
        this.path = path;
    }

    public InputStream getStreamOfImage(){
        Objects.requireNonNull(path);
        if (imageFilter.isImage(path)){
            try {
                return Files.newInputStream(path);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new ResourceNotFoundException("Wrong image path " + path);
        }
    }
}
