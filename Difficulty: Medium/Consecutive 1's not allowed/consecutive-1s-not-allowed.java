class Solution {
    
    int[][] memo;
    
    int countStringsUtil(int pos, int prevBit, int n) {
        if(pos == n) {
            return 1;
        }
        
        if(memo[pos][prevBit] != -1) {
            return memo[pos][prevBit]; 
        }
        
        if(prevBit == 0) {
            int opt1 = countStringsUtil(pos + 1, 1, n);
            int opt2 = countStringsUtil(pos + 1, prevBit, n);
            return memo[pos][prevBit] = opt1 + opt2;
        } else {
            int opt1 = countStringsUtil(pos + 1, 0, n);
            return memo[pos][prevBit] = opt1;
        }
    }
    
    int countStrings(int n) {
        memo = new int[n + 1][2];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return countStringsUtil(0, 0, n);
    }
}