package com.efimchick.gallery.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static com.efimchick.gallery.domain.Utils.escapePath;

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
    private final String parentDirId;

    public LocalImage(Path path) throws IOException {
        this.path = path;

        name = path.getFileName().toString();
        fullName = path.toString();
        size = Files.size(path);
        id = path.getFileName().toString();
        extension = com.google.common.io.Files.getFileExtension(name);

        Dimension dimension = Utils.getImageDimension(path.toFile());
        width = dimension.width;
        height = dimension.height;

        parentDirId = escapePath(path.getParent());
    }

    public static Optional<LocalImage> of(Path p) {
        try {
            return Optional.of(new LocalImage(p));
        } catch (Exception e) {
            log.error("No image found: " + e.getLocalizedMessage());
            return Optional.empty();
        }
    }
}
