class Solution {
    public int maxSameLengthRuns(String s) {
        int n = s.length();

        int maxRun = 0;

        int[] count = new int[n + 1];

        int i = 0;
        while(i < n) {

            int j = i;
            int cnt = 0;

            while(j < n && s.charAt(j) == s.charAt(i)) {
                cnt++;
                j++;
            }
            
            i = j;

            count[cnt]++;

            maxRun = Math.max(maxRun, count[cnt]);
        }

        return maxRun;
    }
}