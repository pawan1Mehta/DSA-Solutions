class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int MOD = 1_000_000_007;

        int l, r, k, v;
        for(int[] query : queries) {
            l = query[0]; r = query[1]; k = query[2]; v = query[3];
            for(int idx = l; idx <= r; idx += k) {
                long tempNum = nums[idx];
                tempNum = (tempNum * v) % MOD;
                nums[idx] = (int) tempNum;
            }
        }

        int xorVal = 0;
        for(int num : nums) {
            xorVal = xorVal ^ num;
        }

        return xorVal;
    }
}