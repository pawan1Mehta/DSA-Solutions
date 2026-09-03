class Solution {

    public int numberOfSpecialChars(String word) {
        int n = word.length();

        int[][] index = new int[26][2];
        for(int i = 0; i < 26; i++) {
            Arrays.fill(index[i], -1);
        }

        for(int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            if(isLowerChar(ch)) {
                index[(ch - 'a')][0] = i;
            } else {
                ch = toLowerChar(ch);
                if(index[(ch - 'a')][1] == -1) {
                    index[(ch - 'a')][1] = i;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < 26; i++) {
            if(index[i][0] != -1 && index[i][1] != -1 && index[i][0] < index[i][1]) {
                count++;
            }
        }

        return count;
    }

    private boolean isLowerChar(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    private char toLowerChar(char ch) {
        int num = ch - 'A';
        char c = (char) ('a' + num);
        return c;
    }
}