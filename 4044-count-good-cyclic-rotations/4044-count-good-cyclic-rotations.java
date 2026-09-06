class Solution {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;

        long[] sums = new long[n];
        sums[0] = nums[0];
        for(int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        
        int cyclicRotations = 0;

        for(int i = 0; i <= n/2; i++) {
            long firstPart = i > 0 ? sums[i - 1] : 0;
            long sum1 = sums[((n/2) - 1) + i] - firstPart;
            long sum2 = sums[n - 1] - sum1;

            if(sum1 > sum2) {
                cyclicRotations++;
            }
        }

        rotateArray(nums, n/2 + 1);
        sums[0] = nums[0];
        for(int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        
        for(int i = 0; i < n/2 - 1; i++) {
            long firstPart = i > 0 ? sums[i - 1] : 0;
            long sum1 = sums[((n/2) - 1) + i] - firstPart;
            long sum2 = sums[n - 1] - sum1;

            if(sum1 > sum2) {
                cyclicRotations++;
            }
        }

        return cyclicRotations;
    }

    private void rotateArray(int[] nums, int idx) {
        int n = nums.length;

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = idx; i < n; i++) {
            list.add(nums[i]);
        }

        for(int i = 0; i < idx; i++) {
            list.add(nums[i]);
        }

        for(int i = 0; i < n; i++) {
            nums[i] = list.get(i);
        }
    }
}