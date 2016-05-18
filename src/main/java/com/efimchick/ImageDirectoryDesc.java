package com.efimchick;

import java.util.List;

/**
 * Created by Jerome on 15.05.2016.
 */
public class ImageDirectoryDesc {
    private List<String> images;
    private List<String> dirs;

    public ImageDirectoryDesc(List<String> images, List<String> dirs) {
        this.images = images;
        this.dirs = dirs;
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getDirs() {
        return dirs;
    }
}
