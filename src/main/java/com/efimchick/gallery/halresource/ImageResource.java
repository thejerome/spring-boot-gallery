package com.efimchick.gallery.halresource;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */
public class ImageResource extends ResourceSupport{

    public String id;

    public String name;
    public long size;
    public int width;
    public int height;


}
