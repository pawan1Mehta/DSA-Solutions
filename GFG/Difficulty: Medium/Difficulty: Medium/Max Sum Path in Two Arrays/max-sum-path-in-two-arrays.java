class Solution {
    
    private int[][] memo;
    
    private int solve(int i, int isFirst, int[] a, int[] b, Map<Integer, Integer> idxA, Map<Integer, Integer> idxB) {
        if((isFirst == 1 && i >= a.length) || (isFirst == 0 && i >= b.length)) {
            return 0;
        }
        
        if(memo[i][isFirst] != -1) {
            return memo[i][isFirst];
        }
        
        int opt1 = 0, opt2 = 0;
        
        if(isFirst == 1) {
            opt1 = a[i] + solve(i + 1, 1, a, b, idxA, idxB);
            if(i + 1 < a.length && idxB.containsKey(a[i + 1])) {
                opt2 = a[i] + solve(idxB.get(a[i + 1]), 0, a, b, idxA, idxB);
            }
        } else {
            opt1 = b[i] + solve(i + 1, 0, a, b, idxA, idxB);
            if(i + 1 < b.length && idxA.containsKey(b[i + 1])) {
                opt2 = b[i] + solve(idxA.get(b[i + 1]), 1, a, b, idxA, idxB);
            }
        }
        
        return memo[i][isFirst] = Math.max(opt1, opt2);
    }
    
    public int maxPathSum(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        
        Map<Integer, Integer> idxA = new HashMap<>();
        Map<Integer, Integer> idxB = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            idxA.put(a[i], i);
        }
        for(int i = 0; i < m; i++) {
            idxB.put(b[i], i);
        }
        
        int maxLen = Math.max(n, m);
        
        memo = new int[maxLen][2];
        for(int i = 0; i < maxLen; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        int res = Math.max(
            solve(0, 1, a, b, idxA, idxB),
            solve(0, 0, a, b, idxA, idxB)
        );
        
        return res;
    }
}