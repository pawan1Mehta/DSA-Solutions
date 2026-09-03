class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;

        int[] res = new int[n - k + 1];

        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, right = 0;
        int resIndx = 0;

        while(right < n && resIndx < res.length) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            if((right - left + 1) == k) {
                PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
                    public int compare(int[] a, int[] b) {
                        if(a[1] != b[1]) {
                            return Integer.compare(b[1], a[1]);
                        }
                        return Integer.compare(b[0], a[0]);
                    }
                });

                for(Map.Entry<Integer, Integer> data : freq.entrySet()) {
                    pq.add(new int[]{data.getKey(), data.getValue()});
                }

                for(int i = 0; i < x && !pq.isEmpty(); i++) {
                    int[] curr = pq.poll();
                    while(curr[1]-- > 0) {
                        res[resIndx] += curr[0];
                    }
                }

                resIndx++;

                freq.put(nums[left], freq.get(nums[left]) - 1);
                if(freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }

                left++;
            }

            right++;
        }

        return res;
    }
}