class Solution {
    private static int MAX_NUM_LIMIT = 5000;
    private static int MOD = 1_000_000_007;

    int[][] memo;

    private int countArraysUtil(int i, int num, int[] digitSum) {
        if(i == digitSum.length) {
            return 1;
        }
        if(num > 5000) {
            return 0;
        }

        if(memo[i][num] != -1) {
            return memo[i][num]; 
        }

        int total = 0;

        if(digitSum(num) == digitSum[i]) {
            total += countArraysUtil(i + 1, num, digitSum);
        }

        total += countArraysUtil(i, num + 1, digitSum);

        return memo[i][num] = total % MOD;
    }

    private int digitSum(int num) {
        int sum = 0;
        
        while(num > 0) {
            int digit = num%10;
            sum += digit;
            num = num/10;
        }

        return sum;
    }

    public int countArrays(int[] digitSum) {
        int n = digitSum.length;

        memo = new int[n][MAX_NUM_LIMIT + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return countArraysUtil(0, 0, digitSum);
    }
}