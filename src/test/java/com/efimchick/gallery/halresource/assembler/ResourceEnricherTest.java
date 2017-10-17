package com.efimchick.gallery.halresource.assembler;

import com.efimchick.gallery.Image;
import com.efimchick.gallery.LocalImage;
import com.efimchick.gallery.halresource.ImageResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Paths;

import static com.efimchick.gallery.halresource.assembler.ResourceAssemblers.imageSelfLinkEnricher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Evgenii_Efimchik on 13-Oct-17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceEnricherTest {

    @Test
    public void linkEnricherMayBeCreated() {
        FakeResourceEnricher enricher = new FakeResourceEnricher();

        assertNotNull(enricher);
    }

    @Test
    public void linkEnricherCanEnrich() {
        ResourceEnricher<Image, ImageResource> resourceEnricher = new FakeResourceEnricher<>();
        resourceEnricher.enrich(new ImageResource());
    }

    @Test
    public void linkEnricherAddsLinkToSelf() {
        ImageResource imageResource = new ImageResource();
        imageResource.id = "id";

        imageSelfLinkEnricher.enrich(imageResource);
        assertEquals("http://localhost/images/id", imageResource.getLink("self").getHref());
    }

    @Test
    public void linkEnricherAddsLinkToSelfForLocalImage() throws IOException {
        ImageResource imageResource = new ImageResourceAssembler().toResource(new LocalImage(Paths.get("pictures_HQ/IMAG0686.jpg")));

        imageSelfLinkEnricher.enrich(imageResource);
        assertEquals("http://localhost/images/pictures_HQ%5CIMAG0686.jpg", imageResource.getLink("self").getHref());
    }


    private static class FakeResourceEnricher<T, R extends ResourceSupport> implements ResourceEnricher<T, R> {

        @Override
        public void enrich(T entity, R resource) {

        }
    }
}