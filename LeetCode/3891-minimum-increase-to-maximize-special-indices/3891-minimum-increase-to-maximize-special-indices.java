class Solution {
    private long[][] memo;

    private long[] minIncrease(int i, int[] nums, int n) {
        if(i >= (n - 1)) {
            return new long[]{Long.MAX_VALUE, 0};
        }

        if(memo[i][0] != -1) {
            return new long[]{memo[i][0], memo[i][1]};
        }

        long[] opt1 = minIncrease(i + 2, nums, n);
        long[] opt2 = minIncrease(i + 1, nums, n);

        long max = (long) Math.max(nums[i - 1], nums[i + 1]);
        if(opt1[0] != Long.MAX_VALUE) {
            if(nums[i] <= max + 1) {
                opt1[0] += (long) ((max + 1) - nums[i]);
            }
            opt1[1]++;
        } else {
            if(nums[i] <= max + 1) {
                opt1[0] = (long) (max + 1) - nums[i];
            } else {
                opt1[0] = 0;
            }
            opt1[1] = 1;
        }

        long[] res;
        
        if(opt2[0] == Long.MAX_VALUE) {
            res = opt1;
        } else {
            if(opt1[1] == opt2[1]) {
                if(opt1[0] < opt2[0]) {
                    res = opt1;
                } else {
                    res = opt2;
                }
            } else {
                if(opt1[1] < opt2[1]) {
                    res = opt2;
                } else {
                    res = opt1;
                } 
            }
        }

        memo[i][0] = res[0];
        memo[i][1] = res[1];

        return res;
    }

    public long minIncrease(int[] nums) {
        int n = nums.length;

        memo = new long[n][2];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        long[] res = minIncrease(1, nums, n);

        return res[0] == Long.MAX_VALUE ? 0 : res[0];
    }
}