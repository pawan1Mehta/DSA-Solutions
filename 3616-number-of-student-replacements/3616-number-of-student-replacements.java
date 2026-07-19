class Solution {
    public int totalReplacements(int[] ranks) {
        int n = ranks.length;

        int selectStudent = ranks[0];
        int replacementCount = 0;

        for(int i = 1; i < n; i++) {
            if(selectStudent > ranks[i]) {
                selectStudent = ranks[i];
                replacementCount++;
            }
        }

        return replacementCount;
    }
}