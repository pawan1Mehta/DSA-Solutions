class Solution {
    private boolean isPowerOfTwo(int n) {
        if(n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0 ? true : false;
    }
    
    public String lexicographicallySmallest(String s, int k) {
        int n = s.length();
        
        if(isPowerOfTwo(n)) {
            k = k/2;
        } else {
            k = 2*k;
        }
        
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && k > 0 && st.peek() > s.charAt(i)) {
                st.pop();
                k--;
            }
            st.push(s.charAt(i));
        }
        
        while(k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }
        
        if(st.isEmpty()) {
            return "-1";
        }
        
        StringBuilder res = new StringBuilder();
        
        while(!st.isEmpty()) {
            res.append(st.pop());
        }
        
        return res.reverse().toString();
    }
}