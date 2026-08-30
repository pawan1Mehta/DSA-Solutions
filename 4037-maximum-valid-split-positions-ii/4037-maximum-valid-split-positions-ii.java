class Solution {

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }

    private int splits(int[] nums, int idx) {
        int n = nums.length;
        
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(i == idx) {
                continue;
            }
            list.add(nums[i]);
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

        int[] gcdPrefix = new int[n];
        gcdPrefix[0] = nums[0];
        for(int i = 1; i < n; i++) {
            gcdPrefix[i] = gcd(gcdPrefix[i - 1], nums[i]);
        }

        int ans = 0;

        for(int i = 1; i < n; i++) {
            if(gcdPrefix[i - 1] == gcdPrefix[i]) {
                continue;
            }
            ans = Math.max(ans, splits(nums, i));
        }

        ans = Math.max(ans, splits(nums, 0));
        ans = Math.max(ans, splits(nums, -1));

        return ans;
    }
}