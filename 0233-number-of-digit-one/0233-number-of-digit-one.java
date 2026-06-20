class Solution {

    private int[][][] dp;

    private int solve(int i, int count, int tight, int[] digits) {
        if(i == digits.length) {
            return count;
        }

        if(dp[i][count][tight] != -1) {
            return dp[i][count][tight];
        }

        int limit = 9;
        if(tight == 1) {
            limit = digits[i];
        }

        int res = 0;
        
        for(int num = 0; num <= limit; num++) {
            int newCount = count;
            int newTight = 0;
            
            if(num == 1) {
                newCount++;
            }

            if((tight == 1 && num == limit)) {
                newTight = 1;
            }

            res += solve(i + 1, newCount, newTight, digits);
        }

        return dp[i][count][tight] = res;
    }

    public int countDigitOne(int n) {
        int[] digits = digits(n);

        int m = digits.length;

        dp = new int[m + 1][m + 1][2];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(0, 0, 1, digits);
    }

    private int[] digits(int num) {
        ArrayList<Integer> digits = new ArrayList<>();
        
        while(num > 0) {
            int d = num%10;
            digits.add(d);
            num = num/10;
        }
        
        Collections.reverse(digits);
        
        return digits.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
    }
}