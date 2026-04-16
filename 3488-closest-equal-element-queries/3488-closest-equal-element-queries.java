class Solution {

    private int binarySearch(List<Integer> nums, int idx) {
        int n = nums.size();

        int l = 0, r = n - 1;

        while(l <= r) {
            int mid = (l + r)/2;

            if(nums.get(mid) == idx) {
                return mid;
            } else if(nums.get(mid) > idx) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;

        Map<Integer, List<Integer>> numIdx = new HashMap<>();

        for(int i = 0; i < n; i++) {
            numIdx.putIfAbsent(nums[i], new ArrayList<>());
            numIdx.get(nums[i]).add(i);
        }

        ArrayList<Integer> minDist = new ArrayList<>();

        for(int j = 0; j < m; j++) {
            int kIdx = queries[j];
            int targetNum = nums[kIdx];
            List<Integer> list = numIdx.get(targetNum);

            int idx = binarySearch(list, kIdx);

            int dist = Integer.MAX_VALUE;

            if(idx + 1 < list.size()) {
                dist = Math.min(dist, list.get(idx + 1) - kIdx);
                dist = Math.min(dist, n - list.get(list.size() - 1) +  kIdx);
            }
            if(idx > 0) {
                dist = Math.min(dist, kIdx - list.get(idx - 1));
                dist = Math.min(dist, n - kIdx  + list.get(0));
            }

            minDist.add(dist == Integer.MAX_VALUE ? -1 : dist);
        }

        return minDist;
    }
}