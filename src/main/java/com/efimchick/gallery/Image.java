package com.efimchick.gallery;

import java.nio.file.Path;

/**
 * Created by Evgenii_Efimchik on 09-Oct-17.
 */
public interface Image extends File {

    long getSize();

    int getWidth();

    int getHeight();

    String getExtension();

    class Dimension {
        public final int width;
        public final int height;

        public Dimension(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "{" + width + ", " + height + "}";
        }
    }
}
