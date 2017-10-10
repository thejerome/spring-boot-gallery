package com.efimchick.gallery;


import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Evgenii_Efimchik on 10-Oct-17.
 */
@EqualsAndHashCode(of = {"path"})
public class LocalDirectory implements Directory {
    private final Path path;
    private final String name;
    private final String fullName;

    public LocalDirectory(Path path) {
        this.path = path;

        name = path.getFileName().toString();
        fullName = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public List<Image> getImages() {
        try {
            return Files.list(path)
                    .map(LocalImage::of)
                    .flatMap(Utils::streamOfOptional)
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Directory> getSubDirs() {
        return Collections.emptyList();
    }
}
