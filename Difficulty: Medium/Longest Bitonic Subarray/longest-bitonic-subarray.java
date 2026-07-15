class Solution {
    public int bitonic(int[] arr) {
        int n = arr.length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        
        for(int i = 1; i < n; i++) {
            if(arr[i] >= arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i] >= arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        
        int maxLen = 0;
        
        for(int i = 0; i < n; i++) {
            if((i > 0 && i < n - 1) 
                && (arr[i - 1] <= arr[i] && arr[i] >= arr[i + 1])) {
                int leftLen = left[i - 1];
                int rightLen = right[i + 1];
                
                maxLen = Math.max(maxLen, leftLen + rightLen + 1);
            }
            
            maxLen = Math.max(maxLen, left[i]);
            maxLen = Math.max(maxLen, right[i]);
        }
        
        return maxLen;
    }
}