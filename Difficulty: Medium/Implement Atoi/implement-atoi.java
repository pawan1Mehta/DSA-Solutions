class Solution {
    public int myAtoi(String s) {
        int n = s.length();
        
        int idx = 0;
        
        // ignore whitespace
        while(idx < n && s.charAt(idx) == ' ') {
            idx++;
        }
        
        // check sign
        boolean isNegative = false;
        if(idx < n && s.charAt(idx) == '-') {
            isNegative = true;
            idx++;
        } else if(idx < n && s.charAt(idx) == '+') {
            idx++;
        }
        
        // read digits
        long num = 0;
        while(idx < n) {
            if(!isDigit(s.charAt(idx))) {
                break;
            }
            
            num = num * 10 + (s.charAt(idx) - '0');
            
            idx++;
        }
        
        if(num > 0 && isNegative) {
            num = -1 * num;
        }
            
        if(Integer.MAX_VALUE < num) {
            return Integer.MAX_VALUE;
        } else if(Integer.MIN_VALUE > num) {
            return Integer.MIN_VALUE;
        }
        
        return (int) num;
    }
    
    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}