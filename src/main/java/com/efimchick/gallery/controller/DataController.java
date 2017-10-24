package com.efimchick.gallery.controller;

import com.efimchick.gallery.service.DataService;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.singletonList;

/**
 * Created by Evgenii_Efimchik on 18-Oct-17.
 */

@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("{dir}/{img:.+}")
    ResponseEntity<byte[]> image(@PathVariable("dir") String dir, @PathVariable("img") String img) {
        return dataService.findImageDataByDirAndId(dir, img)
                .map(inputStreamResource -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.put(HttpHeaders.CONTENT_TYPE, singletonList("image/" + Files.getFileExtension(img).toLowerCase()));
                    return new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
