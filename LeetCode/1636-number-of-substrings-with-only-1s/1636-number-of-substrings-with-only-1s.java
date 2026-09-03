class Solution {
    public int numSub(String s) {
        int MOD = 1_000_000_007;

        int n = s.length();

        int[] nums = new int[n];

        nums[0] = s.charAt(0) - '0';

        for(int i = 1; i < n; i++) {
            if(s.charAt(i) == '0') {
                nums[i] = 0;
            } else {
                nums[i] = (nums[i - 1] + 1) % MOD;
            }
        }

        int count = 0;

        for(int i = 0; i < n; i++) {
            count = (count + nums[i]) % MOD;
        }

        return count;
    }
}