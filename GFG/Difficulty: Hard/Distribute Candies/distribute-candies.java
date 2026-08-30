/*
class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    private int ans;
    
    private int distCandyUtil(Node root) {
        if(root == null) {
            return 0;
        }
        
        int l = distCandyUtil(root.left);
        int r = distCandyUtil(root.right);
        
        ans += Math.abs(l) + Math.abs(r);
        
        return root.data + l + r - 1;
    }
    
    public int distCandy(Node root) {
        ans = 0;
        
        distCandyUtil(root);
        
        return ans;
    }
}