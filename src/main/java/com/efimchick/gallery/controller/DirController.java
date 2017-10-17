package com.efimchick.gallery.controller;

import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.service.DirectoryService;
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
@RequestMapping("/dirs")
@ExposesResourceFor(DirectoryResource.class)
public class DirController {

    private final DirectoryService directoryService;

    @Autowired
    public DirController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping("/{id}")
    ResponseEntity<DirectoryResource> dir(@PathVariable("id") String id) {

        return directoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    ResponseEntity<DirectoryResource> root() {

        return directoryService.getRoot()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
