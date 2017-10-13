package com.efimchick.gallery.halresource.assembler;

import com.efimchick.gallery.Image;
import com.efimchick.gallery.controller.ImageController;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.halresource.links.LinksEnricher;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */
public class ImageResourceAssembler extends AbstractEnrichingResourceAssembler<Image, ImageResource> {

    public ImageResourceAssembler(LinksEnricher<ImageResource>... enrichers) {
        super(Image.class, ImageResource.class, enrichers);
    }

    @Override
    ImageResource createResource(Image image) {
        ImageResource imageResource = new ImageResource();

        imageResource.name = image.getName();
        imageResource.size = image.getSize();
        imageResource.height = image.getHeight();
        imageResource.width = image.getWidth();

        imageResource.id = image.getId();

        return imageResource;
    }


}
