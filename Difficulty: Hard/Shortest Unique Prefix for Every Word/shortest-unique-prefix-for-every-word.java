class TrieNode {
    public final static int ALPHABET_SIZE = 26;
    
    TrieNode[] child;
    int count;
    
    public TrieNode() {
        this.child = new TrieNode[ALPHABET_SIZE];
        this.count = 0;
    }
}

class Trie {
    TrieNode rootNode;
    
    public Trie() {
        rootNode = new TrieNode();
    }
    
    public void add(String str) {
        TrieNode currNode = rootNode;
        
        for(char ch : str.toCharArray()) {
            if(currNode.child[(ch - 'a')] == null) {
                currNode.child[(ch - 'a')] = new TrieNode();
            }
            currNode.child[(ch - 'a')].count++;
            currNode = currNode.child[(ch - 'a')];
        }
    }
    
    private void removeUtil(int i, TrieNode currNode, String str) {
        if(i == str.length()) {
            return;
        }
        
        removeUtil(i + 1, currNode.child[(str.charAt(i) - 'a')], str);
        
        currNode.child[(str.charAt(i) - 'a')].count--;
        
        if(currNode.child[(str.charAt(i) - 'a')].count == 0) {
            currNode.child[(str.charAt(i) - 'a')] = null;
        }
    }
    
    public void remove(String str) {
        removeUtil(0, rootNode, str);
    }
    
    public String findUniquePrefix(String str) {
        TrieNode currNode = rootNode;
        
        StringBuilder resStr = new StringBuilder();
        
        for(char ch : str.toCharArray()) {
            resStr.append(ch);
            
            if(currNode.child[(ch - 'a')] == null) {
                return resStr.toString();
            }
            
            currNode = currNode.child[(ch - 'a')];
        }
        
        return resStr.toString();
    }
}

class Solution {
    public ArrayList<String> findPrefixes(ArrayList<String> arr) {
        Trie trie = new Trie();
        
        for(String str : arr) {
            trie.add(str);
        }
        
        ArrayList<String> res = new ArrayList<>();
        
        for(String str : arr) {
            trie.remove(str);
            res.add(trie.findUniquePrefix(str));
            trie.add(str);
        }
        
        return res;
    }
}