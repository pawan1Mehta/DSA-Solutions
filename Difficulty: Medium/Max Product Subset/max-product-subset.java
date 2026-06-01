class Solution {
    public int findMaxProduct(int[] arr) {
        int n = arr.length;
        
        if(n == 1) {
            return arr[0];
        }
        
        int negativeCount = 0, zerosCount = 0;
        int maxNegative = Integer.MIN_VALUE, maxNegativeIndx = -1;
        
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) {
                zerosCount++;
            } else if(arr[i] < 0) {
                negativeCount++;
                if(maxNegativeIndx == -1 || arr[i] > maxNegative) {
                    maxNegative = arr[i];
                    maxNegativeIndx = i;
                }
            }
        }
        
        if(zerosCount == n) return 0;
        if(negativeCount == 1 && zerosCount == n - 1) return 0;
        
        int MOD = 1_000_000_007;
        long res = 1;
        
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) {
                continue;
            }
            
            if(negativeCount%2 == 1 && maxNegativeIndx == i) {
                continue;
            }
            
            res = (res * arr[i]) % MOD;
        }
        
        return (int) res;
    }
}