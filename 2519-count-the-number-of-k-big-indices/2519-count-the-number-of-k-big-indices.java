class Solution {
    public int kBigIndices(int[] nums, int k) {
        int n = nums.length;

        PriorityQueue<Integer> leftQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightQ = new PriorityQueue<>(Collections.reverseOrder());
        
        boolean[] goodLeftIndex = new boolean[n];

        for(int i = 0; i < k; i++) {
            leftQ.add(nums[i]);
            rightQ.add(nums[n - i - 1]);
        }

        for(int i = k; i < n - k; i++) {
            if(nums[i] > leftQ.peek()) {
                goodLeftIndex[i] = true;
            } else {
                leftQ.poll();
                leftQ.add(nums[i]);
            }
        }

        int count = 0;

        for(int i = n - k - 1; i >= k; i--) {
            if(nums[i] > rightQ.peek() && goodLeftIndex[i]) {
                count++;
            } else {
                rightQ.poll();
                rightQ.add(nums[i]);
            }
        }

        return count;
    }
}