class Solution {
    
    public int[] dijkstra(int V, int[][] edges, int src) {
        List<List<int[]>> adjList = constructAdjList(V, edges);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int[] dist = new int[V];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        minHeap.add(new int[]{src, 0});
        dist[src] = 0;
        
        int u, v, dt;
        while(!minHeap.isEmpty()) {
            int[] node = minHeap.poll();
            u = node[0];
            
            for(int[] adjNode : adjList.get(u)) {
                v = adjNode[0]; dt = adjNode[1];
                if(dist[v] > (dist[u] + dt)) {
                    dist[v] = (dist[u] + dt);
                    minHeap.add(new int[]{v, dist[v]});
                }
            }
        }
        
        return dist;
    }
    
    private List<List<int[]>> constructAdjList(int V, int[][] edges) {
        List<List<int[]>> adjList = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        
        int u, v, dt;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1]; dt = edge[2];
            
            adjList.get(u).add(new int[]{v, dt});
            adjList.get(v).add(new int[]{u, dt});
        }
        
        return adjList;
    }
}