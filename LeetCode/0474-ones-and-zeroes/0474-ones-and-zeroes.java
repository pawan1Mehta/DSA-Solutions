class Solution {
    private int findMaxFormUtil(int index, int[][] nums, int m, int n, int[][][] memo) {
        if(m == 0 && n == 0 || index >= nums.length) {
            return 0;
        }

        if(memo[index][m][n] != -1) {
            return memo[index][m][n];
        }

        int opt1 = 0, opt2 = 0;

        if(canInclude(nums[index], m, n)) {
            opt1 = 1 + findMaxFormUtil(index + 1, nums, m - nums[index][0], n - nums[index][1], memo);
        }
        
        opt2 = findMaxFormUtil(index + 1, nums, m, n, memo);
        
        return memo[index][m][n] = Math.max(opt1, opt2);
    }

    private boolean canInclude(int[] nums, int m, int n) {
        if(nums[0] > m || nums[1] > n) {
            return false;
        }
        return true;
    }
    
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] nums = convertToNum(strs);

        int[][][] memo = new int[strs.length][m + 1][n + 1];

        for(int i = 0; i < strs.length; i++) {
            for(int j = 0; j < m + 1; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return findMaxFormUtil(0, nums, m, n, memo);
    }

    private int[][] convertToNum(String[] strs) {
        int[][] nums = new int[strs.length][2];

        for(int i = 0; i < nums.length; i++) {
            String str = strs[i];

            int zeros = 0;
            int ones = 0;
            for(char ch : str.toCharArray()) {
                if(ch == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }
            
            nums[i] = new int[]{zeros, ones};
        }

        return nums;
    }
}