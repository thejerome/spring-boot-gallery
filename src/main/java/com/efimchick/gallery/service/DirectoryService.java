package com.efimchick.gallery.service;

import com.efimchick.gallery.domain.LocalDirectory;
import com.efimchick.gallery.halresource.DirectoryResource;
import com.efimchick.gallery.halresource.assembler.DirectoryResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.efimchick.gallery.domain.Utils.decodeString;
/**
 * Created by Evgenii_Efimchik on 17-Oct-17.
 */

@Service
public class DirectoryService {

    private final String root;
    private final DirectoryResourceAssembler directoryResourceAssemblerWithSelfLinkAndEmbeddedImages;

    @Autowired
    public DirectoryService(
            String root,
            DirectoryResourceAssembler directoryResourceAssemblerWithSelfLinkAndEmbeddedImages
    ) {
        this.root = root;
        this.directoryResourceAssemblerWithSelfLinkAndEmbeddedImages = directoryResourceAssemblerWithSelfLinkAndEmbeddedImages;
    }

    public Optional<DirectoryResource> findById(String id) {
        Path path = Paths.get(decodeString(id));
        return LocalDirectory.of(path).map(directoryResourceAssemblerWithSelfLinkAndEmbeddedImages::toResource);
    }

    public Optional<DirectoryResource> getRoot() {
        return Optional.empty();
    }
}
