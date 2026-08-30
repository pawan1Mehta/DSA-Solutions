class match {
    void computeLPSArray(String pat, int M, int lps[]) {
        int n = pat.length();
        
        lps[0] = 0;
        
        int len = 0;
        int index = 1;
        
        while(index < n) {
            if(pat.charAt(len) == pat.charAt(index)) {
                len++;
                lps[index] = len;
                index++;
            } else {
                if(len == 0) {
                    lps[index] = 0;
                    index++;
                } else {
                    len = lps[len - 1];
                }
            }
        }
    }

    boolean KMPSearch(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();
        
        int[] lps = new int[m];
        
        computeLPSArray(pat, m, lps);
        
        int i = 0, j = 0;
        
        while((n - i) >= (m - j)) {
            if(txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            
            if(j == m) {
                return true;
            }
            
            if(i < n && txt.charAt(i) != pat.charAt(j)) {
                if(j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }
        
        return false;
    }
}