class TrieNode {
    TrieNode child[] = new TrieNode[2];
    int cnt;

    TrieNode() {
        child[0] = child[1] = null;
        cnt = 0;
    }
}

class Solution {
    public void insertTrie(TrieNode root, int n) {
        for (int i = 31; i >= 0; i--) {
            boolean x = (n & (1 << i)) != 0;

            if (root.child[x ? 1 : 0] == null) {
                root.child[x ? 1 : 0] = new TrieNode();
            }

            root.child[x ? 1 : 0].cnt += 1;
            root = root.child[x ? 1 : 0];
        }
    }
    
    public int cntSmaller(TrieNode root, int n, int k) {
        int cntPairs = 0;

        for (int i = 31; i >= 0 && root != null; i--) {
            boolean x = (n & (1 << i)) != 0;
            boolean y = (k & (1 << i)) != 0;

            if (y) {
                if (root.child[x ? 1 : 0] != null) {
                    cntPairs += root.child[x ? 1 : 0].cnt;
                }

                root = root.child[x ? 0 : 1];
            } else {
                root = root.child[x ? 1 : 0];
            }
        }
        return cntPairs;
    }

    public int cntPairs(int[] arr, int k) {
        TrieNode root = new TrieNode();

        int cntPairs = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            cntPairs += cntSmaller(root, arr[i], k);
            insertTrie(root, arr[i]);
        }
        
        return cntPairs;
    }
}
