class Solution {
    
    private void dfs(int node, List<List<Integer>> adjList, boolean[] visited) {
        visited[node] = true;
        
        for(int adjNode : adjList.get(node)) {
            if(!visited[adjNode]) {
                dfs(adjNode, adjList, visited);
            }
        }
    }
    
    public int findMotherVertex(int V, int[][] edges) {
        List<List<Integer>> adjList = constructAdjList(V, edges);
        
        boolean[] visited = new boolean[V];
        int candidate = 0;
        
        for(int node = 0; node < V; node++) {
            if(!visited[node]) {
                dfs(node, adjList, visited);
                candidate = node;
            }
        }
        
        Arrays.fill(visited, false);
        dfs(candidate, adjList, visited);
        
        for(boolean visit : visited) {
            if(!visit) {
                return -1;
            }
        }
        
        return candidate;
    }
    
    private List<List<Integer>> constructAdjList(int V, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        int u, v;
        for (int[] edge : edges) {
            u = edge[0];
            v = edge[1];
            adjList.get(u).add(v);
        }

        return adjList;
    }
}