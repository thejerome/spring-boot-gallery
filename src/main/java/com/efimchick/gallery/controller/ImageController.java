package com.efimchick.gallery.controller;

import com.efimchick.gallery.Image;
import com.efimchick.gallery.LocalDirectory;
import com.efimchick.gallery.LocalImage;
import com.efimchick.gallery.Utils;
import com.efimchick.gallery.halresource.ImageResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
