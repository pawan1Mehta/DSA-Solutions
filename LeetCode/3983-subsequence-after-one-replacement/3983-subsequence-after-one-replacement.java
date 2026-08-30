class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        int tIndex = 0;
        for(int i = 0; i < n; i++) {
            while(tIndex < m && t.charAt(tIndex) != s.charAt(i)) {
                tIndex++;
            }
            if(tIndex == m) {
                break;
            }
            left[i] = tIndex;
            tIndex++;
        }

        if(left[n - 1] != -1) {
            return true;
        }

        tIndex = m - 1;
        for(int i = n - 1; i >= 0; i--) {
            while(tIndex >= 0 && t.charAt(tIndex) != s.charAt(i)) {
                tIndex--;
            }
            if(tIndex < 0) {
                break;
            }
            right[i] = tIndex;
            tIndex--;
        }

        for(int i = 0; i < n; i++) {
            if((i == 0 || left[i - 1] != -1) 
                && (i == n -1 || right[i + 1] != -1)) {
                int L = (i == 0 ? -1 : left[i - 1]);
                int R = (i == n - 1 ? m : right[i + 1]);

                if(L + 1 < R) {
                    return true;
                }
            }
        }
        
        return false;
    }
}