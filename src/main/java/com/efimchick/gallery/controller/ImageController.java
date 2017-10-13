package com.efimchick.gallery.controller;

import com.efimchick.gallery.Image;
import com.efimchick.gallery.LocalDirectory;
import com.efimchick.gallery.halresource.ImageResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

import static com.efimchick.gallery.halresource.assembler.ResourceAssemblers.imageResourceAssemblerEnrichingSelfLink;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RestController
@RequestMapping("/img")
@ExposesResourceFor(ImageResource.class)
public class ImageController {

    @GetMapping("/{id}")
    @ResponseBody
    ImageResource hello(Long id) {

        LocalDirectory dir = new LocalDirectory(Paths.get("pictures_HQ"));
        Image image = dir.getImages().iterator().next();

        ImageResource imageResource = imageResourceAssemblerEnrichingSelfLink().toResource(image);


        return imageResource;
    }
}
