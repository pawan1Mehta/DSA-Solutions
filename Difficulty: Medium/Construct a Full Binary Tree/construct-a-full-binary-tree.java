/* Structure of Binary Tree Node
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
} */

class Solution {
    
    private Node buildTree(
        int l, int r,
        Map<Integer, Integer> preIdxMap,
        Map<Integer, Integer> preMirrorIdxMap,
        int[] pre, int[] preMirror ) {
        
        if(l > r) {
            return null;
        }
        
        Node root = new Node(pre[l]);
        
        if(l == r) {
            return root;
        }
        
        int preMirrorIdx = preMirrorIdxMap.get(pre[l]);
        int preIndx = preIdxMap.get(preMirror[preMirrorIdx + 1]);
        
        root.left = buildTree(l + 1, preIndx - 1, preIdxMap, preMirrorIdxMap, pre, preMirror);
        root.right = buildTree(preIndx, r, preIdxMap, preMirrorIdxMap, pre, preMirror);
        
        return root;
    }
    
    public Node constructBinaryTree(int[] pre, int[] preMirror) {
        int n = pre.length;
        
        Map<Integer, Integer> preIdxMap = new HashMap<>();
        Map<Integer, Integer> preMirrorIdxMap = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            preIdxMap.put(pre[i], i);
            preMirrorIdxMap.put(preMirror[i], i);
        }
        
        return buildTree(0, n - 1, preIdxMap, preMirrorIdxMap, pre, preMirror);
    }
}

/*
            pre[] = [1, 2, 4, 5, 3, 6, 7], preMirror[] = [1, 3, 7, 6, 2, 5, 4]
            l = 0, r = 6
                                Node(1)
                        /                   \
            l = 1, r = 3                    l = 4 = r = 6
             Node(2)                                Node(3)
             /  \                               /               \
    l = 2 r = 2  l = 3 r = 3
    Node(4)         Node(5)






*/