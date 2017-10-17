package com.efimchick.gallery.halresource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.Relation;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */
@Relation(value = "dir", collectionRelation = "dirs")
public class DirectoryResource extends ResourceSupport {
    public String name;
    public String fullName;
    public String id;

    @JsonUnwrapped
    public Resources<EmbeddedWrapper> embedded;

}
