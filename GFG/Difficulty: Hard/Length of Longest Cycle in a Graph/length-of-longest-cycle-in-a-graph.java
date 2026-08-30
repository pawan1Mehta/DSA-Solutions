class Solution {

    public int longestCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = adjList(V, edges);
        
        int longestCycLen = -1;
        
        int[] dfsStack = new int[V];
        boolean[] visited = new boolean[V];
        
        Arrays.fill(dfsStack, -1);
        
        for(int v = 0; v < V; v++) {
            if(!visited[v]) {
                dfsStack[v] = 0;
                longestCycLen = Math.max(longestCycLen, dfs(v, adj, dfsStack, visited));
            }
        }
        
        return longestCycLen;
    }
    
    private int dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] dfsStack, boolean[] visited) {
        visited[node] = true;
        
        int maxLen = -1;
        
        for(int adjNode : adj.get(node)) {
            if(!visited[adjNode]) {
                dfsStack[adjNode] = dfsStack[node] + 1;
                maxLen = dfs(adjNode, adj, dfsStack, visited);
            } else if(dfsStack[adjNode] != -1) {
                maxLen = Math.max(maxLen, dfsStack[node] - dfsStack[adjNode] + 1);
            }
        }
        
        dfsStack[node] = -1;
        
        return maxLen;
    }
    
    private ArrayList<ArrayList<Integer>> adjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        int u, v;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1];
            adj.get(u).add(v);
        }
        
        return adj;
    }
}