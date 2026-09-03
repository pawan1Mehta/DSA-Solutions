class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if(n == 1) {
            return nums[0];
        }

        if(n == k) {
            int maxNum = 0;
            for(int num : nums) {
                maxNum = Math.max(maxNum, num);
            }
            return maxNum;
        }

        if(k == 1) {
            Map<Integer, Integer> freq = new HashMap<>();
            for(int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }

            int maxNum = -1;
            for(int num : nums) {
                if(freq.get(num) == 1) {
                    maxNum = Math.max(maxNum, num);
                }
            }
            return maxNum;
        }


        return largestIntegerUtil(nums);
    }

    private int largestIntegerUtil(int[] nums) {
        int n = nums.length;

        if(nums[0] == nums[n - 1]) {
            return -1;
        }

        Map<Integer, Integer> idx = new HashMap<>();
        idx.put(nums[0], 1);
        idx.put(nums[n - 1], 1);

        for(int i = 1; i < n - 1; i++) {
            if(nums[0] == nums[i]) {
                idx.put(nums[0], idx.get(nums[0]) + 1);
            }
            if(nums[n - 1] == nums[i]) {
                idx.put(nums[n - 1], idx.get(nums[n - 1]) + 1);
            }
        }

        if(idx.get(nums[0]) != 1 && idx.get(nums[n - 1]) != 1) {
            return -1;
        } else if(idx.get(nums[0]) != 1 && idx.get(nums[n - 1]) == 1) {
            return nums[n - 1];
        } else if(idx.get(nums[0]) == 1 && idx.get(nums[n - 1]) != 1) {
            return nums[0];
        }

        return Math.max(nums[0], nums[n - 1]);
    }
}