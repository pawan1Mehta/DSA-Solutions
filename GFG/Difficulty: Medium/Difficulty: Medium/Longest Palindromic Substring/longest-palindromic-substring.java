class Solution {
    public String getLongestPal(String inputStr) {
        StringBuilder str = new StringBuilder();
        
        str.append('@');
        
        for(char ch : inputStr.toCharArray()) {
            str.append('#').append(ch);
        }
        
        str.append('#');
        str.append('$');
        
        int n = str.length();
        
        int maxLen = 0;
        int centeredIndex = 0;
        
        int[] P = new int[n];
        int C = 0, R = 0;
        
        for(int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i;
            
            if(i < R) {
                P[i] = Math.min(P[mirror], R - i);
            }
            
            while(str.charAt(i + P[i] + 1) == str.charAt(i - P[i] - 1)) {
                P[i]++;
            }
            
            if(i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
            
            if(P[i] > maxLen) {
                maxLen = P[i];
                centeredIndex = i;
            }
        }
        
        int start = (centeredIndex - maxLen)/2;
        
        return inputStr.substring(start, start + maxLen);
    }
}