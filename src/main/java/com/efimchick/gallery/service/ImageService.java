package com.efimchick.gallery.service;

import com.efimchick.gallery.domain.LocalImage;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.halresource.assembler.ImageResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Optional;

import static com.efimchick.gallery.domain.Utils.unescapePath;


/**
 * Created by Evgenii_Efimchik on 17-Oct-17.
 */

@Service
public class ImageService {

    private final ImageResourceAssembler imageResourceAssemblerEnrichingSelfLink;

    @Autowired
    public ImageService(ImageResourceAssembler imageResourceAssemblerEnrichingSelfLink) {
        this.imageResourceAssemblerEnrichingSelfLink = imageResourceAssemblerEnrichingSelfLink;
    }

    public Optional<ImageResource> findById(String id) {

        Path path = unescapePath(id);
        return LocalImage.of(path).map(imageResourceAssemblerEnrichingSelfLink::toResource);

    }
}
