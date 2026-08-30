class Solution {
    private void dfs(int i, int[][] stones, boolean[] visited) {
        if(visited[i]) {
            return;
        }    
        
        visited[i] = true;
        
        for(int j = 0; j < stones.length; j++) {
            if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                dfs(j, stones, visited);
            }
        }
    }
    
    int maxRemove(int[][] stones) {
        int n = stones.length;
        
        boolean[] visited = new boolean[n];
        
        int remaningStones = 0;
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, stones, visited);
                remaningStones++;
            }
        }
        
        return n - remaningStones;
    }
};
