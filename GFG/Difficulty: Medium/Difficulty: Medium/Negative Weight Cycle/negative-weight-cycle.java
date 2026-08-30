class Solution {
    
    public boolean isNegativeWeightCycle(int V, int[][] edges) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[0] = 0;
        
        for(int i = 0; i < V-1; i++) {
            for(int[] edge : edges) {
                int u = edge[0], v = edge[1], dt = edge[2];
                if(dist[u] != Integer.MAX_VALUE && dist[v] > (dist[u] + dt)) {
                    dist[v] = dist[u] + dt;
                }
            }
        }
        
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], dt = edge[2];
            if(dist[u] != Integer.MAX_VALUE && dist[v] > (dist[u] + dt)) {
                return true;
            }
        }
        
        return false;
    }
}