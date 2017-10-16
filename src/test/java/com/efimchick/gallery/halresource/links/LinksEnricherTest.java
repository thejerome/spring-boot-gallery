package com.efimchick.gallery.halresource.links;

import com.efimchick.gallery.LocalImage;
import com.efimchick.gallery.controller.ImageController;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.halresource.assembler.ImageResourceAssembler;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Evgenii_Efimchik on 13-Oct-17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinksEnricherTest {

    @Test
    public void linkEnricherMayBeCreated() {
        FakeLinksEnricher enricher = new FakeLinksEnricher();

        assertNotNull(enricher);
    }

    @Test
    public void linkEnricherCanEnrich() {
        LinksEnricher<ImageResource> linksEnricher = new FakeLinksEnricher<>();
        linksEnricher.enrich(new ImageResource());
    }

    @Test
    public void linkEnricherAddsLinkToSelf() {
        LinksEnricher<ImageResource> linksEnricher = imageResource -> {
            Link selfLink = linkTo(ImageController.class).slash(imageResource.id).withSelfRel();
            imageResource.add(selfLink);
        };

        ImageResource imageResource = new ImageResource();
        imageResource.id = "id";

        linksEnricher.enrich(imageResource);
        assertEquals("http://localhost/images/id", imageResource.getLink("self").getHref());
    }

    @Test
    public void linkEnricherAddsLinkToSelfForLocalImage() throws IOException {
        LinksEnricher<ImageResource> linksEnricher = imageResource -> {
            Link selfLink = linkTo(ImageController.class).slash(imageResource.id).withSelfRel();
            imageResource.add(selfLink);
        };

        ImageResource imageResource = new ImageResourceAssembler().toResource(new LocalImage(Paths.get("pictures_HQ/IMAG0686.jpg")));

        linksEnricher.enrich(imageResource);
        assertEquals("http://localhost/images/pictures_HQ%5CIMAG0686.jpg", imageResource.getLink("self").getHref());
    }


    private static class FakeLinksEnricher<R extends ResourceSupport> implements LinksEnricher<R> {
        @Override
        public void enrich(R imageResource) {

        }
    }
}