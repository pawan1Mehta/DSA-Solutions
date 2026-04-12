class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> idx = new HashMap<>();
        
        idx.put(0, -1);

        int maxLen = 0;
        int ones = 0, zeros = 0;

        for(int i = 0; i < n; i++) {
            ones += (nums[i] == 1 ? 1 : 0);
            zeros += (nums[i] == 0 ? 1 : 0);

            int diff = ones - zeros;

            if(diff == 0) {
                maxLen = Math.max(maxLen, i + 1);
            }

            if(idx.containsKey(diff)) {
                maxLen = Math.max(maxLen, i - idx.get(diff));
            } else {
                idx.put(diff, i);
            }
        }

        return maxLen;
    }
}