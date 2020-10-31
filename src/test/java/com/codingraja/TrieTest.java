package com.codingraja;

import com.codingraja.ds.Trie;
import com.codingraja.ds.TrieImpl;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created By CL Verma on 10/31/20
 */
public class TrieTest {

    @Test
    public void addTest() {
        Trie trie = new TrieImpl();
        trie.insert("java");
        trie.insert("sql");
        assertEquals(true, trie.contains("java"));
        assertEquals(true, trie.contains("sql"));
    }

    @Test
    public void frequencyTest() {
        Trie trie = new TrieImpl();
        trie.insert("java");
        trie.insert("java");
        trie.insert("map");
        trie.insert("sql");
        trie.insert("solid");
        assertEquals(2, trie.frequency("java"));
        assertEquals(1, trie.frequency("map"));
        assertEquals(1, trie.frequency("sql"));
        assertEquals(0, trie.frequency("gof"));
        for (Trie.TrieEntry word : trie.getAll()) {
            System.out.println(word);
        }
    }
}
