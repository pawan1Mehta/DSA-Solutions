class Solution {

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }

    private int splits(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int num : nums) {
            if(num != Integer.MAX_VALUE) {
                list.add(num);
            }
        }

        int m = list.size();

        int[] left = new int[m];
        int[] right = new int[m];

        left[0] = list.get(0);
        for(int i = 1; i < m; i++) {
            left[i] = gcd(left[i - 1], list.get(i));
        }

        right[m - 1] = list.get(m - 1);
        for(int i = m - 2; i >= 0; i--) {
            right[i] = gcd(right[i + 1], list.get(i));
        }

        int splits = 0;

        for(int i = 0; i < m - 1; i++) {
            if(left[i] == right[i + 1]) {
                splits++;
            }
        }

        return splits;
    }

    public int maxValidSplits(int[] nums) {
        int n = nums.length;

        int maxSplits = 0;

        for(int i = 0; i < n; i++) {
            int tempNum = nums[i];
            nums[i] = Integer.MAX_VALUE;
            maxSplits = Math.max(maxSplits, splits(nums));
            nums[i] = tempNum;
        }

        maxSplits = Math.max(maxSplits, splits(nums));

        return maxSplits;
    }
}