class Solution {
    public int minSwaps(int[] arr) {
        int n = arr.length;
        
        int k = 0;
        for(int num : arr) {
            if(num == 1) {
                k++;
            }
        }
        
        if(k == 0) {
            return -1;
        }
        
        int minSwap = Integer.MAX_VALUE;
        int zeros = 0, ones = 0;
        int i = 0, j = 0;
        
        while(j < n) {
            if(arr[j] == 0) {
                zeros++;
            } else {
                ones++;
            }
            
            if((j - i + 1) > k) {
                if(arr[i] == 0) {
                    zeros--;
                }
                i++;
            }
            
            if((j - i + 1) == k) {
                minSwap = Math.min(minSwap, zeros);
            }
                
            j++;
        }
        
        return minSwap == Integer.MAX_VALUE ? 0 : minSwap;
    }
}
