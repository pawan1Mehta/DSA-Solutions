class Solution {
    public ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(V, edges);
        
        int[] indegree = new int[V];
        
        for(int node = 0; node < V; node++) {
            for(int adjNode : adjList.get(node)) {
                indegree[adjNode]++;
            }
        }
        
        Queue<Integer> bfs = new LinkedList<>();
        
        for(int node = 0; node < V; node++) {
            if(indegree[node] == 1) {
                bfs.add(node);
            }
        }
        
        while(V > 2) {
            int size = bfs.size();
            V -= size;
            
            while(size-- > 0) {
                int node = bfs.poll();
                for(int adjNode : adjList.get(node)) {
                    --indegree[adjNode];
                    if(indegree[adjNode] == 1) {
                        bfs.add(adjNode);
                    }
                }
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        
        while(!bfs.isEmpty()) {
            res.add(bfs.poll());
        }
        
        return res;
    }
    
    static ArrayList<ArrayList<Integer>> constructAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        
        int u, v;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1];
            
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        return adjList;
    }
}