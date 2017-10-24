package com.efimchick.gallery.controller;

import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RestController
@RequestMapping("/dirs")
@ExposesResourceFor(DirectoryResource.class)
public class DirController {

    private final DirectoryService directoryService;

    @Autowired
    public DirController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping("/{dir}")
    ResponseEntity<DirectoryResource> dir(@PathVariable("dir") String dir) {

        return directoryService.findDirectoryById(dir)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{dir}/{img:.+}")
    ResponseEntity<ImageResource> image(@PathVariable("dir") String dir, @PathVariable("img") String img) {

        return directoryService.findImageInDirById(dir, img)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping
    ResponseEntity root(HttpServletResponse httpResponse) {

        return directoryService.getRoot()
                .map(root -> ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(root.getId().getHref()))
                        .build()
                )
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
