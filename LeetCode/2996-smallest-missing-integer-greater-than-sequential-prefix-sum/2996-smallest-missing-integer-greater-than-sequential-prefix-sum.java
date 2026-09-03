class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;

        Set<Integer> st = new HashSet<>();
        for(int num : nums) {
            st.add(num);
        }

        int longestSequentialPrefix = 1;
        int longestSequentialPrefixSum = nums[0];

        for(int i = 1; i < n; i++) {
            if(nums[i] == (nums[i - 1] + 1)) {
                longestSequentialPrefix ++;
                longestSequentialPrefixSum += nums[i];
            } else {
                break;
            }
        }

        for(int num = longestSequentialPrefixSum; num <= 10000; num++) {
            if(!st.contains(num)) {
                return num;
            }
        }

        return longestSequentialPrefixSum;
    }
}