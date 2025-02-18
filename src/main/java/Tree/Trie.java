package Tree;

class TrieNode {
  public char val;
  public boolean isWord;
  public TrieNode[] children = new TrieNode[26];
  TrieNode(char c) {
    this.val = c;
  }
}

// prefix tree
public class Trie {
  private TrieNode root;
  public Trie() {
    root = new TrieNode(' ');
  }

  public void insert(String word) {
    TrieNode ws = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (ws.children[index(c)] == null) {
        ws.children[index(c)] = new TrieNode(c);
      }
      ws = ws.children[index(c)];
    }
    ws.isWord = true;
  }

  public boolean search(String word) {
    TrieNode ws = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (ws.children[index(c)] == null) {
        return false;
      }
      ws = ws.children[index(c)];
    }
    return ws.isWord;
  }

  public boolean startWith(String prefix) {
    TrieNode ws = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (ws.children[index(c)] == null) {
        return false;
      }
      ws = ws.children[index(c)];
    }
    return true;
  }

  public int index(char c) {
    return c - 'a';
  }
}
