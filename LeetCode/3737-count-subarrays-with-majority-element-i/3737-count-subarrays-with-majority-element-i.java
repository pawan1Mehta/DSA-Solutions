class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int count = 0;

        for(int i = 0; i < n; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            for(int j = i; j < n; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
                int len = ((j - i) + 1)/2;
                if(freq.containsKey(target) && freq.get(target) > len) {
                    count++;
                }
            }
        }

        return count;
    }
}