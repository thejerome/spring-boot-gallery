package com.efimchick.gallery.halresource.assembler;

import com.efimchick.gallery.Image;
import com.efimchick.gallery.halresource.ImageResource;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */
public class ImageResourceAssembler extends AbstractEnrichingResourceAssembler<Image, ImageResource> {

    public ImageResourceAssembler(ResourceEnricher<Image, ImageResource>... enrichers) {
        super(Image.class, ImageResource.class, enrichers);
    }

    @Override
    ImageResource createResource(Image image) {
        ImageResource imageResource = new ImageResource();

        imageResource.name = image.getName();
        imageResource.fullName = image.getFullName();
        imageResource.size = image.getSize();
        imageResource.height = image.getHeight();
        imageResource.width = image.getWidth();

        imageResource.id = image.getId();

        return imageResource;
    }


}
