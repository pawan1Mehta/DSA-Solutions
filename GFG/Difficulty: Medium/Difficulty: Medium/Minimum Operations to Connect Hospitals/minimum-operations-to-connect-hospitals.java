class Solution {
    private void dfs(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        visited[node] = true;
        
        for(int adjNode : adjList.get(node)) {
            if(!visited[adjNode]) {
                dfs(adjNode, adjList, visited);
            }
        }
    }
    
    public int minConnect(int V, int[][] edges) {
        int minNeedEdges = V - 1;
        
        if(minNeedEdges > edges.length) {
            return -1;
        }
        
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(V, edges);
        
        boolean[] visited = new boolean[V];
        int connectedComp = 0;
        
        for(int node = 0; node < V; node++) {
            if(!visited[node]) {
                dfs(node, adjList, visited);
                connectedComp++;
            }
        }
        
        return connectedComp - 1;
    }
    
    private ArrayList<ArrayList<Integer>> constructAdjList(int V, int[][] edges) {
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
