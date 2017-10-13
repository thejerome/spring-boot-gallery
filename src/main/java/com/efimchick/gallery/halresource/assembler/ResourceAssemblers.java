package com.efimchick.gallery.halresource.assembler;

import com.efimchick.gallery.controller.DirController;
import com.efimchick.gallery.controller.ImageController;
import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.halresource.links.LinksEnricher;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Evgenii_Efimchik on 13-Oct-17.
 */
public class ResourceAssemblers {
    public static ImageResourceAssembler imageResourceAssemblerEnrichingSelfLink() {
        return new ImageResourceAssembler((LinksEnricher<ImageResource>) resource -> {
            resource.add(linkTo(ImageController.class).slash(resource.id).withSelfRel());
        });
    }

    public static DirectoryResourceAssembler directoryResourceAssemblerEnrichingSelfLink() {
        return new DirectoryResourceAssembler((LinksEnricher<DirectoryResource>) resource -> {
            resource.add(linkTo(DirController.class).slash(resource.id).withSelfRel());
        });
    }
}
