package com.efimchick.gallery;

import java.nio.file.Path;

/**
 * Created by Evgenii_Efimchik on 10-Oct-17.
 */
public interface File {
    String getName();

    String getFullName();

    Path getPath();

}
