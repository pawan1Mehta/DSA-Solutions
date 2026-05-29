class TrieNode {
    int index;
    int len;
    boolean isEnd;
    TrieNode[] children;

    public TrieNode(int index, int len) {
        this.index = index;
        this.len = len;
        this.isEnd = false;
        children = new TrieNode[26];
    }
}

class Trie {

    TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode(-1, -1);
    }

    void insert(String word, int index) {
        int n = word.length();
        TrieNode currNode = rootNode;

        for(char ch : word.toCharArray()) {

            if(currNode.children[(ch - 'a')] == null) {
                currNode.children[(ch - 'a')] = new TrieNode(index, n);
            }

            if(currNode.children[(ch - 'a')].len > n) {
                currNode.children[(ch - 'a')].len = n;
                currNode.children[(ch - 'a')].index = index;
            } else if(currNode.children[(ch - 'a')].len == n && 
                        currNode.children[(ch - 'a')].index > index) {
                currNode.children[(ch - 'a')].index = index;
            }

            currNode = currNode.children[(ch - 'a')];
        }
        
        currNode.isEnd = true;
    }

    public int search(String word) {
        TrieNode currNode = rootNode;
        int res = -1;
        for(char ch : word.toCharArray()) {
            if(currNode.children[(ch - 'a')] == null) {
                return currNode.index;
            }
            currNode = currNode.children[(ch - 'a')];
            res = currNode.index;
        }
        
        return res;
    }
}

class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int n = wordsContainer.length;
        int m = wordsQuery.length;

        Trie trie = new Trie();
        int idx = 0;

        for(int i = 0; i < n; i++) {
            trie.insert(new StringBuilder(wordsContainer[i]).reverse().toString(), i);

            if(wordsContainer[idx].length() > wordsContainer[i].length()) {
                idx = i;
            }
        }

        int[] res = new int[m];

        for(int i = 0; i < m; i++) {
            int index = trie.search(new StringBuilder(wordsQuery[i]).reverse().toString());
            res[i] = (index == - 1 ? idx : index);
        }

        return res;
    }
}