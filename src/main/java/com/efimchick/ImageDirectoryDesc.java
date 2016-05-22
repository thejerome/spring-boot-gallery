package com.efimchick;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jerome on 15.05.2016.
 */
public class ImageDirectoryDesc {
    private String name;
    private List<String> images;
    private List<ImageDirectoryDesc> dirs;

    public ImageDirectoryDesc(String name, List<String> images, List<ImageDirectoryDesc> dirs) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(images);
        Objects.requireNonNull(dirs);

        this.images = images;
        this.dirs = dirs;
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public List<ImageDirectoryDesc> getDirs() {
        return dirs;
    }

    public String getName() {
        return name;
    }
}
