package com.codingraja;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By CL Verma on 10/31/20
 */
public class ReadWebPage {

    private static TopKFrequentWords topKFrequentWords = new TopKFrequentWords(10);

    /*public static void main(String[] args) {
        String url = "https://www.314e.com";

        System.out.println(extractLinksWithLevel(url, 3));

        System.out.println(topKFrequentWords.getWords());
    }*/


    public TopKFrequentWords findTopKFrequentWords(String url, int level) {
        extractLinksWithLevel(url, level);
        return topKFrequentWords;
    }

    /**
     * Read HTML Document from URL
     * @param url
     * @return
     */
    private Elements readWebPage(String url) {
        Elements elements = null;
        try {
            if (url.startsWith("http")) {
                Connection connection = Jsoup.connect(url);
                connection.timeout(5000);
                elements = connection.get().body().getAllElements();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return elements;
    }

    /**
     * Adding HTML contents in Trie Data Structure
     * @param topKFrequentWords
     * @param element
     */
    private void addPageContent(TopKFrequentWords topKFrequentWords, Element element) {
        String s = element.ownText();
        if (!s.isEmpty()) {
            String[] words = s.split(" ");
            for (String w : words) {
                topKFrequentWords.add(w);
            }
        }
    }

    /**
     * Extract Hyper Links from web page with level
     * @param baseUrl
     * @param level
     * @return
     */
    private Set<String> extractLinksWithLevel(String baseUrl, int level) {
        Set<String> result = new HashSet<>();
        result.add(baseUrl);

        if (level == 0)
            return result;

        Set<String> levelResult = result;
        for (int i = 1; i <= level; i++) {
            Set<String> temps = new HashSet<>();
            for (String url : levelResult) {
                temps.addAll(extractLinks(readWebPage(url)));
            }
            result.addAll(temps);
            levelResult = temps;
        }
        return result;

    }

    /**
     * Extracts Hyper Links from web page body
     * @param elements
     * @return
     */
    private Set<String> extractLinks(Elements elements) {
        Set<String> links = new HashSet<>();
        if (elements == null)
            return links;

        for (Element e: elements) {
            if (e.hasAttr("href")) {
                links.add(e.attr("href"));
            }
            addPageContent(topKFrequentWords, e);
        }

        return links;
    }
}
