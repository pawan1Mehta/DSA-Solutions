
class Node {
    int data;
    int index;
    Node left, right;
    
    Node(int data, int index) {
        this.data = data;
        this.index = index;
        this.left = this.right = null;
    }
    
    Node(Node node1, Node node2) {
        this.data = node1.data + node2.data;
        this.index = Math.min(node1.index, node2.index);
        this.left = node1;
        this.right = node2;
    }
}

class Solution {
    
    private void preOrder(Node root, ArrayList<String> ans, StringBuilder currStr) {
        if(root == null) {
            return;
        }
        
        if(root.left == null && root.right == null) {
            ans.add(currStr.toString());
            return;
        }
        
        currStr.append('0');
        preOrder(root.left, ans, currStr);
        currStr.deleteCharAt(currStr.length() - 1);
        
        currStr.append('1');
        preOrder(root.right, ans, currStr);
        currStr.deleteCharAt(currStr.length() - 1);
    }
    
    public ArrayList<String> huffmanCodes(String s, int f[]) {
        int n = f.length;
        
        ArrayList<String> ans = new ArrayList<>();
        
        if(n == 0) {
            return ans;
        }
        
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>(){
           public int compare(Node node1, Node node2) {
               if(node1.data != node2.data) {
                   return Integer.compare(node1.data, node2.data);
               }
               return Integer.compare(node1.index, node2.index);
           } 
        });
        
        for(int i = 0; i < n; i++) {
            Node node = new Node(f[i], i);
            minHeap.add(node);
        }
        
        if(minHeap.size() == 1) {
            ans.add("0");
            return ans;
        }
        
        while(minHeap.size() > 1) {
            Node left = minHeap.poll();
            Node right = minHeap.poll();
            
            Node internalNode = new Node(left, right);
            
            minHeap.add(internalNode);
        }
        
        StringBuilder currStr = new StringBuilder();
        
        preOrder(minHeap.poll(), ans, currStr);
        
        return ans;
    }
}