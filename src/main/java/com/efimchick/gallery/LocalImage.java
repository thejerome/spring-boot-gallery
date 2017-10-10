package com.efimchick.gallery;

import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Created by Evgenii_Efimchik on 10-Oct-17.
 */

@EqualsAndHashCode(of = {"path"})
public class LocalImage implements Image {

    private final Path path;
    private final String fileName;
    private final long size;
    private final Dimension dimension;
    private final String extension;

    public LocalImage(Path path) throws IOException {
        this.path = path;
        fileName = path.getFileName().toString();
        size = Files.size(path);
        dimension = Utils.getImageDimension(path.toFile());
        extension = com.google.common.io.Files.getFileExtension(fileName);
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getFullName() {
        return fileName;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public int getWidth() {
        return dimension.width;
    }

    @Override
    public int getHeight() {
        return dimension.height;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    public static Optional<LocalImage> of(Path p) {
        try {
            return Optional.of(new LocalImage(p));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
