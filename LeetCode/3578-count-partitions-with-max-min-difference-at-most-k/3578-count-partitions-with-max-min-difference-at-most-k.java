class Solution {
    public int countPartitions(int[] nums, int k) {
        int MOD = 1_000_000_007;

        int n = nums.length;

        int[] pMaxSum = new int[n];
        TreeMap<Integer, Integer> mp = new TreeMap<>();

        for(int r = 0, l = 0; r < n; r++) {
            mp.put(nums[r], mp.getOrDefault(nums[r], 0) + 1);

            while((mp.lastKey() - mp.firstKey()) > k) {
                mp.put(nums[l], mp.get(nums[l]) - 1);
                if(mp.get(nums[l]) == 0) {
                    mp.remove(nums[l]);
                }
                l++;
            }

            pMaxSum[r] = l;
        }

        int[] dp = new int[n + 1];
        int[] dpSum = new int[n + 1];

        dp[0] = 1;
        dpSum[0] = 1;

        for(int r = 0; r < n; r++) {
            int l = pMaxSum[r];
            int rangeDPSum = dpSum[r];
            if(l > 0) {
                rangeDPSum -= dpSum[l - 1];
            }

            rangeDPSum = rangeDPSum % MOD;

            if(rangeDPSum < 0) {
                rangeDPSum += MOD;
            }

            dp[r + 1] = rangeDPSum;

            dpSum[r + 1] = (dpSum[r] + dp[r + 1]) % MOD;
        }

        return dp[n];
    }
}