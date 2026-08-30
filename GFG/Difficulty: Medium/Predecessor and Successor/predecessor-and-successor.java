/*
class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/

class Solution {
    
    private void inorderTraversal(Node root, int val, ArrayList<Node> res) {
        if(root == null) {
            return;
        }    
        
        inorderTraversal(root.left, val, res);
        
        if(root.data < val) {
            res.set(0, root);
        }
        if(res.get(1) == null && root.data > val) {
            res.set(1, root);
        }
        
        inorderTraversal(root.right, val, res);
    }
    
    public ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> res = new ArrayList<>();
        res.add(null);
        res.add(null);
        
        inorderTraversal(root, key, res);
        
        return res;
    }
}