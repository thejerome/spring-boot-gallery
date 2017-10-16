package com.efimchick.gallery;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Evgenii_Efimchik on 10-Oct-17.
 */
@EqualsAndHashCode(of = {"path"})
@Getter
public class LocalDirectory implements Directory {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LocalDirectory.class);

    private final Path path;
    private final String name;
    private final String fullName;
    private final String id;

    public LocalDirectory(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("No such directory");
        }

        this.path = path;

        name = path.getFileName().toString();
        fullName = path.toString();
        id = path.toString();
    }

    @Override
    public List<Image> getImages() {
        try {
            return Files.list(path)
                    .filter(Utils::isImageByExtension)
                    .map(LocalImage::of)
                    .flatMap(Utils::streamOfOptional)
                    .collect(toList());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }


    @Override
    public List<Directory> getSubDirs() {
        try {
            return Files.list(path)
                    .filter(Files::isDirectory)
                    .map(LocalDirectory::new)
                    .collect(toList());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public static Optional<LocalDirectory> of(Path path) {
        try {
            return Optional.of(new LocalDirectory(path));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
