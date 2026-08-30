class Solution {
    public static ArrayList<Integer> subsetXOR(int n) {
        ArrayList<Integer> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ans.add(i + 1);
        }

        int xorVal = 0;
        for (int i = 1; i <= n; i++) {
            xorVal ^= i;
        }

        if (xorVal == n) {
            return ans;
        } else {
            int val = xorVal ^ n;
            ArrayList<Integer> res = new ArrayList<>();

            for (int num : ans) {
                if (num != val) {
                    res.add(num);
                }
            }
            return res;
        }
    }
}