package com.example.btlpokedex.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
@Entity(tableName = "article")
public class Article {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String link;

    @Element(name = "pubDate")
    private String pubDate;

    @Ignore
    @Element(name = "thumbnail", required = false)
    private Thumbnail thumbnail;

    private String image;

    public Article() {
    }

    public Article(String title, String link, String pubDate, String image) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.image = image;
    }

    public Article(int id, String title, String link, String pubDate, String image) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}