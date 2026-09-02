class Solution {
    public int longestBalanced(String s) {
        int n = s.length();

        int maxLen = 0;

        int[] count = new int[26];

        for(int i = 0; i < n; i++) {
            Arrays.fill(count, 0);
            for(int j = i; j < n; j++) {
                count[s.charAt(j) - 'a']++;

                boolean isValidSubstring = true;
                int baseChar = s.charAt(j) - 'a';

                for(int k = 0; k < 26; k++) {
                    if(count[k] > 0 && count[k] != count[baseChar]) {
                        isValidSubstring = false;
                        break;
                    }
                }

                if(isValidSubstring) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }
}