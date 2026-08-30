class Solution {
    public String largestSwap(String s) {
        int n = s.length();
        
        StringBuilder str = new StringBuilder(s);
        
        int[] maxIndex = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                maxIndex[i] = i;
            } else {
                maxIndex[i] = str.charAt(i) > str.charAt(maxIndex[i + 1]) ? i : maxIndex[i + 1];
            }
        }
        
        for(int i = 0; i < n - 1; i++) {
            if(str.charAt(i) < str.charAt(maxIndex[i + 1])) {
                char tempChar = str.charAt(i);
                str.setCharAt(i, str.charAt(maxIndex[i + 1]));
                str.setCharAt(maxIndex[i + 1], tempChar);
                break;
            }
        }
        
        return str.toString();
    }
}