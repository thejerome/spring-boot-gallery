package com.efimchick.gallery.config;

import com.efimchick.gallery.controller.DirController;
import com.efimchick.gallery.controller.ImageController;
import com.efimchick.gallery.domain.Directory;
import com.efimchick.gallery.domain.Image;
import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.halresource.assembler.DirectoryResourceAssembler;
import com.efimchick.gallery.halresource.assembler.ImageResourceAssembler;
import com.efimchick.gallery.halresource.assembler.ResourceEnricher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.efimchick.gallery.halresource.assembler.Utils.wrapResources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Evgenii_Efimchik on 16-Oct-17.
 */
@Configuration
public class AssemblersConfig {

    @Bean()
    public ResourceEnricher<Image, ImageResource> imageSelfLinkEnricher() {
        return (image, resource) -> resource.add(
                linkTo(ImageController.class).slash(resource.id).withSelfRel()
        );
    }

    @Bean
    public ResourceEnricher<Directory, DirectoryResource> directorySelfLinkEnricher() {
        return (dir, resource) -> resource.add(
                linkTo(DirController.class).slash(resource.id).withSelfRel()
        );
    }

    @Bean
    public ResourceEnricher<Directory, DirectoryResource> embeddedImageResourcesEnricher(ImageResourceAssembler imageResourceAssembler) {
        return (directory, resource) -> resource.embedded = wrapResources(imageResourceAssembler.toResources(directory.getImages()));
    }

    @Bean
    @Scope("prototype")
    public ImageResourceAssembler imageResourceAssemblerEnrichingSelfLink(
            ResourceEnricher<Image, ImageResource> imageSelfLinkEnricher
    ) {
        return new ImageResourceAssembler(imageSelfLinkEnricher);
    }

    @Bean
    @Scope("prototype")
    public DirectoryResourceAssembler directoryResourceAssemblerEnrichingSelfLink(
            ResourceEnricher<Directory, DirectoryResource> directorySelfLinkEnricher
    ) {
        return new DirectoryResourceAssembler(directorySelfLinkEnricher);
    }

    @Bean
    @Scope("prototype")
    public DirectoryResourceAssembler directoryResourceAssemblerWithSelfLinkAndEmbeddedImages(
            ResourceEnricher<Directory, DirectoryResource> directorySelfLinkEnricher,
            ResourceEnricher<Directory, DirectoryResource> embeddedImageResourcesEnricher
    ) {
        return new DirectoryResourceAssembler(
                directorySelfLinkEnricher,
                embeddedImageResourcesEnricher
        );
    }
}
