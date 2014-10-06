package com.example.footballtardsapp;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 *
 * @author ITCuties
 */


public class RssItem {
    // item title
    private String title;
    // item link
    private String link;
    
    private String content;
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
    

    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        return title;
    }
}
//- See more at: http://www.itcuties.com/android/how-to-write-android-rss-parser/#sthash.IkWuq76s.dpuf}