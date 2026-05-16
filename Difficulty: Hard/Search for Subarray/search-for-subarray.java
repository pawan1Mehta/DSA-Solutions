class Solution {
    
    private int[] LPS(int[] arr) {
        int n = arr.length;
        
        int[] lps = new int[n];
        
        int lpsLen = 0;
        int i = 1;
        
        while(i < n) {
            if(arr[i] == arr[lpsLen]) {
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
    
    public ArrayList<Integer> search(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        
        int[] lps = LPS(b);
        
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        
        while((n - i) >= (m - j)) {
            if(a[i] == b[j]) {
                i++;
                j++;
            }
            
            if(j == m) {
                res.add(i - j);
                j = lps[j - 1];
            } else if(i < n && a[i] != b[j]) {
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