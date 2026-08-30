class Solution {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        List<List<Integer>> adjList = constructAdjList(V, edges);
        int[] indegree = calIndegree(V, adjList);
        
        ArrayList<Integer> toposort = new ArrayList<>();
        
        Queue<Integer> bfs = new LinkedList<>();
        
        for(int i = 0; i < V; i++) {
            if(indegree[i] == 0) {
                bfs.add(i);
            }
        }
    
        while(!bfs.isEmpty()) {
            int node = bfs.poll();
            toposort.add(node);
            
            for(int adjNode : adjList.get(node)) {
                indegree[adjNode]--;
                if(indegree[adjNode] == 0) {
                    bfs.add(adjNode);
                }
            }
        }
        
        return toposort;
    }
    
    private int[] calIndegree(int V, List<List<Integer>> adjList) {
        int[] indegree = new int[V];
        
        for(int u = 0; u < V; u++) {
            for(int v : adjList.get(u)) {
                indegree[v]++;
            }
        }
        
        return indegree;
    }
    
    private List<List<Integer>> constructAdjList(int V, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        
        int u, v;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1];
            adjList.get(u).add(v);
        }
        
        return adjList;
    }
}