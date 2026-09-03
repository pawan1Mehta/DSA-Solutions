class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();

        int[] count = new int[26];
        for(char ch : s.toCharArray()) {
            count[(ch - 'a')]++;
        }

        StringBuilder resPalindromStr = new StringBuilder();
        StringBuilder oddChars = new StringBuilder();

        for(int i = 0; i < 26; i++) {
            if(count[i]%2 == 0) {
                appendKTimes(resPalindromStr, (char) ('a' + i), count[i]/2);
            } else {
                if(count[i] == 1) {
                    appendKTimes(oddChars, (char) ('a' + i), count[i]);
                } else {
                    appendKTimes(resPalindromStr, (char) ('a' + i), (count[i] - 1)/2);
                    appendKTimes(oddChars, (char) ('a' + i), 1);
                }
            }
        }

        StringBuilder res = new StringBuilder();
        res.append(resPalindromStr.toString());
        res.append(oddChars);
        res.append(resPalindromStr.reverse().toString());

        return res.toString();
    }

    private void appendKTimes(StringBuilder sb, char ch, int k) {
        while(k-- > 0) {
            sb.append(ch);
        }
    }
}