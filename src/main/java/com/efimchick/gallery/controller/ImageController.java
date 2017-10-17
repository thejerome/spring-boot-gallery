package com.efimchick.gallery.controller;

import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RestController
@RequestMapping("/images")
@ExposesResourceFor(ImageResource.class)
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id:.+}")
    ResponseEntity<ImageResource> image(@PathVariable("id") String id) {

        return imageService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
