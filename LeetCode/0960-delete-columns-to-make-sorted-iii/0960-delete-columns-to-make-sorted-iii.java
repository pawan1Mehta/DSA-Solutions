class Solution {
    public int minDeletionSize(String[] strs) {
        if (strs == null || strs.length == 0) {
            return 0;
        }

        int W = strs[0].length();
        int[] dp = new int[W];
        Arrays.fill(dp, 1);
        
        for (int i = W - 2; i >= 0; --i) {
            search: for (int j = i + 1; j < W; ++j) {
                for (String row : strs) {
                    if (row.charAt(i) > row.charAt(j)) {
                        continue search;
                    }
                }
                dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
        }

        int kept = 0;
        for (int x : dp) {
            kept = Math.max(kept, x);
        }

        return W - kept;
    }
}