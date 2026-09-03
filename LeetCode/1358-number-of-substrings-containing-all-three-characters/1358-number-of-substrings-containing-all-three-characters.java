class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();

        int total = 0;
        int[] freq = new int[]{0, 0, 0};
        int i = 0, j = 0;

        while(j < n) {
            char ch = s.charAt(j);
            freq[(ch - 'a')]++;

            while(hasAllChars(freq)) {
                total += (n - j);
                ch = s.charAt(i);
                freq[(ch - 'a')]--;
                i++;
            }

            j++;
        }

        return total;
    }

    private boolean hasAllChars(int[] freq) {
        return freq[0] > 0 && freq[1] > 0 && freq[2] > 0;
    }
}