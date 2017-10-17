package com.efimchick.gallery.halresource.assembler;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Evgenii_Efimchik on 13-Oct-17.
 */
public interface ResourceEnricher<T, R extends ResourceSupport> {
    void enrich(T entity, R resource);

    default void enrich(R resource) {
        enrich(null, resource);
    }
}
