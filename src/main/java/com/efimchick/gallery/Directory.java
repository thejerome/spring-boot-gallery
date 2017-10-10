package com.efimchick.gallery;

import java.util.List;

/**
 * Created by Evgenii_Efimchik on 09-Oct-17.
 */
public interface Directory extends File{

    List<Image> getImages();
    List<Directory> getSubDirs();

}
