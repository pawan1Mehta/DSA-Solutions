class Solution {
    public int minFlips(String s) {
        int n = s.length();

        StringBuilder str = new StringBuilder();
        str.append(s).append(s);

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        
        for(int i = 0; i < str.length(); i++) {
            s1.append(i%2 == 0 ? '1' : '0');
            s2.append(i%2 == 0 ? '0' : '1');   
        }

        int ans = Integer.MAX_VALUE;
        int ans1 = 0, ans2 = 0;

        for(int i = 0; i < str.length(); i++) {
            if(s1.charAt(i) != str.charAt(i)) {
                ans1++;
            }
            if(s2.charAt(i) != str.charAt(i)) {
                ans2++;
            }

            if(i >= n) {
                if(s1.charAt(i - n) != str.charAt(i)) {
                    ans1--;
                }
                if(s2.charAt(i - n) != str.charAt(i)) {
                    ans2--;
                }
            }

            if(i >= n - 1) {
                ans = Math.min(ans, Math.min(ans1, ans2));
            }
        }

        return ans;
    }
}