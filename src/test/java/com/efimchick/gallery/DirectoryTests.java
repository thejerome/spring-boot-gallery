package com.efimchick.gallery;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;
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

        System.out.println(localDir.getName());
        System.out.println(localDir.getPath());
        System.out.println(localDir.getImages());
        System.out.println(localDir.getSubDirs());


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
    }
}
