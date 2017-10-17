package com.efimchick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.efimchick.gallery.controller")
@ComponentScan(basePackages = "com.efimchick.gallery.service")
public class SpringBootGalleryApplication {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(SpringBootGalleryApplication.class, args);
    }

    @RequestMapping("")
    String hello() {
        return "Hello!";
    }

//
//    @RequestMapping("/hierarchy")
//    ImageDirectoryDesc hierarchy() {
//        Path rootPath = Paths.get(root);
//        if (root != null && exists(rootPath) && isDirectory(rootPath)) {
//            ImageDirectory dir = applicationContext.getBean(ImageDirectory.class, rootPath);
//            return dir.getDesc();
//        }
//        return null;
//    }

//    @RequestMapping(value = "/i", method = RequestMethod.GET, produces = "image/jpg")
//    ResponseEntity<byte[]> image(@RequestParam(required = true) String image) {
//        Path path = getPathUnderRoot(image);
//        InputStreamResource inputStream = new InputStreamResource(applicationContext.getBean(ImageSeeker.class, path).getStreamOfImage());
//        return new ResponseEntity(inputStream, HttpStatus.OK);
//    }

//    private Path getPathUnderRoot(String image) {
//        Path path;
//        try {
//            path = Paths.get(image);
//            if (!path.startsWith(root)) {
//                throw new ResourceNotFoundException("Forbidden access to files that are not under root dir");
//            }
//        } catch (InvalidPathException e) {
//            throw new ResourceNotFoundException(e);
//        }
//        return path;
//    }

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public static class ResourceNotFoundException extends RuntimeException {
//        public ResourceNotFoundException() {
//        }
//
//        public ResourceNotFoundException(String message) {
//            super(message);
//        }
//
//        public ResourceNotFoundException(String message, Throwable cause) {
//            super(message, cause);
//        }
//
//        public ResourceNotFoundException(Throwable cause) {
//            super(cause);
//        }
//
//        public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//            super(message, cause, enableSuppression, writableStackTrace);
//        }
//    }

//
//    @Bean
//    @Scope("prototype")
//    public ImageDirectory imageDirectory(Path path) {
//        return new ImageDirectory(path);
//    }
//
//    @Bean
//    @Scope("prototype")
//    public ImageSeeker imageSeeker(Path path) {
//        return new ImageSeeker(path);
//    }
}
