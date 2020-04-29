package graph;

/*
Question:
Create a data structure containing lowercase words which can efficiently add words and
search for a wildcard expression for example f.o meaning f + any letter + 0

Solution:
Form a prefix tree from the added words. Search by traversing the prefix tree.

*/

public class WordDictionary {
  // for whatever reason 'prefix tree' is abbreviated as 'trie'
  // each trienode represents a prefix of a word and points to
  // letters which can be appended on it
  public class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public boolean isWord;
  }

  private TrieNode root = new TrieNode();

  public void addWord(String word) {
    TrieNode node = root;
    // create a branch in the prefix tree for this word
    for (char c : word.toCharArray()) {
      if (node.children[c-'a'] == null) {
        node.children[c - 'a'] = new TrieNode();
      }
      node = node.children[c - 'a'];
    }
    // mark the last node as a word
    node.isWord = true;
  }

  public boolean search(String word) {
    // search for the word in the trie
    return match(word.toCharArray(), 0, root);
  }

  private boolean match(char[] chs, int k, TrieNode node) {
    // finished traversal => return true if we landed on a word node
    if (k == chs.length) {
      return node.isWord;
    }
    // wildcard => dfs every child
    if (chs[k] == '.') {
      for (int i = 0; i < node.children.length; i++) {
        if (node.children[i] != null && match(chs, k+1, node.children[i])) {
          return true;
        }
      }
    }
    // otherwise dfs the next character
    else {
      return node.children[chs[k] - 'a'] != null && match(chs, k+1, node.children[chs[k] - 'a']);
    }
    return false;
  }

  // unit test
  public static void main(String[] args) {
    WordDictionary dict = new WordDictionary();
    String[] testWords = {"foo", "bar", "baz"};
    String[] testQueries = {"foo", "garply", "f.o", "..", "..z"};
    for (String word : testWords) {
      dict.addWord(word);
    }
    // expected true false true false true
    for (String query : testQueries) {
      System.out.println(dict.search(query));
    }
  }
}