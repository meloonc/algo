package org.melon.tree;

/**
 * 前缀树
 */
public class TrieTree {

    private Node root;

    public TrieTree() {
        root = new Node();
    }

    static class Node {
        int pass;
        int end;
        Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }

    /**
     * Adds a word to the tire tree.
     *
     * @param word the word to be added
     * @return void
     */
    public void add(String word) {
        if (word == null) {
            return;
        }
        Node cur = root;
        cur.pass++;
        char[] charArray = word.toCharArray();
        int path;
        for (char c : charArray) {
            path = c - 'a';
            if (cur.nexts[path] == null) {
                cur.nexts[path] = new Node();
            }
            cur = cur.nexts[path];
            cur.pass++;
        }
        cur.end++;
    }

    /**
     * Searches for a given word in the tire tree.
     *
     * @param word the word to search for
     * @return the number of occurrences of the word in the data structure
     */
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        Node cur = root;
        char[] charArray = word.toCharArray();
        int path;
        for (char c : charArray) {
            path = c - 'a';
            if (cur.nexts[path] == null) {
                return 0;
            }
            cur = cur.nexts[path];
        }
        return cur.end;
    }

    /**
     * Finds the number of words in the trie that start with the given prefix.
     *
     * @param prefix the prefix to search for
     * @return the number of words that start with the prefix
     */
    public int startWith(String prefix) {
        if (prefix == null) {
            return 0;
        }
        Node cur = root;
        char[] charArray = prefix.toCharArray();
        int path;
        for (char c : charArray) {
            path = c - 'a';
            if (cur.nexts[path] == null) {
                return 0;
            }
            cur = cur.nexts[path];
        }
        return cur.pass;
    }

    public void delete(String word) {
        if(search(word) !=0) {
            Node cur = root;
            cur.pass--;
            int path;
            char[] charArray = word.toCharArray();
            for (char c : charArray) {
                path = c - 'a';
                if(cur.nexts[path] != null) {
                    cur = cur.nexts[path];
                    cur.pass--;
                    cur.end--;
                    if(cur.pass == 0) {
                        cur.nexts[path] = null;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        trieTree.add("apple");
        trieTree.add("application");
        trieTree.add("apply");
        trieTree.add("apollo");
        trieTree.delete("apple");
        System.out.println(trieTree.search("apple"));
        System.out.println(trieTree.startWith("app"));}

}
