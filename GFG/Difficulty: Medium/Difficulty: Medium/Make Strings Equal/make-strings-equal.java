class Solution {
    public int minCost(String s, String t, char[][] transform, int[] cost) {
        int MAX = Integer.MAX_VALUE;
        
        int[][] dist = new int[26][26];
        for(int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], MAX);
        }
        
        for(int i = 0; i < 26; i++) {
            dist[i][i] = 0;
        }
        
        for(int i = 0; i < transform.length; i++) {
            int u = transform[i][0] - 'a';
            int v = transform[i][1] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        
        for(int k = 0; k < 26; k++) {
            for(int i = 0; i < 26; i++) {
                for(int j = 0; j < 26; j++) {
                    if(dist[i][k] < MAX && dist[k][j] < MAX) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        
        int ans = 0;
        
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == t.charAt(i)) {
                continue;
            }
            
            int min = MAX;
            
            int a = s.charAt(i) - 'a';
            int b = t.charAt(i) - 'a';
            
            for(int c = 0; c < 26; c++) {
                if(dist[a][c] < MAX && dist[b][c] < MAX) {
                    min = Math.min(min, dist[a][c] + dist[b][c]);
                }
            }
            
            if(min == MAX) {
                return -1;
            }
            
            ans += min;
        }
        
        return ans;
    }
}