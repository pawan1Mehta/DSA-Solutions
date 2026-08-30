class Solution {
    static int lis(int arr[]) {
        int n = arr.length;
        
        int[] lsis = new int[n];
        Arrays.fill(lsis, 1);
        
        int maxVal = 1;
        
        for(int i = 1; i < n; i++) {
            
            int j = i - 1;
            while(j >= 0) {
                if(arr[j] < arr[i]) {
                    lsis[i] = Math.max(lsis[i], lsis[j] + 1);
                }
                j--;
            } 
            
            maxVal = Math.max(maxVal, lsis[i]);
        }
        
        return maxVal;
    }
}