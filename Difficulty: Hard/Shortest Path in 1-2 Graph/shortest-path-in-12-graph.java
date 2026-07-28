class Solution {
    public int shortestPath(int V, int src, int dest, int[][] edges) {
        ArrayList<ArrayList<int[]>> adjList = constructAdjList(V, edges);
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });
        
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        minHeap.add(new int[]{src, 0});
        dist[src] = 0;
        
        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int u = curr[0];
            int currDist = curr[1];
            
            if(dist[u] < currDist) {
                continue;
            }
            
            for(int[] adjNode : adjList.get(u)) {
                int v = adjNode[0];
                int wt = adjNode[1];
                
                if(dist[v] > (dist[u] + wt)) {
                    dist[v] = dist[u] + wt;
                    minHeap.add(new int[]{v, dist[v]});
                }
            }
        }
        
        int res = dist[dest];
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    private ArrayList<ArrayList<int[]>> constructAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        int u, v, wt;
        for (int[] edge : edges) {
            u = edge[0];
            v = edge[1];
            wt = edge[2];
            
            adjList.get(u).add(new int[]{v, wt});
            adjList.get(v).add(new int[]{u, wt});
        }

        return adjList;
    }
}