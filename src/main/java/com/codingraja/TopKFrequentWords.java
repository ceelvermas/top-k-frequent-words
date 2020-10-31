package com.codingraja;

/**
 * Created By CL Verma on 10/31/20
 */
import com.codingraja.ds.Trie;
import com.codingraja.ds.TrieImpl;

import java.util.*;

import static com.codingraja.ds.Trie.TrieEntry;

public class TopKFrequentWords {

    private int maxSize;

    private Trie trie = new TrieImpl();
    private PriorityQueue<TrieEntry> maxHeap;

    public TopKFrequentWords(int k) {
        this.maxSize = k;
        this.maxHeap = new PriorityQueue<>(k, Comparator.comparingInt(TrieEntry::getFrequency));
    }

    public void add(String word) {
        this.trie.insert(word);
    }

    public List<TopK> getWords() {

        for (TrieEntry trieEntry : this.trie.getAll()) {
            if (this.maxHeap.size() < this.maxSize) {
                this.maxHeap.add(trieEntry);
            } else if (this.maxHeap.peek().getFrequency() < trieEntry.getFrequency()) {
                this.maxHeap.remove();
                this.maxHeap.add(trieEntry);
            }
        }
        List<TopK> result = new ArrayList();
        for (TrieEntry entry : this.maxHeap) {
            result.add(new TopK(entry));
        }
        Collections.sort(result, (o1, o2) -> o2.frequency - o1.frequency);
        return result;
    }

    private class TopK {
        private String word;
        private int frequency;

        public TopK(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        public TopK(TrieEntry entry) {
            this(entry.getWord(), entry.getFrequency());
        }

        @Override
        public String toString() {
            return String.format("[Word=%s, Frequency=%s]", word, frequency);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TopK topK = (TopK) o;
            return frequency == topK.frequency &&
                    Objects.equals(word, topK.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word, frequency);
        }
    }
}
