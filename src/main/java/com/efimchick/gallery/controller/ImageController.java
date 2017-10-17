package com.efimchick.gallery.controller;

import com.efimchick.gallery.LocalImage;
import com.efimchick.gallery.halresource.ImageResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.efimchick.gallery.Utils.decodeString;
import static com.efimchick.gallery.halresource.assembler.ResourceAssemblers.imageResourceAssemblerEnrichingSelfLink;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RestController
@RequestMapping("/images")
@ExposesResourceFor(ImageResource.class)
public class ImageController {

    @GetMapping("/{id:.+}")
    ResponseEntity<ImageResource> image(@PathVariable("id") String id) {

        Path path = Paths.get(decodeString(id));
        Optional<LocalImage> imageOpt = LocalImage.of(path);


        if (imageOpt.isPresent()) {
            ImageResource res = imageResourceAssemblerEnrichingSelfLink().toResource(imageOpt.get());
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
}
