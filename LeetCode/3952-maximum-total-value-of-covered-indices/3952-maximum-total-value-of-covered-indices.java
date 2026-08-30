class Solution {

    private long[][] memo;

    private long maxTotalUtil(int i, int incomingToken, int[] nums, StringBuilder s) {
        if(i < 0) {
            return 0;
        }

        if(memo[i][incomingToken] != -1) {
            return memo[i][incomingToken];
        }

        long best = 0;

        if(incomingToken == 1) {
            best = nums[i] + maxTotalUtil(i - 1, 0, nums, s);
            if(i > 0 && s.charAt(i) == '1') {
                best = Math.max(best, nums[i] + maxTotalUtil(i - 1, 1, nums, s));
            }
        } else if(s.charAt(i) == '0') {
            best = maxTotalUtil(i - 1, 0, nums, s);
        } else {
            best = nums[i] + maxTotalUtil(i - 1, 0, nums, s);

            if(i > 0 ) {
                best = Math.max(best, maxTotalUtil(i - 1, 1, nums, s));
            }
        }

        return memo[i][incomingToken] = best;
    }

    public long maxTotal(int[] nums, String s) {
        int n = nums.length;

        if(n == 1) {
            return s.charAt(0) == '1' ? nums[0] : 0;
        }

        memo = new long[n][2];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return maxTotalUtil(n - 1, 0, nums, new StringBuilder(s));
    }
}