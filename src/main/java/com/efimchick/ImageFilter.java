package com.efimchick;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.isDirectory;

/**
 * Created by Jerome on 22.05.2016.
 */
@Service
public class ImageFilter {
    @Resource
    List<String> imgExtensions;

    public boolean isImage(Path path){
        Objects.requireNonNull(path);
        for (String extension : imgExtensions) {
            if (!isDirectory(path) && path.toString().toLowerCase().endsWith(extension.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
