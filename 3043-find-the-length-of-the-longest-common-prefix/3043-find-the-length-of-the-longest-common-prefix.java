class TrieNode {
    TrieNode[] child;
    int num;

    public TrieNode(int num) {
        this.child = new TrieNode[10];
        this.num = num;
    }
}

class Trie {
    TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode(0);
    }

    public void insert(ArrayList<Integer> digits) {
        TrieNode currNode = rootNode;

        for(int num : digits) {
            if(currNode.child[num] == null) {
                currNode.child[num] = new TrieNode(num);
            }
            currNode = currNode.child[num];
        }
    }

    public int prefixMatch(ArrayList<Integer> digits) {
        TrieNode currNode = rootNode;
        
        int count = 0;

        for(int num : digits) {
            if(currNode.child[num] == null) {
                return count;
            }
            currNode = currNode.child[num];
            count++;
        }

        return count;
    }
}

class Solution {
    
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie = new Trie();

        for(int num : arr1) {
            trie.insert(digits(num));
        } 

        int lcp = 0;
        for(int num : arr2) {
            lcp = Math.max(lcp, trie.prefixMatch(digits(num)));
        }

        return lcp;
    }

    private ArrayList<Integer> digits(int num) {
        ArrayList<Integer> dgs = new ArrayList<>();

        while(num > 0) {
            int d = num%10;
            dgs.add(d);
            num = num/10;
        }

        Collections.reverse(dgs);

        return dgs;
    }
}