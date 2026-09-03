class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;

        int maxSemiRepeatingSubArraLen = 0;

        Map<Integer, Integer> freq = new HashMap<>();
        int apearMoreThanOnceCount = 0;
        int i = 0, j = 0;

        while(j < n) {
            freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            if(freq.get(nums[j]) == 2) {
                apearMoreThanOnceCount++;
            }

            while(apearMoreThanOnceCount > k) {
                freq.put(nums[i], freq.get(nums[i]) - 1);
                if(freq.get(nums[i]) == 1) {
                    apearMoreThanOnceCount--;
                }
                if(freq.get(nums[i]) == 0) {
                    freq.remove(nums[i]);
                }
                i++;
            }

            maxSemiRepeatingSubArraLen = Math.max(
                maxSemiRepeatingSubArraLen,
                j - i + 1
            );

            j++;
        }

        return maxSemiRepeatingSubArraLen;
    }
}