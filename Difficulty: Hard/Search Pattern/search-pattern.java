class Solution {
    
    private int[] LPS(String str) {
        int n = str.length();
        
        int[] lps = new int[n];
        
        int lpsLen = 0;
        int i = 1;
        
        while(i < n) {
            if(str.charAt(i) == str.charAt(lpsLen)) {
                lps[i] = ++lpsLen;
                i++;
            } else {
                if(lpsLen == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    lpsLen = lps[lpsLen - 1];
                }
            }
        }
        
        return lps;
    }
    
    ArrayList<Integer> search(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();
        
        int[] lps = LPS(pat);
        
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        
        while((n - i) >= (m - j)) {
            if(txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            
            if(j == m) {
                res.add(i - j);
                j = lps[j - 1];
            } else if(i < n && txt.charAt(i) != pat.charAt(j)) {
                if(j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }
        
        return res;
    }
}