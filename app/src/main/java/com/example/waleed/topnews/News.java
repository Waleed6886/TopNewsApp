package com.example.waleed.topnews;

import java.net.URL;

/**
 * Created by Waleed on 10/05/17.
 */

public class News {
    private String title;
    private String sectionName;
    private String publicationDate;
    private String url;

    public News(String title, String sectionName, String publicationDate, String url) {
        this.title = title;
        this.sectionName = sectionName;
        this.publicationDate = publicationDate;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getUrl() {
        return url;
    }
}