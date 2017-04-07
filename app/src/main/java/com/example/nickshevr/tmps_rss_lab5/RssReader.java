package com.example.nickshevr.tmps_rss_lab5;

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import  com.example.nickshevr.tmps_rss_lab5.RssItem;

public class RssReader {
    private String rssUrl;
    
    public RssReader(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    public List<RssItem> getItems() throws Exception {
        // At first we need to get an SAX Parser Factory object
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // Using factory we create a new SAX Parser instance
        SAXParser saxParser = factory.newSAXParser();
        // We need the SAX parser handler object
        RssParseHandler handler = new RssParseHandler();
        // We call the method parsing our RSS Feed
        saxParser.parse(rssUrl, handler);
        // The result of the parsing process is being stored in the handler object
        return handler.getItems();
    }
}
