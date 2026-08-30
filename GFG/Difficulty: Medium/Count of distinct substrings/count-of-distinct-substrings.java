class TrieNode {
    boolean isWord;
    TrieNode[] child;

    TrieNode() {
        isWord = false;
        child = new TrieNode[26];
        for (int i = 0; i < 26; i++)
            child[i] = null;
    }
}

class Solution {
    public static int countSubs(String s) {
        TrieNode head = new TrieNode();
        
        int count = 0;
    
        for (int i = 0; i < s.length(); i++) {
            TrieNode temp = head;
            for (int j = i; j < s.length(); j++) {
                int index = s.charAt(j) - 'a';
                
                if (temp.child[index] == null) {
                    temp.child[index] = new TrieNode();
                    temp.isWord = true;
                    count++;
                }
                
                temp = temp.child[index];
            }
        }
    
        return count;
    }
}