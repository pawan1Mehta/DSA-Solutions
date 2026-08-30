// User function Template for Java

class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        
        int MAX_VAL = 100000000;
        
        Arrays.fill(dist, MAX_VAL);
        
        dist[src] = 0;
        
        int u, v, dt;
        
        for(int i = 0; i < V - 1; i++) {
            for(int[] edge : edges) {
                u = edge[0]; v = edge[1]; dt = edge[2];
                if(dist[u] != MAX_VAL && dist[v] > (dist[u] + dt)) {
                    dist[v] = (dist[u] + dt);
                }
            }
        }
        
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1]; dt = edge[2];
            if(dist[u] != MAX_VAL && dist[v] > (dist[u] + dt)) {
                return new int[]{-1};
            }
        }
        
        return dist;
    }
}
