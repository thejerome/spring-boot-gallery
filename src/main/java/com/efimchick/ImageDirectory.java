package com.efimchick;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.nio.file.Files.*;
import static java.nio.file.Files.list;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;

/**
 * Created by Jerome on 14.05.2016.
 */
public class ImageDirectory {
    private Path root;
    private Predicate<Path> isImageValidation;

    public ImageDirectory(Path root, Predicate<Path> isImageValidation) {
        Objects.requireNonNull(root);
        Objects.requireNonNull(isImageValidation);
        if (!(exists(root) && isDirectory(root))){
            throw new IllegalArgumentException("Root is not exist or not a directory");
        }
        this.root = root;
        this.isImageValidation = isImageValidation;
    }

    public List<String> getImages(){
        return getFilteredPaths(isImageValidation);
    }

    public List<String> getDirectories(){
        return getFilteredPaths(p -> isDirectory(p));
    }

    public ImageDirectoryDesc getDesc(){
        return new ImageDirectoryDesc(getImages(), getDirectories());
    }

    private List<String> getFilteredPaths(Predicate<Path> predicate){
        try {
            return list(root).filter(predicate)
                    .map(p -> p.toString())
                    .map(p -> p.replaceAll("\\\\", "/"))
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emptyList();
    }





}
