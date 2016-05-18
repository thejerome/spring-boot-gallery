package com.efimchick;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by Jerome on 15.05.2016.
 */
public class ImageSeeker {
    public static InputStream getStreamOfImage(String path, Predicate<Path> isImageValidation){
        Objects.requireNonNull(path);
        Path actualPath = Paths.get(path);
        if (isImageValidation.test(actualPath)){
            try {
                return Files.newInputStream(actualPath);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else throw new IllegalArgumentException("Wrong image path " + path);
    }
}
