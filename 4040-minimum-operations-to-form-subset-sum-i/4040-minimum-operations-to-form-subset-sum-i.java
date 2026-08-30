class Solution {
    public int minOperations(int[] nums, int sum) {
        int n = nums.length;

        int[] dp = new int[sum + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int num : nums) {
            Set<Integer> options = new HashSet<>();

            int[] toMake = new int[sum + 1];
            Arrays.fill(toMake, Integer.MAX_VALUE);

            int curr = num;
            int steps = 0;
            while(curr <= sum) {
                toMake[curr] = Math.min(toMake[curr], steps);
                options.add(curr);
                steps++;
                curr *= 2;
            }

            curr = num;
            steps = 0;
            while(curr > 0) {
                if(curr <= sum) {
                    toMake[curr] = Math.min(toMake[curr], steps);
                    options.add(curr);
                }
                steps++;
                curr /= 2;
            }

            int[] tempDP = dp.clone();
            for(int i = 0; i <= sum; i++) {
                if(dp[i] == Integer.MAX_VALUE) {
                    continue;
                }
                for(int j : options) {
                    if(i + j > sum) {
                        continue;
                    }

                    if(toMake[j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    
                    tempDP[i + j] = Math.min(tempDP[i + j], dp[i] + toMake[j]);
                }
            }

            dp = tempDP;
        }

        if(dp[sum] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[sum];
    }
}