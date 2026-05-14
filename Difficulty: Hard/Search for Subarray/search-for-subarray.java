class Solution {
    
    int[] LPS(int[] arr) {
        int n = arr.length;
        
        int[] lps = new int[n];
        
        lps[0] = 0;
        
        int idx = 1;
        int lpsLen = 0;
        
        while(idx < n) {
            if(arr[idx] == arr[lpsLen]) {
                lpsLen++;
                lps[idx] = lpsLen;
                idx++;
            } else {
                if(lpsLen == 0) {
                    lps[idx] = 0;
                    idx++;
                } else {
                    lpsLen = lps[lpsLen - 1];
                }
            }
        }
        
        return lps;
    }
    
    ArrayList<Integer> KMP(int[] txt, int[] pattern) {
        int n = txt.length;
        int m = pattern.length;
        
        int[] lps = LPS(pattern);
        
        ArrayList<Integer> res = new ArrayList<>();
        
        int i = 0, j = 0;
        
        while((n - i) >= (m - j)) {
            if(txt[i] == pattern[j]) {
                i++;
                j++;
            }
            
            if(j == m) {
                res.add(i - j);
                j = lps[j - 1];
            } else if(i < n && txt[i] != pattern[j]) {
                if(j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }
        
        return res;
    }
    
    public ArrayList<Integer> search(int[] a, int[] b) {
        return KMP(a, b);
    }
}