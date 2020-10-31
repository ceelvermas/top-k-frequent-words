package com.codingraja;

import org.junit.Test;

/**
 * Created By CL Verma on 10/31/20
 */
public class TopKFrequentWordsTest {

    private static final String BASE_URL = "https://www.314e.com";
    private static final int LEVEL = 3;

    @Test
    public void addTest() {
        ReadWebPage webPage = new ReadWebPage();
        System.out.println(webPage.findTopKFrequentWords(BASE_URL, LEVEL).getWords());
    }
}
