package com.efimchick;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.*;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Created by Jerome on 14.05.2016.
 */

public class ImageDirectory {
    private Path root;

    @Autowired
    private ImageFilter imageFilter;

    public ImageDirectory(Path root) {
        Objects.requireNonNull(root);
        if (!(exists(root) && isDirectory(root))) {
            throw new IllegalArgumentException(root + " is not exist or not a directory");
        }
        this.root = root;
    }

    public ImageDirectory(Path root, ImageFilter imageFilter) {
        this(root);
        Objects.requireNonNull(imageFilter);
        this.imageFilter = imageFilter;
    }

    public List<String> getImages() {
        try {
            return list(root).filter(p -> imageFilter.isImage(p))
                    .map(Path::toString)
                    .map(p -> p.replaceAll("\\\\", "/"))
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emptyList();
    }

    public List<ImageDirectoryDesc> getDirectories() {
        try {
            return list(root).filter(p -> isDirectory(p))
                    .map(p -> new ImageDirectory(p, imageFilter).getDesc())
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emptyList();
    }

    public ImageDirectoryDesc getDesc() {
        return new ImageDirectoryDesc(root.getFileName().toString(), getImages(), getDirectories());
    }


}
