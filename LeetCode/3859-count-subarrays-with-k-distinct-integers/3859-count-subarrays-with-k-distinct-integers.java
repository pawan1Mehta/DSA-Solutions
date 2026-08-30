class Solution {
    public long countSubarrays(int[] nums, int k, int m) {
        int n = nums.length;

        long count = 0;

        Map<Integer, Integer> mp = new HashMap<>();

        int distinctNums = 0, validSubarrays = 0;
        int extraNums = 0;

        int left = 0, right = 0;
        while(right < n) {
            mp.put(nums[right], mp.getOrDefault(nums[right], 0) + 1);

            if(mp.get(nums[right]) == 1) {
                distinctNums++;
            }
            if(mp.get(nums[right]) == m) {
                validSubarrays++;
            }

            while(distinctNums > k) {
                if(mp.get(nums[left]) == m) {
                    validSubarrays--;
                }

                if(mp.get(nums[left]) == 1) {
                    mp.remove(nums[left]);
                    distinctNums--;
                } else {
                    mp.put(nums[left], mp.get(nums[left]) - 1);
                }

                left++;
                extraNums = 0;
            }

            if(distinctNums == k && validSubarrays == k) {
                while(mp.get(nums[left]) > m) {
                    mp.put(nums[left], mp.get(nums[left]) - 1);
                    left++;
                    extraNums++;
                }

                count += (extraNums + 1);
            }

            right++;
        }

        return count;
    }
}