package com.efimchick.gallery.halresource.assembler;

import com.efimchick.gallery.Image;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.halresource.links.LinksEnricher;
import com.google.common.collect.ImmutableList;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by Evgenii_Efimchik on 13-Oct-17.
 */
public abstract class AbstractEnrichingResourceAssembler<T, R extends ResourceSupport> extends ResourceAssemblerSupport<T, R> {

    private final List<LinksEnricher<R>> enrichers;

    public AbstractEnrichingResourceAssembler(Class<?> controllerClass, Class<R> resourceType, LinksEnricher<R>... enrichers) {
        super(controllerClass, resourceType);
        this.enrichers = ImmutableList.copyOf(enrichers);
    }


    @Override
    public R toResource(T entity) {
        R resource = createResource(entity);
        enrichers.forEach(e -> e.enrich(resource));

        return resource;
    }

    abstract R createResource(T entity);
}
