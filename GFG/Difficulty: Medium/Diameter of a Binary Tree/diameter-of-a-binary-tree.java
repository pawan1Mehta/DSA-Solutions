/*
Definition for Node
class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = right = null;
    }
} */

class Solution {
    int res = 0;
    
    public int diameterUtil(Node root) {
        if(root == null) {
            return 0;
        }
        
        int left = diameterUtil(root.left);
        int right = diameterUtil(root.right);
        
        res = Math.max(res, left + right);
        
        return 1 + Math.max(left, right);
    }
    
    public int diameter(Node root) {
        diameterUtil(root);
        return res;
    }
}