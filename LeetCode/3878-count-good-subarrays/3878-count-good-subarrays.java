class Solution {
    public long countGoodSubarrays(int[] nums) {
        int n = nums.length;

        int[] prevOne = new int[32];
        int[] nextOne = new int[32];
        Arrays.fill(prevOne, -1);
        Arrays.fill(nextOne, n);

        int[] L = new int[n];
        int[] R = new int[n];
        Arrays.fill(R, n - 1);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 31; j++) {
                if((nums[i] & (1 << j)) == 0) {
                    L[i] = Math.max(L[i], prevOne[j] + 1);
                } else {
                    prevOne[j] = i;
                }
            }
        }

        for(int i = n-1; i >= 0; i--) {
            for(int j = 0; j < 31; j++) {
                if((nums[i] & (1 << j)) == 0) {
                    R[i] = Math.min(R[i], nextOne[j] - 1);
                } else {
                    nextOne[j] = i;
                }
            }
        }

        Map<Integer, Integer> idx = new HashMap<>();
        long ans = 0;

        for(int i = 0; i < n; i++) {
            int l = L[i];
            int r = R[i];

            if(idx.containsKey(nums[i])) {
                l = Math.max(l, idx.get(nums[i]) + 1);
            }
            idx.put(nums[i], i);

            ans += 1L * (i - l + 1) * (r - i + 1);
        }

        return ans;
    }
}