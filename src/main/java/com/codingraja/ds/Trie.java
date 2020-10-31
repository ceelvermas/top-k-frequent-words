package com.codingraja.ds;

import java.util.List;

/**
 * Created By CL Verma on 10/31/20
 */
public interface Trie {

    boolean contains(String word);
    void insert(String word);
    int frequency(String word);
    List<TrieEntry> getAll();

    class TrieEntry {
        private String word;
        private int frequency;

        public TrieEntry(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return String.format("TrieEntry [word=%s, frequency=%d]", word, frequency);
        }
    }
}
