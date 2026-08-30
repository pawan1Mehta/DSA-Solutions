class Solution {
    public String minWindow(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        int minSubstringLen = Integer.MAX_VALUE;
        int statIndex = -1;
        
        for(int i = 0; i < n; i++) {
            int substringLen = 0;
            int j = i, k = 0;

            while(j < n && k < m) {
                if(s1.charAt(j) == s2.charAt(k)) {
                    k++;
                    
                }
                
                substringLen++;
                
                j++;
            }
            
            if(k == m && minSubstringLen > substringLen) {
                minSubstringLen = substringLen;
                statIndex = i;
            }
        } 
        
        if(minSubstringLen == Integer.MAX_VALUE) {
            return "";
        }
        
        return s1.substring(statIndex, statIndex + minSubstringLen); 
    }
}
