class Solution {
    public boolean makePalindrome(String s) {
        int n = s.length();

        int i = 0, j = n - 1;
        int operation = 0;

        while(i <= j) {
            if(s.charAt(i) != s.charAt(j)) {
                operation++;
            }
            i++;
            j--;
        }

        if(operation > 2) {
            return false;
        }

        return true;
    }
}