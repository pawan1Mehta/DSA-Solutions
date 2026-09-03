class Solution {
    
    private int[] firstSmallestElementInLeft(int[] arr) {
        int n = arr.length;

        Stack<Integer> st = new Stack<>();
        int[] index = new int[n];

        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            
            if(st.isEmpty()) {
                index[i] = -1;
            } else {
                index[i] = st.peek();
            }

            st.add(i);
        }

        return index;
    }

    private int[] firstSmallestElementInRight(int[] arr) {
        int n = arr.length;

        Stack<Integer> st = new Stack<>();
        int[] index = new int[n];

        for(int i = n - 1;  i >= 0; i--) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            
            if(st.isEmpty()) {
                index[i] = n;
            } else {
                index[i] = st.peek();
            }

            st.add(i);
        }

        return index;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int[] left = firstSmallestElementInLeft(heights);
        int[] right = firstSmallestElementInRight(heights);

        int largestRectangle = 0;

        for(int i = 0; i < n; i++) {
            int width = (right[i] - left[i]) - 1;
            int height = heights[i];
            largestRectangle = Math.max(largestRectangle, width * height);
        }

        return largestRectangle;
    }
}