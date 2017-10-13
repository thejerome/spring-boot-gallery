package com.efimchick.gallery.halresource.assembler;

import com.efimchick.gallery.Directory;
import com.efimchick.gallery.Image;
import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.halresource.links.LinksEnricher;
import com.google.common.collect.ImmutableList;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */
public class DirectoryResourceAssembler extends AbstractEnrichingResourceAssembler<Directory, DirectoryResource> {

    public DirectoryResourceAssembler(LinksEnricher<DirectoryResource>... enrichers) {
        super(Directory.class, DirectoryResource.class, enrichers);
    }

    @Override
    public DirectoryResource createResource(Directory directory) {
        DirectoryResource resource = new DirectoryResource();

        resource.name = directory.getName();
        resource.id = directory.getId();

        return resource;
    }
}