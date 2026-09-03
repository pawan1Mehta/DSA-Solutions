class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] count = new long[n + 1];

        for(int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n, i + r + 1);

            count[left] += stations[i];
            count[right] -= stations[i];
        }

        long low = Arrays.stream(stations).min().getAsInt();
        long high = Arrays.stream(stations).asLongStream().sum() + k;
        long res = 0;

        while(low <= high) {
            long mid = low + (high - low)/2;
            if(check(count, mid, r, k)) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return res;
    }

    private boolean check(long[] count, long val, int r, int k) {
        int n = count.length - 1;
        long[] diff = count.clone();
        long sum = 0;
        long remaining = k;

        for(int i = 0; i < n; i++) {
            sum += diff[i];
            if(sum < val) {
                long add = val - sum;
                if(remaining < add) {
                    return false;
                }
                remaining -= add;
                int end = Math.min(n, i + 2  * r + 1);
                diff[end] -= add;
                sum += add;
            }
        }

        return true;
    }
}