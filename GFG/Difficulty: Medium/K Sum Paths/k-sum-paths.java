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
    
    private int downwardPaths;
    
    private void countAllPathsUtil(Node root, int sum, int k, Map<Integer, Integer> sumMp) {
        if(root == null) {
            return;
        }    
        
        sum += root.data;
        
        downwardPaths += sumMp.getOrDefault(sum - k, 0);
        
        sumMp.put(sum, sumMp.getOrDefault(sum, 0) + 1);
        
        countAllPathsUtil(root.left, sum, k, sumMp);
        countAllPathsUtil(root.right, sum, k, sumMp);
        
        sumMp.put(sum, sumMp.get(sum) - 1);
        if(sumMp.get(sum) == 0) {
            sumMp.remove(sum);
        }
    }
    
    public int countAllPaths(Node root, int k) {
        downwardPaths = 0;
        
        Map<Integer, Integer> sumMp = new HashMap<>();
        sumMp.put(0, 1);
        
        countAllPathsUtil(root, 0, k, sumMp);
        
        return downwardPaths;
    }
}