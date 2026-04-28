class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();

        int maxLen = 0;

        for(char ch = 'A'; ch <= 'Z'; ch++) {
            int i = 0, j = 0;
            int count = 0;

            while(j < n) {
                if(s.charAt(j) != ch) {
                    count++;
                }

                while(count > k) {
                    if(s.charAt(i) != ch) {
                        count--;
                    }
                    i++;
                }

                maxLen = Math.max(maxLen, j - i + 1);

                j++;
            }
        }

        return maxLen;
    }
}