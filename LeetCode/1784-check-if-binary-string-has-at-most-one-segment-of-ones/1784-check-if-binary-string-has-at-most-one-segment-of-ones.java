class Solution {
    public boolean checkOnesSegment(String s) {
        int n = s.length();

        int oneSegment = 0;

        int i = 0;
        while(i < n) {
            if(s.charAt(i) == '1') {
                while(i < n && s.charAt(i) == '1') {
                    i++;
                }
                oneSegment++;
            } else {
                i++;
            }
        }

        return oneSegment == 1;
    }
}