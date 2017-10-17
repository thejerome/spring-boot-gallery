package com.efimchick.gallery.halresource.assembler;

import com.efimchick.gallery.Directory;
import com.efimchick.gallery.halresource.DirectoryResource;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */
public class DirectoryResourceAssembler extends AbstractEnrichingResourceAssembler<Directory, DirectoryResource> {

    public DirectoryResourceAssembler(ResourceEnricher<Directory, DirectoryResource>... enrichers) {
        super(Directory.class, DirectoryResource.class, enrichers);
    }

    @Override
    public DirectoryResource createResource(Directory directory) {
        DirectoryResource resource = new DirectoryResource();

        resource.name = directory.getName();
        resource.fullName = directory.getFullName();
        resource.id = directory.getId();

        return resource;
    }
}
