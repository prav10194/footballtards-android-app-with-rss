package com.example.footballtardsapp;

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.example.*;
 
/**
 * Class reads RSS data.
 * @author ITCuties
 */
public class RssReader {
    // Our class has an attribute which represents RSS Feed URL
    private String rssUrl;
    /**
     * We set this URL with the constructor
     */
    public RssReader(String rssUrl) {
        this.rssUrl = rssUrl;
    }
    /**
     * Get RSS items. This method will be called to get the parsing process result.
     * @return
     */
    public List<RssItem> getItems() throws Exception {
        // At first we need to get an SAX Parser Factory object
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // Using factory we create a new SAX Parser instance
        SAXParser saxParser = factory.newSAXParser();
        // We need the SAX parser handler object
        RssHandler handler = new RssHandler();
        // We call the method parsing our RSS Feed
        saxParser.parse(rssUrl, handler);
        // The result of the parsing process is being stored in the handler object
        return handler.getItems();
    }
}
//- See more at: http://www.itcuties.com/android/how-to-write-android-rss-parser/#sthash.IkWuq76s.dpuf