package com.efimchick.gallery;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Evgenii_Efimchik on 09-Oct-17.
 */

public class ImageTests {

    private final Image fakeImage = new FakeImage();

    @Test
    public void imageMayBeCreated() {
        Image fakeImage = new FakeImage();
    }

    @Test
    public void imageHasNameAndFullName() {
        assertNotNull(fakeImage.getName());
        assertNotNull(fakeImage.getFullName());
    }

    @Test
    public void imageHasPath() {
        assertNotNull(fakeImage.getPath());
    }

    @Test
    public void imageHasSize() {
        assertNotNull(fakeImage.getSize());
    }

    @Test
    public void imageHasExtension() {
        assertNotNull(fakeImage.getExtension());
    }

    @Test
    public void imageHasWidthAndHeight() {
        assertNotNull(fakeImage.getWidth());
        assertNotNull(fakeImage.getHeight());
    }


    @Test
    public void localImageMayBeProperlyCreated() throws IOException {

        LocalImage image = new LocalImage(Paths.get("pictures_HQ/Carimate/IMAG0674.jpg"));
        assertNotNull(image.getExtension());
        assertNotNull(image.getFullName());
        assertNotNull(image.getName());
        assertNotNull(image.getPath());
        assertNotEquals(0, image.getHeight());
        assertNotEquals(0, image.getWidth());
        assertNotEquals(0, image.getSize());


        assertEquals("jpg", image.getExtension());
        assertEquals("pictures_HQ\\Carimate\\IMAG0674.jpg", image.getFullName());
        assertEquals("IMAG0674.jpg", image.getName());
        assertEquals(Paths.get("pictures_HQ\\Carimate\\IMAG0674.jpg"), image.getPath());
        assertEquals(2368, image.getHeight());
        assertEquals(4224, image.getWidth());
        assertEquals(2689467, image.getSize());

    }


    private class FakeImage implements Image {
        private String name = "fake";

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
        public long getSize() {
            return 0;
        }

        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public String getExtension() {
            return "fake";
        }

        @Override
        public String getId() {
            return name;
        }
    }
}
