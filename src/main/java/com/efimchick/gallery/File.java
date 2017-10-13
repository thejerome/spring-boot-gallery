package com.efimchick.gallery;

import org.springframework.hateoas.Identifiable;

import java.nio.file.Path;

/**
 * Created by Evgenii_Efimchik on 10-Oct-17.
 */
public interface File extends Identifiable<String> {
    String getName();

    String getFullName();

    Path getPath();

}
