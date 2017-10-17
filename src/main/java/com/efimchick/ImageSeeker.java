package com.efimchick;

import java.nio.file.Path;

/**
 * Created by Jerome on 15.05.2016.
 */

public class ImageSeeker {

    private Path path;

//    @Autowired
//    private ImageFilter imageFilter;

    public ImageSeeker(Path path) {
        this.path = path;
    }

//    public InputStream getStreamOfImage(){
//        Objects.requireNonNull(path);
//        if (imageFilter.isImage(path)){
//            try {
//                return Files.newInputStream(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        } else {
//            throw new ResourceNotFoundException("Wrong image path " + path);
//        }
//    }
}
