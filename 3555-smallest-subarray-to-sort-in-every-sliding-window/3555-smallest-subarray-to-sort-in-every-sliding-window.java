class Solution {
    
    private int findUnsortedSubarray(int[] nums, int start, int end) {
        int n = nums.length;

        Stack<Integer> st = new Stack<>();

        int firstMismatchIdx = Integer.MAX_VALUE;
        int lastMismatchIdx = Integer.MIN_VALUE;
        
        for(int i = start; i <= end; i++) {
            while(!st.isEmpty() && nums[st.peek()] > nums[i]) {
                firstMismatchIdx = Math.min(firstMismatchIdx, st.pop());
            }
            st.push(i);
        }

        st.clear();

        for(int i = end; i >= start; i--) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) {
                lastMismatchIdx = Math.max(lastMismatchIdx, st.pop());
            }
            st.push(i);
        }

        if(firstMismatchIdx == Integer.MAX_VALUE && lastMismatchIdx == Integer.MIN_VALUE) {
            return 0;
        }

        return lastMismatchIdx - firstMismatchIdx + 1;
    }

    public int[] minSubarraySort(int[] nums, int k) {
        int n = nums.length;

        int[] res = new int[n - k + 1];
        int idx = 0;

        for(int i = k-1; i < n; i++) {
            res[idx++] = findUnsortedSubarray(nums, i - k + 1, i);
        }

        return res;
    }
}