class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        int count1 = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '1') {
                count1++;
            }
        }

        ArrayList<Integer> zeroBlocks = new ArrayList<>();

        int i = 0;
        while(i < n) {
            int start = i;

            while(i < n && s.charAt(i) == s.charAt(start)) {
                i++;
            }

            if(s.charAt(start) == '0') {
                zeroBlocks.add(i - start);
            }
        }

        int m = zeroBlocks.size();
        if(m < 2) {
            return count1;
        }

        int maxActiveSections = 0;

        for(int j = 0; j < m - 1; j++) {
            maxActiveSections = Math.max(
                maxActiveSections,
                zeroBlocks.get(j) + zeroBlocks.get(j + 1)
            );
        }

        return maxActiveSections + count1;
    }
}