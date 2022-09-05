package com.example.btlpokedex.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "thumbnail", strict = false)
public class Thumbnail {

    @Attribute(name = "url")
    private String url;

    public Thumbnail() {
    }

    public Thumbnail(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}