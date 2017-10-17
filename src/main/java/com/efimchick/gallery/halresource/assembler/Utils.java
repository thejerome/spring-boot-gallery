package com.efimchick.gallery.halresource.assembler;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;

import static com.google.common.collect.Streams.stream;
import static java.util.stream.Collectors.toList;

/**
 * Created by Evgenii_Efimchik on 17-Oct-17.
 */
public class Utils {
    public static <R extends ResourceSupport> Resources<EmbeddedWrapper> wrapResources(Iterable<R> resources) {
        EmbeddedWrappers wrappers = new EmbeddedWrappers(true);
        return new Resources<>(stream(resources).map(wrappers::wrap).collect(toList()));
    }
}
