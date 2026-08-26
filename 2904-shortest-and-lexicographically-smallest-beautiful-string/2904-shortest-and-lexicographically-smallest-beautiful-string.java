class Solution {
    private int lengthOfShortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        int minLen = Integer.MAX_VALUE;
        int i = 0, j = 0;

        while(j < n) {
            if(s.charAt(j) == '1') k--;
            
            while(k < 0) {
                if(s.charAt(i) == '1') k++;
                i++;
            }

            while(k == 0 && s.charAt(i) == '0') {
                i++;
            }
            
            if(k == 0) {
                minLen = Math.min(minLen, j - i + 1);
            }

            j++;
        }

        return minLen;
    }

    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        int len = lengthOfShortestBeautifulSubstring(s, k);
        if(len == Integer.MAX_VALUE) {
            return "";
        }

        String smStr = null;
        int i = 0, j = 0;

        while(j < n) {
            if(s.charAt(j) == '1') k--;

            while((j - i + 1) > len || k < 0) {
                if(s.charAt(i) == '1') k++;
                i++;
            }

            if(k == 0 && (j - i + 1) == len) {
                String tmpStr = s.substring(i, i + len);
                if(smStr == null || isLexicographicallySmaller(smStr, tmpStr)) {
                    smStr = tmpStr;
                }
            }

            j++;
        }

        return smStr;
    }

    private boolean isLexicographicallySmaller(String a, String b) {
        int n = a.length();
        
        for(int i = 0; i < n; i++) {
            if(b.charAt(i) < a.charAt(i)) {
                return true;
            }
            if(a.charAt(i) < b.charAt(i)) {
                return false;
            }
        }

        return false;
    }
}