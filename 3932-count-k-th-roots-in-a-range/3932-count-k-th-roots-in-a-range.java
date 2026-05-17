class Solution {

    private long power(int base, int exp, int limit) {
        long res = 1;

        while(exp-- > 0) {
            res = res * base;
            if(res > limit) {
                return limit + 1;
            }
        }
        
        return res;
    }

    private int lowerBound(int l, int r, int k) {
        int low = 0, high = r;
        int ans = -1;

        while(low <= high) {
            int mid = low + (high - low)/2;

            long val = power(mid, k, r);

            if(val >= l) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private int upperBound(int l, int r, int k) {
        int low = 0, high = r;
        int ans = -1;

        while(low <= high) {
            int mid = low + (high - low)/2;

            long val = power(mid, k, r);

            if(val <= r) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    public int countKthRoots(int l, int r, int k) {
        int left = lowerBound(l, r, k);
        int right = upperBound(l, r, k);

        if(left == -1 || right == -1 || left > right) {
            return 0;
        }

        return right - left + 1;
    }
}