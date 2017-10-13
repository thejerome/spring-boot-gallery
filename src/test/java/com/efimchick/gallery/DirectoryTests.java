package com.efimchick.gallery;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Evgenii_Efimchik on 09-Oct-17.
 */
public class DirectoryTests {

    private final Directory fakeDir = new FakeDirectory();

    @Test
    public void dirMayBeCreated() {
        Directory fakeDir = new FakeDirectory();
    }

    @Test
    public void dirHasNameAndFullName() {
        assertNotNull(fakeDir.getName());
        assertNotNull(fakeDir.getFullName());
    }


    @Test
    public void dirHasPath() {
        assertNotNull(fakeDir.getPath());
    }

    @Test
    public void dirHasSubDirs() {
        assertNotNull(fakeDir.getImages());
    }

    @Test
    public void dirHasImages() {
        assertNotNull(fakeDir.getImages());
    }


    @Test
    public void localDirMayBeCreated() throws IOException {
        LocalDirectory localDir = new LocalDirectory(Paths.get("pictures_HQ/Carimate"));

        assertNotNull(localDir.getFullName());
        assertNotNull(localDir.getName());
        assertNotNull(localDir.getPath());
        assertNotNull(localDir.getImages());
        assertNotNull(localDir.getSubDirs());

        assertEquals("Carimate", localDir.getFullName());
        assertEquals("Carimate", localDir.getName());
        assertEquals(Paths.get("pictures_HQ", "Carimate"), localDir.getPath());
        assertEquals(Collections.emptyList(), localDir.getSubDirs());

        List<LocalImage> actualImages = Files.list(Paths.get("pictures_HQ", "Carimate"))
                .map(LocalImage::of)
                .flatMap(Utils::streamOfOptional)
                .collect(toList());

        assertEquals(actualImages, localDir.getImages());
    }

    @Test
    public void localDirMayHaveSubDirs() throws IOException {
        LocalDirectory localDir = new LocalDirectory(Paths.get("pictures_HQ"));

        List<Directory> expected = Files.list(Paths.get("pictures_HQ"))
                .filter(Files::isDirectory)
                .map(LocalDirectory::new)
                .collect(toList());

        assertEquals(expected, localDir.getSubDirs());
    }

    @Test
    public void localDirMayNotContainAnyFilesAsImagesExceptImages() throws IOException {
        LocalDirectory localDir = new LocalDirectory(Paths.get("pictures_HQ"));

        List<LocalImage> expected = Files.list(Paths.get("pictures_HQ"))
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".jpg"))
                .map(LocalImage::of)
                .flatMap(Utils::streamOfOptional)
                .collect(toList());

        assertEquals(expected, localDir.getImages());
    }


    private class FakeDirectory implements Directory {
        private String name = "fake";

        @Override
        public List<Image> getImages() {
            return Collections.emptyList();
        }

        @Override
        public List<Directory> getSubDirs() {
            return Collections.emptyList();
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getFullName() {
            return name;
        }

        @Override
        public Path getPath() {
            return Paths.get(name);
        }

        @Override
        public String getId() {
            return name;
        }
    }
}
