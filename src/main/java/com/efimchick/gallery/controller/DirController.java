package com.efimchick.gallery.controller;

import com.efimchick.gallery.LocalDirectory;
import com.efimchick.gallery.halresource.DirectoryResource;
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
import static com.efimchick.gallery.halresource.assembler.ResourceAssemblers.directoryResourceAssemblerWithSelfLinkAndEmbeddedImages;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RestController
@RequestMapping("/dirs")
@ExposesResourceFor(DirectoryResource.class)
public class DirController {

    @GetMapping("/{id}")
    ResponseEntity<DirectoryResource> dir(@PathVariable("id") String id) {

        Path path = Paths.get(decodeString(id));
        Optional<LocalDirectory> dirOpt = LocalDirectory.of(path);

        if (dirOpt.isPresent()) {
            DirectoryResource res = directoryResourceAssemblerWithSelfLinkAndEmbeddedImages()
                    .toResource(dirOpt.get());
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
