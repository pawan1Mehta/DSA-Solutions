class Solution {
    public int maxOperations(String s) {
        int n = s.length();

        int maxOperations = 0;
        int oneCounts = 0;
        int index = 0;

        while(index < n) {
            if(s.charAt(index) == '0') {
                while(index + 1 < n && s.charAt(index + 1) == '0') {
                    index++;
                }
                maxOperations += oneCounts;
            } else {
                oneCounts++;
            }
            index++;
        }

        return maxOperations;
    }
}