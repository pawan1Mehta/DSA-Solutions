class TrieNode {
    TrieNode[] child;
    char ch;
    boolean isEnd;

    public TrieNode(char ch) {
        this.child = new TrieNode[26];
        this.ch = ch;
        this.isEnd = false;
    }
}

class Trie {
    TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode('#');    
    }
    
    public void insert(String word) {
        TrieNode currNode = rootNode;
        
        for(char ch : word.toCharArray()) {
            if(currNode.child[(ch - 'a')] == null) {
                currNode.child[(ch - 'a')] = new TrieNode(ch);
            }
            currNode = currNode.child[(ch - 'a')];
        }

        currNode.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode currNode = rootNode;

        for(char ch : word.toCharArray()) {
            if(currNode.child[(ch - 'a')] == null) {
                return false;
            }
            currNode = currNode.child[(ch - 'a')];
        }

        return currNode.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode currNode = rootNode;

        for(char ch : prefix.toCharArray()) {
            if(currNode.child[(ch - 'a')] == null) {
                return false;
            }
            currNode = currNode.child[(ch - 'a')];
        }

        return true;
    }
}
