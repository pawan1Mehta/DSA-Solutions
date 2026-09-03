class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;

        Stack<Integer> st = new Stack<>();

        int firstMismatchIdx = Integer.MAX_VALUE;
        int lastMismatchIdx = Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && nums[st.peek()] > nums[i]) {
                firstMismatchIdx = Math.min(firstMismatchIdx, st.pop());
            }
            st.push(i);
        }

        st.clear();

        for(int i = n - 1; i >= 0; i--) {
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
}