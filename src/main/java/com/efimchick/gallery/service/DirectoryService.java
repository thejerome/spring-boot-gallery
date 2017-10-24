package com.efimchick.gallery.service;

import com.efimchick.gallery.domain.LocalDirectory;
import com.efimchick.gallery.domain.LocalImage;
import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.halresource.ImageResource;
import com.efimchick.gallery.halresource.assembler.DirectoryResourceAssembler;
import com.efimchick.gallery.halresource.assembler.ImageResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.efimchick.gallery.domain.Utils.unescapePath;

/**
 * Created by Evgenii_Efimchik on 17-Oct-17.
 */

@Service
public class DirectoryService {

    @Value("${root}")
    private String root;
    private LocalDirectory rootDirectory;
    private final DirectoryResourceAssembler directoryResourceAssemblerWithSelfLinkAndEmbeddedImages;
    private final ImageResourceAssembler imageResourceAssemblerEnrichingSelfLink;


    @PostConstruct
    public void checkRoot() {
        Optional<LocalDirectory> rootDirOpt = LocalDirectory.of(Paths.get(root));
        if (rootDirOpt.isPresent()){
            rootDirectory = rootDirOpt.get();
        } else {
            throw new IllegalStateException("Invalid root is set: " + root);
        }
    }

    @Autowired
    public DirectoryService(DirectoryResourceAssembler directoryResourceAssemblerWithSelfLinkAndEmbeddedImages,
                            ImageResourceAssembler imageResourceAssemblerEnrichingSelfLink) {
        this.directoryResourceAssemblerWithSelfLinkAndEmbeddedImages = directoryResourceAssemblerWithSelfLinkAndEmbeddedImages;
        this.imageResourceAssemblerEnrichingSelfLink = imageResourceAssemblerEnrichingSelfLink;
    }

    public Optional<DirectoryResource> findDirectoryById(String dirId) {
        Path path = unescapePath(dirId);
        return LocalDirectory.of(path).map(directoryResourceAssemblerWithSelfLinkAndEmbeddedImages::toResource);
    }

    public Optional<DirectoryResource> getRoot() {
        return Optional.ofNullable(directoryResourceAssemblerWithSelfLinkAndEmbeddedImages.toResource(rootDirectory));
    }

    public Optional<ImageResource> findImageInDirById(String dirId, String imgId) {
        Path path = unescapePath(dirId).resolve(imgId);
        return LocalImage.of(path).map(imageResourceAssemblerEnrichingSelfLink::toResource);

    }
}
