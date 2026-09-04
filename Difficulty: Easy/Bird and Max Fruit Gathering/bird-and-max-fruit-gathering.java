class Solution {
    public int maxFruits(ArrayList<Integer> arr, int m) {
        int n = arr.size();
        
        int maxTotalFruits = 0;
        
        int i = 0, j = 0;
        int currFruits = 0;
        
        while(j < m - 1) {
            currFruits += arr.get(j);
            j++;
        }
        
        while(j < n && i < n) {
            currFruits += arr.get(j);
            maxTotalFruits = Math.max(maxTotalFruits, currFruits);
            currFruits -= arr.get(i);
            
            i++;
            j = (j + 1)%n;
        }
        
        return maxTotalFruits;
    }
}