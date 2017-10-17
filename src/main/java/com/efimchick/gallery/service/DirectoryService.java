package com.efimchick.gallery.service;

import com.efimchick.gallery.domain.LocalDirectory;
import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.halresource.assembler.DirectoryResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.efimchick.gallery.domain.Utils.decodeString;

/**
 * Created by Evgenii_Efimchik on 17-Oct-17.
 */

@Service
public class DirectoryService {

    @Value("${root}")
    private String root;
    private LocalDirectory rootDirectory;
    private final DirectoryResourceAssembler directoryResourceAssemblerWithSelfLinkAndEmbeddedImages;

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
    public DirectoryService(DirectoryResourceAssembler directoryResourceAssemblerWithSelfLinkAndEmbeddedImages
    ) {
        this.directoryResourceAssemblerWithSelfLinkAndEmbeddedImages = directoryResourceAssemblerWithSelfLinkAndEmbeddedImages;
    }

    public Optional<DirectoryResource> findById(String id) {
        Path path = Paths.get(decodeString(id));
        return LocalDirectory.of(path).map(directoryResourceAssemblerWithSelfLinkAndEmbeddedImages::toResource);
    }

    public Optional<DirectoryResource> getRoot() {
        return Optional.ofNullable(directoryResourceAssemblerWithSelfLinkAndEmbeddedImages.toResource(rootDirectory));
    }
}
