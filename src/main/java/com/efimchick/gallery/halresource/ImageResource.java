package com.efimchick.gallery.halresource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@Relation(collectionRelation = "images")
public class ImageResource extends ResourceSupport{

    public String id;

    public String name;
    public String fullName;
    public long size;
    public int width;
    public int height;
    public String directoryId;


}
