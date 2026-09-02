class Solution {
    public int minimumOR(int[][] grid) {
        int ans = 0;
        int forbidden = 0;

        for(int bit = 31; bit >= 0; bit--) {
            int testForbidden = forbidden | (1 << bit);

            boolean isPossible = true;

            for(int[] col : grid) {
                boolean isPresent = false;

                for(int num : col) {
                    if((num & testForbidden) == 0) {
                        isPresent = true;
                        break;
                    }
                }

                if(!isPresent) {
                    isPossible = false;
                    break;
                }
            }

            if(isPossible) {
                forbidden = testForbidden;
            } else {
                ans = ans | (1 << bit);
            }
        }

        return ans;
    }
}