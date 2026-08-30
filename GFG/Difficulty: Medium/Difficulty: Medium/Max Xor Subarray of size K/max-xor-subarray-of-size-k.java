class Solution {
    public int maxSubarrayXOR(int[] arr, int k) {
        int n = arr.length;
        
        int maxXorNum = 0;
        int xorNum = 0;
        
        for(int i = 0; i < k; i++) {
            xorNum = xorNum ^ arr[i];
        }
        
        maxXorNum = Math.max(maxXorNum, xorNum);
        
        for(int i = 0, j = k; j < n; j++, i++) {
            xorNum = xorNum ^ arr[i];
            xorNum = xorNum ^ arr[j];
            maxXorNum = Math.max(maxXorNum, xorNum);
        }
        
        return maxXorNum;
    }
}

/*

    1 -> 00000001
    
    2 -> 00000010
    
    5 -> 00000101
    
    8 -> 00001000
    
    
    2 ^ 5 ^ 8 = 00001101
    
    5 ^ 8 = 00001101
    
    1 - (1 ^ 2 ^ 5) = 2 ^ 5 ?
    
    
    
    


*/