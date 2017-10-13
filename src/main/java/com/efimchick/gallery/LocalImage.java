package com.efimchick.gallery;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Created by Evgenii_Efimchik on 10-Oct-17.
 */

@EqualsAndHashCode(of = {"path"})
@Getter
public class LocalImage implements Image {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LocalImage.class);

    private final Path path;
    private final String name;
    private final long size;
    private final String extension;
    private final String id;
    private final int width;
    private final int height;
    private final String fullName;

    public LocalImage(Path path) throws IOException {
        this.path = path;

        name = path.getFileName().toString();
        fullName = path.getFileName().toString();
        size = Files.size(path);
        id = path.toString();
        extension = com.google.common.io.Files.getFileExtension(name);

        Dimension dimension = Utils.getImageDimension(path.toFile());
        width = dimension.width;
        height = dimension.height;
    }

    public static Optional<LocalImage> of(Path p) {
        try {
            return Optional.of(new LocalImage(p));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }
}
