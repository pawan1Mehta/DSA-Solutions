class Solution {
    public String removeKdig(String s, int k) {
        int n = s.length();
        
        if(n == k) {
            return "0";
        }
        
        int[] digits = new int[n];
        for(int i = 0; i < n; i++) {
            digits[i] = s.charAt(i) - '0';
        }
        
        Stack<Integer> st = new Stack<>();
            
        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && st.peek() > digits[i] && k > 0) {
                st.pop();
                k--;
            }
            
            if(!st.isEmpty() || (st.isEmpty() && digits[i] > 0)) {
                st.push(digits[i]);
            }
        }
        
        while(!st.isEmpty() && k > 0) {
            st.pop();
            k--;
        }
        
        if(st.isEmpty()) {
            return "0";
        }
        
        StringBuilder numStr = new StringBuilder();
        while(!st.isEmpty()) {
            numStr.append(st.pop());
        }
        
        numStr.reverse();
        
        return numStr.toString();
    }
}