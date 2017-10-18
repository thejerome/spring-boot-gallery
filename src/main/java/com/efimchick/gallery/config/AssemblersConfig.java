package com.efimchick.gallery.config;

import com.efimchick.gallery.controller.DataController;
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
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.efimchick.gallery.halresource.assembler.Utils.wrapResources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Evgenii_Efimchik on 16-Oct-17.
 */
@Configuration
public class AssemblersConfig {

    @Bean
    public ResourceEnricher<Image, ImageResource> imageSelfLinkEnricher() {
        return (image, resource) -> resource.add(
                linkTo(ImageController.class).slash(resource.id).withSelfRel()
        );
    }

    @Bean
    public ResourceEnricher<Image, ImageResource> imageDataLinkEnricher() {
        return (image, resource) -> resource.add(
                linkTo(DataController.class).slash(resource.id).withRel("data")
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
        return (EmbeddedCollectionDirectoryResourceEnricher) (directory, resource) ->
                wrapResources(imageResourceAssembler.toResources(directory.getImages()));
    }

    @Bean
    public ResourceEnricher<Directory, DirectoryResource> embeddedSubDirsResourcesEnricher(DirectoryResourceAssembler directoryResourceAssemblerEnrichingSelfLink) {
        return (EmbeddedCollectionDirectoryResourceEnricher) (directory, resource) ->
                wrapResources(directoryResourceAssemblerEnrichingSelfLink.toResources(directory.getSubDirs()));
    }

    @Bean
    @Scope("prototype")
    public ImageResourceAssembler imageResourceAssemblerEnrichingSelfLink(
            ResourceEnricher<Image, ImageResource> imageSelfLinkEnricher,
            ResourceEnricher<Image, ImageResource> imageDataLinkEnricher) {
        return new ImageResourceAssembler(imageSelfLinkEnricher, imageDataLinkEnricher);
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
            ResourceEnricher<Directory, DirectoryResource> embeddedImageResourcesEnricher,
            ResourceEnricher<Directory, DirectoryResource> embeddedSubDirsResourcesEnricher) {
        return new DirectoryResourceAssembler(
                directorySelfLinkEnricher,
                embeddedImageResourcesEnricher,
                embeddedSubDirsResourcesEnricher
        );
    }

    private interface EmbeddedCollectionDirectoryResourceEnricher extends ResourceEnricher<Directory, DirectoryResource> {

        @Override
        default void enrich(Directory entity, DirectoryResource resource) {
            if (resource.embedded == null) {
                resource.embedded = getResources(entity, resource);
            } else {
                resource.embedded = new Resources<>(
                        Stream.concat(
                                resource.embedded.getContent().stream(),
                                getResources(entity, resource).getContent().stream()
                        ).collect(Collectors.toList())
                );
            }
        }

        Resources<EmbeddedWrapper> getResources(Directory entity, DirectoryResource resource);
    }
}
