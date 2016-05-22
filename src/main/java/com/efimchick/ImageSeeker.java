package com.efimchick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Predicate;

import static com.efimchick.SpringBootGalleryApplication.*;

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
