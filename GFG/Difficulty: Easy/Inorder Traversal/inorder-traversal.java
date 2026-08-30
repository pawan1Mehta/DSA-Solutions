/*
class Node {
    int data;
    Node left, right;
    Node(int item){
        data = item;
        left = right = null;
    }
}
*/
class Solution {
    public ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        
        Node currNode = root;
        
        while(currNode != null) {
            if(currNode.left == null) {
                res.add(currNode.data);
                currNode = currNode.right;
            } else {
                Node prevNode = currNode.left;
                while(prevNode.right != null && prevNode.right != currNode) {
                    prevNode = prevNode.right;
                }
                
                if(prevNode.right == null) {
                    prevNode.right = currNode;
                    currNode = currNode.left;
                } else {
                    prevNode.right = null;
                    res.add(currNode.data);
                    currNode = currNode.right;
                }
            }
        }
        
        return res;
    }
}