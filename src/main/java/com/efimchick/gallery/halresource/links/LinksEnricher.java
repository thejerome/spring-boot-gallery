package com.efimchick.gallery.halresource.links;

import com.efimchick.gallery.halresource.ImageResource;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Evgenii_Efimchik on 13-Oct-17.
 */
public interface LinksEnricher<R extends ResourceSupport> {
    void enrich(R imageResource);
}
