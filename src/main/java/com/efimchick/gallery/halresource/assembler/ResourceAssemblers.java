package com.efimchick.gallery.halresource.assembler;

import com.efimchick.gallery.Directory;
import com.efimchick.gallery.Image;
import com.efimchick.gallery.controller.DirController;
import com.efimchick.gallery.controller.ImageController;
import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.halresource.ImageResource;
import com.google.common.collect.ImmutableList;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;

import static com.google.common.collect.Streams.stream;
import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Evgenii_Efimchik on 13-Oct-17.
 */
public class ResourceAssemblers {

    static final ResourceEnricher<Image, ImageResource> imageSelfLinkEnricher = (image, resource) -> resource.add(
            linkTo(ImageController.class).slash(resource.id).withSelfRel()
    );
    static final ResourceEnricher<Directory, DirectoryResource> directorySelfLinkEnricher = (dir, resource) -> resource.add(
            linkTo(DirController.class).slash(resource.id).withSelfRel()
    );
    static final ResourceEnricher<Directory, DirectoryResource> embeddedImageResourcesEnricher = new ResourceEnricher<Directory, DirectoryResource>() {
        @Override
        public void enrich(Directory directory, DirectoryResource resource) {
            ImageResourceAssembler imageResourceAssembler = imageResourceAssemblerEnrichingSelfLink();
            resource.embedded = wrapResources(imageResourceAssembler.toResources(directory.getImages()));
        }
    };

    private static <R extends ResourceSupport> Resources<EmbeddedWrapper> wrapResources(Iterable<R> resources) {
        EmbeddedWrappers wrappers = new EmbeddedWrappers(true);
        return new Resources<>(stream(resources).map(wrappers::wrap).collect(toList()));
    }

    private static <R extends ResourceSupport> Resources<EmbeddedWrapper> wrapResource(R resource) {
        EmbeddedWrappers wrappers = new EmbeddedWrappers(true);
        return new Resources<>(ImmutableList.of(wrappers.wrap(resource)));
    }

    public static ImageResourceAssembler imageResourceAssemblerEnrichingSelfLink() {
        return new ImageResourceAssembler(imageSelfLinkEnricher);
    }

    public static DirectoryResourceAssembler directoryResourceAssemblerEnrichingSelfLink() {
        return new DirectoryResourceAssembler(directorySelfLinkEnricher);
    }

    public static DirectoryResourceAssembler directoryResourceAssemblerWithSelfLinkAndEmbeddedImages() {
        return new DirectoryResourceAssembler(
                directorySelfLinkEnricher,
                embeddedImageResourcesEnricher
        );
    }
}
