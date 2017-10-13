package com.efimchick.gallery.controller;

import com.efimchick.gallery.LocalDirectory;
import com.efimchick.gallery.halresource.DirectoryResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

import static com.efimchick.gallery.halresource.assembler.ResourceAssemblers.directoryResourceAssemblerEnrichingSelfLink;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RestController
@RequestMapping("/dirs")
@ExposesResourceFor(DirectoryResource.class)
public class DirController {

    @GetMapping("/{id}")
    HttpEntity<DirectoryResource> hello(Long id) {

        DirectoryResource res = directoryResourceAssemblerEnrichingSelfLink()
                .toResource(
                        new LocalDirectory(Paths.get("pictures_HQ"))
                );

        return new HttpEntity<>(res);
    }
}
