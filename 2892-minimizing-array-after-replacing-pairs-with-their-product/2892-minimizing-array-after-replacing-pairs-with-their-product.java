class Solution {
    public int minArrayLength(int[] nums, int k) {
        int n = nums.length;

        Stack<Long> st = new Stack<>();

        for(int i = 0; i < n; i++) {
            if(st.isEmpty()) {
                st.push((long) nums[i]);
            } else {
                long prod = st.peek() * nums[i];
                long lastProd = nums[i];

                while(prod <= k) {
                    st.pop();

                    lastProd = prod;

                    if(!st.isEmpty()) {
                        prod = prod * st.peek();
                    } else {
                        break;
                    }
                }

                st.add(lastProd);
            }
        }

        int rem = (n - st.size());

        return n - rem;
    }
}